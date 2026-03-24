# ================================
# Stage 1: Build JAR với Maven
# ================================
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set work directory
WORKDIR /app

# Copy pom.xml và source code
COPY pom.xml .
COPY src ./src

# Build JAR (bỏ test để nhanh)
RUN mvn clean package -DskipTests

# ================================
# Stage 2: Chạy JAR
# ================================
FROM eclipse-temurin:21-jdk-alpine

# Tạo workdir trong container
WORKDIR /app

# Copy JAR từ stage builder
COPY --from=builder /app/target/nexcircle-0.0.1-SNAPSHOT.jar app.jar

# Mở port 8081
EXPOSE 8081

# Environment variables (tùy chỉnh nếu cần)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Run Spring Boot
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
