FROM openjdk:11
COPY ./deployment/api.war /usr/local/src/
WORKDIR /usr/local/src/
CMD ["java", "-jar" , "api.war"]