/*
 * File:   pontifex.c
 * Author: Anthony Canino
 *
 * Description: Definitions of methods used to implement the Pontifex encryption scheme.
 *              http://www.schneier.com/solitaire.html
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "deck.h"
#include "files.h"
#include "pontifex.h"

/*
 * Description: Reads the text from a file, dynamically allocates memory
 *              so that encryption and decryption operations can be done
 *              entirely in memory, never taking a random disk access penalty
 * Input:       A character string with a file name.
 * Result:      Returns a char* containing the entire text of a file.
 * PRE:         The file name references a valid file available for reading.
 * POST:        The resulting char* contains the entire file, regardless of length
 *              and does not contain an EOF, newline, or carriage return character.
 */
char* read_filetext( char* filename )
{
  struct inputTextList* topBlock = (struct inputTextList*) calloc( 1, sizeof(struct inputTextList) );
  topBlock->text = (char*) calloc( BUF_SIZE, sizeof(char) );
  FILE* fileptr = open_input_file( filename );
  struct inputTextList* currentBlock = topBlock;
  int numRead = 0;
  int numBlocks = 1;
  numRead = fread( topBlock->text, 1, BUF_SIZE, fileptr );
  while( numRead == BUF_SIZE )
  {
    struct inputTextList* newBlock = (struct inputTextList*) calloc( 1, sizeof(struct inputTextList) );
    newBlock->text = (char*) calloc( BUF_SIZE, sizeof(char) );
    numRead = fread( newBlock->text, 1, BUF_SIZE, fileptr );
    newBlock->text[ strcspn( newBlock->text, "\n" ) ] = '\0';
    newBlock->text[ strcspn( newBlock->text, "\r" ) ] = '\0';
    currentBlock->next = newBlock;
    currentBlock = newBlock;
    numBlocks++;
  }
  char* filetext = (char*) calloc( numBlocks * BUF_SIZE + 1, sizeof(char) ); // +1 to ensure I get my null terminator
  currentBlock = topBlock;
  int i;
  for( i = 0; i < numBlocks; i++ )
  {
    strncpy( filetext + i * BUF_SIZE, currentBlock->text, BUF_SIZE );
    currentBlock = currentBlock->next;
  }

  fclose( fileptr );
  return filetext;
}

/*
 * Description: Writes input string to the designated filename.
 * Input:       A character string with the text to write and a file name.
 * Result:      The text is written and the file is closed.
 * PRE:         The file name references a valid file available for writing
 *              and the provided char* is valid.
 * POST:        The file is written and closed.
 */
void write_filetext( char* text, char* filename )
{
  FILE* fileptr = open_output_file( filename );
  fprintf( fileptr, "%s", text );
  fclose( fileptr );
}

/*
 * Description: Encodes the provided plaintext with the provided deck.
 * Input:       A char* of the plaintext and a pointer to the top of the deck.
 * Result:      Returns a pointer to the encrypted string.
 * PRE:         Both of the pointers point to valid plaintext and a valid deck.
 * POST:        A new string with the encrypted text is returned, the deck is modified,
 *              and the original plaintext is preserved.
 */
char* encode_message( char* plaintext, card_ptr topOfDeck )
{
  char* encrypted = (char*) calloc( strlen( plaintext ), sizeof(char) );
  int i;
  char* keystream = generate_keystream( topOfDeck, strlen( plaintext ) );
  for( i = 0; i < strlen( plaintext ); i++ )
  {
    int charval = plaintext[ i ] - 64;
    int keyval = keystream[ i ] - 64;
    encrypted[ i ] = ( charval + keyval - 1 ) % 26 + 65;
  }
  return encrypted;
}

/*
 * Description: Decodes the provided ciphertext with the provided deck.
 * Input:       A char* of the ciphertext and a pointer to the top of the deck.
 * Result:      Returns a pointer to the encrypted string.
 * PRE:         Both of the pointers point to valid ciphertext and a valid deck.
 * POST:        A new string with the decrypted text is returned, the deck is modified,
 *              and the original ciphertext is preserved.
 */
char* decode_message( char* ciphertext, card_ptr topOfDeck )
{
  char* decrypted = (char*) calloc( strlen( ciphertext ), sizeof(char) );
  int i;
  char* keystream = generate_keystream( topOfDeck, strlen( ciphertext ) );
  for( i = 0; i < strlen( ciphertext ); i++ )
  {
    int charval = ciphertext[ i ] - 64;
    int keyval = keystream[ i ] - 64;
    if( charval <= keyval )
    {
      charval += 26;
    }
    decrypted[ i ] = charval - keyval + 64;
  }
  return decrypted;
}

/*
 * Description: Generates a keystream from the provided deck.
 * Input:       A pointer to the top of the deck and the number of
 *              keys to generate.
 * Result:      Returns a char* of the keystream.
 * PRE:         The pointer to the top of the deck is valid.
 * POST:        The deck is modified, a valid keystream is generated.
 */
char* generate_keystream( card_ptr topOfDeck, int streamLength )
{
  char* keystream = (char*) calloc( streamLength + 1, sizeof(char) );
  card_ptr deck = topOfDeck;
  int i;
  for( i = 0; i < streamLength; i++ )
  {
    int streamval;
    do
    {
      //	Step 1, find joker A
      card_ptr currentCard = deck;
      while( currentCard->number != JOKER_A )
      {
        currentCard = currentCard->next;
      }
      deck = swap_joker1( deck, currentCard );

      //	Step 2, find joker B
      currentCard = deck;
      while( currentCard->number != JOKER_B )
      {
        currentCard = currentCard->next;
      }
      deck = swap_joker2( deck, currentCard );

      //	Step 3, do triple cut
      deck = triple_cut( deck );

      //	Step 4, do count cut
      deck = count_cut( deck );

      //	Step 5, find output card
      streamval = get_output_card( deck );
    }
    while( streamval == JOKER_A || streamval == JOKER_B );
    streamval--;
    keystream[ i ] = streamval % 26 + 65;
  }
  return keystream;
}

