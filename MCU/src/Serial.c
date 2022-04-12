#include "Serial.h"

volatile uint8_t receivedByte[11], byteNum = 0;
bool receiveFlag;

ISR(USART_RX_vect)
{
    receivedByte[byteNum] = UDR0;
	byteNum++;
	if(byteNum > 10)
	{
		byteNum = 0;
		receiveFlag = 1;
	}
}

void serialBegin()
{
	uint16_t ubrrValue = UBRR_VALUE;

	UBRR0H = (uint8_t)(ubrrValue >> 8);
	UBRR0L = (uint8_t)(ubrrValue);
	UCSR0B |= (1 << TXEN0) | (1 << RXEN0) | (1 << RXCIE0);
	UCSR0C |= (3 << UCSZ00);
}

void serialSendByte(char data)
{
	while (!(UCSR0A & (1 << UDRE0)));
	UDR0 = data;
}

void serialPrintln(const char* buff)
{
	while(*buff != '\0')
	{
		serialSendByte(*buff);
		buff++;
	}
	serialSendByte('\n');
}