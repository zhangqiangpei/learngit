@echo off
cd ..
del pom.xml.releaseBackup
del release.properties
call mvn clean
pause