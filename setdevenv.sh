# This Batch file is used to set all the environment files necessary to 
# run the eCorp, by Digital Artifacts, Inc.  This batch file should be
export APP_HOME=.
#To find out your java home, run this command: /usr/libexec/java_home --verbose
export JAVA_HOME=/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home
export MAVEN_HOME=/Users/stevefurlong/dev/maven/apache-maven-3.6.3
export JAVA_OPTS="-Xms64M -Xmx256M"
export ANT_HOME=/Users/stevefurlong/dev/ant

# ##########################################
export CLASSPATH=
export CLASSPATH=$APP_HOME/lib/dai.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/daiBeans.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/jcommon-1.0.22.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/jfreechart-1.0.17.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/jfreesvg-3.3.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/ojdbc5.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/ObfuscationAnnotation.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/commons-compress-1.8.1.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/junit-4.12.jar:$CLASSPATH
export PATH=$JAVA_HOME/bin:$APP_HOME/lib:$PATH
#export PATH=$ANT_HOME/bin:$PATH
export PATH=$MAVEN_HOME/bin:$PATH

# cd %APP_HOME%
echo Completed Environment Setup
