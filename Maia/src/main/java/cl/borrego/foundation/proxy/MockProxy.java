package cl.borrego.foundation.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cl.borrego.foundation.mock.MockGenerator;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class MockProxy implements InvocationHandler
{
	private Object m_target;
	private boolean m_isReal;
	
	public static Object newInstance( Object obj , boolean isReal )
	{
		return Proxy.newProxyInstance( 	obj.getClass().getClassLoader(), 
										obj.getClass().getInterfaces(), 
										new MockProxy( obj , isReal ) );
	}
	
	private MockProxy( Object obj , boolean isReal )
	{
		m_target = obj;
		m_isReal = isReal;
	}
	
	public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable
	{
		if ( m_isReal )
		{
			return method.invoke( m_target , arguments );
		}
		else
		{
			return MockGenerator.newInstance( method );
		}
	}
	
}