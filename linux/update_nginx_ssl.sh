#!/bin/bash

SSL_LOCAL_PATH=/etc/nginx/conf.d/ssl/

SSL_SERVER_ROOT=https://ssl.server/

#DOMAIN_LIST="test.baidu.com"


### 批量自动更新
DOMAIN_LIST="test.com"
for DOMAIN in ${DOMAIN_LIST}
do
    echo "${DOMAIN}  开始检查"

    END_TIME=$(echo | timeout 1 openssl s_client -servername all.${DOMAIN} -connect 127.0.0.1:443 2>/dev/null | openssl x509 -noout -enddate 2>/dev/null | awk -F '=' '{print $2}')    
    END_TIMESTAMP=`date -d "${END_TIME}" +%s`

    CURRENT_TIMESTAMP=`date -d "$(date -u '+%b %d %T %Y GMT') " +%s`
    let LEFT_SECONDS=${END_TIMESTAMP}-${CURRENT_TIMESTAMP}

    LEFT_DAYS=`expr ${LEFT_SECONDS} / 86400`
    echo "${DOMAIN} 证书剩余有效天数: ${LEFT_DAYS}"


    if [ ${LEFT_DAYS} -lt 15 ] 
    then
        echo "证书有效期不足 15 天，准备更新"

        wget -O ${SSL_LOCAL_PATH}/all.${DOMAIN}.crt ${SSL_SERVER_ROOT}/${DOMAIN}/fullchain.pem
        wget -O ${SSL_LOCAL_PATH}/all.${DOMAIN}.key ${SSL_SERVER_ROOT}/${DOMAIN}/privkey.pem      
        
        if [ ${LEFT_DAYS} -lt 10 ]
        then
            echo "证书有效期不足 10 天，请及时更新"
            
        fi
    fi

    echo -e "${DOMAIN}  检查完成\n"

done

/usr/sbin/nginx -s reload

