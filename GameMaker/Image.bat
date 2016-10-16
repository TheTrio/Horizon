cd ..
cd JavaGame-master
cd bin
cd Images

FOR /f "tokens=1,2" %%a IN ('"wmic desktopmonitor get screenheight, screenwidth"') DO (
    SET /a ScreenHeight=%%a
    SET /a ScreenWidth=%%b
)

echo %ScreenWidth%
echo %ScreenHeight%

if %ScreenHeight% EQU 720 (
rpcmd.exe -s 720.set -i %cd%\*.jpg -o %cd%
exit
)

if %ScreenHeight% EQU 768 (
rpcmd.exe -s 768.set -i %cd%\*.jpg -o %cd%
exit
)

if %ScreenHeight% EQU 900 (
rpcmd.exe -s 900.set -i %cd%\*.jpg -o %cd%
exit
)

if %ScreenHeight% EQU 1080 (
rpcmd.exe -s 1080.set -i %cd%\*.jpg -o %cd%
exit
)

if %ScreenWidth% EQU 1024 (
rpcmd.exe -s 1024.set -i %cd%\*.jpg -o %cd%
)


exit