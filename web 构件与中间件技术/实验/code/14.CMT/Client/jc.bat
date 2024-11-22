@echo off
echo javac -classpath "%%CLASSPATH%%;BankAppClient.jar" BankerClient.java
javac -classpath "%CLASSPATH%;BankAppClient.jar" BankerClient.java