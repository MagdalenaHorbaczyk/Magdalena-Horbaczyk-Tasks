call runcrud
if "%ERRORLEVEL%" == "0" goto gettasks
echo.
echo runcrud.bat has errors - breaking work
goto fail

:gettasks
start mozilla "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open Mozilla and show tasks - breaking work

:fail
echo.
echo There were errors

:end
echo.
echo Showtasks work is finished.
