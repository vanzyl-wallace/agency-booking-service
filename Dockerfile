FROM openjdk:17-oracle
MAINTAINER wallacevanzyl.co.za
COPY target/agency-booking-service-0.0.1-SNAPSHOT.jar agency-booking-service-image-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/agency-booking-service-image-0.0.1-SNAPSHOT.jar"]