FROM eclipse-temurin:11-alpine

WORKDIR /app

ARG JAR_FILE=./sol-client/sol-app.jar

COPY ${JAR_FILE} sol-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/sol-app.jar"]
