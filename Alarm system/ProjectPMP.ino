 #include <Adafruit_GFX.h> // Include core graphics library for the display

#include <Adafruit_SSD1306.h> // Include Adafruit_SSD1306 library to drive the display

Adafruit_SSD1306 display(128, 64); // Create display

#include <Fonts/FreeMonoBold12pt7b.h> // Add a custom font

#include <Fonts/FreeMono9pt7b.h> // Add a custom font


#define echoPin 33 // attach pin D12 Arduino to pin Echo of HC-SR04
#define trigPin 32 //attach pin D13 Arduino to pin Trig of HC-SR04

#include <Servo.h>
Servo servo;
int led = 13;
long duration;
int distance;
int redpin = 51; // pin for red signal
int greenpin = 53; // pin for green signal
int pirSensor=29;
int Laser = 39;
int Detector = 38;

boolean aIntrat=0;
boolean ePersoana=0;
boolean aTerminatDeVerificat=0;
int semn=-1;
int angle=90;
long currentTime=0;
long previousTime=0;
int delayPrincipal=200;
int delayPersoana=5000;

void setup() {
    pinMode(led, OUTPUT);
    pinMode(pirSensor,INPUT);
    pinMode(Laser, OUTPUT);
    pinMode(Detector, INPUT);
    pinMode(redpin, OUTPUT);
    pinMode(greenpin, OUTPUT);
    servo.attach(24);
    servo.write(angle);
    pinMode(trigPin, OUTPUT); // Sets the trigPin as an OUTPUT
    pinMode(echoPin, INPUT); // Sets the echoPin as an INPUT
    delay(100); // This delay is needed to let the display to initialize
    display.begin(SSD1306_SWITCHCAPVCC, 0x3C); // Initialize display with the I2C address of 0x3C
    display.clearDisplay(); // Clear the buffer
    display.setTextColor(WHITE); // Set color of the text
    display.setRotation(0); // Set orientation. Goes from 0, 1, 2 or 3
    display.setTextWrap(false); // By default, long lines of text are set to automatically ΓÇ£wrapΓÇ¥ back to the leftmost column.
    // To override this behavior (so text will run off the right side of the display - useful for
    // scrolling marquee effects), use setTextWrap(false). The normal wrapping behavior is restored
    // with setTextWrap(true).
    display.dim(0); //Set brightness (0 is maximun and 1 is a little dim)
    digitalWrite(Laser, HIGH);
    Serial.begin(9600);
}

void loop() {
  display.clearDisplay(); // Clear the display so we can refresh
  display.setFont(&FreeMono9pt7b); // Set a custom font
  display.setTextSize(0);
  currentTime=millis();
  if(aIntrat==0){
    display.clearDisplay();
     servo.write(angle);
        display.clearDisplay();
        display.setCursor(0, 10);
        display.print("inca");
        display.setCursor(0, 30);
        display.print("asteptam"); 
        display.display();
      Serial.println(0);
      // Print everything we set previously
      delay(200);
  }
  if(aIntrat==0 && (currentTime-previousTime>=delayPrincipal ) ){
   
      previousTime=currentTime;
      boolean val = digitalRead(Detector);
      if(val==1){
       aIntrat=1;
      }
  }
  
  if(aIntrat==1 &&  (currentTime-previousTime>=delayPrincipal) && ePersoana==0){
     display.clearDisplay();
         display.setCursor(0, 10);
         display.print("a intrat");
         display.setCursor(0, 30);
         display.print("se asteapta");
         display.setCursor(0, 50);
         display.print("initializarea");
         display.display();
      Serial.println(0);
    previousTime=currentTime;
    digitalWrite(Laser, LOW);
    int pirValue=digitalRead(pirSensor);
     if (pirValue==1){
         digitalWrite(led, HIGH);
         ePersoana=1;
         
      }
      else
      {
          digitalWrite(led, LOW);
      }
  }
  if(ePersoana==1 && (currentTime-previousTime>=delayPersoana) && aTerminatDeVerificat==0 ){
    previousTime=currentTime;
         
    aTerminatDeVerificat=1;
    angle=45;
  }
  if(aTerminatDeVerificat==1 && (currentTime-previousTime>delayPrincipal)){
    previousTime=currentTime;
    afisarePozitie();
  }
    
}
void afisarePozitie(){
        display.clearDisplay();
        display.setCursor(0, 10);
        display.drawRect(80, 3, 40, 54, WHITE);
        servo.write(angle);
        // Clears the trigPin condition
        // Clears the trigPin
        digitalWrite(trigPin, LOW);
        delayMicroseconds(2);
        // Sets the trigPin on HIGH state for 10 micro seconds
        digitalWrite(trigPin, HIGH);
        delayMicroseconds(10);
        digitalWrite(trigPin, LOW);
        // Reads the echoPin, returns the sound wave travel time in microseconds
        duration = pulseIn(echoPin, HIGH);
        // Calculating the distance
        distance= duration*0.034/2;
        int z=calculareZona(angle,distance);
        Serial.println(z);
        desenareDisplay(z);
          
        display.setCursor(0, 10);
        display.print("Dis: ");
        display.setCursor(0, 30);
        display.print(distance);
        display.println(" cm");
        display.display(); // Print everything we set previously
        if(angle==45 or angle==135){
          semn=semn*(-1);
          
        }
        angle=angle+semn;
}

