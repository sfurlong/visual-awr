#!/bin/bash
export APP_HOME=.
export JAVA_OPTS=-Xms64M -Xmx256M
# export JAVA_OPTS=$JAVA_OPTS -DDEBUG

export CLASSPATH=
export CLASSPATH=$APP_HOME/lib/jcommon-1.0.22.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/jfreechart-1.0.17.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/vawr.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/vawr-o.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/vawr-utils.jar:$CLASSPATH
export CLASSPATH=$APP_HOME/lib/ojdbc5.jar:$CLASSPATH

java $JAVA_OPTS -DAPP_HOME=$APP_HOME org.altaprise.vawr.ui.RootFrame
