FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/*.jar productService.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "productService.jar"]
