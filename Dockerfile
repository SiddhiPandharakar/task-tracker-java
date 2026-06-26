# ============================
# Build Stage
# ============================

FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests



# ============================
# Runtime Stage
# ============================

FROM eclipse-temurin:21.0.7_6-jre


RUN useradd \
    --create-home \
    --shell /bin/bash \
    appuser


WORKDIR /app


COPY --from=builder /app/target/*.jar app.jar


USER appuser


EXPOSE 8080


ENTRYPOINT ["java","-jar","app.jar"]