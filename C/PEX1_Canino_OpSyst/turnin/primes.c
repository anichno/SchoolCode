/*
 *primes.c
 *
 *  Author: Anthony Canino
 *
 *  Documentation:  cplusplus.com for quick function lookups.
*/

#include "primes.h"

int main(int argc, char** argv) {
  signal(SIGFPE, print_usage);
  if (argc != 3) {
    print_usage();
  }
  printf("Testing primality of %s using %s threads...\n",argv[1],argv[2]);
  int prime = runtest(argv[1],argv[2]);
  if (prime > 0) {
    printf("Number is not prime.\n");
  }
  else {
	  printf("Number is Prime.\n");
  }
  return EXIT_SUCCESS;
}

void testprime(struct params* inputparams) {
	//printf("low: %lli high: %lli shared: %lli\n",inputparams->low,inputparams->high,*inputparams->share);
	for (;*inputparams->share == 0 && inputparams->low < inputparams->high; inputparams->low++) {
		if (inputparams->testnum%inputparams->low == 0) {
			*inputparams->share = inputparams->low; // No mutex here, because even if it gets mangled, it wont end up as 0
		}
	}
}

long long sqroot(long long num) {
	long long i = 0;
	while (i*i < num) i++;
	return i;
}

void splitjobs(long long jobnum,int numthreads, long long* splitarylow, long long* splitaryhigh) {
	long long square = (long long) sqroot(jobnum) + 1; // These +1 adjustments fix certain incorrect prime cases
	long long splitsize = square/numthreads + 1;
	for (int i = 0, j = 1; i < numthreads; i++) {
		splitarylow[i] = j+1;
		for (; j < splitsize*(i+1); j++);
		splitaryhigh[i] = j;
	}
}

int runtest(char* usernum, char* userthreads) {
	long long numtotest = atoll(usernum);
	int numthreads = atoi(userthreads);
	long long splitlow[numthreads];
	long long splithigh[numthreads];
	splitjobs(numtotest,numthreads,splitlow,splithigh);
	pthread_t thread[numthreads];
	long long* sharedmem = (long long*)calloc(1,sizeof(long long));
	struct params threadparams;
	threadparams.testnum = numtotest;
	threadparams.share = sharedmem;
	for (int local = 0; local < numthreads; local++) {
		//printf("creating: %d\n",local);
		threadparams.low = splitlow[local];
		threadparams.high = splithigh[local];
		pthread_create(&thread[local],NULL,(void*)testprime,&threadparams);
	}   
	for (int local = 0; local < numthreads; local++) {
		pthread_join(thread[local],NULL);
	}
	int returnval = 0;
	if (*sharedmem > 0) {
		returnval = 1;
	}
	free(sharedmem);
	return returnval;
}

void print_usage() {
	printf("Usage: primes number_to_test number_of_threads\n");
	exit(EXIT_FAILURE);
}
