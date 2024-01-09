FROM openjdk:17-alpine
WORKDIR /app
COPY target/GlovoDeliveryAPI-0.0.1-SNAPSHOT.jar glovodeliveryapi.jar
ENTRYPOINT ["java", "-jar", "glovodeliveryapi.jar"]