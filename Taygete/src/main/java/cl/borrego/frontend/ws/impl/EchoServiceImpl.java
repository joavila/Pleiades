package cl.borrego.frontend.ws.impl;

import cl.borrego.foundation.util.Message;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 01-04-2010
 * Time: 11:34:28 PM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
@WebServiceProvider(portName = EchoServiceImpl.WS_PORT_NAME, targetNamespace = EchoServiceImpl.WS_TARGET_NAMESPACE, serviceName = EchoServiceImpl.WS_NAME)
@ServiceMode(value = Service.Mode.PAYLOAD)
public class EchoServiceImpl extends AbstractServiceImpl implements Provider<DOMSource> {
    protected static final String WS_PORT_NAME = "EchoServicePort";
    protected static final String WS_NAME = "EchoServiceImpl";

    @Override
    public DOMSource invoke(DOMSource request) {
        Message message = new Message("wsMessages");
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            final StringWriter writer = new StringWriter();
            Result output = new StreamResult(writer);
            transformer.transform(request, output);
            message.dumpFormattedMessage(Message.LevelEnum.INFO, "receivedMessage", writer.toString());
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return request;
    }
}
