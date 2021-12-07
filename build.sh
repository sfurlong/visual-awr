#  ***** Builds the VisualAWR project *****
# 
#    Author:         Stephen Furlong
#    Last updated:   May 8, 2015
# 
#    This script starts building the Visual AWR packages, using Jakarta-ANT as
#    the build tool.
# 
#    The script expects the following environment variables to be set prior
#    to execution:
# 
#        JAVA_HOME     Home directory for current version of JDK (also used by ANT)
#        ANT_HOME      Home directory of Ant
#        APP_HOME      Root of the development environment (ie. d:\release\v71)
# 

#   Verify that all required variables are set
# if "%JAVA_HOME%"=="" goto badvar
# if "%ANT_HOME%"=="" goto badvar
# if "%APP_HOME%"=="" goto badvar

echo $CLASSPATH
echo $APP_HOME
$ANT_HOME/bin/ant -Ddebug="on" -Djdk.home=$JAVA_HOME -Dbuild.root.dir=$APP_HOME -buildfile $APP_HOME/build_vawr.xml $1 $2 $3 $4 

# :badvar
#echo Error - One or more required environment variables not set!
# echo.
# if "%JAVA_HOME%"=="" set JAVA_HOME
# if "%ANT_HOME%"=="" set ANT_HOME
# if "%APP_HOME%"=="" set DEV_ROOT
# goto end

#:help
#echo Builds the masterplanning project.
#echo.
#echo BUILD target
#echo.
#echo or
#echo
#echo BUILD -projecthelp
#echo.
#echo This show all available targets.
#echo.
#:end
