FROM java:8-jre-alpine
MAINTAINER Raoni Gabriel

ARG JAR_FILE

RUN apk update --no-cache && apk upgrade
ADD target/${JAR_FILE} /spark-qrcode-example.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/spark-qrcode-example.jar"]