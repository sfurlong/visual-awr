@echo off
rem ***** Builds the MicroERP project *****
rem
rem   Author:         Stephen Furlong
rem   Last updated:   March 19, 2003
rem
rem   This script starts building the MicroERP packages, using Jakarta-ANT as
rem   the build tool.
rem
rem   The script expects the following environment variables to be set prior
rem   to execution:
rem
rem       JAVA_HOME     Home directory for current version of JDK (also used by ANT)
rem       ANT_HOME      Home directory of Ant
rem       APP_HOME      Root of the development environment (ie. d:\release\v71)
rem

@setlocal

rem  Verify that all required variables are set
if "%JAVA_HOME%"=="" goto badvar
if "%ANT_HOME%"=="" goto badvar
if "%APP_HOME%"=="" goto badvar


echo %CLASSPATH%

call ant -Ddebug="on" -Djdk.home=%JAVA_HOME% -Dbuild.root.dir=%APP_HOME% -buildfile %APP_HOME%\build_vawr.xml %1 %2 %3 %4 
goto end


:badvar
echo Error - One or more required environment variables not set!
echo.
if "%JAVA_HOME%"=="" set JAVA_HOME
if "%ANT_HOME%"=="" set ANT_HOME
if "%APP_HOME%"=="" set DEV_ROOT
goto end


:help
echo Builds the masterplanning project.
echo.
echo BUILD target
echo.
echo or
echo
echo BUILD -projecthelp
echo.
echo This show all available targets.
echo.


:end
@endlocal