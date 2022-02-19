#!/bin/bash

LOGS_PATH=/opt/nginx_logs/

#YESTERDAY=$(date +%Y-%m-%d)
YESTERDAY=$(date -d yesterday +%Y-%m-%d)


### access.log
cp ${LOGS_PATH}/access.log ${LOGS_PATH}/access_${YESTERDAY}.log
echo > ${LOGS_PATH}/access.log

sleep 1

gzip ${LOGS_PATH}/access_${YESTERDAY}.log

# 最多保留30天的日志备份
find ${LOGS_PATH} -type f -name "access_*.log.gz" -ctime +30 -exec rm -f {} \;


### error.log
### 注：error.log 日志在 /var/log/nginx/ 下，已被自动切割备份
#cp ${LOGS_PATH}/error.log ${LOGS_PATH}/error_${YESTERDAY}.log
#echo > ${LOGS_PATH}/error.log

#sleep 1

#gzip ${LOGS_PATH}/error_${YESTERDAY}.log
