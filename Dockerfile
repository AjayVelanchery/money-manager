# ---------- Stage 1: Build the application ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory inside container
WORKDIR /app

# Copy only pom.xml first (for dependency caching)
COPY pom.xml ./

# Pre-download dependencies to speed up subsequent builds
RUN mvn dependency:go-offline -B

# Now copy the rest of the source code
COPY src ./src

# Package the application (skip tests to save time)
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run the application ----------
FROM eclipse-temurin:21-jre

# Set working directory for runtime
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/moneymanager-0.0.1-SNAPSHOT.jar app.jar

# Expose your application's port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
