# Use a known available Maven image
FROM maven:3.8.4-openjdk-11-slim AS build
 
# Set the working directory inside the container
WORKDIR /app
 
# Copy the Maven project files to the container
COPY pom.xml ./
COPY src ./src
 
# Build the project and package it into a JAR file
RUN mvn clean package -DskipTests
 
# Use a lightweight OpenJDK image to run the application
FROM openjdk:17-jdk-alpine
 
# Set the working directory inside the container
WORKDIR /app
 
# Copy the JAR file from the build stage to the runtime container
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/app.jar
 
# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
