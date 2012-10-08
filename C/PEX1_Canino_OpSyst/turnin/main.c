/*
 * main.c
 *
 *  Author: Anthony Canino
 *
 *  Documentation:  Past anthony for large blocks of code, blatantly plagerized.
 *                  Included link and cplusplus.com for quick function lookups.
 *                  makefile was generated from the eclipse IDE.
 *
 *  Note for grader:  This program was intended as an experiment of a concept:
 *                    what would a program look like that could take a massive amount
 *                    of input, both an unlimited size of arguments and number of them.
 *                    The system("stty raw") gives me the command line characters without
 *                    having to wait for the return, this is needed because the buffer is
 *                    only 4096 bytes for stdin. This unfortunately destroys POSIX compliance.
 *                    This program can take input as long as there is available memory, both real
 *                    and swap, without the limits of the stdin buffer.
 */
// http://forum.soft32.com/linux2/Howto-unbuffered-keyboard-input-ftopict11584.html message 4

#include "main.h"

int main(void) {
  printf("Welcome to AwesomeTerm!\n");
  int exitTerm = 0;
  struct historyBlock* storedHistory = (struct historyBlock*) calloc(1,sizeof(struct historyBlock));
  char** userArgs;
  int numBlocks;

  system("stty raw");
  while (exitTerm == 0) {
    struct argumentBlock* userInput = (struct argumentBlock*) calloc(1,sizeof(struct argumentBlock));
    numBlocks = 1;
    int maxBlockLen = 0;
    char inputChar = '\0';
    printf("Awesome:#");
    userInput->firstChar = (struct argumentChar*) calloc(1,sizeof(struct argumentChar));
    struct argumentBlock* curBlock = userInput;
    struct argumentChar* curChar = userInput->firstChar;
    int tempMaxLen = 1;
    while (inputChar != '\r' && inputChar != '\n') {
      inputChar = getc(stdin);
      if (inputChar != '\r' && inputChar != '\n') {
        if (inputChar == ' ') {
          curBlock->next = (struct argumentBlock*) calloc(1,sizeof(struct argumentBlock));
          curBlock = curBlock->next;
          curBlock->firstChar = (struct argumentChar*) calloc(1,sizeof(struct argumentChar));
          curChar = curBlock->firstChar;
          numBlocks++;
          if (tempMaxLen > maxBlockLen) {
            maxBlockLen = tempMaxLen;
          }
          tempMaxLen = 1;
        }
        else {
          curChar->argChar = inputChar;
          curChar->next = (struct argumentChar*) calloc(1,sizeof(struct argumentChar));
          curChar = curChar->next;
          tempMaxLen++;
        }
      }
    }
    if (tempMaxLen > maxBlockLen) {
                maxBlockLen = tempMaxLen;
    }
    if (maxBlockLen == 0) {
      maxBlockLen = tempMaxLen;
    }
    system("stty sane");
    userArgs = compressInput(userInput,numBlocks,maxBlockLen);

    exitTerm = runCommand(userArgs,numBlocks,storedHistory);

    storedHistory = addToHistory(userArgs,numBlocks,storedHistory);
  }

  //  Free history (also frees userArgs... handy!)
  struct historyBlock* histToFree;
  while (storedHistory->arguments != NULL) {
    free(storedHistory->arguments);
    histToFree = storedHistory;
    storedHistory = storedHistory->next;
    free(histToFree);
  }

  printf("Thanks for using AwesomeTerm!\n");
  return EXIT_SUCCESS;
}

char** compressInput(struct argumentBlock* firstBlock,int numBlocks,int maxBlockLen) {
  char** userArgs = (char**) calloc(numBlocks+1, sizeof(char*));
  char* temp = (char*) calloc(numBlocks+1, maxBlockLen * sizeof(char));
  int i,j;
  for (i = 0; i < numBlocks; i++) {
    userArgs[i] = temp + (i*maxBlockLen);
  }

  struct argumentBlock* curBlock = firstBlock;
  struct argumentChar* curChar = firstBlock->firstChar;
  struct argumentBlock* blockToFree;
  struct argumentChar* charToFree;
  for (i = 0; i < numBlocks; i++) {
    for (j = 0; curChar != NULL; j++) {
      userArgs[i][j] = curChar->argChar;
      charToFree = curChar;
      curChar = curChar->next;
      free(charToFree);
    }
    blockToFree = curBlock;
    curBlock = curBlock->next;
    free(blockToFree);
    if (curBlock != NULL) {
      curChar = curBlock->firstChar;
    }
  }

  return userArgs;
}

struct historyBlock* addToHistory(char** userArgs, int numBlocks, struct historyBlock* storedHistory) {
  //  Check if unique
  if (strcmp(userArgs[0],"history") == 0) {
    return storedHistory; // we dont add the history command to the history... that would be silly
  }
  struct historyBlock* curPos = storedHistory;
  while (curPos->arguments != NULL) {
    int i;
    for (i = 0; i < numBlocks; i++) {
      if (numBlocks != curPos->numArgs) {
        break;
      }
      if (strcmp(userArgs[i],curPos->arguments[i]) != 0) {
        break;
      }
    }
    if (i == numBlocks) {
      //  Not unique
      return storedHistory;
    }
    curPos = curPos->next;
  }

