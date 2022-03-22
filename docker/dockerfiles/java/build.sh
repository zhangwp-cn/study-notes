#!/bin/bash


set -e

if [ ${0:0:1} == '/' ]; then
    CD=$(dirname $0)
else
    CD=$(dirname $(pwd)/$0)
fi
cd $CD

repo="test/java"

tag="v1.0"

docker build -t ${repo}:${tag} $@ .
docker push ${repo}:${tag}

