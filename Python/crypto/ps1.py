from anthony_crypto_lib import *

print "Reading file"
testMsg = file_to_string(open('crypto_chap1.txt','r'),True)
unranked,ranked = do_frequency_analysis(testMsg)
print "Ranked Letter Frequencies"
for x in range(len(unranked)):
    print unranked[x][0],'-',unranked[x][1],'%'
print "\nUnranked Letter Frequencies"
for x in range(len(ranked)):
    print chr(x+65),'-',ranked[x]
