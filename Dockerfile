FROM globaldevtools.bbva.com:5000/base/fqqtm/fqqtm_java:alpine3.10.2-openjdk11-jre11.0.4-build2
COPY ./target /target
CMD ["/usr/bin/java", "-jar", "/target/pdp-1.0-SNAPSHOT.jar"]