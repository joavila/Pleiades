package cl.borrego.foundation.srv;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import cl.borrego.foundation.srv.to.ComplexTO;
import cl.borrego.foundation.srv.to.RecursiveTO;
import cl.borrego.foundation.srv.to.SimpleTO;
import cl.borrego.foundation.srv.to.VeryComplexTO;

public interface ExampleSrv
{
	public long sum( long a , long b);
	public Long sum( Long a , Long b);
	public SimpleTO getSimpleTO( Long id );
	public ComplexTO getComplexTO( Long id );
	public RecursiveTO getRecursiveTO(Long id);
	public Set<ComplexTO> getSet();
	public Collection<ComplexTO> getCollection();
	public List<ComplexTO> getList();
	public List<VeryComplexTO> getVeryComplexList();
}
