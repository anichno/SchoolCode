import re,operator,math,random,time,struct

#   http://stackoverflow.com/a/1545678
def file_to_string(fileOb,doCleanup):
    strFile = ''
    for line in fileOb:
        tempLine = line
        if doCleanup:
            tempLine = re.sub('\s+','',tempLine) # Remove whitespace
            tempLine = re.sub(r'[^\w\s]','',tempLine) # Remove puctuation
            tempLine = re.sub('[0-9_]+','',tempLine) # Remove numbers and underscore
        strFile += tempLine
    return strFile

#   http://stackoverflow.com/questions/613183/python-sort-a-dictionary-by-value/613218#613218
#   http://stackoverflow.com/a/1545678
def do_frequency_analysis(sampleText):
    
    sampleText = re.sub('\s+','',sampleText) # Remove whitespace
    sampleText = re.sub(r'[^\w\s]','',sampleText) # Remove puctuation
    sampleText = re.sub('[0-9_]+','',sampleText) # Remove numbers and underscore
    #numLetters = dict()
    numLetters = [0 for x in range(26)]
    totLetters = 0.0
    for x in sampleText.upper():
        numLetters[ord(x)-65] += 1
        totLetters += 1
    for x in range(len(numLetters)):
        numLetters[x] = numLetters[x]/totLetters*100
    unrankedLetters = []
    for x in numLetters:
        unrankedLetters.append(x)
    rankedLetters = [0 for x in range(len(numLetters))]
    for x in range(len(numLetters)):
        rankedLetters[x] = [chr(numLetters.index(max(numLetters))+65),max(numLetters)]
        numLetters[numLetters.index(max(numLetters))] = 0
    return rankedLetters,unrankedLetters

def shift_encrypt(plaintext,shift):
    ciphertext = ""
    for char in plaintext.upper():
        ciphertext += chr((ord(char)-65+shift)%26 + 65)
    return ciphertext

def shift_decrypt(ciphertext,shift):
    plaintext = ""
    for char in ciphertext.upper():
        plaintext += chr((ord(char)-65-shift)%26 + 65)
    return plaintext

def shift_crack(ciphertext):
    rankedLetters,unrankedLetters = do_frequency_analysis(ciphertext)
    offset = (ord(rankedLetters[0][0])-ord('E'))
    return shift_decrypt(ciphertext,offset)

def affine_encrypt(plaintext,A,B):
    ciphertext = ""
    for char in plaintext.upper():
        ciphertext += chr((A*(ord(char)-65)+B)%26+65)
    return ciphertext

#   http://programmingpraxis.com/2009/12/15/affine-shift-cipher/
def find_mult_inv(a,mod):
    for i in range(mod):
        if ((i*a)%mod) == 1:
            return i
    return None

#   http://practicalcryptography.com/ciphers/affine-cipher/
def affine_decrypt(ciphertext,A,B):
    # d(x) = a-1(x - b)
    plaintext = ""
    m = find_mult_inv(A,26)
    for char in ciphertext.upper():
        plaintext += chr((m*(ord(char)-65-B))%26 + 65)
    return plaintext

#   http://practicalcryptography.com/ciphers/affine-cipher/
def affine_crack(ciphertext):
    letterFreq,unranked = do_frequency_analysis(ciphertext)
    print "hi"
    return affine_crack_known(ciphertext,('E',letterFreq[0][0]),('T',letterFreq[1][0]),True)

def affine_crack_known(ciphertext,L1,L2,showWork):
    p = ord(L1[0])-65
    r = ord(L1[1])-65
    q = ord(L2[0])-65
    s = ord(L2[1])-65
    d = find_mult_inv(p-q,26)
    a = (d*(r-s))%26
    b = (d*(p*s-q*r))%26
    if showWork:
        print "a x",p,"+ b =",r,"(mod 26)"
        print "a x",q,"+ b =",s,"(mod 26)"
        print "a = D^-1(",r,"-",s,") (mod 26)"
        print "b = D^-1(",p*s,"-",q*r,") (mod 26)"
        print "D^-1 =",d
    print "Key: A -",a,"B -",b
    return affine_decrypt(ciphertext,a,b)

