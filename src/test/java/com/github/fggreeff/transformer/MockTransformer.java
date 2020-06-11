package com.github.fggreeff.transformer;

import com.github.fggreeff.domain.in.MessageBody;
import com.github.fggreeff.domain.out.ResultEvent;
import com.github.fggreeff.domain.out.ResultMetadata;
import com.github.fggreeff.domain.out.ResultPayload;

/**
 * Mocked implementation of Transformer interface. This can be used in the topology for testing the transformation
 * */
public class MockTransformer implements Transformer<MessageBody, ResultEvent>{

    @Override
    public ResultEvent transform(MessageBody source) {
        if (source.getPayload() == null) {
            throw new TransformationException("Payload Empty Exception");
        }

        return ResultEvent.builder()
                .metadata(ResultMetadata.builder()
                        .messageId("12300000")
                        .messageTimestamp("2020-06-12T10:59:59.430Z")
                        .build())
                .payload(ResultPayload.builder()
                        .accountId("12340000")
                        .customerUniqueKey("000000000")
                        .shopAccountHolderId("1020304000000")
                        .build())
                .build();
    }
}
