FROM openjdk:8-jre-slim-stretch

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS=""

# Add a exchangerate user to run our application so that it doesn't need to run as root
RUN adduser --disabled-password --shell /bin/sh exchangerate
WORKDIR /home/exchangerate

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown exchangerate:exchangerate entrypoint.sh
USER exchangerate

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080 5701/udp

ADD target/*.jar app.jar