def vigenere_encrypt(plaintext,key):
    ciphertext = ""
    for (char,count) in zip(plaintext.upper(),range(len(plaintext))):
        ciphertext += chr(((ord(char)-65)+(ord(key.upper()[count%len(key)])-65))%26+65)
    return ciphertext

def vigenere_decrypt(ciphertext,key):
    plaintext = ""
    for (char,count) in zip(ciphertext.upper(),range(len(ciphertext))):
        plaintext += chr(((ord(char)-65)-(ord(key.upper()[count%len(key)])-65))%26+65)
    return plaintext

#   http://www.simonsingh.net/The_Black_Chamber/vigenere_cracking_tool.html
def vigenere_crack(ciphertext,maxKeyLength):
    sampleText = ciphertext.upper()[:1000]
    stdLetterFreq = [8.2,1.5,2.8,4.3,12.7,2.2,2.0,6.1,7.0,0.2,0.8,4.0,2.4,6.7,7.5,1.9,0.1,6.0,6.3,9.1,2.8,1.0,2.4,0.2,1.0,2.4,0.2,0.1]
    possibleKeyLengths = [0 for x in range(maxKeyLength)]
    print "Finding patterns...",
    lastProgPrint = 0
    for a in range(ord('A'),ord('Z')):
        for b in range(ord('A'),ord('Z')):
            for c in range(ord('A'),ord('Z')):
                searchRegEx = "("+chr(a)+chr(b)+chr(c)+")+"
                repeatedPat = re.findall(searchRegEx,sampleText)
                if len(repeatedPat) > 1:
                    firstIndex = len(sampleText)
                    for index in range(len(sampleText)-3):
                        if sampleText[index:index+3] == repeatedPat[0]:
                            if index < firstIndex:
                                firstIndex = index
                            if index > firstIndex:
                                for x in range(2,min([maxKeyLength,index-firstIndex])):
                                    if (index-firstIndex)%x == 0:
                                        possibleKeyLengths[x] += 1
                                firstIndex = index
        progress = int(((a-65)/26.0)*100)/10*10
        if progress > lastProgPrint:
            lastProgPrint = progress
            print str(progress)+'%',
    print "100%"
    averageFactor = 0.0
    for x in possibleKeyLengths:
        averageFactor += x
    averageFactor /= len(possibleKeyLengths)
    keyLengths = []
    for x in range(len(possibleKeyLengths)):
        if max(possibleKeyLengths) > averageFactor:
            keyLengths.append(possibleKeyLengths.index(max(possibleKeyLengths)))
            possibleKeyLengths[possibleKeyLengths.index(max(possibleKeyLengths))] = 0
    if len(keyLengths) == 0:
        for count in range(1,maxKeyLength):
            keyLengths.append(count)
    print keyLengths
    print "Attempting key discovery"
    for keyLength in keyLengths:
        print "Trying key length of:",keyLength
        key = ""
        for offset in range(0,keyLength):
            numLetters = [0 for count in range(26)]
            totLetters = 0.0
            for x in range(offset,len(sampleText),keyLength):
                numLetters[ord(sampleText[x])-65] += 1
                totLetters += 1

            for x in range(len(numLetters)):
                numLetters[x] = numLetters[x]/totLetters*100
            possibleOffsets = []
            for shift in range(26):
                difFromStd = 0.0
                for pos in range(26):
                    if numLetters[(shift+pos)%26] > 5:
                        difFromStd += abs(stdLetterFreq[pos] - numLetters[(shift+pos)%26])
                    
                possibleOffsets.append(difFromStd)
            key += chr(possibleOffsets.index(min(possibleOffsets))+65)
        print "Using key:",key
        print "Does the following appear correct?",vigenere_decrypt(sampleText,key)[:30]
        yesno = raw_input("(y/N): ")
        if yesno == "y":
            return vigenere_decrypt(ciphertext,key)
        
    return "Decryption failed"

