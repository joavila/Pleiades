package cl.borrego.foundation.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class ReflectionUtils
{
	public static Class<?> getReturnType( Method method)
	{
		return method.getReturnType();
	}
	
	public static Type[] getTypes( Method method )
	{
		Type[] genericTypes = null;
		try
		{
			ParameterizedType params = (ParameterizedType) method.getGenericReturnType();
			genericTypes = params.getActualTypeArguments();
		}
		catch( ClassCastException ex )
		{
			// No es un tipo generico
		}
		return genericTypes;
	}
}
