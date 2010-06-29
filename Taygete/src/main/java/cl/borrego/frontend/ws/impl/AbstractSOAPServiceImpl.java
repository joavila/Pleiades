package cl.borrego.frontend.ws.impl;

import cl.borrego.foundation.util.Message;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 07-04-2010
 * Time: 11:09:18 PM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class AbstractSOAPServiceImpl extends AbstractServiceImpl {

    protected void dumpSOAPBody(SOAPMessage request, Message message) {
        try {
            SOAPBody soapBody = request.getSOAPBody();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Source source = new DOMSource(soapBody.extractContentAsDocument());
            final StringWriter writer = new StringWriter();
            Result output = new StreamResult(writer);
            transformer.transform(source, output);
            message.dumpFormattedMessage(Message.LevelEnum.INFO, "receivedMessage.body", writer.toString());
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    protected void dumpRequest(SOAPMessage request, Message message) {
        try {
            ByteArrayOutputStream sos = new ByteArrayOutputStream();
            request.writeTo(sos);
            message.dumpFormattedMessage(Message.LevelEnum.INFO, "receivedMessage", sos.toString());
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