def extEuclidean(a,b,showWork=False):
    if b == 0:
        d = a
        x = 1
        y = 0
        return (d,x,y)
    x2 = 1
    x1 = 0
    y2 = 0
    y1 = 1
    while (b > 0):
        q = a/b
        r = a - q * b
        x = x2 - q*x1
        y = y2 - q*y1
        a = b
        b = r
        x2 = x1
        x1 = x
        y2 = y1
        y1 = y
        if showWork:
            print 'q={} r={} x={} y={} a={} b={} x2={} x1={} y2={} y1={}'.format(q,r,x,y,a,b,x2,x1,y2,y1)
    return (a,x2,y2)

def findPrimeBrute(testNum):
    for x in range(2,testNum):
        if testNum%x == 0:
            return x
    return None

def euclidean(a,b):
    while (b > 0):
        r = a%b
        a = b
        b = r
    return a

def findPrimePollard(testNum):
    a = 2
    b = 2
    for x in range(testNum):
        a = ((a*a) + 1)%testNum
        b = ((b*b) + 1)%testNum
        b = ((b*b) + 1)%testNum
        d = euclidean(a-b,testNum)
        if 1 < d and d < testNum:
            return d
        if d == testNum:
            return None
    return None

def printTable(table):
    for col in range(0,len(table)):
        print table[col]
        
def mod2GaussianElim(identMat,gaussMat):
    for col in range(0,len(identMat)):
        if gaussMat[col][col] == 0:
           for row in range(col+1,len(identMat)):
               if gaussMat[row][col] == 1:
                   for k in range(0,len(identMat)):
                       tmp = gaussMat[row][k]
                       gaussMat[row][k] = gaussMat[col][k]
                       gaussMat[col][k] = tmp
                   for k in range(0,len(identMat)):
                      tmp = identMat[row][k]
                      identMat[row][k] = identMat[col][k]
                      identMat[col][k] = tmp
                   break
        if gaussMat[col][col] == 1:
            for row in range(col+1,len(identMat)):
                if gaussMat[row][col] == 1:
                    for k in range(0,len(identMat)):
                        gaussMat[row][k] = (gaussMat[row][k]+gaussMat[col][k])%2
                    for k in range(0,len(identMat)):
                        identMat[row][k] = (identMat[row][k]+identMat[col][k])%2
    

def findPrimeDixon(testNum,factorBaseNum,factorBaseFile,verbose):
    #   Step B
    factorBase = []
    for x in range(0,factorBaseNum):
        factorBase.append(int(factorBaseFile.readline()))
    for lol in range(0,1000):
        #   Step C
        table = []
        numGoodEqu = 0
        randNums = []
        while numGoodEqu < factorBaseNum:
            boolGoodEqu = False
            tempRandNum = random.randint(int(math.sqrt(testNum)),testNum)
            randNum = int(math.pow(tempRandNum,2)%testNum)
            
            row = []
            for x in factorBase:
                tempNum = randNum
                count = 0
                while tempNum%x == 0:
                    count += 1
                    tempNum /= x
                row.append(count)
                if count > 0:
                    boolGoodEqu = True
            
            if boolGoodEqu:
                if verbose: print randNum,"\t- ",row
                table.append(row)
                numGoodEqu += 1
                randNums.append(tempRandNum)
            
        if verbose: print "Table C"
        if verbose: printTable(table)
        gaussTable = []
        for row in table:
            gaussRow = []
            for field in row:
                gaussRow.append(field%2)
            gaussTable.append(gaussRow)
        if verbose: print "Table C\'"
        if verbose: printTable(gaussTable)
        identityMatrix = []
        for x in range(0,factorBaseNum):
            identityRow = []
            for a in range(0,factorBaseNum):
                if a == x:
                    identityRow.append(1)
                else:
                    identityRow.append(0)
            identityMatrix.append(identityRow)
        if verbose: print "Identity Matrix"
        if verbose: printTable(identityMatrix)
        mod2GaussianElim(identityMatrix,gaussTable)
        if verbose: print "Transformed Matrix"
        if verbose: printTable(identityMatrix)
        if verbose: print "Transformed C\'"
        if verbose: printTable(gaussTable)
        # Flip tables
        identityMatrix.reverse()
        gaussTable.reverse()
        if verbose: print "Reversed C\'"
        if verbose: printTable(gaussTable)

        if verbose: print "Matching solution rows"
        for (gRow,iRow) in zip(gaussTable,identityMatrix):
            if not 1 in gRow:
                if verbose: print gRow," - ",iRow
                combinedEqu = 1
                combinedFact = 1
                totExp = [0 for blah in range(factorBaseNum)]
                for field in range(0,len(iRow)):
                    if iRow[field] == 1:
                        combinedEqu *= randNums[field]
                        for solvField in range(0,len(identityMatrix)):
                            totExp[solvField] += table[field][solvField]
                        
                pair_x = combinedEqu%testNum
                for exp in range(0,factorBaseNum):
                    combinedFact *= int(math.pow(factorBase[exp],totExp[exp]))
                pair_y = int(math.sqrt(combinedFact)%testNum)
                if verbose: print pair_x,"\t- ",pair_y
                if abs(pair_x) != abs(pair_y):
                    theFactor = euclidean(abs(pair_x-pair_y),testNum)
                    if theFactor != 1:
                        #print lol
                        #print randNums
                        return theFactor
    return None

