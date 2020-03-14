FROM adoptopenjdk/openjdk12:x86_64-ubuntu-jre-12.0.2_10
LABEL maintainer="jllado@gmail.com"
RUN apt-get update
RUN apt-get install -y libttspico0 libttspico-utils libttspico-data sox
EXPOSE 8080
ADD build/libs/vibotvoice-0.0.1-SNAPSHOT.jar vibotvoice.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/vibotvoice.jar"]