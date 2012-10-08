######################################################
# Author: C14Anthony.Canino
# Date: 22 Feb 12
# Filename: Canino_PEX_1
# Purpose: A simple interactive program demonstrating multiple assembly program constructs
#
# Documentation Statement: I received no help on this assignment other than the web
# 		           sites listed in the bubble sort function
######################################################

	.data
strOption1:	.asciiz	"1. What Nth Number of the Fibonacci Sequence?\n"
strOption2:	.asciiz	"2. Sort X values.\n"
strOption3:	.asciiz	"3. Is the number divisible by 4?\n"
strOption4:	.asciiz	"4. Quit.\n"
strUserPrompt:	.asciiz	"Choice: "

strFiboPrompt:	.asciiz	"Number of sequence: "
strFiboAnswer1:	.asciiz	"Number "
strFiboAnswer2:	.asciiz	" of the Fibonacci Sequence is: "

strSortXNums:	.asciiz	"Sort how many numbers: "
strSortNum1:	.asciiz	"Number "
strSortNum2:	.asciiz	": "
strSortResult:	.asciiz	"The sorted order is: "
.align 2
arrSort:	.word	0:15

strDivPrompt:	.asciiz "Number to check: "
strDivFalse:	.asciiz	"The inputed number IS NOT divisible by 4\n\n"
strDivTrue:	.asciiz	"The inputed number IS divisible by 4\n\n"

strBadInput:	.asciiz	"Sorry, your input was incorrect.\n\n"
	
	.text
main:
################
# Registers used:
# None
################

	#	print all options
	la	$a0,strOption1
	jal	printStr
	
	la	$a0,strOption2
	jal	printStr
	
	la	$a0,strOption3
	jal	printStr
	
	la	$a0,strOption4
	jal	printStr
	
	la	$a0,strUserPrompt
	jal	printStr
	
	#	Read from console
	jal	getInt
	
	#	Branch to appropriate subroutine
	beq	$v0,1,fibo
	beq	$v0,2,sort
	beq	$v0,3,isdiv4
	beq	$v0,4,quit
	
	jal	printBadInput		# Oops, bad input
	
	j	main
	
fibo:
################
# Registers used:
# $s0 - Number of sequence desired
# $s1 - Loop Counter
# $t0 - Fibonacci temp var 1
# $t1 - Fibonacci temp var 2
# $t2 - Fibonacci temp sum
################

	#	Prompt for nth number
	li	$v0,4
	la	$a0,strFiboPrompt
	syscall
	
	#	Read from console
	jal	getInt
	
	move	$s0,$v0		# Store inputted number in $s0
	li	$s1,3		# Initialize loop counter
	move	$t0,$zero	# Init temp var 1
	li	$t1,1		# Init temp var 2
	move	$t2,$zero	# Zero temp sum
	
	bgt	$s0,1,fiboLoop	# Only do loop if desired number is greater than 1
	beq	$s0,1,endLoop	# Don't do loop if desired number is 1, the first number should then be zero
	
	jal	printBadInput	# User put in less than 1, scold them
	j	fibo		# If there was bad input, then it will start the subroutine over
	
	fiboLoop:
		add	$t2,$t0,$t1		# Set temp sum to temp var 1 + temp var 2
		move	$t0,$t1			# Set temp var 1 to temp var 2
		move	$t1,$t2			# Set temp var 2 to temp sum
		
		addi	$s1,$s1,1		# Increment loop counter
		bge	$s0,$s1,fiboLoop	# Break loop if at desired number
	endLoop:
		#	Leave loop
		
	#	Print nth number
	la	$a0,strFiboAnswer1
	jal	printStr
	
	move	$a0,$s0
	jal	printInt
	
	la	$a0,strFiboAnswer2
	jal	printStr
	
	move	$a0,$t2
	jal	printInt
	
	jal	printNewline
	jal	printNewline
	
	j	main		# Return to main after printing desired number of Fibonacci Sequence
	
