FROM maven:latest AS builder
WORKDIR /usr/app-1
COPY . .
RUN mvn package
FROM openjdk:17
COPY --from=builder /usr/app-1/target/transaction-service.jar .
EXPOSE 3020
CMD ["java", "-jar", "transaction-service.jar"]
