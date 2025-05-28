# ---- Stage 1: Build the JAR using a Gradle container ----
FROM gradle:8.7-jdk21 AS builder

# Copy source code
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project

# Build the Spring Boot fat JAR
RUN gradle bootJar --no-daemon

# ---- Stage 2: Create minimal runtime image using distroless ----
FROM gcr.io/distroless/java21-debian12

# Copy the fat JAR from the builder image
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
