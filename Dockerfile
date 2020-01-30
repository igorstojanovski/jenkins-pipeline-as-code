FROM openjdk:11-stretch

COPY build/libs/pipeline-as-code-demo-0.0.1-SNAPSHOT.jar /home/root/application.jar

ENTRYPOINT [ "java", "-jar", "/home/root/application.jar" ]
