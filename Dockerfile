FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Cache deps via .m2 (optional, if BuildKit is on)
# RUN --mount=type=cache,target=/root/.m2 true

COPY pom.xml .
# (removed go-offline)

COPY src ./src
RUN mvn -B -q clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV JAVA_OPTS="-Xmx256m -Xms128m -XX:+UseSerialGC"
EXPOSE 9070
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
