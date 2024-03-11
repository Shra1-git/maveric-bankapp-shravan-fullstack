FROM maven:latest AS builder
WORKDIR /usr/app-1
COPY . .
RUN mvn package
FROM openjdk:17
COPY --from=builder /usr/app-1/target/balance-service.jar .
EXPOSE 3015
CMD ["java", "-jar", "balance-service.jar"]
