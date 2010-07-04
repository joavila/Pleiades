package cl.borrego.frontend.ws.impl;

import cl.borrego.foundation.util.Message;

import javax.xml.namespace.QName;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 02-04-2010
 * Time: 01:03:36 AM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 */
public class EchoServiceClient {
    private static final String APP_SERVER = System.getProperty("app.server", "http://localhost:8080/frontend-1.0-SNAPSHOT");

    @org.junit.Test
    public void testEcho() {
        String path = "/frontend-1.0-SNAPSHOT/ws/echo";
        httpPost(path);
    }

    @org.junit.Test
    public void testSimple() {
        String path = "/frontend-1.0-SNAPSHOT/ws/simple";
        httpPost(path);
    }

    private void httpPost(String path) {
        Socket sock = null;
        try {
            final String xmldata = "<Hello>World</Hello>";
            //Create socket
            String hostname = "localhost";
            int port = 8080;
            InetAddress address = InetAddress.getByName(hostname);
            sock = new Socket(address, port);

            //Send header
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), "UTF-8"));
            // You can use "UTF8" for compatibility with the Microsoft virtual machine.
            wr.write("POST " + path + " HTTP/1.0\r\n");
            wr.write("Host: " + hostname + "\r\n");
            wr.write("Content-Length: " + xmldata.length() + "\r\n");
            wr.write("Content-Type: text/xml; charset=\"utf-8\"\r\n");
            wr.write("\r\n");
            //Send data
            wr.write(xmldata);
            wr.flush();

            // Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail(String.valueOf(e));
        } finally {
            if (sock != null) {
                try {
                    sock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @org.junit.Test
    public void testSOAPEcho() {
        final String path = "/ws/soapecho?wsdl";
        soapMessage(path, SOAPEchoServiceImpl.WS_NAME, SOAPEchoServiceImpl.WS_PORT_NAME);

    }

    @org.junit.Test
    public void testSOAP() {
        final String path = "/ws/soapsimple?wsdl";
        soapMessage(path, SOAPSimpleServiceImpl.WS_NAME, SOAPSimpleServiceImpl.WS_PORT_NAME);

    }

    private void soapMessage(String path, String wsName, String wsPortName) {
        try {
            URL wsdlURL = new URL(APP_SERVER + path);
            final String NS = AbstractServiceImpl.WS_TARGET_NAMESPACE;
            Service service = Service.create(wsdlURL, new QName(NS, wsName));
            Dispatch<Source> dispatcher = service.createDispatch(new QName(NS, wsPortName), Source.class, Service.Mode.PAYLOAD);
            Source request = new StreamSource(new StringReader("<hello/>"));
            Source response = dispatcher.invoke(request);
            assertNotNull(response);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            final StringWriter writer = new StringWriter();
            Result output = new StreamResult(writer);
            transformer.transform(response, output);
            Message message = new Message("clientMessages");
            message.dumpFormattedMessage(EchoServiceClient.class, Message.LevelEnum.INFO, "receivedAnswer", writer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            fail(String.valueOf(e));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
