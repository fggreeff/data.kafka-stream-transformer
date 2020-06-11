package com.github.fggreeff.domain.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.Metadata;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageBody {

    @JsonProperty(value = "metadata", required = true)
    private Metadata metadata;

    @JsonProperty(value = "payload", required = true)
    private Payload payload;
}
