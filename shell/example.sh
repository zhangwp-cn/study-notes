#!/bin/bash

# 设置文件根路径
if [ ${0:0:1} == '/' ]; then
    ROOT_PATH=$(dirname $0)
else
    ROOT_PATH=$(dirname $(pwd)/$0)
fi

echo ${ROOT_PATH}
cd ${ROOT_PATH}



sleep 10