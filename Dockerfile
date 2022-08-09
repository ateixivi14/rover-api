FROM openjdk:17.0.2-jdk-slim AS BUILD_IMAGE
RUN apt-get update &&  apt-get install -y findutils
ENV APP_HOME=/root/dev/app
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME
COPY settings.gradle build.gradle gradlew gradlew.bat $APP_HOME/
COPY gradle $APP_HOME/gradle
COPY src src
RUN ./gradlew build -x test

FROM openjdk:17
WORKDIR /root/
COPY --from=BUILD_IMAGE /root/dev/app/build/libs/rover-api.jar .
EXPOSE 8080
CMD ["java","-jar","rover-api.jar"]