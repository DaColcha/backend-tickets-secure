
# Usamos una imagen de Maven para construir el proyecto
FROM maven:3.9.9-amazoncorretto-17-alpine AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Usamos una imagen más ligera para ejecutar el JAR
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp

ENV MYSQLDB_HOST=${DB_HOST}
ENV MYSQLDB_USER=${DB_USER}
ENV MYSQLDB_PASSWORD=${DB_PASSWORD}

COPY --from=build /app/target/*.jar backend-tickets.jar

ENTRYPOINT ["java", "-jar", "/backend-tickets.jar"]
EXPOSE 8080
