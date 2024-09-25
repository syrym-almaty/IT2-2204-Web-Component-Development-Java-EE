# Use an official Maven image to build the project
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Build the application (this will download all dependencies automatically)
RUN mvn clean package -DskipTests

# Use a smaller OpenJDK image to run the app
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file from the previous stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar

# Expose the application port (Spring Boot uses 8080 by default)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]
