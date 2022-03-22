FROM openjdk:8-jdk-slim

RUN apt update \
    && apt install -y  tzdata bash curl libc6 telnet vim net-tools \
    && apt clean \
    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone

ENV JAVA_OPTS="" JAVA_DEBUG="false" JAVA_DEBUG_PORT="8000"
#ENV LC_ALL="en_US.UTF-8" LANG="en_US.UTF-8"

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]

