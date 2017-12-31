# This Batch file is used to set all the environment files necessary to 
###########################################
####  SET THESE TO YOUR WIN32 ENV SETTINGS
###########################################
export APP_HOME=.
export JAVA_HOME=/usr
export JAVA_OPTS=
# set JAVA_OPTS=%JAVA_OPTS% -DDEBUG
###########################################
export CLASSPATH=
export CLASSPATH=$APP_HOME/lib/jcommon-1.0.22.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/jfreechart-1.0.17.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/vawr.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/vawr-o.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/vawr-utils.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/ojdbc5.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/junit-4.12.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/hamcrest-junit-2.0.0.0.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/java-hamcrest-2.0.0.0.jar:$CLASSPATH
export PATH=$JAVA_HOME/bin:$PATH
echo $APP_HOME
echo $CLASSPATH
echo $PATH
java $JAVA_OPTS -DAPP_HOME=$APP_HOME org.junit.runner.JUnitCore org.altaprise.test.TestDemoFilesAndCharts > junit.out
