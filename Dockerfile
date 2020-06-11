FROM adoptopenjdk/openjdk11:alpine-slim

ENV USER app
ENV WORKDIR /var/$USER

WORKDIR $WORKDIR

RUN addgroup -S $USER && adduser -Sg $USER $USER && \
    chown -R $USER:$USER $WORKDIR && \
    apk add --update curl && \
    rm -rf /var/cache/apk/*

USER $USER

ENV ENVIRONMENT default
ENV JAVA_OPTS -Xmx1g
ENV HEALTH_PORT 8000
ENV METRIC_SCRAPE_PERIOD 20
ENV METRIC_PREFIX data

COPY target/data.kafka-stream-transformer-0.0.1-SNAPSHOT.jar ./

HEALTHCHECK --interval=5s --retries=10 CMD curl -fs http://localhost:$HEALTH_PORT/health || exit 1

EXPOSE $HEALTH_PORT

CMD java -jar $JAVA_OPTS \
    -Djava.security.egd=file:/dev/./urandom \
    ./data.martech-app-engagement-transformer.jar \
    --spring.profiles.active=$ENVIRONMENT