######################################################
# Author: C14Anthony.Canino
# Date: 07 Mar 12
# Filename: Canino_PEX_1
# Purpose: A simple database program demonstrating multiple assembly array opperations
#
# Documentation Statement: I received no help on this assignment
######################################################

	.data
#	database {
#		char	squadName[52],	# The 52 is to align the buffer to word boundary
#		int	squadNum,
#		int	squadNumCadets};
#	database is 60 bytes, there are 40 squads, therefore need to allocate 2400 bytes for storage block
database:		.byte	0:2400
intCurSquad:		.word	0	# Keeps track of last squad index

strPromptBegin:		.asciiz	"Please enter cadet data:\n"

strSquadName:		.asciiz	"\nSquad Name: "
strSquadNum:		.asciiz	"Squad Number: "
strSquadNumCadets:	.asciiz	"Number of Cadets in Squad: "

strMenuSearchName:	.asciiz	"1. Search by squadron name\n"
strMenuSearchNum:	.asciiz	"2. Search by number of members\n"
strMenuDisplayAll:	.asciiz	"3. Display all\n"
strMenuQuit:		.asciiz	"4. Quit\n"
strMenuUser:		.asciiz	"Your selection: "

strSearchNamePrompt:	.asciiz	"Search squads by: "
strSearchInput:		.byte	0:52

strSearchNumPrompt:	.asciiz	"Search squads by number of cadets: "

strNone:		.asciiz	"none"

	.text
main:
################
# Registers used:
# $s0 - Loop Counter
################
	#	Prompt to enter data
	li	$v0,4
	la	$a0,strPromptBegin
	syscall
	
	li	$s0,0	# Index Counter
	inputLoop:
		#	Save registers
		subi	$sp,$sp,4
		sw	$s0,($sp)
		
		move	$a0,$s0		# put index into argument
		jal	calcStartAddr
		jal	addData		# $a0 now has start address to add data
		
		#	Restore registers
		lw	$s0,($sp)
		add	$sp,$sp,4
		
		addi	$s0,$s0,1	# Increment counter
		
		bnez	$a0,inputLoop 	# Continue loop if user did not put in "none"
	
	mainLoop:
		jal	displayMenu
		#	Branch to users input
		beq	$a0,1,optSearchName
		beq	$a0,2,optSearchNum
		beq	$a0,3,optPrintAll
		beq	$a0,4,quit
		j	mainLoop
		
		optSearchName:
			jal	searchName
			j	mainLoop
		optSearchNum:
			jal	searchNum
			j	mainLoop
		optPrintAll:
			jal	printAllRecords
			j	mainLoop
		
		j	mainLoop	# Got here if bad input, prompt again
	
printAllRecords:
################
# Registers used:
# $s0 - Loop Counter
# $s1 - Index of Last inputed squad
################
	li	$s0,0			# Init loop counter
	lw	$s1,intCurSquad		# Load last index
	printAllLoop:	# for (int i = 0; i != intCurSquad; i++)
		beq	$s0,$s1,endPrintAllLoop
		
		#	Save registers
		subi	$sp,$sp,12
		sw	$s0,0($sp)
		sw	$s1,4($sp)
		sw	$ra,8($sp)
		
		move	$a0,$s0		# prep $a0 with curent index
		jal	calcStartAddr
		jal	printRecord
		
		#	Restore registers
		lw	$s0,0($sp)
		lw	$s1,4($sp)
		lw	$ra,8($sp)
		addi	$sp,$sp,12
		
		addi	$s0,$s0,1	# Increment counter
		
		j	printAllLoop
	endPrintAllLoop:
		#	Print newline and return
		li	$v0,11
		li	$a0,'\n'
		syscall
		jr	$ra
	
calcStartAddr:
################
# Function: Calculates start address of desired index in database
# Input:
#	$a0 - Index of database
# Output:
#	$a0 - Start address of index 
# Registers used:
# $t0 - Base address of database
# $t1 - Offset for index
################
	la	$t0,database
	li	$t1,60
	mul	$a0,$a0,$t1
	add	$a0,$a0,$t0
	
	jr	$ra
	
