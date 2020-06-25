FROM openjdk:11-jre-slim

ADD target/java-spring-api-1.0.0-SNAPSHOT.jar /srv/app/app.jar
COPY logback.xml /srv/app

WORKDIR /srv/app

ENTRYPOINT [ "java", "-Dlogging.config=file:/srv/app/logback.xml", "-jar", "app.jar" ]
