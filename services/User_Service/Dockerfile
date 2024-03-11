FROM maven:latest AS builder
WORKDIR /usr/app-1
COPY . .
RUN mvn package
FROM openjdk:17
COPY --from=builder /usr/app-1/target/user-service.jar .
EXPOSE 3005
CMD ["java", "-jar", "user-service.jar"]
