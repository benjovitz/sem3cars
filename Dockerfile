FROM openjdk:17-jdk-slim-bullseye

#ENV JAVA_OPTS="-Xmx256m -Xms128"

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN apt-get update && apt-get install dos2unix
RUN dos2unix mvnw
RUN chmod +x mvnw && ./mvnw dependency:resolve

COPY src ./src

ENV JDBC_DATABASE_URL=jdbc:mysql://74.234.105.100:3306/cars_r_us
ENV JDBC_PASSWORD=webpassword1
ENV JDBC_USERNAME=web

CMD ["./mvnw", "spring-boot:run"]
