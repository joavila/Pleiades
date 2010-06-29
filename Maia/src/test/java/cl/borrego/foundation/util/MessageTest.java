package cl.borrego.foundation.util;

import cl.borrego.foundation.system.Properties;

import static org.junit.Assert.*;
import java.util.MissingResourceException;

public class MessageTest {
    private static final Message MESSAGE = new Message();
    private static final Message CUSTOM_MESSAGE = new Message(Properties.APP_MESSAGES_RESOURCE_BUNDLE);

    @org.junit.Test
  public void testValidMessageWithArgs() {
    {
      String key = "valid.message.with.parameter";
      {
        final String msg = MESSAGE.getFormattedMessage(key);
        assertEquals("Valid Message with Parameter: {0}", msg);
      }
      {
        final String msg = MESSAGE.getFormattedMessage(key, "value");
        assertEquals("Valid Message with Parameter: value", msg);
      }
      {
        final String msg = MESSAGE.getFormattedMessage(key, "value0", "value1");
        assertEquals("Valid Message with Parameter: value0", msg);
      }
    }
    {
      String key = "tryingTo.acquireTask";
      {
        final String msg = CUSTOM_MESSAGE.getFormattedMessage(key);
        assertEquals("Trying to acquire task {0}", msg);
      }
      {
        final String msg = CUSTOM_MESSAGE.getFormattedMessage(key, "value");
        assertEquals("Trying to acquire task value", msg);
      }
      {
        final String msg = CUSTOM_MESSAGE.getFormattedMessage(key, "value0", "value1");
        assertEquals("Trying to acquire task value0", msg);
      }
    }
  }

  @org.junit.Test
  public void testValidMessage() {
    {
      String key = "valid.message";
      final String msg = MESSAGE.getFormattedMessage(key);
      assertEquals("Valid Message", msg);
    }
    {
      String key = "tryingTo.acquireTask";
      final String msg = CUSTOM_MESSAGE.getFormattedMessage(key);
      assertEquals("Trying to acquire task {0}", msg);
    }
  }

  @org.junit.Test(expected=MissingResourceException.class)
  public void testMissingResourceException() {
    {
      final String msg = MESSAGE.getFormattedMessage("invalid.message.non.existent");
      assertEquals("", msg);
    }
    {
      final String msg = MESSAGE.getFormattedMessage(null, "invalid.message.non.existent", null);
      assertEquals("", msg);
    }
  }

  @org.junit.Test
  public void testEmpty() {
    {
      final String msg = MESSAGE.getFormattedMessage(null);
      assertEquals("", msg);
    }
    {
      final String msg = MESSAGE.getFormattedMessage(null);
      assertEquals("", msg);
    }
  }
}
