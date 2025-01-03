FROM openjdk:17-jdk-slim
COPY target/backend.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
