FROM openjdk:22

COPY target/opioid.war opioid.war

EXPOSE 8081

ENTRYPOINT ["java", "war", "/opioid.war"]