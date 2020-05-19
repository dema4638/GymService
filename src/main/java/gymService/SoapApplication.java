package gymService;

import javax.ws.rs.core.Application;
import javax.xml.ws.Endpoint;

public class SoapApplication extends Application {
    public SoapApplication () {
        super();
        System.setProperty("javax.xml.soap.SAAJMetaFactory", "com.sun.xml.messaging.saaj.soap.SAAJMetaFactoryImpl");
        Endpoint.publish("http://localhost:8080/ws/people", new SoapGymService());
    }
}
