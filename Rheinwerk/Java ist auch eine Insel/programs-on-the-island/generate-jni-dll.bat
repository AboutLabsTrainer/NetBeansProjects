@echo off
call "C:\Program Files (x86)\Microsoft Visual Studio\2017\BuildTools\VC\Auxiliary\Build\vcvarsall" amd64
@echo on
"C:\Program Files (x86)\Microsoft Visual Studio\2017\BuildTools\VC\Tools\MSVC\14.15.26726\bin\Hostx64\x64\cl"^
 -I"C:\Programme\java\jdk-11\include" -I"C:\Programme\java\jdk-11\include\win32"^
 /MD^
 "src\main\cpp\strlen.c"^
 /LD^
 -Festrlen.dll
