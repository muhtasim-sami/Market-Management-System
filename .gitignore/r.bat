cd..
cd scr
set path = C:\Program Files\Java\jdk-22\bin
javac -d ../bin frame\*.java  management\security\*.java management\employee\*.java management\product\*.java
cd ../bin
java frame.Login
pause