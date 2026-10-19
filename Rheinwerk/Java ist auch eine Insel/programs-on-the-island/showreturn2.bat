@echo off
cd target\classes
java com.tutego.insel.array.SystemExitDemo
SET EXITCODE=%ERRORLEVEL%
dir > NUL:
echo %ERRORLEVEL%
echo %EXITCODE%
pause