/*
 * File:   deck.h
 * Author: Anthony Canino
 *
 * Description: This file contains the definition of a Card structure which will be used
 * to create a "deck" of cards. The "deck" of cards will be a circular, doubly-linked list
 * of NUM_CARDS Card structures.
 */

#ifndef DECK_H_
#define DECK_H_

struct Card // The card or node structure for the doubly-linked, circular list.
{
  short number;
  struct Card* next;
  struct Card* prev;
};

typedef struct Card* card_ptr; // This is simply a convenient shorthand.

// Various numeric constants.
#define NUM_SUIT_CARDS 52           // Four suits with 13 cards each. Change this to test with a smaller deck.
#define JOKER_A (NUM_SUIT_CARDS+1)  // First card after the suit cards is the A joker.
#define JOKER_B (NUM_SUIT_CARDS+2)  // Second card after the suit cards is the B joker.
#define NUM_CARDS JOKER_B           // The B joker is the last card in the deck, so it is the total number of cards.
// A couple handy card macros that can be used as boolean functions.
#define JOKER(N) (N > NUM_SUIT_CARDS)
#define NOT_JOKER(N) (N <= NUM_SUIT_CARDS)

// Function prototypes for deck manipulation functions.
card_ptr new_deck(); // Create a new deck with all cards in ascending order.
card_ptr shuffle_deck( card_ptr ); // Shuffles the given deck and returns a pointer to the new top card.
void show_deck( card_ptr ); // Shows a deck from top to bottom.
void kced_wohs( card_ptr ); // Shows a deck in reversed order. (Get it?)

// Function prototypes for deck/file operations.
void write_deck_to_file( card_ptr, char* ); // Writes a deck to a file with the given file name.
card_ptr read_deck_from_file( char* ); // Creates a new deck with card values read from the given file name.

#endif /* DECK_H_ */