  struct historyBlock* newTop = (struct historyBlock*) calloc(1,sizeof(struct historyBlock));
  newTop->arguments = userArgs;
  newTop->numArgs = numBlocks;
  newTop->next = storedHistory;
  storedHistory->prev =newTop;
  return newTop;
}

void printHistory(char* userArgs, struct historyBlock* storedHistory) {
  if (storedHistory->arguments == NULL) {
    printf("No history to print\n");
    return;
  }
  int numToPrint = 10;
  if (userArgs != NULL) {
    sscanf(userArgs,"%d",&numToPrint);
    numToPrint--;
  }
  struct historyBlock* curPos = storedHistory;
  int histNum;
  for (histNum = 0; histNum < numToPrint && curPos->next->next != NULL; histNum++) {
    curPos = curPos->next;
  }
  int i;
  for (i = histNum+1; i > 0; i--) {
    printf("%2d ",i);
    int block;
    for (block = 0; block < curPos->numArgs; block++) {
      printf("%s ", curPos->arguments[block]);
    }
    printf("\n");
    curPos = curPos->prev;
  }
  return;
}

void recallHistory(char* userArgs, struct historyBlock* storedHistory) {
  if (userArgs == NULL) {
    printf("Usage: recall X\n");
    return;
  }
  int cmdToRecall = 0;
  sscanf(userArgs,"%d",&cmdToRecall);
  struct historyBlock* curPos = storedHistory;
  int i;
  for (i = 1; i < cmdToRecall; i++) {
    if (curPos->arguments == NULL) {
      printf("Error: That is not a valid command index\n");
      return;
    }
    curPos = curPos->next;
  }
  runCommand(curPos->arguments,curPos->numArgs,storedHistory);
  return;
}

void changeDirectory(char* userArgs) {
  char* homedir = getenv("HOME");
  char directory[strlen(userArgs)+strlen(homedir)];
  memset(directory, 0, strlen(userArgs)+strlen(homedir));
  if (userArgs[0] == '~') {
    userArgs[0] = '/';
    strncpy(directory,homedir,strlen(homedir));
    strcat(directory,userArgs);
    userArgs[0] = '~';
  }
  else {
    strncpy(directory,userArgs,strlen(userArgs));
  }

  int retCode = chdir(directory);
  if (retCode != 0) {
    printf("Directory not found\n");
  }
}

//  http://www.thegeekstuff.com/2012/05/c-fork-function/
void runExternalCommand(char** userArgs) {
  int i = 0;
  int retCode;
  while(userArgs[i] != NULL) {
    if (strcmp(userArgs[i],"|") == 0) {
      retCode = runPipedCommand(userArgs);
      if (retCode != 0) {
          printf("Command not found\n");
        }
      return;
    }
    i++;
  }
  pid_t childPID;
  childPID = fork();

  if(childPID >= 0) {
    if (childPID == 0) {
      exit(execvp(userArgs[0],userArgs));
    }
    else {
      wait(&retCode);
    }
  }
  else // fork failed
  {
      printf("\n Fork failed\n");
  }
  if (retCode != 0) {
    printf("Command not found\n");
  }
  return;
}

// http://stackoverflow.com/a/7369648
int runPipedCommand(char** command) {
  int rightside,i;
  for (i = 0, rightside = 0; command[i] != NULL && strcmp(command[i],"|") != 0; i++, rightside++);
  char* pipeStr = command[rightside];
  command[rightside] = NULL;
  pid_t childPID[2];
  int retCode[2];
  int fd[2];
  pipe(fd);
  childPID[0] = fork();

  if (childPID[0] >= 0) {
    if (childPID[0] == 0) {
      dup2(fd[1],1);
      close(fd[0]);
      close(fd[1]);
      exit(execvp(command[0],command));
    }
    else {
      wait(&retCode[0]);

      childPID[1] = fork();
      if (childPID[1] >= 0) {
          if (childPID[1] == 0) {
            dup2(fd[0],0);
            close(fd[0]);
            close(fd[1]);
            exit(execvp(command[rightside+1],&command[rightside+1]));
          }
          else {
            close(fd[0]);
            close(fd[1]);
            wait(&retCode[1]);
          }
        }
    }
    //  Fix command since i mangled it a bit
    command[rightside] = pipeStr;

    if (retCode[0] == EXIT_SUCCESS && retCode[1] == EXIT_SUCCESS) {
      return EXIT_SUCCESS;
    }
    else {
      return EXIT_FAILURE;
    }
  }
  return EXIT_FAILURE;
}

int checkBuiltIn(char* userProg) {
  int i;
  for (i = 0; strcmp("EOF",builtInFunctions[i]) != 0; i++) {
    if (strcmp(userProg,builtInFunctions[i]) == 0) {
      return i;
    }
  }
  return -1;
}

int runCommand(char** userArgs,int numBlocks,struct historyBlock* storedHistory) {
  //  Compare against builtin functions
  int userBuiltIn = checkBuiltIn(userArgs[0]);
  switch(userBuiltIn) {
    case -1:
        runExternalCommand(userArgs);
        break;
    case 0:
        return 1;
        break;
    case 1:
        printHistory(userArgs[1], storedHistory);
        break;
    case 2:
        recallHistory(userArgs[1], storedHistory);
        break;
    case 3:
        changeDirectory(userArgs[1]);
        break;
  }
  return 0;
}
