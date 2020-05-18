package gymService;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        System.setProperty("javax.xml.soap.SAAJMetaFactory", "com.sun.xml.messaging.saaj.soap.SAAJMetaFactoryImpl");
       Endpoint.publish("http://localhost:8888/ws/people", new GymServiceImpl());
        //new GymServiceImpl();
    }
}
