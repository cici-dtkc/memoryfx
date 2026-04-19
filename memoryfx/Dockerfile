# Stage 1: Build
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-jammy
RUN apt-get update && apt-get install -y \
    xvfb \
    tigervnc-standalone-server \
    websockify \
    novnc \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY --from=builder /app/target/memoryfx-*.jar app.jar
COPY start.sh .
RUN chmod +x start.sh

EXPOSE 5901 6080

HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
    CMD xdpyinfo -display :99 || exit 1

ENTRYPOINT ["./start.sh"]
