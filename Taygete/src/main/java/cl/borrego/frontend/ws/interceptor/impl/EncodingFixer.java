package cl.borrego.frontend.ws.interceptor.impl;


import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 04-Jul-2010
 * Time: 15:36:01
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 */
public class EncodingFixer extends AbstractPhaseInterceptor<Message> {
    protected static final String ENCODING = "ISO-8859-1";

    public EncodingFixer() {
        super(Phase.SEND);
    }

    public void handleMessage(Message msg) {
        String contentType = (String) msg.get(Message.ENCODING);
        System.out.println("EncodingFixer found encoding: " + contentType);
        System.out.println("Replacing encoding with: " + ENCODING);
        msg.put(Message.ENCODING, ENCODING);
    }

}