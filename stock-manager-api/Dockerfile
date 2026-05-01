# 🟡 BUILD STAGE
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# cache dépendances (très important)
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


# 🟢 RUNTIME STAGE (ultra léger)
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# uniquement le JAR final
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]