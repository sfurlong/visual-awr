# This Batch file is used to set all the environment files necessary to 
# run the eCorp, by Digital Artifacts, Inc.  This batch file should be
# used on Win32 platforms only.
export APP_HOME=.

# Java 12. (Doesn't work, sun.*.* not supported.)
export JAVA_HOME=/e/jdk-12.0.1

# Java 8.
#export JAVA_HOME=/e/jdk-1.8.0-241

# Java 6 (Doesn't work with ANT!)
#export JAVA_HOME=/e/app/client/hisg494/product/12.1.0/client_1/jdk
export JAVA_OPTS="-Xms64M -Xmx256M"
export ANT_HOME=/e/apache-ant-1.10.7

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