int calculareZona(int unghi, int dist){
  int nr=0;
  if(unghi>=45 && unghi<=70){
    nr=40;
   }
   else if(unghi>70 && unghi<=90){
    nr=30;
   }
   else if(unghi>90 && unghi<=110){
    nr=20;
   }
   else if(unghi>110 ){
    nr=10;
   }
   if(dist<28 && dist>=25){
    nr+=6;
   }
   else if(dist<25 && dist>=20){
    nr+=5;
   }
   else if(dist<20 && dist>=15){
    nr+=4;
   }
   else if(dist<15 && dist>=10){
    nr+=3;
   }
   else if(dist<10 && dist>=5){
    nr+=2;
   }
   else if(dist<5 ){
    nr+=1;
   }
    return nr;
}
void desenareDisplay(int zonad){
  if(zonad==16){
        display.drawRect(80, 3, 10, 9, WHITE);
        analogWrite(redpin,0 ); 
  }
  else if(zonad==15){
        display.drawRect(80, 12, 10, 9, WHITE);
        analogWrite(redpin,50 ); 
  }
  else if(zonad==14){
        display.drawRect(80, 21, 10, 9, WHITE);
        analogWrite(redpin,100 ); 
  }
  else if(zonad==13){
        display.drawRect(80, 30, 10, 9, WHITE);
        analogWrite(redpin,150 ); 
  }
  else if(zonad==12){
        display.drawRect(80, 39, 10, 9, WHITE);
        analogWrite(redpin,200 ); 
  }
  else if(zonad==11){
        display.drawRect(80, 48, 10, 9, WHITE);
        analogWrite(redpin,255 ); 
  }
  else if(zonad==26){
        display.drawRect(90, 3, 10, 9, WHITE);
        analogWrite(redpin,0 ); 
  }
  else if(zonad==25){
        display.drawRect(90, 12, 10, 9, WHITE);
        analogWrite(redpin,50 ); 
  }
  else if(zonad==24){
        display.drawRect(90, 21, 10, 9, WHITE);
        analogWrite(redpin,100 ); 
  }
  else if(zonad==23){
        display.drawRect(90, 30, 10, 9, WHITE);
        analogWrite(redpin,150 ); 
  }
  else if(zonad==22){
        display.drawRect(90, 39, 10, 9, WHITE);
        analogWrite(redpin,200 ); 
  }
  else if(zonad==21){
        display.drawRect(90, 48, 10, 9, WHITE);
        analogWrite(redpin,255 ); 
  }
   else if(zonad==36){
        display.drawRect(100, 3, 10, 9, WHITE);
        analogWrite(redpin,0 ); 
  }
  else if(zonad==35){
        display.drawRect(100, 12, 10, 9, WHITE);
        analogWrite(redpin,50 );
  }
  else if(zonad==34){
        display.drawRect(100, 21, 10, 9, WHITE);
        analogWrite(redpin,100 );
  }
  else if(zonad==33){
        display.drawRect(100, 30, 10, 9, WHITE);
        analogWrite(redpin,150 );
  }
  else if(zonad==32){
        display.drawRect(100, 39, 10, 9, WHITE);
        analogWrite(redpin,200 );
  }
  else if(zonad==31){
        display.drawRect(100, 48, 10, 9, WHITE);
        analogWrite(redpin,255 );
  }
  else if(zonad==46){
        display.drawRect(110, 3, 10, 9, WHITE);
        analogWrite(redpin,0 );
  }
  else if(zonad==45){
        display.drawRect(110, 12, 10, 9, WHITE);
        analogWrite(redpin,50 );
  }
  else if(zonad==44){
        display.drawRect(110, 21, 10, 9, WHITE);
        analogWrite(redpin,100 );
  }
  else if(zonad==43){
        display.drawRect(110, 30, 10, 9, WHITE);
        analogWrite(redpin,150 );
  }
  else if(zonad==42){
        display.drawRect(110, 39, 10, 9, WHITE);
        analogWrite(redpin,200 );
  }
  else if(zonad==41){
        display.drawRect(110, 48, 10, 9, WHITE);
        analogWrite(redpin,255 );
  }
}

