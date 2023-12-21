FROM openjdk:21-rc-jdk
LABEL authors="eddie"

COPY src/main/resources/fonts /app/resources/fonts
COPY src/main/resources/fonts /usr/share/fonts/
RUN fc-cache -f -v

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} converter.jar

RUN chmod 644 /app/resources/fonts/*

ENTRYPOINT ["java", "-jar", "converter.jar"]
