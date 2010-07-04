package cl.borrego.foundation.util;

import java.util.ResourceBundle;
import java.text.MessageFormat;

import cl.borrego.foundation.system.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class Message {
    private ResourceBundle m_lookup;
    private static final Log DEFAULT_LOG = LogFactory.getLog(Message.class.getName());

    public Message() {
        this.m_lookup = ResourceBundle.getBundle(Properties.DEFAULT_RESOURCE_BUNDLE);
    }

    public Message(String bundle) {
        this.m_lookup = ResourceBundle.getBundle(bundle);
    }

    public String getFormattedMessage(String key, Object... arguments) {
        String ret = "";
        if (this.m_lookup == null) {
            //TODO handle this
        } else if (key == null) {
            //TODO handle this
        } else {
            String msg = this.m_lookup.getString(key);
            ret = MessageFormat.format(msg, arguments);
        }
        return ret;
    }

    public void dumpFormattedMessage(LevelEnum level, String key, Object... arguments) {
        dumpFormattedMessage( DEFAULT_LOG, level, key, arguments);
    }

    public void dumpFormattedMessage(Class clazz, LevelEnum level, String key, Object... arguments) {
        final Log log = LogFactory.getLog(clazz.getName());
        dumpFormattedMessage( log, level, key, arguments);
    }
    
    protected void dumpFormattedMessage(Log log, LevelEnum level, String key, Object... arguments) {
        switch( level ) {
          case INFO:
              log.info(getFormattedMessage(key, arguments));
              break;
          case DEBUG:
              log.debug(getFormattedMessage(key, arguments));
              break;
          default:
        }
    }

    public enum LevelEnum {
        INFO, DEBUG, ERROR
    }
}
