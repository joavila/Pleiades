package cl.borrego.foundation.proxy;

import cl.borrego.foundation.system.Properties;
import cl.borrego.foundation.util.Message;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class ChronometerProxy implements InvocationHandler
{
	private Object m_target;

	public static Object newInstance( Object obj )
	{
		return Proxy.newProxyInstance( 	obj.getClass().getClassLoader(),
										obj.getClass().getInterfaces(),
										new ChronometerProxy( obj ) );
	}

	private ChronometerProxy( Object obj )
	{
		m_target = obj;
	}

	public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable
	{
        final long startTime = System.nanoTime();
		final Object ret = method.invoke(m_target, arguments);
        final long estimatedTime = System.nanoTime();
        new Message(Properties.APP_MESSAGES_RESOURCE_BUNDLE).dumpFormattedMessage(ChronometerProxy.class, Message.LevelEnum.DEBUG, "chronometer.time.spent", method.getName(), estimatedTime-startTime);
        return ret;
	}

}