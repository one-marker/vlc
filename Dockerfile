FROM openjdk:17-jdk-slim-buster

WORKDIR /app

COPY ./target/vlc-redirect-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 6060

ENTRYPOINT java -jar app.jar