FROM openjdk:17-jdk-alpine

RUN apk add --no-cache \
fontconfig \
freetype \
libxrender \
libxtst \
libxi \
ttf-dejavu

RUN mkdir /app

WORKDIR /app

COPY target/*.jar /app/app.jar

EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]