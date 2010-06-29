package cl.borrego.foundation.srv.factory;

import cl.borrego.foundation.proxy.MockProxy;
import cl.borrego.foundation.srv.ExampleSrv;
import cl.borrego.foundation.srv.impl.ExampleSrvImpl;

public class FactorySrv
{

	public static ExampleSrv getExampleSrv( boolean isReal )
	{
		return (ExampleSrv) MockProxy.newInstance( new ExampleSrvImpl() , isReal);
	}
	
}
