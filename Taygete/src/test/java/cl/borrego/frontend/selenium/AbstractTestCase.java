package cl.borrego.frontend.selenium;

import org.junit.*;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 02-Jul-2010
 * Time: 23:55:57
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 */
public class AbstractTestCase {
    protected Selenium m_selenium;

    @Before
    public void init() throws Exception {
        this.m_selenium = new DefaultSelenium(
                "localhost",
                org.openqa.selenium.server.SeleniumServer.getDefaultPort(),
                "*iehta",//IE "*firefox"
                "http://localhost:9080");
        this.m_selenium.start();
    }

    @After
    public void tearDown() throws Exception {
        this.m_selenium.stop();
        this.m_selenium = null;
    }

}
