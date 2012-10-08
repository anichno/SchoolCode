/*
 ============================================================================
 Name        : ProgPara_PEX_1.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void solitaire_encrypt(char* encrypted, char* plaintext, char* keystream);
void solitaire_decrypt(char* decrypted, char* ciphertext, char* keystream);

int main(void) {
	char testMsg[] = "DONOTUSEPC";
	char testKey[] = "KDWUPONOWT";
	char encrypted[strlen(testMsg)];
	char decrypted[strlen(testMsg)];
	solitaire_encrypt(encrypted,testMsg,testKey);
	printf("%s\n",encrypted);
	solitaire_decrypt(decrypted,encrypted,testKey);
	printf("%s\n",decrypted);
	return EXIT_SUCCESS;
}

void solitaire_encrypt(char* encrypted, char* plaintext, char* keystream) {
	int i = 0;
	for (i = 0; i < strlen(plaintext); i++) {
		encrypted[i] = (plaintext[i]-64+keystream[i]-64)%26+64;
	}
	encrypted[i] = 0;
}

void solitaire_decrypt(char* decrypted, char* ciphertext, char* keystream) {
	int i = 0;
	for (i = 0; i < strlen(ciphertext); i++) {
		int charval = ciphertext[i] - 64;
		int keyval = keystream[i] - 64;
		if (charval <= keyval) {
			charval += 26;
		}
		decrypted[i] = charval-keyval+64;
	}
	decrypted[i] = 0;
}
