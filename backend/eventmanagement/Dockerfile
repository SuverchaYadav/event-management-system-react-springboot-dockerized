# backend/Dockerfile
FROM eclipse-temurin:21-jdk as build

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
