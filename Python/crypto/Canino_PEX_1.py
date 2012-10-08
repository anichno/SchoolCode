from anthony_crypto_lib import *
    
print "PEX1 - Factoring! - by Anthony Canino"
print "CS431 - Fall 2012"
userNumToFactor = 1
while userNumToFactor != 0:
    while True:
        userNumToFactor = int(raw_input("Enter a number to factor: "))
        if isPrime(userNumToFactor,open("first_100K_primes.txt",'r')):
            print "Sorry, this number is prime. Please select a new number..."
        else:
            break
    if userNumToFactor == 0:
            continue
        
    print "\nBrute force factoring"
    stopwatch = time.time()
    theFactor = findPrimeBrute(userNumToFactor)
    print "Found a factor = ",theFactor
    print "It took ",time.time()-stopwatch," seconds.\n"

    print "Pollard Rho"
    stopwatch = time.time()
    theFactor = findPrimePollard(userNumToFactor)
    if theFactor:
        print "Found a factor = ",theFactor
    else:
        print "Could not find a factor"
    print "It took ",time.time()-stopwatch," seconds.\n"

    print "Dixon's Algorithm"
    userFactorBase = int(raw_input("Enter # of factors in factor base: "))
    stopwatch = time.time()
    theFactor = findPrimeDixon(userNumToFactor,userFactorBase,open('first_100K_primes.txt','r'),False)
    if theFactor:
        print "Found a factor = ",theFactor
    else:
        print "Could not find a factor"
    print "It took ",time.time()-stopwatch," seconds."
    
print "Thank you for using this program. Quitting..."
