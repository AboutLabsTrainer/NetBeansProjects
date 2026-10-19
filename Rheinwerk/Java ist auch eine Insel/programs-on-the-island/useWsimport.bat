PATH=%PATH%;C:\Program Files\Java\jdk-21\bin

wsimport -s src\main\java -p com.tutego.insel.ws.gen.geoipservice http://www.webservicex.net/geoipservice.asmx?WSDL
REM wsimport -s src\main\java -keep -p com.tutego.insel.ws.gen.chrisws http://localhost:8080/services?wsdl