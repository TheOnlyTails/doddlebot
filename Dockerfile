FROM gradle:jdk18 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle build

FROM eclipse-temurin:18
COPY --from=gradleimage /home/gradle/source/build/libs/doddlebot-1.0-SNAPSHOT-standalone.jar /app/
RUN useradd -ms /bin/bash admin
WORKDIR /app/
RUN chown -R admin:admin /app
RUN chmod 755 /app

USER admin
ENTRYPOINT ["java", "-jar", "doddlebot-1.0-SNAPSHOT-standalone.jar"]
