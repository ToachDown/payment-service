FROM maven:3.8.1-adoptopenjdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests



FROM openjdk:11.0.1-jdk-oracle
COPY --from=build jars .
EXPOSE 8080
CMD ["java", "-jar", "payment-backend-service.jar"]
