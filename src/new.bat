@echo off
rem copies  the Clock to end of ini file.
cls
ren clock.ini clock.bak
command /c copy clock.bak +new.ini clock.ini >nul
command /c del clock.bak
command /c del new.ini
command /c del new.bat