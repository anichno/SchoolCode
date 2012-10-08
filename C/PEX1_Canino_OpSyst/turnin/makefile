RM := rm -rf

# All Target
all: PEX1_Canino_OpSyst

# Tool invocations
PEX1_Canino_OpSyst: 
	@echo 'Building target: $@'
	@echo 'Invoking: Cygwin C Linker'
	gcc  -o "PEX1_Canino_OpSyst" main.c -Wall
	gcc  -o "primes" primes.c -std=gnu99 -lpthread -Wall
	@echo 'Finished building target: $@'
	@echo ' '

# Other Targets
clean:
	-$(RM) PEX1_Canino_OpSyst primes
	-@echo ' '

