FROM openjdk:8-jdk-alpine
MAINTAINER FiapEcoviasChallenge
VOLUME /tmp
COPY ecovias-service.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]