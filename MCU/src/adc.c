#include "adc.h"

volatile unsigned char adcFlag;
volatile unsigned int adcCounter;
unsigned char adcPreviousValue;

ISR(ADC_vect)
{
    adcCounter++;
    if(adcCounter == ADC_COUNTER_MAX) 
    {
        adcCounter = 0;
        adcFlag = 1; 
    }   
}

void adcInit()
{
    ADMUX = (1 << ADLAR);
    ADCSRA = (1 << ADEN) | (1 << ADSC) | (1 << ADATE) | (1 << ADIE) | (1 << ADPS2) | (1 << ADPS1) | (1 << ADPS0);	
}
