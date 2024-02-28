FROM maven:3.9.6-amazoncorretto-21 as builder
LABEL authors="Haydn.Brierley-Jones"

WORKDIR /app
COPY src src
COPY pom.xml .

RUN mvn clean install spring-boot:repackage

FROM amazoncorretto:21.0.2-alpine3.19

ARG JAR_FILE=/app/target/*.jar
COPY --from=builder $JAR_FILE /app/runner.jar
EXPOSE 8080
ENTRYPOINT java -jar /app/runner.jar