sort:
################
# Registers used:
# $s0 - Number of numbers to sort
# $s1 - Loop counter
# $s7 - Saved $s0 for after doSort returns
# $t0 - Array offset
################

	#	Prompt for number of numbers to sort
	la	$a0,strSortXNums
	jal	printStr
	
	#	Get Input
	jal	getInt
	
	#	If input number is less than 1 or greater than 15, then it is bad input
	bgt	$v0,15,badInput
	blt	$v0,1,badInput
	j	goodInput
	
	badInput:
		jal	printBadInput
		j	sort
	
	goodInput:
		# Continue function
	
	move	$s0,$v0		# Store input number in $s0
	subi	$s0,$s0,1	# Dec $s0 to get it to base zero
	move	$s7,$s0		# Save $s0 for after doSort returns
	li	$s1,0		# Init loop counter
	
	#	for ($s1=0; $s1 < $s0; $s1++)
	loopGetNums:
		blt	$s0,$s1,loopGetNumsEnd
		
		#	Prompt for next number
		la	$a0,strSortNum1
		jal	printStr
		addi	$a0,$s1,1
		jal	printInt
		la	$a0,strSortNum2
		jal	printStr
		jal	getInt
		
		mul	$t0,$s1,4		# Calculate array offset
		sw	$v0,arrSort($t0)	# Store input number in array
		
		addi	$s1,$s1,1		# Increment counter
		j	loopGetNums
		
		
	loopGetNumsEnd:
		# Leave loop
	
	#	Call the doSort function
	la	$a0,arrSort
	addi	$a1,$s0,1
	jal	doSort
	
	#	Print sorted
	la	$a0,strSortResult
	jal	printStr
	
	move	$s0,$s7		# Restore $s0 to saved value
	li	$s1,0
	
	loopPrintSorted:
		mul	$t0,$s1,4		# Calculate array offset
		lw	$a0,arrSort($t0)
		jal	printInt
		
		addi	$s1,$s1,1		# Increment counter
		blt	$s0,$s1,loopPrintSortedEnd
		
		jal	printComma
		jal	printSpace
		
		j	loopPrintSorted
		
		
	loopPrintSortedEnd:
		# Leave loop
	
	#	Print newlines
	jal	printNewline
	jal	printNewline
	
	j	main
	
isdiv4:
################
# Registers used:
# $t0 - Saved $s0 for after doSort returns
################

	#	Prompt for number
	la	$a0,strDivPrompt
	jal	printStr
	
	#	Get input
	jal	getInt
	
	#	Test input against mask of 0011
	andi	$t0,$v0,3
	beq	$t0,$zero,divTrue
	
	#	Output not divisible
	la	$a0,strDivFalse
	jal	printStr
	
	j	main
	
	divTrue:
		#	Output divisible
		la	$a0,strDivTrue
		jal	printStr
		
		j	main

quit:
	li	$v0,10
	syscall
	
printBadInput:
	li	$v0,4
	la	$a0,strBadInput
	syscall
	
	jr	$ra	
	
printStr:
	li	$v0,4
	syscall
	
	jr	$ra
	
printInt:
	li	$v0,1
	syscall
	
	jr	$ra
	
printNewline:
	li	$v0,11
	li	$a0,'\n'
	syscall
	
	jr	$ra
	
printComma:
	li	$v0,11
	li	$a0,','
	syscall
	
	jr	$ra
	
printSpace:
	li	$v0,11
	li	$a0,' '
	syscall
	
	jr	$ra

getInt:
	li	$v0,5
	syscall
	
	jr	$ra

doSort:
################
# Implements direct translation of bubble sort from: http://www.algolist.net/Algorithms/Sorting/Bubble_sort
# Also includes xor swap from: http://en.wikipedia.org/wiki/XOR_swap_algorithm
# Therefore comments in method not included due to complexity, just reference the websites for exact meaning of code
#
# Registers used:
# $a0 - Location of array to sort
# $a1 - Length of the array
# $s0 - Boolean whether array is sorted or not
# $s1 - Beginning index of next iteration
# $s2 - Loop Counter
# $s3 - Temp num 1
# $s4 - Temp num 2
# $t0 - Used for calculating array offset
################
	li	$s0,1
	li	$s1,1
	bubSort:
		beqz	$s0,allSorted
		li	$s0,0
		addi	$s1,$s1,1
		li	$s2,0
		loopThrough:
			sub	$t0,$a1,$s1
			bgt	$s2,$t0,bubSort
			mul	$t0,$s2,4
			add	$t0,$a0,$t0
			lw	$s3,($t0)
			addi	$t0,$t0,4
			lw	$s4,($t0)
			blt	$s3,$s4,noSwap
			xor	$s3,$s3,$s4
			xor	$s4,$s3,$s4
			xor	$s3,$s3,$s4
			subi	$t0,$t0,4
			sw	$s3,($t0)
			addi	$t0,$t0,4
			sw	$s4,($t0)
			li	$s0,1
			noSwap:
				# Do Nothing
			addi	$s2,$s2,1
			j	loopThrough
		j	bubSort
		
		allSorted:
			jr	$ra
