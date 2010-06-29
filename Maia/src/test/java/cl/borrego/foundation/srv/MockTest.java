package cl.borrego.foundation.srv;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import cl.borrego.foundation.mock.utils.DefaultValues;
import cl.borrego.foundation.srv.factory.FactorySrv;
import cl.borrego.foundation.srv.ExampleSrv;
import cl.borrego.foundation.srv.to.ComplexTO;
import cl.borrego.foundation.srv.to.RecursiveTO;
import cl.borrego.foundation.srv.to.SimpleTO;
import cl.borrego.foundation.srv.to.VeryComplexTO;

public class MockTest extends TestCase
{
	private ExampleSrv m_RealSrv;
	private ExampleSrv m_MockSrv;
	
	public MockTest( String str )
	{
		super( str );
	}
	protected void setUp() throws Exception
	{
		super.setUp();
		m_RealSrv = FactorySrv.getExampleSrv( true );
		m_MockSrv = FactorySrv.getExampleSrv( false );
	}
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testSumReal()throws Exception
	{
		Long sum1 = m_RealSrv.sum( new Long( 1 ), new Long( 2 ) );
		long sum2 = m_RealSrv.sum( 1, 2 );
		Long sum3 = m_RealSrv.sum( null, (long) 2);
		Long sum4 = m_RealSrv.sum((long) 2, null );
		Long sum5 = m_RealSrv.sum( null , null );
		assertNotNull( sum1 );
		assertTrue( 3 == sum1);
		assertTrue( 3 == sum2 );
		assertNull( sum3 );
		assertNull( sum4 );
		assertNull( sum5 );
	}
	
	public void testSumMock()throws Exception
	{
		Long sum1 = m_MockSrv.sum( new Long( 1 ), new Long( 2 ) );
		long sum2 = m_MockSrv.sum( 1, 2 );
		Long sum3 = m_MockSrv.sum( null, (long) 2);
		Long sum4 = m_MockSrv.sum((long) 2, null );
		Long sum5 = m_MockSrv.sum( null , null );
		assertNotNull( sum1 );
		assertTrue( ((Long)DefaultValues.defaultFinalClassValue( Long.class ,"")).longValue() == sum1.longValue() );
		assertTrue( (Long) DefaultValues.defaultPrimitiveValue(long.class) == sum2 );
		assertNotNull( sum3 );
		assertNotNull( sum4 );
		assertNotNull( sum5 );
	}
	
	public void testSimpleTOReal()throws Exception
	{
		SimpleTO to = m_RealSrv.getSimpleTO((long) 1);
		checkSimpleTO( to );
	}
	
	
	public void testSimpleTOMock()throws Exception
	{
		SimpleTO to = m_MockSrv.getSimpleTO((long) 1);
		checkSimpleTO( to );
	}
	
	public void testComplexTOReal()throws Exception
	{
		ComplexTO complex = m_RealSrv.getComplexTO((long) 1);
		checkComplexTO( complex );
	}
	
	
	public void testComplexTOMock() throws Exception
	{
		ComplexTO complex = m_MockSrv.getComplexTO((long) 1);
		checkComplexTO( complex );
	}
	
	public void testRecursiveTOReal()throws Exception
	{
		RecursiveTO recursive = m_RealSrv.getRecursiveTO((long) 1);
		checkRecursiveTO( recursive );
	}
	
	public void testRecursiveTOMock()throws Exception
	{
		RecursiveTO recursive = m_MockSrv.getRecursiveTO((long) 1);
		checkRecursiveTO( recursive );
	}
	
	public void testgetCollectionReal() throws Exception
	{
		Collection<ComplexTO> col = m_RealSrv.getCollection();
		checkComplexTOCollection( col );
	}
	
	
	public void testgetCollectionMock() throws Exception
	{
		Collection<ComplexTO> col = m_MockSrv.getCollection();
		checkComplexTOCollection( col );
	}
	
	public void testgetListReal() throws Exception
	{
		List<ComplexTO> col = m_RealSrv.getList();
		checkComplexTOCollection( col );
	}
	
	public void testgetListMock() throws Exception
	{
		List<ComplexTO> col = m_MockSrv.getList();
		checkComplexTOCollection( col );
	}
	
	public void testGetSetReal() throws Exception
	{
		Set<ComplexTO> col = m_RealSrv.getSet();
		checkComplexTOCollection( col );
	}
	
	public void testGetSetMock() throws Exception
	{
		Set<ComplexTO> col = m_MockSrv.getSet();
		checkComplexTOCollection( col );
	}
	
	public void testGetVeryComplexListReal() throws Exception
	{
		List<VeryComplexTO> col = m_RealSrv.getVeryComplexList();
		checkVeryComplexList( col );
	}
	
	
	public void testGetVeryComplexListMock() throws Exception
	{
		List<VeryComplexTO> col = m_MockSrv.getVeryComplexList();
		checkVeryComplexList( col );
	}

	// Auxiliary methods
    
	private void checkSimpleTO( SimpleTO to )
	{
		assertNotNull( to );
		assertNotNull( to.getAdopted() );
		assertNotNull( to.getSurname() );
		assertNotNull( to.getDateOfBirth() );
		assertNotNull( to.getId() );
		assertNotNull( to.getName() );
	}
	
	private void checkRecursiveTO( RecursiveTO recursive )
	{
		assertNotNull( recursive );
		assertNotNull( recursive.getId() );
		assertNotNull( recursive.getParent() );
		assertNotNull( recursive.getId() );
	}
	
	private void checkComplexTO( ComplexTO complex )
	{
		assertNotNull( complex );
		assertNotNull( complex.getId() );
		assertNotNull( complex.getComplex() );
		SimpleTO simple = complex.getSimple();
		checkSimpleTO( simple );
	}
	
	private void checkComplexTOCollection( Collection< ComplexTO > col )
	{
		for ( ComplexTO element: col )
		{
			checkComplexTO( element );
		}
	}
	
	private void checkVeryComplexList( List< VeryComplexTO > col )
	{
		for ( VeryComplexTO element: col )
		{
			assertNotNull(  element.getId() );
			checkSimpleTO( element.getSimple() );
			checkComplexTOCollection( element.getList() );
		}
	}
}
