FROM adoptopenjdk/openjdk11:jre-11.0.22_7
COPY *.jar .
CMD java -Xms128m -Xmx256m -Dspring.profiles.active=stage -jar *.jar
