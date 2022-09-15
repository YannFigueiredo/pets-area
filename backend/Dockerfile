FROM openjdk:17

VOLUME /tmp

EXPOSE 8080

ADD ./target/petsarea-0.0.1-SNAPSHOT.jar petsarea.jar

ENTRYPOINT ["java", "-jar", "petsarea.jar"]