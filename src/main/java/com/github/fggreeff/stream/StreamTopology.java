package com.github.fggreeff.stream;

import com.github.fggreeff.config.TopicsConfig;
import com.github.fggreeff.domain.out.ResultEvent;
import com.github.fggreeff.serde.JacksonJsonSerde;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class StreamTopology {

    private static final Logger LOG = LoggerFactory.getLogger(StreamTopology.class);

    private static final int SUCCESS = 0;
    private static final int ERROR = 1;

    private final TopicsConfig topics;
    private final JacksonJsonSerde stringSerde;
    private final JacksonJsonSerde<ResultEvent> resultEventJsonSerde;


    public StreamTopology(final TopicsConfig topics, final JacksonJsonSerde stringSerde, final JacksonJsonSerde<ResultEvent> resultEventJsonSerde) {
        this.topics = topics;
        this.stringSerde = stringSerde;
        this.resultEventJsonSerde = resultEventJsonSerde;
    }

    /**
     * This method creates the topology for the stream which consumes records from a given source topic and then
     * attempts to transform them into ResultEvent. If initial deserialization fails, an ERROR LOG is
     * created and the malformed records are send to a Dead Letter Topic. If this transformation fails due to
     * malformed or null attribute in which the transformation depends, a warn LOG is created and the events are send
     * to a respective Dead Letter Queue topic.
     *
     * @return the topology of the kafka stream
     */

    /*
    @Bean("topology")
    public Topology getTopology() {
        final StreamsBuilder builder = new StreamsBuilder();

        final KStream<byte[], byte[]>[] eventStream = builder
                .stream(topics.getSourceTopic(), Consumed.with(Serdes.ByteArray(), Serdes.ByteArray()))
                .branch(this::safelyDeserialize, (key, value) -> true);

        eventStream[ERROR].to(topics.getDlqTopic());

        final KStream<String, ResultEvent>[] transformedStreams = eventStream[SUCCESS]
                .map(this::deserialize)
                .mapValues(this::transform)
                .branch((key, value) -> value instanceof ResultEvent, (key, value) -> true);

        transformedStreams[ERROR].to(topics.getDlqTopic(), Produced.with(stringSerde, resultEventJsonSerde));

        transformedStreams[SUCCESS]
                .to(topics.getDestinationTopic(), Produced.with(stringSerde, resultEventJsonSerde));

        return builder.build();
    }

    private boolean safelyDeserialize(byte[] key, byte[] value) {
        try {

            stringSerde.deserializer().deserialize(topics.getSourceTopic(), key);
            var event = stringSerde.deserializer().deserialize(topics.getSourceTopic(), value);
            return true;
        } catch (SerializationException exception) {
            LOG.error("Unable to deserialize record with key: {} due to ", key, exception);
            LOG.error("Marking malformed to be sent to {}", topics.getDlqTopic());
            return false;
        }
    }

    private KeyValue<String, ResultEvent> deserialize(byte[] key, byte[] value) {
        try {
            return KeyValue.pair(
                    stringSerde.deserializer().deserialize(topics.getSourceTopic(), key),
                    resultEventJsonSerde.deserializer().deserialize(topics.getSourceTopic(), value));
        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    private ResultEvent transform(ResultEvent value) {
        try {
            Instant startTime = Instant.now();
            ResultEvent transformedEnquiry = transform(value);
            return transformedEnquiry;
        } catch (Exception ex) {
            LOG.warn("Unable to transform record due to: ", ex);
            LOG.error("Marking malformed to be sent to {}", topics.getDlqTopic());
            return value;
        }
    }

     */
}
