#include "device.h"

unsigned char communicationFlag = 0;
uint8_t buf[LEDS_NUM];
uint8_t redColorValue, greenColorValue, blueColorValue, ledNum;

void deviceInit()
{
    serialBegin();  
    STRIP_DDR |= (1 << STRIP_PIN); 
	stripOff(); 
    adcInit();
    sei();
}  

void deviceControl()
{
    if(receiveFlag)
	{
		receiveFlag = 0;
        if(receivedByte[0] == 2) 
        {
            communicationFlag = 1;
        } 

        if(receivedByte[0] == 1) 
        {            
            ledNum = receivedByte[1];
            greenColorValue = (int)(receivedByte[2] * 100 + receivedByte[3] * 10 + receivedByte[4]);
            redColorValue = (int)(receivedByte[5] * 100 + receivedByte[6] * 10 + receivedByte[7]);
            blueColorValue = (int)(receivedByte[8] * 100 + receivedByte[9] * 10 + receivedByte[10]);
            setLedColor(buf, ledNum, redColorValue, greenColorValue, blueColorValue); 
            stripRefresh(buf, sizeof(buf));       
        }
	}

    if(communicationFlag)
    {
        if(adcFlag)
        {
            adcFlag = 0;
            unsigned char adcCurrentValue = (unsigned char)(ADCH * 100 / 255);
            if(adcPreviousValue != adcCurrentValue)
            {
                adcPreviousValue = adcCurrentValue;
                serialSendByte(adcCurrentValue);
            }
        }
    }
}

void stripOff()
{
	memset(buf, 0, sizeof(buf));
	stripRefresh(buf, sizeof(buf));
}

void setLedColor(uint8_t* p_buf, uint8_t led, uint8_t red, uint8_t green, uint8_t blue)
{
	uint16_t index = 3 * led;
	p_buf[index++] = red;
	p_buf[index++] = green;
	p_buf[index] = blue;
}