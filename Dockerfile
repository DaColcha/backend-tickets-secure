
# Usamos una imagen de Maven para construir el proyecto
FROM maven:3.9.9-amazoncorretto-17-alpine AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY .env .env

RUN mvn clean package -DskipTests

# Usamos una imagen más ligera para ejecutar el JAR
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp

COPY --from=build /app/target/*.jar backend-tickets.jar
COPY .env .env

ENTRYPOINT ["java", "-jar", "/backend-tickets.jar"]
EXPOSE 8080
