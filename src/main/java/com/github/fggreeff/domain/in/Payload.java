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
public class Payload {

    @JsonProperty
    private String accountId;

    @JsonProperty
    private String emailAddress;

    @JsonProperty
    private String shoppingMembershipId;

}
