FROM openjdk:17
LABEL maintainer="Dailan Bueno / daylansantos@gmail.com"
ENV PROFILE=prod
WORKDIR /app
COPY   target/sw-service-0.0.1-SNAPSHOT.jar /app/sw-service-app.jar
ENTRYPOINT ["java", "-jar", "sw-service-app.jar"]
EXPOSE 8100
