# syntax=docker/dockerfile:1.7-labs

########################
# Build stage
########################
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# (optional) Make Maven honor container memory limits & be quiet but still show errors
ENV MAVEN_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

COPY pom.xml .

# Warm the local Maven cache (fast with BuildKit cache mount)
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -q package -DskipTests


# Now add sources (invalidates cache only when src changes)
COPY src ./src

# Actual build, cached .m2 between builds
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -q clean package -DskipTests

########################
# Runtime stage
########################
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the fat jar
COPY --from=build /app/target/*.jar app.jar

# Prefer container-aware memory flags automatically picked up by JVM
# You can still override at runtime with -e JAVA_TOOL_OPTIONS="..."
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75 -XX:+UseContainerSupport -XX:+UseSerialGC"

# If your app listens on 9070 (per your compose), expose it
EXPOSE 9070

# Use exec form; no shell needed
ENTRYPOINT ["java","-jar","/app/app.jar"]
