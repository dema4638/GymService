FROM maven:3.6.3-jdk-11 as builder
ADD . /project
WORKDIR /project
RUN mvn clean validate install

FROM jboss/wildfly as server
COPY --from=builder project/target/GymService-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/
RUN mv /opt/jboss/wildfly/standalone/deployments/GymService-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/ROOT.war