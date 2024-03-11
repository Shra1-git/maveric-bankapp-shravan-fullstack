FROM maven:latest AS builder
WORKDIR /usr/app-1
COPY . .
RUN mvn package
FROM openjdk:17
COPY --from=builder /usr/app-1/target/eureka-service.jar .
EXPOSE 8761
CMD ["java", "-jar", "eureka-service.jar"]
