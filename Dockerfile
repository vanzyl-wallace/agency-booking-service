FROM openjdk:17-oracle
MAINTAINER wallacevanzyl.co.za.com
COPY target/agency-booking-0.0.1-SNAPSHOT.jar agency-booking-image-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/agency-booking-image-0.0.1-SNAPSHOT.jar"]