# a0 set with start of strSquadName
addData:
################
# Function: Adds new entry to database
# Input:
#	$a0 - Address in database of where to put new entry
# Output:
#	$a0 - 0 if user typed "none", 1 if data entered properly
# Registers used:
# $s0 - Address to put data into
# $s1 - Loop counter
# $t0 - Stores character from input
# $t1 - Stores character from strNone
################
	move	$s0,$a0		# Protect start address by saving it somewhere else
	
	#	Print Squad Name:
	li	$v0,4
	la	$a0,strSquadName
	syscall
	
	#	Save user input to database
	li	$v0,8
	move	$a0,$s0
	li	$a1,51
	syscall
	
	#	Check if none
	li	$s1,0		# Init loop counter
	loopCheckNone:	# for(int i = 0; i != 4; i++)
		beq	$s1,4,typedNone
		#	Load address from database from where user input squad name
		la	$t0,($a0)
		add	$t0,$t0,$s1	# add counter to get to index of char to check
		
		#	Load address of strNone to check against
		la	$t1,strNone
		add	$t1,$t1,$s1	# add counter to get to index of char to check
		
		lb	$t0,($t0)
		lb	$t1,($t1)
		#	compare and branch if user did not input none (break on first mismatch
		sub	$t0,$t0,$t1
		bnez	$t0,goodInput
		
		addi	$s1,$s1,1	# increment loop counter
		j	loopCheckNone
		
	typedNone:
		#	Print newline and return 0
		li	$v0,11
		li	$a0,'\n'
		syscall
		li	$a0,0
		jr	$ra
	goodInput:
		# Continue
		
	#	Prompt for rest of squad info
	li	$v0,4
	la	$a0,strSquadNum
	syscall
	
	li	$v0,5
	syscall
	add	$s0,$s0,52
	sw	$v0,($s0)
	
	li	$v0,4
	la	$a0,strSquadNumCadets
	syscall
	
	li	$v0,5
	syscall
	add	$s0,$s0,4
	sw	$v0,($s0)
	
	#	Increment index of last inputed squad
	lw	$a0,intCurSquad
	add	$a0,$a0,1
	sw	$a0,intCurSquad
	
	#	return 1
	li	$a0,1
	jr	$ra
	
searchName:
################
# Function: Searches the database for the first instance of an inputed squad name and prints it
# Registers used:
# $s0 - Outer loop counter
# $s1 - Inner loop counter
# $s2 - Index of last database entry
# $t0 - Contains character from input
# $t1 - Contains character from database
################
	#	Prompt for string to check against
	li	$v0,4
	la	$a0,strSearchNamePrompt
	syscall
	
	li	$v0,8
	la	$a0,strSearchInput
	li	$a1,51
	syscall
	
	# Check if same
	li	$s0,0		# Init outer loop counter
	li	$s1,0		# Init inner loop counter
	lw	$s2,intCurSquad	# Load last index in database
	loopSearchNameOuter:	# for(int i = 0; i != intCurSquad; i++)
		beq	$s0,$s2,endLoopSearchName
		
		#	Save registers
		subi	$sp,$sp,16
		sw	$s0,0($sp)
		sw	$s1,4($sp)
		sw	$s2,8($sp)
		sw	$ra,12($sp)
		
		#	get start address of current block and leave it in $a0
		move	$a0,$s0
		jal	calcStartAddr
		
		#	Restore registers
		lw	$ra,12($sp)
		lw	$s2,8($sp)
		lw	$s1,4($sp)
		lw	$s0,0($sp)
		addi	$sp,$sp,16
		loopSearchNameInner:	# for(int j = 0; j != 51; j++)
			beq	$s1,51,nameMatched
			add	$t0,$a0,$s1	# add j to get to letter to check
			la	$t1,strSearchInput
			add	$t1,$t1,$s1	# add j to get to letter to check
			lb	$t0,($t0)
			lb	$t1,($t1)
			#	compare and branch if not equal
			sub	$t0,$t0,$t1
			bnez	$t0,nameNotMatched
			addi	$s1,$s1,1	# Increment counter
			j	loopSearchNameInner		
		
		nameMatched:
			#	Save registers
			subi	$sp,$sp,16
			sw	$s0,0($sp)
			sw	$s1,4($sp)
			sw	$s2,8($sp)
			sw	$ra,12($sp)
			
			jal	printRecord	# $a0 never changed, still contains start address of record
			
			#	Restore registers
			lw	$ra,12($sp)
			lw	$s2,8($sp)
			lw	$s1,4($sp)
			lw	$s0,0($sp)
			addi	$sp,$sp,16
			
			#	Print newline and return
			li	$v0,11
			li	$a0,'\n'
			syscall
			jr	$ra
			
		nameNotMatched:
			#	Increment outer loop counter, reset inner loop counter, and continue outer loop
			addi	$s0,$s0,1
			li	$s1,0
			j	loopSearchNameOuter
		
	endLoopSearchName:
		#	Print newline and return
		li	$v0,11
		li	$a0,'\n'
		syscall
		jr	$ra
	
