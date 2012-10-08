TEXTFILE="motto.txt"
DECK="deck.txt"

cmd /c cls
cp Debug/PEX1_Canino.e* .
rm -f lol.txt
echo "Testing encryption"
Debug/PEX1_Canino.exe -e $DECK $TEXTFILE lol.txt
cat lol.txt
echo ""
read -p "Press [Enter] key to continue..."
printf "\n\n"
echo "Testing decrypting"
Debug/PEX1_Canino.exe -d $DECK lol.txt lol.txt
cat lol.txt
rm lol.txt
echo ""
read -p "Press [Enter] key to continue..."
printf "\n\n"
echo "Testing ascending order deck generation"
Debug/PEX1_Canino.exe -a lol.txt
cat lol.txt
rm lol.txt
echo ""
read -p "Press [Enter] key to continue..."
printf "\n\n"
echo "Testing random order deck generation"
Debug/PEX1_Canino.exe -r lol.txt
cat lol.txt
rm lol.txt
echo ""
read -p "Press [Enter] key to continue..."
printf "\n\n"
echo "Testing printing first 8 keys"
./PEX1_Canino.exe -k  $DECK
read -p "Press [Enter] key to continue..."
printf "\n\n"
echo "Testing reading from file"
Debug/PEX1_Canino.exe -f $DECK
read -p "Press [Enter] key to continue..."
printf "\n\n"
echo "Testing moving jokers"
Debug/PEX1_Canino.exe -m $DECK
read -p "Press [Enter] key to continue..."
printf "\n\n"
echo "Testing triple cut"
Debug/PEX1_Canino.exe -t $DECK
read -p "Press [Enter] key to continue..."
printf "\n\n"
echo "Testing count cut step"
Debug/PEX1_Canino.exe -c $DECK
read -p "Press [Enter] key to continue..."
printf "\n\n"
echo "Testing entire pontifex algorithm"
Debug/PEX1_Canino.exe -p $DECK
read -p "Press [Enter] key to continue..."
printf "\n\n"
echo "Testing output card discovery"
Debug/PEX1_Canino.exe -o $DECK
read -p "Press [Enter] key to continue..."