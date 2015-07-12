# This Batch file is used to set all the environment files necessary to 
# run the eCorp, by Digital Artifacts, Inc.  This batch file should be
# used on Win32 platforms only.


APP_HOME=.
JAVA_HOME=/usr/bin
JAVA_OPTS="-Xms64M -Xmx256M"
ANT_HOME=/Users/sfurlong/eclipse/plugins/org.apache.ant_1.9.2.v201404171502
# ##########################################


CLASSPATH=

CLASSPATH=$APP_HOME/lib/dai.jar:%CLASSPATH%
CLASSPATH=$APP_HOME/lib/daiBeans.jar:$CLASSPATH
CLASSPATH=$APP_HOME/lib/jcommon-1.0.22.jar:$CLASSPATH
CLASSPATH=$APP_HOME/lib/jfreechart-1.0.17.jar:$CLASSPATH
CLASSPATH=$APP_HOME/lib/ojdbc5.jar:$CLASSPATH
CLASSPATH=$APP_HOME/lib/ObfuscationAnnotation.jar:$CLASSPATH
CLASSPATH=$APP_HOME/lib/commons-compress-1.8.1.jar:$CLASSPATH

PATH=$JAVA_HOME/bin:$APP_HOME/lib:$PATH

PATH=$ANT_HOME/bin:$PATH

# cd %APP_HOME%


echo Completed Environment Setup
