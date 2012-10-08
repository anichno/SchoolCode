/*
 * File:   files.c
 * Author: Randall Bower
 *
 * Description: Definitions of file opening utilities.
 */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

/*
 * Description: Opens a file for input.
 * Input:       A character string with a file name.
 * Result:      Returns a FILE* associated with the given file name.
 * PRE:         The file name references a valid file available for reading.
 * POST:        The file is opened for reading; otherwise an error message is
 *              displayed and program execution is terminated with EXIT_FAILURE.
 */
FILE* open_input_file( char* filename )
{
  FILE* filePtr = fopen( filename, "r" );
  if( filePtr == NULL )
  {
    printf( "ERROR: Could not open %s for input.\n", filename );
    exit( EXIT_FAILURE );
  }
  return filePtr;
}

/*
 * Description: Opens a file for output, prompting the user to overwrite
 *              the file if it already exists.
 * Input:       A character string with a file name.
 * Result:      Returns a FILE* associated with the given file name.
 * PRE:         The file name references a valid file available for writing.
 * POST:        The file is opened for writing; otherwise an error message is
 *              displayed and program execution is terminated with EXIT_FAILURE.
 */
FILE* open_output_file( char* filename )
{
  // Try to open the file for reading to see if it exists.
  FILE* filePtr = fopen( filename, "r" );

  // File already exists, so prompt to overwrite.
  if( filePtr != NULL )
  {
    printf( "Warning: File %s already exists. Overwrite? (Y/N) ", filename );
    fflush( stdout );

    char answer;
    scanf( "%c", &answer );

    // Check for 'Y' or 'y' to overwrite.
    if( toupper( (int)answer ) != 'Y' )
    {
      printf( "Exiting without changing contents of %s.\n", filename );
      exit( EXIT_FAILURE );
    }
  }

  // Now open the file for writing.
  filePtr = fopen( filename, "w" );
  if( filePtr == NULL )
  {
    printf( "ERROR: Could not open %s for output.\n", filename );
    exit( EXIT_FAILURE );
  }

  return filePtr;
}
