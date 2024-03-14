FROM adoptopenjdk/openjdk11:jre-11.0.22_7

COPY ./build/libs/*.jar ./app.jar
COPY ./build/resources/main/jmx/*.jar ./jmx.jar
COPY ./build/resources/main/jmx/config.yaml ./config.yaml

EXPOSE 8080
EXPOSE 9090

ENTRYPOINT ["java", "-javaagent:./jmx.jar=9090:./config.yaml", "-Dspring.profiles.active=stage", "-Xms128m", "-Xmx256m", "-jar","./app.jar"]
