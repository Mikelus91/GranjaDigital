# Etapa 1: construir el proyecto con Maven
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

# Etapa 2: ejecutar la aplicación
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/GranjaDigital-1.0.jar app.jar
COPY config.properties config.properties
CMD ["java", "-jar", "app.jar"]
