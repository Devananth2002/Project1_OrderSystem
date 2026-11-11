FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/*.jar orderService.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "orderService.jar"]
