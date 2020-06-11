package com.github.fggreeff.domain.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultPayload {
    @JsonProperty
    private String accountId;

    @JsonProperty
    private String customerUniqueKey;

    @JsonProperty
    private String shopAccountHolderId;


}
