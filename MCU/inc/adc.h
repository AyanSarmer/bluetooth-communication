#ifndef ADC_H_
#define ADC_H_

#include <avr/io.h>
#include <avr/interrupt.h>

#define ADC_COUNTER_MAX         1000

void adcInit();

extern volatile unsigned char adcFlag;
extern volatile unsigned int adcCounter;
extern unsigned char adcPreviousValue;

#endif /* ADC_H_ */