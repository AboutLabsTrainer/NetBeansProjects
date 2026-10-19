package de.fleetdata.messaging.config;

import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Definiert den Broker als Bean, die mit dem Application-Context startet und stoppt.
 * Connector, Persistenz und Datenverzeichnis kommen aus den Properties dieses Moduls.
 */
@Configuration
public class BrokerConfig {

    @Value("${broker.url:tcp://localhost:61616}")
    private String brokerUrl;

    @Value("${broker.data.directory:./data/activemq}")
    private String dataDirectory;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public BrokerService brokerService() throws Exception {
        BrokerService broker = new BrokerService();

        // TCP-Connector für externe Verbindungen
        broker.addConnector(brokerUrl);

        // Persistenz aktivieren
        broker.setPersistent(true);

        // Datenverzeichnis
        broker.setDataDirectory(dataDirectory);

        // Broker-Name
        broker.setBrokerName("FleetDataBroker");

        return broker;
    }
}
