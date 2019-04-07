import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;

public class caClient {
    public static void main(String[] argv) {

        Service service = new Service();
        String url = "http://localhost:8080/services/HelloWorld?wsdl";

        try {
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(url));
            call.setOperationName(new QName("http://example", "sendEmail"));
            call.addParameter(new QName("url"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter(new QName("payload"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.setUseSOAPAction(true);
            call.setReturnType(XMLType.SOAP_STRING);
            Object[] c=new Object[]{"7522566693@qq.com","123456"};
            String result = (String) call.invoke(c);

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}