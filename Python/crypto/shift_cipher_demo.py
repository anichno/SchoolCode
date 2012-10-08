from anthony_crypto_lib import *

shift = 3
testMsg = file_to_string(open('testpassage.txt','r'),True)
print "The original message is:"
print testMsg
encrypted = shift_encrypt(testMsg,shift)
print "\nThe encrypted message is:"
print encrypted
print "\nThe decrypted message is:"
print shift_decrypt(encrypted,shift)
print "\nThe cracked message is:"
print shift_crack(encrypted)
