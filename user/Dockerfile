FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/*.jar userService.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "userService.jar"]

