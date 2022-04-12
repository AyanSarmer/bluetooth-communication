#ifndef DEVICE_H_
#define DEVICE_H_

#define F_CPU               8000000UL 

#include <util/delay.h>
#include <avr/io.h>
#include <string.h>
#include "Serial.h"
#include "adc.h"

#define STRIP_LENGTH    105
#define LEDS_NUM		(STRIP_LENGTH * 3)

#define STRIP_DDR       DDRB
#define STRIP_PIN       PB0

void deviceInit();
void deviceControl();
void stripOff();
void setLedColor(uint8_t* p_buf, uint8_t led, uint8_t red, uint8_t green, uint8_t blue);

extern void stripRefresh(uint8_t * ptr, uint16_t count);

extern uint8_t buf[LEDS_NUM];

#endif /* DEVICE_H_ */