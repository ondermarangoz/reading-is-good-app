FROM amazoncorretto:11-alpine-jdk
ENTRYPOINT ["java","-jar","/message-server-1.0.0.jar"]