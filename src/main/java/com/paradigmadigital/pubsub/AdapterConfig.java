package com.paradigmadigital.pubsub;

import com.paradigmadigital.pubsub.commons.CommonsConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * Configuración del canal de entrada.
 *
 * El nombre del canal de entrada ServiceActivator debe coincidir con el nombre
 * del método del canal de entrada (ver ServiceActivatorConfig).
 *
 */
@Component
public class AdapterConfig extends CommonsConfig {

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            @Qualifier("pubsubInputChannel") MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, getSubscriptionName());
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);

        return adapter;
    }
}
