package com.paradigmadigital.pubsub.commons;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;


@Getter
public class CommonsConfig {

    /**
     * Subscripción de Google Pub/Sub
     */
    @Value("${spring.cloud.gcp.pubsub.subscription-name}")
    private String subscriptionName;

}
