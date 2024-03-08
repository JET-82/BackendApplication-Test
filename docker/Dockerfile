FROM adoptopenjdk/openjdk11:jre-11.0.22_7
COPY *.jar .
COPY ./jmx/* ./jmx/
EXPOSE 8080
EXPOSE 9090

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-Xms128m", "-Xmx256m", "-javaagent:./jmx/*.jar=9090:./jmx/config.yaml","-jar","*.jar"]
