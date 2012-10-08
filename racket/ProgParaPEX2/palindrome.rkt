;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname palindrome) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ())))
; File: palindrome.rkt
;
; Author: Dr. Randy Bower
;
; Description: Demonstrates use of a Scheme list to represent Stack and
; Queue Abstract Data Types. The quintessential example of course being
; determining if a string is a palindrome.
;
; Documentation: None.

; Function: (palindrome? str)
; Description: Predicate function that determines if a string contains a palindrome.
; PRE: str is a string (possibly empty)
; POST: The function will return true if the string is a palindrome; false otherwise.
; Use: (palindrome? "racecar")
(define (palindrome? str)
  (palindromeHelper? (string->list str) (newStack) (newQueue)))

; Function: (palindromeHelper? charList stk que)
; Description: A helper for the palindrome? function.
; PRE: charList is a list of characters, stk is a stack, and que is a queue.
; POST: The function will return true if charList is a palindrome; false otherwise.
; Use: (palindromeHelper? (string->list str) (newStack) (newQueue))
(define (palindromeHelper? charList stk que)
  (cond ((null? charList)
         (or (and (isEmpty? stk) (isEmpty? que))
             (and (eqv? (top stk) (front que))
                  (palindromeHelper? charList (pop stk) (dequeue que)))))
        (else
         (palindromeHelper? (rest charList) 
                            (push (first charList) stk)
                            (enqueue (first charList) que)))))

; Stack functions.
(define (newStack)
  '())

(define (isEmpty? stk)
  (null? stk))

(define (push item stk)
  (cons item stk))

(define (top stk)
  (if (isEmpty? stk)
      (error "ERROR: Accessed top of empty stack.")
      (first stk)))

(define (pop stk)
  (if (isEmpty? stk)
      (error "ERROR: Popped empty stack.")
      (rest stk)))

; Queue functions (NOTE: isEmpty? from stack works here.)
(define (newQueue)
  '())

(define (enqueue item que)
  (if (null? que)
      (list item)
      (cons (first que) (enqueue item (rest que)))))

(define (front que)
  (if (isEmpty? que)
      (error "ERROR: Accessed front of empty queue.")
      (first que)))

(define (dequeue que)
  (if (isEmpty? que)
      (error "ERROR: Dequeued empty queue.")
      (rest que)))
