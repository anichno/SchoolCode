from anthony_crypto_lib import *

print "Reading file"
testMsg = file_to_string(open('testpassage.txt','r'),True)
testKey = "ANTHONYISAWESOME"
print "The original message is:"
print testMsg
encrypted = vigenere_encrypt(testMsg,testKey)
print "\nThe encrypted message is:"
print encrypted
print "\nThe decrypted message is:"
print vigenere_decrypt(encrypted,testKey)
cracked = vigenere_crack(encrypted,20)
print "\nThe cracked message is:"
print cracked
