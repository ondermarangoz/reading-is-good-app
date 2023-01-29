FROM amazoncorretto:11-alpine-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["jdocker build --tag=reading-is-good-app:latest .ava","-jar","/app.jar"]
