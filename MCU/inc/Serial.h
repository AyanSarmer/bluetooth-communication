#ifndef SERIAL_H_
#define SERIAL_H_

#include <avr/io.h>
#include <avr/interrupt.h>
#include <stdbool.h>

#define UART_BAUDRATE			9600
#define FREQUENCY				8000000UL
#define UBRR_VALUE				FREQUENCY / 16 / UART_BAUDRATE - 1

void serialBegin();
void serialPrintln(const char* buff);
void serialSendByte(char symbol);

extern volatile uint8_t receivedByte[11];
extern bool receiveFlag;

#endif /* SERIAL_H_ */