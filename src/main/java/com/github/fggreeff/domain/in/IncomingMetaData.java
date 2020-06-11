package com.github.fggreeff.domain.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncomingMetaData {
    @JsonProperty(value = "occurredAt", required = true)
    private String occurredAt;

    @JsonProperty(value = "eventName")
    private String eventName;

    @JsonProperty(value = "eventId", required = true)
    private String eventId;
}

