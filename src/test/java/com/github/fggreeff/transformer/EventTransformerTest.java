package com.github.fggreeff.transformer;

import com.github.fggreeff.domain.in.IncomingMetaData;
import com.github.fggreeff.domain.in.MessageBody;
import com.github.fggreeff.domain.in.Payload;
import com.github.fggreeff.utility.CryptoUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class EventTransformerTest {

    private EventTransformer underTest;


    private static final String EXPECTED_MESSAGE_TIMESTAMP = "2020-06-12T10:59:59.430Z";

    private static final String EXPECTED_ACCOUNT_ID = "1234";
    private static final String EXPECTED_SHOP_ACCOUNT_HOLDER_ID = "10203040";
    private static final String EXPECTED_CUSTOMER_UNIQUE_KEY = CryptoUtil
            .keyHashFor("joe.blogs@example.com");



    @BeforeEach
    void setUp() {
        underTest = new EventTransformer();
    }

    @Test
    void transform() {
    }

    @Nested
    class HappyPathTestCases {

        @SneakyThrows
        @Test
        void shouldSuccessfullyTransformValidEvent() {
            // Given
            var messageBody = MessageBody.builder()
                    .metadata(buildIncomingMetaData())
                    .payload(buildPayload())
                    .build();

            // When
            var transformedOutput = underTest.transform(messageBody);

            // Then
            var metadata = transformedOutput.getMetadata();
            var payload = transformedOutput.getPayload();

            assertThat(payload).isNotNull();
            assertThat(metadata).isNotNull();

            assertThat(UUID.fromString(metadata.getMessageId())).isInstanceOf(UUID.class);
            assertThat(metadata.getMessageTimestamp()).isEqualTo(EXPECTED_MESSAGE_TIMESTAMP);

            assertThat(payload.getAccountId()).isEqualTo(EXPECTED_ACCOUNT_ID);
            assertThat(payload.getCustomerUniqueKey()).isEqualTo(EXPECTED_CUSTOMER_UNIQUE_KEY);
            assertThat(payload.getShopAccountHolderId()).isEqualTo(EXPECTED_SHOP_ACCOUNT_HOLDER_ID);
        }
    }

    private Payload buildPayload() {
        return Payload.builder()
                .accountId("1234")
                .emailAddress("joe.blogs@example.com")
                .shoppingMembershipId("10203040")
                .build();
    }

    private IncomingMetaData buildIncomingMetaData() {
        return IncomingMetaData.builder()
                .eventId("4321")
                .eventName("ShoppingUserTracked")
                .occurredAt("2020-06-12T10:59:59.430Z")
                .build();
    }
}