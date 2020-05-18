package gymService.exception;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "ResponseFaultInfo")
@XmlRootElement
public class ResponseFaultInfo {
    @Getter @Setter
    private String faultMessage;
    @Getter @Setter
    private Object response;
}
