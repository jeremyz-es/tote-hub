FROM maven:3.9.6-amazoncorretto-21 as base
WORKDIR /build
COPY . ./
RUN amazon-linux-extras install -y postgresql10
RUN mvn clean install && ls -l ./

FROM amazoncorretto:21 as pgi-event-service
COPY --from=base /build/pgi/pgi-services/pgi-event-service/target/pgi-event-service-1.0-SNAPSHOT.jar /app/pgi-event-service/
CMD ["java", "-jar", "/app/pgi-event-service/pgi-event-service-1.0-SNAPSHOT.jar"]

FROM amazoncorretto:21 as pgi-feed-service
COPY --from=base /build/pgi/pgi-services/pgi-feed-service/target/pgi-feed-service-1.0-SNAPSHOT.jar /app/pgi-feed-service/
CMD ["java", "-jar", "/app/pgi-feed-service/pgi-feed-service-1.0-SNAPSHOT.jar"]

FROM amazoncorretto:21 as pgi-execution-gateway
COPY --from=base /build/pgi/pgi-services/pgi-execution-gateway/target/pgi-execution-gateway-1.0-SNAPSHOT.jar /app/pgi-execution-gateway/
CMD ["java", "-jar", "/app/pgi-execution-gateway/pgi-execution-gateway-1.0-SNAPSHOT.jar"]

FROM amazoncorretto:21 as opticon
COPY --from=base /build/opticon/opticon-service/target/opticon-service-1.0-SNAPSHOT.jar /app/opticon-service/
CMD ["java", "-jar", "/app/opticon-service/opticon-service-1.0-SNAPSHOT.jar"]



