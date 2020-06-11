package com.github.fggreeff.transformer;

import com.github.fggreeff.domain.in.MessageBody;
import com.github.fggreeff.domain.in.Payload;
import com.github.fggreeff.domain.in.IncomingMetaData;
import com.github.fggreeff.domain.out.ResultEvent;
import com.github.fggreeff.domain.out.ResultMetadata;
import com.github.fggreeff.domain.out.ResultPayload;
import com.github.fggreeff.utility.CryptoUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class EventTransformer implements Transformer<MessageBody, ResultEvent>  {

    @Override
    @SneakyThrows
    public ResultEvent transform(final MessageBody source) {
        IncomingMetaData originalMetadata = source.getMetadata();
        Payload originalPayload = source.getPayload();

        final String customerUniqueEmailKey = CryptoUtil
                .keyHashFor(originalPayload.getEmailAddress());

        ResultMetadata appEngagementMetadata = generateResultMetadata(
                originalMetadata);
        ResultPayload appEngagementPayload = generateResultPayload(originalPayload,
                customerUniqueEmailKey);

        return ResultEvent.builder()
                .metadata(appEngagementMetadata)
                .payload(appEngagementPayload)
                .build();
    }

    private ResultMetadata generateResultMetadata(final IncomingMetaData originalMetadata) {
        String messageId = UUID.randomUUID().toString();

        return ResultMetadata.builder()
                .messageId(messageId)
                .messageTimestamp(originalMetadata.getOccurredAt())
                .build();
    }

    /**
     * Generate the payload that will be sent to the out topic
     */
    private ResultPayload generateResultPayload(final Payload payload,
                                                              String customerUniqueEmailKey) {
        return ResultPayload.builder()
                .accountId(payload.getAccountId())
                .customerUniqueKey(customerUniqueEmailKey)
                .shopAccountHolderId(payload.getShoppingMembershipId())
                .build();
    }
}
