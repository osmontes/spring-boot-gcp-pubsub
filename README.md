# spring-boot-gcp-pubsub
Microservicio para la lectura de mensajes de una subscripción de Google Cloud Pub/Sub. 
#### Tecnologías
Java 11

Spring Boot 2.3.4.RELEASE
Spring Cloud 1.2.5.RELEASE

#### Configuración
Editar el fichero *application.yml* las entradas.
> **spring.cloud.gcp.project-id**: Id del proyecto de GCP.
>
> **spring.cloud.pubsub.subscription-name**: Nombre de la subscripción de PubSub.
 
#### Ejecución
Se puede levantar el proyector directamente desde IntelliJ o Eclipse, quedará un handler levantado esperando mensaje
en la subscripción configurada.

Código basado en el tutorial: https://spring.io/guides/gs/messaging-gcp-pubsub/ 
capítulo *Create an inbound channel adapter*