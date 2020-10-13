package com.paradigmadigital.pubsub;

import com.paradigmadigital.pubsub.commons.CommonsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

/**
 *
 *  Service activator utilizado para procesar los mensajes entrantes
 *
 *  En este ejemplo, el mensaje se procesa simplemente registrando su cuerpo y reconociéndolo.
 *  En el reconocimiento manual, se reconoce un mensaje mediante el objeto BasicAcknowledgeablePubsubMessage,
 *  que se adjunta a los encabezados del mensaje y se puede extraer mediante la clave GcpPubSubHeaders.ORIGINAL_MESSAGE.
 *
 *  El nombre del canal de entrada ServiceActivator debe coincidir con el nombre
 *  del método del canal de entrada (ver PubSubConfig).
 *
 *  Siempre que llegue un mensaje nuevo a ese canal, el MessageHandler devuelto lo procesa.
 *
 */
@Component
@Slf4j
public class ServiceActivatorConfig extends CommonsConfig {

    @Bean
    public MessageChannel pubsubInputChannel() {
        return new DirectChannel();
    }

    /**
     *  El mensaje se procesa simplemente registrando su cuerpo y reconociéndolo.
     *  En el reconocimiento manual, se reconoce un mensaje mediante el objeto BasicAcknowledgeablePubsubMessage,
     *  que se adjunta a los encabezados del mensaje y se puede extraer mediante la clave GcpPubSubHeaders.ORIGINAL_MESSAGE
     *
     * @return MessageHandler
     */
    @Bean
    @ServiceActivator(inputChannel = "pubsubInputChannel")
    public MessageHandler messageReceiver() {
        return message -> {
            log.info("Mensaje recibido desde la subscripcion '{}' ==> {}", getSubscriptionName(), new String((byte[]) message.getPayload()));

            BasicAcknowledgeablePubsubMessage originalMessage =
                    message.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
            originalMessage.getProjectSubscriptionName();
            originalMessage.ack();
        };
    }
}
