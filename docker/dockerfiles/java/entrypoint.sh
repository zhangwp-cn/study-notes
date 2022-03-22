#!/bin/bash

set -e

function memory_limit() {
 # local max_mem_unbounded="$(cat /sys/fs/cgroup/memory/memory.memsw.limit_in_bytes)"
  local mem_file="/sys/fs/cgroup/memory/memory.limit_in_bytes"
  local max_mem="$(cat ${mem_file})"
  echo "${max_mem}"
}

function ceiling() {
  awk -vnumber="$1" -vdiv="$2" '
    function ceiling(x){
      return x%1 ? int(x)+1 : x
    }
    BEGIN{
      print ceiling(number/div)
    }
  '
}

function core_limit() {
  local cpu_period_file="/sys/fs/cgroup/cpu/cpu.cfs_period_us"
  local cpu_quota_file="/sys/fs/cgroup/cpu/cpu.cfs_quota_us"
  if [ -r "${cpu_period_file}" ]; then
    local cpu_period="$(cat ${cpu_period_file})"

    if [ -r "${cpu_quota_file}" ]; then
      local cpu_quota="$(cat ${cpu_quota_file})"
      # cfs_quota_us == -1 --> no restrictions
      if [ "x$cpu_quota" != "x-1" ]; then
        ceiling "$cpu_quota" "$cpu_period"
      fi
    fi
  fi
}

function memory_tunning() {
  if echo "${JAVA_OPTS}" | grep -q -- "-Xmx"; then
    return
  fi

  local max_mem="$(memory_limit)"
  if [ "x${max_mem}" != "x" ]; then
    local ratio=25
    local max_mem_mb=$(echo "${max_mem} 1048576" | awk '{printf "%d\n", $1/$2 + 0.5}')
    local mx=$(echo "${max_mem} ${ratio} 1048576" | awk '{printf "%d\n", ($1*$2)/(100*$3) + 0.5}')
    local mn=$(expr ${mx} '*' 3 / 8)
    echo "-Xms${mx}m -Xmx${mx}m -Xmn${mn}m"
  fi
}

function diagnostics() {
  echo "-XX:NativeMemoryTracking=summary"\
    #"-XX:+PrintGC"\
    #"-XX:+PrintGCDateStamps"\
    #"-XX:+PrintGCTimeStamps"\
    #"-XX:+UnlockDiagnosticVMOptions "
}

function cpu_core_tunning() {
  local max_core="$(core_limit)"
  if [ "x${max_core}" != "x" ]; then
    echo "-XX:-UseGCOverheadLimit"\
      "-XX:ParallelGCThreads=${max_core}"\
      "-XX:ConcGCThreads=${max_core}"\
      "-Djava.util.concurrent.ForkJoinPool.common.parallelism=${max_core}"
  fi
}

function tunning() {
  echo "-server"\
    "-Duser.timezone=GMT+08"\
    "-Djava.security.egd=file:/dev/./urandom"\
    "-Xss256k"\
    "-XX:MaxMetaspaceSize=128m"\
    "-XX:MaxDirectMemorySize=64m"\
    "-XX:+UseParallelGC"\
    "$(cpu_core_tunning)"\
    "$(memory_tunning)"
}

function debug() {
  if [ "x${JAVA_DEBUG}" != "xfalse" ]; then
    echo "-Xdebug"\
      "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=${JAVA_DEBUG_PORT}"
  fi
}

export JAVA_OPTS="$(diagnostics) $(tunning) $(debug) ${JAVA_OPTS}"

echo "JAVA_OPTS = ${JAVA_OPTS}"

exec "$@"
