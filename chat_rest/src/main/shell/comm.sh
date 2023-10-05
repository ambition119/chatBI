#!/bin/sh

# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

COMMAND="$1"

# Get standard environment variables
PRG_DIR=`dirname "$PRG"`
PLATFORM_HOME=`cd "$PRG_DIR/.." >/dev/null; pwd`
export PLATFORM_HOME

PLATFORM_CONF_DIR="$PLATFORM_HOME/config"
PLATFORM_LIB_DIR="$PLATFORM_HOME/lib"
export PLATFORM_LIB_DIR

LIB_JARS=`ls $PLATFORM_LIB_DIR | grep -E '.jar$' | awk '{print "'$PLATFORM_LIB_DIR'/"$0}' | tr "\n" ":"`

PRO_NAME=${project.artifactId}
PIDFILE="$PLATFORM_HOME/$(basename $PRO_NAME).pid"
PID=0
if [[ -f $PIDFILE ]]; then
  PID=`cat $PIDFILE`
fi

JAVA_OPTS=""
JAVA_MEM_OPTS=""
JAVA_SPRING_OPTS=" --spring.profiles.active=${env}"

PLATFORM_MAIN_CLASS=${start-class}


running() {
  if [[ -z $1 || $1 == 0 ]]; then
    echo 0
    return
  fi
  if ps -p $1 > /dev/null; then
    echo 1
    return
  fi
  echo 0
  return
}

set_env() {
    if [ -r "$PLATFORM_HOME/bin/setenv.sh" ]; then
      source "$PLATFORM_HOME/bin/setenv.sh"
    else
      JAVA_MEM_OPTS=" -server -Xms4g -Xmx4g -XX:SurvivorRatio=2 -XX:+UseParallelGC "
    fi
}

start() {
  if [[ $(running $PID) != 0 ]]; then
    echo "$PRO_NAME is running"
    return
  fi
  echo "### starting $PRO_NAME `date '+%Y-%m-%d %H:%M:%S'` ###" >> /dev/null 2>&1 &
  set_env
  echo "$JAVA_HOME/bin/java $JAVA_OPTS $JAVA_MEM_OPTS -classpath $PLATFORM_CONF_DIR:$LIB_JARS $PLATFORM_MAIN_CLASS $JAVA_SPRING_OPTS"
  START_CMD="$JAVA_HOME/bin/java $JAVA_OPTS $JAVA_MEM_OPTS -classpath $PLATFORM_CONF_DIR:$LIB_JARS $PLATFORM_MAIN_CLASS $JAVA_SPRING_OPTS"
  print_env
  nohup $START_CMD >> $PLATFORM_HOME/server.log 2>&1 &
  if [[ $(running $!) == 0 ]]; then
    echo "failed to start $PRO_NAME"
    exit 1
  fi
  PID=$!
  echo $! > $PIDFILE
#  echo "new pid $!"
}

stop() {
  if [[ $(running $PID) == 0 ]]; then
    echo "no $PRO_NAME is running"
    return
  fi
  echo "stopping $PID of $PRO_NAME ..."
  kill $PID
}

restart() {
  stop
  start
}

print_env() {
  echo "JRE_HOME:               $JAVA_HOME"
  echo "ENV:                    ${env}"
  echo "JAVA_OPTS:              $JAVA_OPTS"
  echo "JAVA_MEM_OPTS:          $JAVA_MEM_OPTS"
  echo "JAVA_SPRING_OPTS:       $JAVA_SPRING_OPTS"
  echo "START_CMD:              $START_CMD"
}

print_usage() {
  echo "Usage: label.sh (start|stop)"
}

case $COMMAND in

(start)
  start
  ;;

(stop)
  stop
  ;;

(*)
  print_usage
  exit 1
  ;;
esac