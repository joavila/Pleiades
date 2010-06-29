package cl.borrego.frontend.ws.impl;

import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 02-04-2010
 * Time: 01:52:57 AM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
@WebServiceProvider(portName = SOAPEchoServiceImpl.WS_PORT_NAME, targetNamespace = SOAPEchoServiceImpl.WS_TARGET_NAMESPACE, serviceName = SOAPEchoServiceImpl.WS_NAME)
@ServiceMode(value = Service.Mode.MESSAGE)
public class SOAPEchoServiceImpl extends AbstractSOAPServiceImpl implements Provider<SOAPMessage> {
    protected static final String WS_PORT_NAME = "SOAPEchoServicePort";
    protected static final String WS_NAME = "SOAPEchoServiceImpl";

    @Override
    public SOAPMessage invoke(SOAPMessage request) {
        return request;
    }

}
