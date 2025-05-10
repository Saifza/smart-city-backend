# Use a lightweight OpenJDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/waste-service-1.0-SNAPSHOT.jar app.jar

# Expose the service port 
EXPOSE 8082

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
