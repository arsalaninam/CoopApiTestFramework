# Use an official OpenJDK runtime as a parent image
FROM openjdk:8-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml and src directories to the container
COPY pom.xml .
COPY src/ ./src/
COPY testng.xml .

# Install Maven
RUN apk add --no-cache curl tar bash && \
    curl -fL https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz | tar xvz -C /usr/share && \
    ln -s /usr/share/apache-maven-3.6.3/bin/mvn /usr/bin/mvn

# Build the application
RUN mvn clean package

# Copy the built JAR file and results to the working directory
COPY target/ .