searchNum:
################
# Function: Searches database for all occurences of the searched number of cadets in squad
# Registers used:
# $s0 - Loop counter
# $s1 - Last index in database
# $t0 - Contains number of cadets from database
################
	#	Prompt for search number and read input
	li	$v0,4
	la	$a0,strSearchNumPrompt
	syscall
	li	$v0,5
	syscall		# $v0 now contains user input
	
	li	$s0,0		# Init loop counter
	lw	$s1,intCurSquad	# Load last record index
	searchNumLoop:	# for(int i = 0; i != intCurSquad; i++)
		beq	$s0,$s1,endSearchNumLoop
		
		#	Save registers
		subi	$sp,$sp,16
		sw	$v0,0($sp)
		sw	$s0,4($sp)
		sw	$s1,8($sp)
		sw	$ra,12($sp)
		
		move	$a0,$s0
		jal	calcStartAddr
		addi	$a0,$a0,56	# Jump past Squad name and number
		lw	$t0,($a0)
		lw	$v0,0($sp)
		bne	$v0,$t0,numNotMatched	# Jump if nums not equal
		
		subi	$a0,$a0,56	# Get back to start of record and print it
		jal	printRecord
		numNotMatched:
			# Continue
			
		#	Restore registers
		lw	$ra,12($sp)
		lw	$s1,8($sp)
		lw	$s0,4($sp)
		lw	$v0,0($sp)
		addi	$sp,$sp,16
		
		addi	$s0,$s0,1	# Increment loop counter
		
		j	searchNumLoop
	endSearchNumLoop:
		#	Print newline and return
		li	$v0,11
		li	$a0,'\n'
		syscall
		jr	$ra	
	
displayMenu:
################
# Function: Displays the menu
# Output:
#	$a0 - User's choice
# Registers used:
# none
################
	#	Display all options
	li	$v0,4
	la	$a0,strMenuSearchName
	syscall
	
	li	$v0,4
	la	$a0,strMenuSearchNum
	syscall
	
	li	$v0,4
	la	$a0,strMenuDisplayAll
	syscall
	
	li	$v0,4
	la	$a0,strMenuQuit
	syscall
	
	li	$v0,4
	la	$a0,strMenuUser
	syscall
	
	li	$v0,5
	syscall
	
	move $a0,$v0	# we return input in $a0, so move it there
	
	jr	$ra

printRecord:
################
# Function: Prints desired record
# Input:
#	$a0 - Index of entry to print
# Registers used:
# $s0 - Holds address of entry to print
################
	move	$s0,$a0		# Protect $a0 by moving it to $s0
	
	#	Print Squad Name
	li	$v0,4
	la	$a0,strSquadName
	syscall		
	li	$v0,4
	la	$a0,($s0)
	syscall
	
	add	$s0,$s0,52	# Add to get to Squad num
	
	#	Print Squad Num
	li	$v0,4
	la	$a0,strSquadNum
	syscall		
	li	$v0,1
	lw	$a0,($s0)
	syscall
	
	#	Print newline
	li	$v0,11
	li	$a0,'\n'
	syscall
	
	add	$s0,$s0,4	# Add to get to Num cadets in squad
	
	#	Print Number of Cadets in Squad
	li	$v0,4
	la	$a0,strSquadNumCadets
	syscall
	li	$v0,1
	lw	$a0,($s0)
	syscall
	
	#	Print newline and return
	li	$v0,11
	li	$a0,'\n'
	syscall
	jr	$ra
	
quit:
################
# Function: Exits gracefully
# Registers used:
# none
################
	li	$v0,10
	syscall
