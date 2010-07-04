package cl.borrego.frontend.ws.impl;

import cl.borrego.foundation.util.Message;
import org.w3c.dom.Document;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 07-04-2010
 * Time: 11:01:03 PM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 */
@WebServiceProvider(portName = SOAPSimpleServiceImpl.WS_PORT_NAME, targetNamespace = SOAPSimpleServiceImpl.WS_TARGET_NAMESPACE, serviceName = SOAPSimpleServiceImpl.WS_NAME)
@ServiceMode(value = Service.Mode.MESSAGE)
public class SOAPSimpleServiceImpl extends AbstractSOAPServiceImpl implements Provider<SOAPMessage> {
    protected static final String WS_PORT_NAME = "SOAPSimpleServicePort";
    protected static final String WS_NAME = "SOAPSimpleServiceImpl";

    @Override
    public SOAPMessage invoke(SOAPMessage request) {
        Message message = new Message("wsMessages");
        dumpRequest(request, message);
        dumpSOAPBody(request, message);
        Document body = buildResponse();
        return dumpResponse(body);
    }

    private SOAPMessage dumpResponse( Document body) {
        SOAPMessage ret = null;
        try {
            MessageFactory factory = MessageFactory.newInstance();
            ret = factory.createMessage();
//            String answer = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><world/></soapenv:Body></soapenv:Envelope>";
//            ret.getSOAPPart().setContent(new StreamSource(new StringReader(answer)));
            ret.getSOAPBody().addDocument(body);
            ret.saveChanges();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
