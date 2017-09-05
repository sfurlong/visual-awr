@echo off
@setlocal
REM This Batch file is used to set all the environment files necessary to 
REM ###########################################
REM ####  SET THESE TO YOUR WIN32 ENV SETTINGS
REM ###########################################
set APP_HOME=.
REM set JAVA_HOME=D:\oracle\Java\jdk1.7.0_40
set JAVA_OPTS=-Xms64M -Xmx512M
REM set JAVA_OPTS=%JAVA_OPTS% -DDEBUG
REM ###########################################


set CLASSPATH=

set CLASSPATH=%APP_HOME%\lib\jcommon-1.0.22.jar;%CLASSPATH%
set CLASSPATH=%APP_HOME%\lib\jfreechart-1.0.17.jar;%CLASSPATH%
set CLASSPATH=%APP_HOME%\lib\vawr.jar;%CLASSPATH%
set CLASSPATH=%APP_HOME%\lib\vawr-o.jar;%CLASSPATH%
set CLASSPATH=%APP_HOME%\lib\vawr-utils.jar;%CLASSPATH%
set CLASSPATH=%APP_HOME%\lib\ojdbc5.jar;%CLASSPATH%
set CLASSPATH=%APP_HOME%\lib\junit-4.12.jar;%CLASSPATH%

set path=%JAVA_HOME%\bin;%path%

start java %JAVA_OPTS% -DAPP_HOME=%APP_HOME% org.altaprise.vawr.ui.RootFrame
