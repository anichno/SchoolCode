/*
 * File:   files.h
 * Author: Randall Bower
 *
 * Description: Declarations of file opening utilities.
 */

#include <stdio.h>

#ifndef FILES_H_
#define FILES_H_

FILE* open_input_file( char* ); // Opens a file for input.
FILE* open_output_file( char* ); // Opens a file for output.

#endif /* FILES_H_ */
