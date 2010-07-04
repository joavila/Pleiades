package cl.borrego.foundation.mock.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cl.borrego.foundation.reflection.ReflectionUtils;
import cl.borrego.foundation.system.Properties;
import cl.borrego.foundation.util.Message;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class DefaultValues
{
    private static final Message MESSAGE = new Message(Properties.MOCK_APP_MESSAGES_RESOURCE_BUNDLE);

    public static Object newInstance( Method method , String str )
		throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException
	{
		Class<?> returnType = ReflectionUtils.getReturnType( method );
		Type[] genericTypes = ReflectionUtils.getTypes( method );
		if ( returnType.isPrimitive() )
		{
			return defaultPrimitiveValue( returnType );
		}
		else if ( isFinalClass( returnType ) )
		{
			return defaultFinalClassValue( returnType , str );
		}
		else
		{
			return 	defaultInstance( returnType , genericTypes );
		}
	}
	
	public static Object defaultPrimitiveValue ( Class<?> returnType )
	{
		if ( returnType == void.class ) return null;
		else if ( returnType == boolean.class  ) return true;
		else if ( returnType == short.class ) return (short) 0;
		else if ( returnType == int.class ) return 0;
		else if ( returnType == long.class ) return (long) 0;
		else if ( returnType == double.class ) return (double) 0;
		else if ( returnType == byte.class ) return (byte) 0;
		else if ( returnType == char.class ) return (char) 0;
		else if ( returnType == float.class ) return (float) 0;
		else {
            final String message = MESSAGE.getFormattedMessage("unknown.return.type", returnType);
            throw new Error( message );
        }
	}

	public static Object defaultFinalClassValue(Class<?> returnType, String str )
	{
		if ( returnType == Boolean.class  ) return true;
		else if ( returnType == Short.class ) return (short) 0;
		else if ( returnType == Integer.class ) return 0;
		else if ( returnType == Long.class ) return (long) 0;
		else if ( returnType == Double.class ) return (double) 0;
		else if ( returnType == Byte.class ) return (byte) 0;
		else if ( returnType == Character.class ) return (char) 0;
		else if ( returnType == Float.class )return (float) 0;
		else if ( returnType == String.class ) return str + "-mock";
		else if ( returnType == java.util.Date.class ) return new java.util.Date();
		else if ( returnType == java.sql.Date.class ) return new java.sql.Date( (new java.util.Date()).getTime() );
		else {
            final String message = MESSAGE.getFormattedMessage("unknown.return.type", returnType);
            throw new Error( message );
        }
	}
	
	private static boolean isFinalClass ( Class<?> returnType )
	{
		return  ( returnType == Boolean.class  )        ||
                ( returnType == Short.class )           ||
                ( returnType == Integer.class )         ||
                ( returnType == Long.class )            ||
                ( returnType == Double.class )          ||
                ( returnType == Byte.class )            ||
                ( returnType == Character.class )       ||
                ( returnType == Float.class )           ||
                ( returnType == String.class )          ||
                ( returnType == java.util.Date.class )  ||
                ( returnType == java.sql.Date.class );
	}
	
	private static Object defaultInstance ( Class<?> returnType , Type[] genericTypes ) 
		throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException
	{
		if ( returnType.isInterface() ) 
		{
			return defaultInterfaceInstance( returnType , genericTypes );
		}
		else if ( returnType.isEnum() )
		{
			return defaultEnumInstance( returnType );
		}
		else 
		{
			Object ret = returnType.newInstance();
			fillObject( ret );
			return ret;
		}
	}

	private static Object defaultEnumInstance( Class< ? > returnType )
	{
		return returnType.getEnumConstants()[0];
	}

	@SuppressWarnings("unchecked")
	private static Object defaultInterfaceInstance( Class<?> returnType , Type[] genericTypes ) 
		throws IllegalArgumentException, IllegalAccessException, 
		InstantiationException, ClassNotFoundException
	{
		if ( returnType == Collection.class )
		{
			Collection ret = new ArrayList();
			fillCollection( ret , genericTypes );
			return ret;
		}
		else if ( returnType == List.class )
		{
			List ret = new ArrayList();
			fillCollection( ret , genericTypes );
			return ret;
		}
		else if ( returnType == Set.class )
		{
			Set ret = new HashSet();
			fillCollection( ret , genericTypes );
			return ret;
		}
		else
		{
            final String message = MESSAGE.getFormattedMessage("interface.not.implemented", returnType);
            throw new Error( message );
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void fillCollection( Collection col , Type[] genericTypes ) 
		throws IllegalArgumentException, IllegalAccessException, 
		InstantiationException, ClassNotFoundException
	{
		if ( null != genericTypes )
		{
			String className = String.valueOf( genericTypes[0] ).split( " " )[1];
			Class clazz = Class.forName( className );
			for ( int i = 0 ; i < 20 ; i++ )
			{
				if ( clazz.isEnum() )
				{
					if ( i < clazz.getEnumConstants().length )
					{
						col.add( clazz.getEnumConstants()[i] );
					}
				}
				else
				{
					Object added =  clazz.newInstance();
					fillObject( added );
					col.add( added );
				}
			}
		}
	}
	
	private static void fillObject( Object ret ) 
		throws IllegalArgumentException, IllegalAccessException, InstantiationException, 
			ClassNotFoundException
	{
		Field[] fields = ret.getClass().getDeclaredFields();
		Method[] methods = ret.getClass().getDeclaredMethods();
		AccessibleObject.setAccessible( fields, true );
		if ( null != fields )
		{
			for (Field field: fields)
			{
				field.setAccessible( true );
				if ( !"serialVersionUID".equals( field.getName() ))
				{
                    Method getterMethod = getGetterMethod(field, methods);
                    Object instance;
                    Class<?> obj = ReflectionUtils.getReturnType(getterMethod);
                    if(  ret.getClass().equals(obj) ) {//Hack
                        instance = ret;
                    } else {
                        instance = newInstance(getterMethod, field.getName());
                    }
                    field.set( ret, instance);
				}
			}
		}
	}
	
	private static Method getGetterMethod( Field field , Method[] methods )
	{
		String methodName = getMethodName( field );
		for ( Method method: methods )
		{
			if ( method.getName().equals( methodName ) )
			{
				return method;
			}
		}
        final String message = MESSAGE.getFormattedMessage("method.not.found", methodName);
        throw new Error( message );
	}

	private static String getMethodName( Field field  )
	{
		String fieldName = field.getName();
		String firstLetter = fieldName.substring( 0 , 1 );
		Class<?> returnType = field.getType();
		boolean isBoolean = ( returnType == boolean.class );
		if ( isBoolean )
		{
			return "is" + firstLetter.toUpperCase() + fieldName.substring( 1 );
		}
		else
		{
			return "get" + firstLetter.toUpperCase() + fieldName.substring( 1 );
		}
	}
}
