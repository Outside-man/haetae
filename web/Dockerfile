FROM openjdk:8-jre-slim-stretch
MAINTAINER  NathanDai "daizheng1998@qq.com"

ADD target/haetae-web-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8000
ENTRYPOINT [ "sh", "-c","java -jar app.jar"]