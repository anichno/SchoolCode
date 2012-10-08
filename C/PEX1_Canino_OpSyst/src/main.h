/*
 * main.h
 *
 *  Created on: Sep 8, 2012
 *      Author: anichno
 */

#ifndef MAIN_H_
#define MAIN_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

char builtInFunctions[][10] = {{"exit"},{"history"},{"recall"},{"cd"},{"EOF"}};
struct historyBlock{
    char** arguments;
    int numArgs;
    struct historyBlock* next;
    struct historyBlock* prev;
};
struct argumentChar{
    char argChar;
    struct argumentChar* next;
};
struct argumentBlock{
    struct argumentChar* firstChar;
    struct argumentBlock* next;
};


char** compressInput(struct argumentBlock* firstBlock,int numBlocks,int maxBlockLen);
struct historyBlock* addToHistory(char** userArgs, int numBlocks, struct historyBlock* storedHistory);
void printHistory(char* userArgs, struct historyBlock* storedHistory);
void recallHistory(char* userArgs, struct historyBlock* storedHistory);
void runExternalCommand(char** userArgs);
int checkBuiltIn(char* userProg);
int runCommand(char** userArgs,int numBlocks,struct historyBlock* storedHistory);
void changeDirectory(char* userArgs);
int runPipedCommand(char** command);


#endif /* MAIN_H_ */