/*
 * Description: Performs the first joker swap.
 * Input:       Pointers to the top of the deck and the joker.
 * Result:      A pointer to the new top of the deck.
 * PRE:         The pointers are both valid.
 * POST:        Deck is modified, first joker swap has been performed.
 */
card_ptr swap_joker1( card_ptr topOfDeck, card_ptr joker )
{
  card_ptr newTop = (card_ptr) malloc( sizeof(struct Card) );
  newTop = topOfDeck;
  //	Check if joker is bottom card
  if( joker->next == topOfDeck )
  {
    move_cards( joker, topOfDeck );
  }
  else
  {
    move_cards( joker, joker->next );
  }
  if( joker == topOfDeck )
  {
    newTop = joker->prev;
  }
  return newTop;
}

/*
 * Description: Performs the second joker swap.
 * Input:       Pointers to the top of the deck and the joker.
 * Result:      A pointer to the new top of the deck.
 * PRE:         The pointers are both valid.
 * POST:        Deck is modified, second joker swap has been performed.
 */
card_ptr swap_joker2( card_ptr topOfDeck, card_ptr joker )
{
  card_ptr newTop = (card_ptr) malloc( sizeof(struct Card) );
  newTop = topOfDeck;
  if( joker->next == topOfDeck )
  {
    move_cards( joker, topOfDeck->next );
  }
  else if( joker->next->next == topOfDeck )
  {
    move_cards( joker, topOfDeck );
  }
  else
  {
    move_cards( joker, joker->next->next );
  }
  if( joker == topOfDeck )
  {
    newTop = joker->prev->prev;
  }
  return newTop;
}

/*
 * Description: Swaps cards 1 and 2.
 * Input:       Pointers to cards to be swapped.
 * Result:      Cards have been swapped.
 * PRE:         Pointers for both cards are valid.
 * POST:        Deck is modified, cards are swapped.
 */
void move_cards( card_ptr card1, card_ptr card2 )
{
  //	pull card
  card1->next->prev = card1->prev;
  card1->prev->next = card1->next;

  //	fix leftside links
  card1->next = card2->next;
  card1->prev = card2;

  //	fix rightside links
  card1->next->prev = card1;
  card2->next = card1;
}

/*
 * Description: Performs a triple cut of the deck.
 * Input:       A pointer to the top of the deck.
 * Result:      Returns a pointer to the new top of deck.
 * PRE:         The pointer to the top of deck is valid.
 * POST:        Deck is modified, new top is returned.
 */
card_ptr triple_cut( card_ptr topOfDeck )
{
  card_ptr newTop = (card_ptr) malloc( sizeof(struct Card) );
  card_ptr joker1 = topOfDeck;
  card_ptr joker2 = topOfDeck;
  card_ptr topCard = topOfDeck;
  card_ptr bottomCard = topOfDeck->prev;
  card_ptr currentCard = topCard->next;

  //	Break links on ends
  topCard->prev = NULL;
  bottomCard->next = NULL;

  //	Locate jokers
  while( joker1->number != JOKER_A && joker1->number != JOKER_B )
  {
    joker1 = joker1->next;
  }
  joker2 = joker1->next;
  while( joker2->number != JOKER_A && joker2->number != JOKER_B )
  {
    joker2 = joker2->next;
  }

  //	move set in front of joker1
  if( joker2->next == NULL )
  {
    newTop = joker1;
  }
  else
  {
    newTop = joker2->next;
    currentCard = newTop;
    while( currentCard->next != NULL )
    {
      currentCard = currentCard->next;
    }
    currentCard->next = joker1;
  }

  //	move set behind joker2
  joker2->next = topOfDeck;
  currentCard = topOfDeck;
  while( currentCard->next != joker1 )
  {
    currentCard = currentCard->next;
  }
  currentCard->next = newTop;

  card_ptr prevCard = newTop;
  currentCard = newTop->next;
  do
  {
    currentCard->prev = prevCard;
    prevCard = currentCard;
    currentCard = currentCard->next;
  }
  while( currentCard != newTop->next );

  return newTop;
}

/*
 * Description: Performs a count cut of the deck.
 * Input:       A pointer to the top of the deck.
 * Result:      Returns a pointer to the new top of deck.
 * PRE:         The pointer to the top of deck is valid.
 * POST:        Deck is modified, new top is returned.
 */
card_ptr count_cut( card_ptr topOfDeck )
{
  card_ptr newTop = (card_ptr) malloc( sizeof(struct Card) );
  short count = topOfDeck->prev->number;
  if( count == JOKER_B )
  {
    count = 53;
  }
  int i;
  card_ptr currentCard = topOfDeck;
  newTop = topOfDeck;
  for( i = 0; i < count; i++ )
  {
    newTop = newTop->next;
    move_cards( currentCard, newTop->prev->prev->prev );
    currentCard = newTop;
  }
  return newTop;
}

/*
 * Description: Determines value of output card.
 * Input:       A pointer to the top of the deck.
 * Result:      Returns the integer value of the output card.
 * PRE:         The pointer to the top of deck is valid.
 * POST:        Deck is not modified, output card is returned.
 */
int get_output_card( card_ptr deck )
{
  short count = deck->number;
  if( count == JOKER_B )
  {
    count = 53;
  }
  card_ptr currentCard = deck;
  int i;
  for( i = 0; i < count; i++ )
  {
    currentCard = currentCard->next;
  }
  return currentCard->number;
}

