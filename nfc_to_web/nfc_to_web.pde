import processing.serial.*;

Serial myPort;  // The serial port

void setup() {
  // List all the available serial ports
  printArray(Serial.list());
  // Open the port you are using at the rate you want:
  myPort = new Serial(this, Serial.list()[0], 9600);
  loadStrings("http://sdh306.dothome.co.kr/?user=");
}

void draw() {
  while (myPort.available() > 0) {
    int inByte = myPort.read();
    if(inByte=='1'){
       loadStrings("http://sdh306.dothome.co.kr/?user=SonYongBin,01077618187");
    }else if(inByte=='2'){
      loadStrings("http://sdh306.dothome.co.kr/?user=ChoiTaeun,01055831834");
    }
  }
}
