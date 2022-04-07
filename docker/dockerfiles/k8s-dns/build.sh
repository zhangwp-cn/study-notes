#!/bin/bash

set -e

if [ ${0:0:1} == '/' ]; then
    CD=$(dirname $0)
else
    CD=$(dirname $(pwd)/$0)
fi
cd $CD

tag="1.14.4"

repo_base="test"
repos=("${repo_base}/k8s-dns" "${repo_base}/k8s-dns-dnsmasq" "${repo_base}/k8s-dns-sidecar")

origin_base="gcr.io/google_containers/k8s-dns"
origins=("${origin_base}-kube-dns-amd64" "${origin_base}-dnsmasq-nanny-amd64" "${origin_base}-sidecar-amd64")

for i in $(seq 0 $((${#repos[@]}-1))); do
    docker pull ${origins[$i]}:${tag}
    docker tag ${origins[$i]}:${tag} ${repos[$i]}:v${tag}
    docker push ${repos[$i]}:v${tag}
done
