FROM adoptopenjdk/openjdk11:jre-11.0.22_7

ARG PROFILE

ENV PROFILE=$PROFILE

COPY *.jar ./app.jar
COPY ./jmx/*.jar ./jmx.jar
COPY ./jmx/config.yaml ./config.yaml

EXPOSE 8080
EXPOSE 9090
EXPOSE 3306

ENTRYPOINT ["java", "-javaagent:./jmx.jar=9090:./config.yaml", "-Dspring.profiles.active=$PROFILE", "-Xms128m", "-Xmx256m", "-jar","./app.jar"]
