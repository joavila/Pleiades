package cl.borrego.foundation.system;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENTS ACCEPTANCE OF THIS AGREEMENT.
 */
public class Properties {
    private static final String DEFAULT_RESOURCE_BUNDLE_KEY = "default.resource.bundle";
    
    public static final String DEFAULT_RESOURCE_BUNDLE = System.getProperty(DEFAULT_RESOURCE_BUNDLE_KEY, "defaultMessages");
    public static final String APP_MESSAGES_RESOURCE_BUNDLE = "applicationMessages";
    public static final String MOCK_APP_MESSAGES_RESOURCE_BUNDLE = "mockApplicationMessages";
}

