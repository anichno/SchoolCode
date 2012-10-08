/*
 * File:   pontifex.h
 * Author: Anthony Canino
 *
 * Description: Declarations of methods used to implement the Pontifex encryption scheme.
 *              http://www.schneier.com/solitaire.html
 */

#ifndef PONTIFEX_H_
#define PONTIFEX_H_

#define ALPHABET_SIZE 26  // This could be changed if used with a different language/alphabet.
#define BUF_SIZE 512

struct inputTextList
{
  char* text;
  struct inputTextList* next;
};

// YOUR PONTIFEX FUNCTION PROTOTYPES GO HERE
char* read_filetext( char* filename );
void write_filetext( char* text, char* filename );
char* encode_message( char* plaintext, card_ptr topOfDeck );
char* decode_message( char* ciphertext, card_ptr topOfDeck );
char* generate_keystream( card_ptr topOfDeck, int streamLength );
card_ptr swap_joker1( card_ptr topOfDeck, card_ptr joker );
card_ptr swap_joker2( card_ptr topOfDeck, card_ptr joker );
void move_cards( card_ptr card1, card_ptr card2 );
card_ptr triple_cut( card_ptr topOfDeck );
card_ptr count_cut( card_ptr topOfDeck );
int get_output_card( card_ptr deck );

#endif /* PONTIFEX_H_ */
