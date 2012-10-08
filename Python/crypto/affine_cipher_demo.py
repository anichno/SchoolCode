from anthony_crypto_lib import *

A = 15
B = 25
testMsg = file_to_string(open('testpassage.txt','r'),True)
print "The original message is:"
print testMsg
encrypted = affine_encrypt(testMsg,A,B)
print "\nThe encrypted message is:"
print encrypted
print "\nThe decrypted message is:"
print affine_decrypt(encrypted,A,B)
cracked = affine_crack(encrypted)
print "\nThe cracked message is:"
print cracked
