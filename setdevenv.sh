# This Batch file is used to set all the environment files necessary to 
# run the eCorp, by Digital Artifacts, Inc.  This batch file should be
# used on Win32 platforms only.
export APP_HOME=.
export JAVA_HOME=/usr
export JAVA_OPTS="-Xms64M -Xmx256M"
export ANT_HOME=/Users/furlons/Documents/DEV/ant

# ##########################################
export CLASSPATH=
export CLASSPATH=$APP_HOME/lib/dai.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/daiBeans.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/jcommon-1.0.22.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/jfreechart-1.0.17.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/ojdbc5.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/ObfuscationAnnotation.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/commons-compress-1.8.1.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/junit-4.12.jar:$CLASSPATH
export PATH=$JAVA_HOME/bin:$APP_HOME/lib:$PATH
export PATH=$ANT_HOME/bin:$PATH

# cd %APP_HOME%
echo Completed Environment Setup
