/*
 * File:   deck.c
 * Author: Anthony Canino
 *
 * Description: This file contains various deck creation and manipulation functions.
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "deck.h"
#include "files.h"

/*
 * Description: Creates a new deck with all cards in ascending order. This function
 *              must explicitly allocate memory for each card structure.
 * Input:       None.
 * Result:      Returns a pointer to the "top" card in a new deck.
 * PRE:         None.
 * POST:        The pointer returned will point to a circular, doubly-linked list
 *              of NUM_CARDS Card structures sorted in ascending order.
 */
card_ptr new_deck()
{
  card_ptr topCard = (card_ptr) malloc( sizeof(struct Card) );
  topCard->number = 1;
  int i;
  card_ptr prevCard = topCard;
  card_ptr newCard;

  for( i = 1; i < NUM_CARDS; i++ )
  {
    newCard = (card_ptr) malloc( sizeof(struct Card) );
    prevCard->next = newCard;
    newCard->prev = prevCard;
    newCard->number = i + 1;
    prevCard = newCard;
  }

  topCard->prev = newCard;
  newCard->next = topCard;

  return topCard;
}

/*
 * Description: Shuffles a deck by removing cards one at a time from the original deck,
 *              inserting them in random locations into a new deck, and then returning
 *              a pointer to the top of the new deck.
 *              NOTE: The number field with the Card structures are NOT changed! The
 *              shuffle is accomplished by rearranging pointers to the structures.
 * Input:       A pointer to a "deck" of cards.
 * Result:      A pointer to a randomly shuffled "deck" of cards.
 * PRE:         The input pointer references a doubly-linked, circular list of Card structures.
 * POST:        The returned pointer references a doubly-linked, circular list of Card structures
 *              containing all Card structures from the original deck (i.e., nothing is lost).
 */
card_ptr shuffle_deck( card_ptr oldTop )
{
  // Seed the random number generator using the system clock.
  // Leave this as the very first line in this function!!!
  srand( (int) time( NULL ) );

  card_ptr newTop = (card_ptr) malloc( sizeof(struct Card) );
  int r = rand() % NUM_CARDS;
  int i = 0;
  int j = 0;
  card_ptr pullCard = oldTop;
  for( i = 0; i < r; i++ )
  {
    pullCard = pullCard->next;
  }
  newTop = pullCard;
  card_ptr currentCard = newTop;
  pullCard->next->prev = pullCard->prev;
  pullCard->prev->next = pullCard->next;
  for( i = 0; i < NUM_CARDS - 1; i++ )
  {
    r = rand() % NUM_CARDS;
    pullCard = oldTop;
    for( j = 0; j < r; j++ )
    {
      pullCard = pullCard->next;
    }
    currentCard->next = pullCard;
    pullCard->next->prev = pullCard->prev;
    pullCard->prev->next = pullCard->next;
    currentCard = currentCard->next;
    if( currentCard == oldTop )
    {
      oldTop = oldTop->next;
    }
  }
  currentCard->next = newTop;
  newTop->prev = currentCard;

  return newTop;
}

/*
 * Description: Shows a deck from "top" to "bottom".
 * Input:       A pointer to a "deck" of cards.
 * Result:      The number field of each card displayed on stdout.
 * PRE:         The input pointer references a doubly-linked, circular list of Card structures.
 * POST:        The doubly-linked, circular list of Card structures is unchanged.
 */
void show_deck( card_ptr topOfDeck )
{
  card_ptr card = topOfDeck;
  do
  {
    printf( "%i\t", card->number );
    card = card->next;
  }
  while( card != topOfDeck );
  printf( "\n" );
}

/*
 * Description: Shows a deck from "bottom" to "top". (I.e., backward. Thus the name ... get it?)
 *              NOTE: It is not mandatory in the final product to display a deck of cards in
 *              reversed order, but it will prove very handy to test correctness of all pointers.
 * Input:       A pointer to a "deck" of cards.
 * Result:      The number field of each card displayed on stdout.
 * PRE:         The input pointer references a doubly-linked, circular list of Card structures.
 * POST:        The doubly-linked, circular list of Card structures is unchanged.
 */
void kced_wohs( card_ptr topOfDeck )
{
  card_ptr card = topOfDeck->prev;
  do
  {
    printf( "%i\t", card->number );
    card = card->prev;
  }
  while( card != topOfDeck->prev );
  printf( "\n" );
}

/*
 * Description: Writes a deck to an output file from "top" to "bottom".
 * Input:       A pointer to a "deck" of cards.
 *              A character string with a file name.
 * Result:      The number field of each card is written to a file with the given name.
 * PRE:         The input pointer references a doubly-linked, circular list of Card structures.
 * POST:        The doubly-linked, circular list of Card structures is unchanged.
 *              If there was an error opening the file for writing, an error message will
 *              be displayed (by the open_output_file utility) and execution terminated
 *              with a result of EXIT_FAILURE.
 */
void write_deck_to_file( card_ptr topOfDeck, char* filename )
{
  FILE* fileptr = open_output_file( filename );
  card_ptr card = topOfDeck;
  do
  {
    fprintf( fileptr, "%3d", card->number );
    card = card->next;
  }
  while( card != topOfDeck );
  fclose( fileptr );
}

/*
 * Description: Creates a new deck with card values from the given input file. This function
 *              must explicitly allocate memory for each card structure.
 * Input:       A character string with a file name.
 * Result:      Returns a pointer to the "top" card in a new deck.
 * PRE:         The filename must be a valid file containing integers representing a valid deck.
 * POST:        The pointer returned will point to a circular, doubly-linked list
 *              of NUM_CARDS Card structures with card values in the order in which they
 *              appear in the input file.
 *              If there was an error opening the file for reading, an error message will
 *              be displayed (by the open_output_file utility) and execution terminated
 *              with a result of EXIT_FAILURE.
 */
card_ptr read_deck_from_file( char* filename )
{
  FILE* fileptr = open_input_file( filename );
  int cardval = 0;
  int i;
  card_ptr topCard = (card_ptr) malloc( sizeof(struct Card) );
  card_ptr prevCard = topCard;
  card_ptr newCard;

  fscanf( fileptr, "%d", &cardval );
  topCard->number = cardval;

  for( i = 1; i < NUM_CARDS; i++ )
  {
    newCard = (card_ptr) malloc( sizeof(struct Card) );
    prevCard->next = newCard;
    newCard->prev = prevCard;
    fscanf( fileptr, "%d", &cardval );
    newCard->number = cardval;
    prevCard = newCard;
  }

  topCard->prev = newCard;
  newCard->next = topCard;

  fclose( fileptr );
  return topCard;
}
