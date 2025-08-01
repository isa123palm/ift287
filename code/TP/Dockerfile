FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

RUN mvn install:install-file -Dfile=lib/objectdb.jar -DgroupId=com.objectdb -DartifactId=objectdb -Dversion=2.8.8 -Dpackaging=jar -DgeneratePom=true
RUN mvn clean package -DskipTests

FROM tomcat:9.0-jdk17
COPY --from=build /app/target/tp-apce-web.war /usr/local/tomcat/webapps/ROOT.war
COPY lib/objectdb.jar /usr/local/tomcat/lib/objectdb.jar

EXPOSE 8080
CMD ["catalina.sh", "run"]