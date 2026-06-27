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


FROM eclipse-temurin:21.0.8_9-jre-alpine



RUN addgroup -S appgroup && \
    adduser -S appuser -G appgroup



WORKDIR /app



COPY --from=builder \
/app/target/*.jar \
app.jar



RUN chown appuser:appgroup app.jar



USER appuser



EXPOSE 8080



ENTRYPOINT ["java","-jar","app.jar"]