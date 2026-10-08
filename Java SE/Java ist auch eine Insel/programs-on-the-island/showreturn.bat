@echo off
cd target\classes
java com.tutego.insel.array.SystemExitDemo
echo %ERRORLEVEL%
if errorlevel 10 (
   echo Exit-Code ist über 10, genau %ERRORLEVEL%
)
pause