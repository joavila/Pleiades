package cl.borrego.foundation.mock;

import java.lang.reflect.Method;

import cl.borrego.foundation.mock.utils.DefaultValues;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class MockGenerator
{

	public static Object newInstance( Method method )
	{
		return newInstance( method , "" );
	}

	private static Object newInstance( Method method  , String string )
	{
		try
		{
			return DefaultValues.newInstance( method , string );
		}
		catch (InstantiationException ex)
		{
			throw new RuntimeException( ex.getMessage() , ex );
		}
		catch (IllegalAccessException ex)
		{
			throw new RuntimeException( ex.getMessage() , ex );
		}
		catch ( IllegalArgumentException ex )
		{
			throw new RuntimeException( ex.getMessage() , ex );
		}
		catch ( ClassNotFoundException ex )
		{
			throw new RuntimeException( ex.getMessage() , ex );
		}
	}

}
