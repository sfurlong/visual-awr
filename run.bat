@echo off
@setlocal
REM This Batch file is used to set all the environment files necessary to 
REM ###########################################
REM ####  SET THESE TO YOUR WIN32 ENV SETTINGS
REM ###########################################
set APP_HOME=.
rem set JAVA_HOME=D:\oracle\Java\jdk1.7.0_40
set JAVA_OPTS=-Xms64M -Xmx256M
REM ###########################################


set CLASSPATH=

set CLASSPATH=%APP_HOME%\lib\jcommon-1.0.22.jar;%CLASSPATH%
set CLASSPATH=%APP_HOME%\lib\jfreechart-1.0.17.jar;%CLASSPATH%
set CLASSPATH=%APP_HOME%\lib\vawr.jar;%CLASSPATH%
set CLASSPATH=%APP_HOME%\lib\ojdbc5.jar;%CLASSPATH%

set path=%JAVA_HOME%\bin;%APP_HOME%\lib;%path%

start java %JAVA_OPTS% -DAPP_HOME=%APP_HOME% org.altaprise.vawr.ui.RootFrame
