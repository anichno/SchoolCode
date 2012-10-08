#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <signal.h>

struct params {
  long long testnum;
	long long low;
  long long high;
  long long* share;
};

void testprime(struct params* inputparams);
long long sqroot(long long num);
void splitjobs(long long jobnum,int numthreads, long long* splitarylow, long long* splitaryhigh);
int runtest(char* usernum, char* userthreads);
void print_usage();

