FROM maven:latest AS builder
WORKDIR /usr/app-1
COPY . .
RUN mvn package
FROM openjdk:17
COPY --from=builder /usr/app-1/target/gateway-service.jar .
EXPOSE 8000
CMD ["java", "-jar", "gateway-service.jar"]
