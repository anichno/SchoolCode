;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname Canino) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ())))
; File: Canino.rkt
;
; Author: Anthony Canino
;
; Description: Infix to Postfix converter and solver
;
; Prelim documentation statement: Non other than links above specific functions
;
; PEX documentation statement: Non other than links above specific functions

(require racket/base)
(require racket/string)


;http://stackoverflow.com/a/694790
; Function: (operator? testOp)
; Description: Determines whether testOp is a valid operator
; PRE: The argument testOp is a valid Scheme character.
; POST: The value returned is true if testOp is a valid operator.
; USE: (operator? #\+) ; expected result is true
(define (operator? testOp) 
  (member testOp '(#\+ #\- #\* #\/)))

;http://stackoverflow.com/a/694790
; Function: (precedence? testOp1 testOp2)
; Description: Determines if testOp1 has a greater or equal precedence with testOp2
; PRE: Both arguments are valid char operators, performs an error check
; POST: The value returned is true if testOp1 is of greater or equal precedence
; USE: (precedence? #\+ #\-) ; expected result is true
(define (precedence? testOp1 testOp2)
  (and (member? testOp1 '(#\* #\/ #\+ #\-)) (member? testOp2 (memq testOp1 '(#\* #\/ #\+ #\-)))))

; Function: (calculate operator num1 num2)
; Description: Performs the calculation on num1 and num2 determined by the given operator
; PRE: Operator is a valid char and num1 and num2 are integers
; POST: The value returned is num1 operator num2
; USE: (calculate #\* 5 5) ; expected result is 25
(define (calculate operator num1 num2)
  (cond
    [(eq? #\* operator) (* num1 num2)]
    [(eq? #\/ operator) (/ num1 num2)]
    [(eq? #\+ operator) (+ num1 num2)]
    [(eq? #\- operator) (- num1 num2)]))

; Function: (valueOf numChar)
; Description: Converts a numerical character to its numerical value
; PRE: numChar is a numerical character
; POST: Returns the integer that character represents
; USE: (valueOf #\8) ; expected result is 8
(define (valueOf numChar)
  (- (char->integer numChar) 48))

; Function: (getNumber stack)
; Description: Returns a number which has been loaded into a stack
; PRE: stack is a valid stack representing the number
; POST: The value returned is the number represented by the stack
; USE: (getNumber (push #\4 (push #\1 (newStack)))) ; expected result is 14
(define (getNumber stack)
  (if (or (isEmpty? stack) (not (char-numeric? (top stack))))
      0
      (+ (valueOf (top stack)) (* 10 (getNumber (pop stack))))))

;http://web.mit.edu/scheme_v9.0.1/doc/mit-scheme-ref/Construction-of-Lists.html
; Function: (stringToStack strNum)
; Description: Converts a string to a stack
; PRE: strNum is a string
; POST: The value returned is a stack representing the string
; USE: (stringToStack "123") ; expected result is (list #\3 #\2 #\1)
(define (stringToStack strNum)
  (if (eq? (string-length strNum) 0)
      (newStack)
      (push (string-ref strNum (- (string-length strNum) 1)) 
            (stringToStack (substring strNum 0 (- (string-length strNum) 1))))))

; Function: (stringToNumber strNum)
; Description: Converts a string representation of a number to a number
; PRE: strNum represents a number
; POST: The value returned is the number represented by strNum
; USE: (stringToNumber "123") ; expected result is 123
(define (stringToNumber strNum)
  (getNumber(stringToStack strNum)))

; Function: (doTokenize stackInput lastWasNum)
; Description: Converts the stack into tokens with all numbers converted
; PRE: stackInput is a stack representation of a string, lastWasNum = #f
; POST: The value returned is a stack representation of the string with numbers converted
; USE: (doTokenize (push #\) (push #\4 (push #\1 (push #\( (newStack))))) #f) ; expected result is (list #\) 14 #\()
(define (doTokenize stackInput lastWasNum)
  (cond
    [(isEmpty? stackInput) (newStack)]
    [(and lastWasNum (char-numeric? (top stackInput))) (doTokenize (pop stackInput) #t)]
    [(char-numeric? (top stackInput)) (push (getNumber stackInput) (doTokenize (pop stackInput) #t))]
    [(push (top stackInput) (doTokenize (pop stackInput) #f))]))

; Function: (reverseTokenStack reversed)
; Description: Reverses a token stack into a list in proper order
; PRE: reversed is a token stack
; POST: Returns a list representing the token stack in proper order
; USE: (reverseTokenStack (push #\) (push 14 (push #\( (newStack))))) ; expected result is (list #\( 14 #\))
(define (reverseTokenStack reversed)
  (if (isEmpty? reversed)
      (newStack)
      (append  (reverseTokenStack (pop reversed)) (list (top reversed)))))

; Function: (tokenize input)
; Description: Converts a string representation of a function to tokens
; PRE: input represents a math function
; POST: The value returned is a list containing a tokenized version of the input string
; USE: (tokenize "(1+2)") ; expected result is (list #\( 1 #\+ 2 #\))
(define (tokenize input)
  (reverseTokenStack (doTokenize (stringToStack (string-replace input " " "")) #f)))

; Function: (doInToPost inStack inList)
; Description: Creates a postfix representation of the inList
; PRE: inStack is loaded with a #\(, inList has a #\) appended to it
; POST: The value returned is a list containing a postfix version of the inList
; USE: (doInToPost (push #\( (newStack)) (append (list 1 #\+ 2) '(#\)))) ; expected result is (list 1 2 #\+)
(define (doInToPost inStack inList)
  (if (isEmpty? inStack)
      inList
      (cond
        [(eq? (first inList) #\() (doInToPost (push (first inList) inStack) (rest inList))]
        [(operator? (first inList)) (if (precedence? (top inStack) (first inList))
                                        (cons (top inStack) (doInToPost (pop inStack) inList))
                                        (doInToPost (push (first inList) inStack) (rest inList)))]
        [(eq? (first inList) #\)) (if (not (eq? (top inStack) #\())
                                      (cons (top inStack) (doInToPost (pop inStack) inList))
                                      (doInToPost (pop inStack) (rest inList)))]
        [(cons (first inList) (doInToPost inStack (rest inList)))])))

; Function: (infixToPostfix input)
; Description: Converts a list representation of a function to postfix notation
; PRE: input is a list in infix notation
; POST: The value returned is a list containing a postfix notation version of the input list
; USE: (infixToPostfix '(1 #\+ 2)) ; expected result is (list 1 2 #\+)
(define (infixToPostfix input)
  (doInToPost (push #\( (newStack)) (append input '(#\)))))

; Function: (doEvalPostfix inStack input)
; Description: Evaluates the math function in input
; PRE: input is a list in postfix notation, inStack is an empty stack
; POST: The value returned is the solution of the input function
; USE: (doEvalPostfix (newStack) '(1 2 #\+)) ; expected result is 3
(define (doEvalPostfix inStack input)
  (if (null? input)
      (top inStack)
      (if (number? (first input))
          (doEvalPostfix (push (first input) inStack) (rest input))
          (doEvalPostfix (push (calculate (first input) (top (pop inStack)) (top inStack))
                               (pop (pop inStack))) (rest input)))))

; Function: (evaluatePostfix input)
; Description: Evaluates the math function in input
; PRE: input is a list in postfix notation
; POST: The value returned is the solution of the input function
; USE: (evaluatePostfix '(1 2 #\+)) ; expected result is 3
(define (evaluatePostfix input)
  (doEvalPostfix (newStack) input))

; Function: (evaluate input)
; Description: Evaluates the math function represented as a string
; PRE: input is a string in infix notation
; POST: The value returned is the solution of the input function
; USE: (evaluate "1+2") ; expected result is 3
(define (evaluate input)
  (evaluatePostfix (infixToPostfix (tokenize input))))


; ================================================================
; Stack functions - Leave these at the bottom of your file.
; You do not have to write function headers for these functions.
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
