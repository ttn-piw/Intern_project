FROM openjdk:11.0.16-jre-slim-buster

RUN

ADD build/libs/svc-basecat-1.0.0.jar svc-basecat-1.0.0.jar

EXPOSE 8080
ENTRYPOINT ["java", "-javaagent:./opentelemetry-javaagent.jar", "-jar", "svc-basecat-1.0.0.jar"]