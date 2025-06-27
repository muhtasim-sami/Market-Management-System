cd ../src
javac -cp ".;../lib/ojdbc11.jar" -d ../bin frame\*.java management\validation\*.java management\employee\*.java management\product\*.java management\shop\*.java
cd ../bin
java -cp ".;../lib/ojdbc11.jar" frame.Homepage
pause