def isPrime(testNum,primeNumFile):
    for prime in primeNumFile:
        if re.match("[0-9]+",prime):    # The blank lines at the end of the file really screw it up
            if testNum == int(prime):
                return True
    return False

#   http://tools.ietf.org/html/rfc1321
#   http://en.wikipedia.org/wiki/MD5
#   http://starship.python.net/crew/gherman/programs/md5py/ for bit mask
def caninoMD5F(X,Y,Z):
    return (X&Y) | (~X&Z)

def caninoMD5G(X,Y,Z):
    return (X&Z) | (Y&~Z)

def caninoMD5H(X,Y,Z):
    return X^Y^Z

def caninoMD5I(X,Y,Z):
    return Y^(X|~Z)

def leftrotate(value,shift):
    return (value << shift) | (value >> (32-shift))

def caninoMD5round1(A,B,C,D,k,s,i):
    #   a = b + ((a + F(b,c,d) + X[k] + T[i]) <<< s)
    return B + leftrotate((A + caninoMD5F(B,C,D) + k + i) & 0xffffffffL,s)

def caninoMD5round2(A,B,C,D,k,s,i):
    #   a = b + ((a + G(b,c,d) + X[k] + T[i]) <<< s)
    return B + leftrotate((A + caninoMD5G(B,C,D) + k + i) & 0xffffffffL,s)

def caninoMD5round3(A,B,C,D,k,s,i):
    #   a = b + ((a + H(b,c,d) + X[k] + T[i]) <<< s)
    return B + leftrotate((A + caninoMD5H(B,C,D) + k + i) & 0xffffffffL,s)

def caninoMD5round4(A,B,C,D,k,s,i):
    #   a = b + ((a + I(b,c,d) + X[k] + T[i]) <<< s)
    return B + leftrotate((A + caninoMD5I(B,C,D) + k + i) & 0xffffffffL,s)

