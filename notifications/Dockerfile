FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/*.jar notificationService.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "notificationService.jar"]
