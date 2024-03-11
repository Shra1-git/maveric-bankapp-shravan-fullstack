FROM maven:latest AS builder
WORKDIR /usr/app-1
COPY . .
RUN mvn package
FROM openjdk:17
COPY --from=builder /usr/app-1/target/account-service.jar .
EXPOSE 3010
CMD ["java", "-jar", "account-service.jar"]