def caninoMD5(message):

    r = [   7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
            5,  9, 14, 20, 5,  9, 14, 20, 5,  9, 14, 20, 5,  9, 14, 20,
            4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
            6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21  ]
            
    k = [   0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee,
            0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501,
            0x698098d8, 0x8b44f7af, 0xffff5bb1, 0x895cd7be,
            0x6b901122, 0xfd987193, 0xa679438e, 0x49b40821,
            0xf61e2562, 0xc040b340, 0x265e5a51, 0xe9b6c7aa,
            0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
            0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed,
            0xa9e3e905, 0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a,
            0xfffa3942, 0x8771f681, 0x6d9d6122, 0xfde5380c,
            0xa4beea44, 0x4bdecfa9, 0xf6bb4b60, 0xbebfbc70,
            0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05,
            0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665,
            0xf4292244, 0x432aff97, 0xab9423a7, 0xfc93a039,
            0x655b59c3, 0x8f0ccc92, 0xffeff47d, 0x85845dd1,
            0x6fa87e4f, 0xfe2ce6e0, 0xa3014314, 0x4e0811a1,
            0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391  ]
            
    origLength = len(message)*8
    print "orig length: ",len(message)*8
    message = bytearray(message)
    message.append(0b10000000)
    print "\'1\' bit added: ",len(message)*8
    while (len(message)*8)%512 != 448%512:
        message.append(0b00000000)
    print "padded length: ",len(message)*8
    print "appending length"
    message.extend(struct.pack('<q',origLength))
    print "rounded length: ",(len(message)*8)%512

    #   Convert message to bits
    binMsg = 0
    for (byte,count) in zip(message,range(0,len(message))):
        binMsg |= byte << count*8
    
    # Init MD Buffer
    A = 0x67452301
    B = 0xefcdab89
    C = 0x98badcfe
    D = 0x10325476

    #   Process Chunks
    for chunks in range(0,len(message)/64):
        wordChunk = []
        for word32b in range(0,16):
            tempWord = 0
            for (shift,count) in zip(range(0,32,8),range(chunks*64+word32b*4,chunks*64+word32b*4+4)):
                tempWord |= message[count] << shift
            wordChunk.append(tempWord)

        AA = A
        BB = B
        CC = C
        DD = D

        regList = [A,B,C,D]

        # Round 1
        for pos in range(16):
            
            regList[(16-pos)%4] = caninoMD5round1(regList[(16-pos)%4],regList[(16-pos+1)%4],
                                                  regList[(16-pos+2)%4],regList[(16-pos+3)%4],
                                                  wordChunk[pos],r[pos],k[pos])

        # Round 2
        for (pos,i) in zip(range(16),range(16,32)):
            regList[(16-pos)%4] = caninoMD5round2(regList[(16-pos)%4],regList[(16-pos+1)%4],
                                                  regList[(16-pos+2)%4],regList[(16-pos+3)%4],
                                                  wordChunk[(1+pos*5)%16],r[i],k[i])

        # Round 3
        for (pos,i) in zip(range(16),range(32,48)):
            regList[(16-pos)%4] = caninoMD5round3(regList[(16-pos)%4],regList[(16-pos+1)%4],
                                                  regList[(16-pos+2)%4],regList[(16-pos+3)%4],
                                                  wordChunk[(pos+5+pos*3-pos)%16],r[i],k[i])

        # Round 4
        for (pos,i) in zip(range(16),range(48,64)):
            regList[(16-pos)%4] = caninoMD5round4(regList[(16-pos)%4],regList[(16-pos+1)%4],
                                                  regList[(16-pos+2)%4],regList[(16-pos+3)%4],
                                                  wordChunk[(pos*7)%16],r[i],k[i])
            
        A = (regList[0] + AA)
        B = (regList[1] + BB)
        C = (regList[2] + CC)
        D = (regList[3] + DD)
    hashList = []
    for byte in range(4):
        hashList.append((A & (0xff << byte*8)) >> byte*8)
    for byte in range(4):
        hashList.append((B & (0xff << byte*8)) >> byte*8)
    for byte in range(4):
        hashList.append((C & (0xff << byte*8)) >> byte*8)
    for byte in range(4):
        hashList.append((D & (0xff << byte*8)) >> byte*8)

    digest = ""
    for byte in hashList:
        tempByte = hex(byte)
        tempByte = re.sub("0x",'',tempByte)
        tempByte = re.sub("L",'',tempByte)
        if len(tempByte) == 1:
            tempByte = '0'+tempByte
        digest += tempByte

    return digest

def findCRT(modList,remainderList):
    start = remainderList[modList.index(max(modList))]
    for x in range(start,reduce(operator.mul,modList),max(modList)):
        goodSol = True
        for (mod,remain) in zip(modList,remainderList):
            if x%mod != remain:
                goodSol = False
                break
        if goodSol:
            return x
