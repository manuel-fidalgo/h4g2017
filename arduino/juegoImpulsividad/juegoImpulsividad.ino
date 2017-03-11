
int nRepeticiones = 0;

int estadoBoton = 0;

// Los pines donde están conectados los leds
int posLeds[5]={3,4,5,6,7};

int pinInterruptor = 2;

int estadoLed = 0;

volatile bool interrupt = false;

void setup() {
    pinMode(posLeds[0], OUTPUT);
    pinMode(posLeds[1], OUTPUT);
    pinMode(posLeds[2], OUTPUT);
    pinMode(posLeds[3], OUTPUT);
    pinMode(posLeds[4], OUTPUT);

    pinMode(8, OUTPUT);

    pinMode(pinInterruptor, INPUT);

    attachInterrupt(digitalPinToInterrupt(pinInterruptor), comprobarImpulsividad, RISING );

    //pinMode(pinInterruptor, OUTPUT);
}

void loop() {
   digitalWrite(pinInterruptor, LOW);
    delay(1000);
    nRepeticiones = calcularCicloLedAleatorio();
    ejecutarCiclo(nRepeticiones);
}

/*
* Devuelve un número aleatorio con valor n que se usará para encender n leds
*/
int calcularCicloLedAleatorio(){
    return random(1,6);
}

/*
* Ejecuta el loop del juego
* 1ºVa encendiendo los leds
*  1.1: si pulsa el botón antes de tiempo pierde
* 2º Una vez se han encendido todos espera
*  2.1: si no pulsa pierde
*  2.2: si sí pulsa gana
*    2.2.1: ejecutamos un ciclo con los leds que indica éxito
* 3º Apagamos los leds
*/
void ejecutarCiclo( int n ){
    // Vamos encendiendo los leds
    for ( int i = 0; i < n ;i++){
        if ( interrupt == true){
            digitalWrite(8,HIGH);
            delay(1000);
            digitalWrite(8,LOW);  
            finalizarCiclo(n);
            interrupt = false;
            return;
                  
        }
        
        digitalWrite(i+3,HIGH);
        delay(200);        
        // Si no se ha precipitado continuamos la ejecución
        delay(500);
    }
    
    // Hasta aquí se habrían encendido los leds correspondientes, esperamos 2 segundos al usuario
    delay(2000);
    if ( interrupt == true ){
        finalizarCiclo(n);
        ejecutarCicloExito();
    }
    
    finalizarCiclo(n);
    
}

void finalizarCiclo(int n){
    for ( int i = 0; i < n ;i++){
        digitalWrite(i+3,LOW);
    }
    delay(500);
}


void ejecutarCicloExito(){
    for ( int i = 0; i < 16; i++ ){
        delay(50);
        digitalWrite(posLeds[i%4], HIGH);
        delay(50);
        digitalWrite(posLeds[i%4], LOW);
    }
}


// Si usa el botón antes de tiempo fallará y encenderá el botón rojo
void comprobarImpulsividad() {
    interrupt = true;
    
}

