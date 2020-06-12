package com.github.fggreeff.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fggreeff.domain.out.ResultEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Map;

public class JacksonJsonSerde<T /*extends ResultEvent*/> implements Serializer<T>, Deserializer<T>, Serde<T> {private final ObjectMapper mapper;
    private final Class<T> type;

    private JacksonJsonSerde(ObjectMapper mapper, Class<T> type) {
        this.mapper = mapper;
        this.type = type;
    }

    public static <T> JacksonJsonSerde<T> forClass(Class<T> type) {
        return new JacksonJsonSerde(JacksonJsonMapper.withDefaults(), type);
    }

    public static <T> JacksonJsonSerde<T> forClass(Class<T> type, ObjectMapper overrideMapper) {
        return new JacksonJsonSerde(overrideMapper, type);
    }

    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    public T deserialize(byte[] data) {
        if (data == null) {
            throw new SerializationException("Error deserializing JSON message: message is null");
        } else {
            try {
                return this.mapper.readValue(data, this.type);
            } catch (IOException var3) {
                throw new SerializationException(var3);
            }
        }
    }

    public T deserialize(String topic, byte[] data) {
        return this.deserialize(data);
    }

    public byte[] serialize(String topic, T data) {
        if (data == null) {
            throw new SerializationException("Error serializing JSON message: message is null");
        } else {
            try {
                return this.mapper.writeValueAsBytes(data);
            } catch (Exception var4) {
                throw new SerializationException("Error serializing JSON message", var4);
            }
        }
    }

    public void close() {
    }

    public Serializer<T> serializer() {
        return this;
    }

    public Deserializer<T> deserializer() {
        return this;
    }

    public static <T> JacksonJsonSerde.JacksonJsonSerdeBuilder<T> builder() {
        return new JacksonJsonSerde.JacksonJsonSerdeBuilder();
    }

    public static class JacksonJsonSerdeBuilder<T> {
        private ObjectMapper mapper;
        private Class<T> type;

        JacksonJsonSerdeBuilder() {
        }

        public JacksonJsonSerde.JacksonJsonSerdeBuilder<T> mapper(ObjectMapper mapper) {
            this.mapper = mapper;
            return this;
        }

        public JacksonJsonSerde.JacksonJsonSerdeBuilder<T> type(Class<T> type) {
            this.type = type;
            return this;
        }

        public JacksonJsonSerde<T> build() {
            return new JacksonJsonSerde(this.mapper, this.type);
        }

        public String toString() {
            return "JacksonJsonSerde.JacksonJsonSerdeBuilder(mapper=" + this.mapper + ", type=" + this.type + ")";
        }
    }
}