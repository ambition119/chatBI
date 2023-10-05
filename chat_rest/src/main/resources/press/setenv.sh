#!/bin/sh
PRG="$0"
PRGDIR=`dirname "$PRG"`
BASE_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`

#server内存配置
export JAVA_MEM_OPTS="$JAVA_MEM_OPTS -server -Xms4G -Xmx4G -Xmn2G -XX:PermSize=256M -XX:MaxPermSize=512M -Xss1M -XX:SurvivorRatio=8"

#gc
export JAVA_MEM_OPTS="$JAVA_MEM_OPTS -XX:MaxTenuringThreshold=4 -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=70 -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=2 -XX:+ExplicitGCInvokesConcurrent  -XX:+CMSScavengeBeforeRemark"

#gc日志
export JAVA_MEM_OPTS="$JAVA_MEM_OPTS -Xloggc:$BASE_HOME/logs/jvm/gc.log.`date +%Y-%m-%d_%H_%M_%S` \
 -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime -XX:PrintFLSStatistics=1"

#内存溢出
export JAVA_MEM_OPTS="$JAVA_MEM_OPTS -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$BASE_HOME/logs/heapdump.hprof"
