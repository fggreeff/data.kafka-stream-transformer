package com.github.fggreeff.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class TopicsConfig {

    private final String sourceTopic;
    private final String destinationTopic;
    private final String dlqTopic;

    public TopicsConfig(@Value("${topics.source}") final String sourceTopic,
                        @Value("${topics.destination}") final String destinationTopic,
                        @Value("${topics.dlq}") final String dlqTopic) {
        this.sourceTopic = sourceTopic;
        this.destinationTopic = destinationTopic;
        this.dlqTopic = dlqTopic;
    }
}
