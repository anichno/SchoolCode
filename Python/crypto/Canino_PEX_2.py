from anthony_crypto_lib import *
import hashlib,time

testString = "The quick brown fox jumps over the lazy dog"*500
stopwatch = time.time()
digest = caninoMD5(testString)
print "Calculated hash is:\t\t",digest
print "It took: ",time.time()-stopwatch," seconds"

stopwatch = time.time()
pythonMD5 = hashlib.md5()
pythonMD5.update(testString)
print "Calculated hash should be:\t",pythonMD5.hexdigest()
print "it took: ",time.time()-stopwatch," seconds"
