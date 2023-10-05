#!/bin/sh
PRG="$0"
PRGDIR=`dirname "$PRG"`
PLATFORM_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`
EXECUTABLE=comm.sh
exec "$PRGDIR"/"$EXECUTABLE" stop "$@"
