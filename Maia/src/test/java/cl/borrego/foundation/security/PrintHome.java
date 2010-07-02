package cl.borrego.foundation.security;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 01-Jul-2010
 * Time: 20:00:48
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT�S ACCEPTANCE OF THIS AGREEMENT.
 */
public class PrintHome {//from http://onjava.com/pub/a/onjava/2007/01/03/discovering-java-security-requirements.html
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
    }
}
