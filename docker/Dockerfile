FROM adoptopenjdk/openjdk11:jre-11.0.22_7
COPY *.jar ./app.jar
COPY ./jmx/*.jar ./jmx/jmx.jar
COPY ./jmx/config.yaml ./jmx/config.yaml
EXPOSE 8080
EXPOSE 9090

#CMD java -javaagent:./jmx/jmx.jar=9090:./jmx/config.yaml -Dspring.profiles.active=dev -Xms128m -Xmx256m -jar ./app.jar
ENTRYPOINT ["java", "-javaagent:./jmx/jmx.jar=9090:./jmx/config.yaml", "-Dspring.profiles.active=dev", "-Xms128m", "-Xmx256m", "-jar","app.jar"]
