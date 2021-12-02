FROM maven:3.8.1-adoptopenjdk-11

COPY . .

RUN ["mvn", "clean"]
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:11.0.1-jdk-oracle

EXPOSE 8080

CMD ["java", "-jar", "payment-backend-service/target/payment-backend-service-2.0.jar"]
