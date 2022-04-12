# Project options
FILE				= bluetoothCommunication
MCU					= atmega328p
# Folders
SRC_DIR       		= src
INC_DIR       		= inc
EXE_DIR       		= exe
# Objects
SOURCES       		= $(wildcard $(SRC_DIR)/*.c)
SOURCES       		+= $(wildcard $(SRC_DIR)/*.S)
# Compiler options
PREAMBLE      		= avr-
COMPILER      		= $(PREAMBLE)gcc
	CC_OPTIONS  	= -mmcu=$(MCU)
	CC_OPTIONS  	+= -Wl,-u,vfprintf -lprintf_flt
	CC_OPTIONS  	+= -Os
	CC_OPTIONS		+= -I $(INC_DIR)
	CC_OPTIONS  	+= -o
# Uploader options
UPLOADER      		= avrdude
	U_OPTIONS		= -p $(MCU)
	U_OPTIONS		+= -c usbasp
	U_OPTIONS		+= -P usb
	U_OPTIONS		+= -e
	U_OPTIONS		+= -U flash:w:$(EXE_DIR)/$(FILE).hex
	