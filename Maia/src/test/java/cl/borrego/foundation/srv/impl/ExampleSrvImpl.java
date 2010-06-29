package cl.borrego.foundation.srv.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cl.borrego.foundation.srv.ExampleSrv;
import cl.borrego.foundation.srv.to.ComplexTO;
import cl.borrego.foundation.srv.to.RecursiveTO;
import cl.borrego.foundation.srv.to.SimpleTO;
import cl.borrego.foundation.srv.to.VeryComplexTO;

public class ExampleSrvImpl implements ExampleSrv {

    public long sum(long a, long b) {
        return (a + b);
    }

    public Long sum(Long a, Long b) {
        if (null != a && null != b) {
            return a + b;
        } else {
            return null;
        }
    }

    public SimpleTO getSimpleTO(Long id) {
        SimpleTO ret = new SimpleTO();
        ret.setAdopted(Boolean.FALSE);
        ret.setSurname("real-surname");
        ret.setDateOfBirth(new Date());
        ret.setId(id);
        ret.setName("real-name");
        return ret;
    }

    public ComplexTO getComplexTO(Long id) {
        ComplexTO ret = new ComplexTO();
        ret.setId(id);
        ret.setComplex("real-complex");
        ret.setSimple(getSimpleTO(id));
        return ret;
    }

    public RecursiveTO getRecursiveTO(Long id) {
        RecursiveTO ret = new RecursiveTO();
        ret.setId(id);
        RecursiveTO padre = new RecursiveTO();
        padre.setId(id);
        ret.setParent(padre);
        return ret;
    }

    public Collection<ComplexTO> getCollection() {
        Collection<ComplexTO> ret = new ArrayList<ComplexTO>();
        fillCollection(ret);
        return ret;
    }

    public List<ComplexTO> getList() {
        List<ComplexTO> ret = new ArrayList<ComplexTO>();
        fillCollection(ret);
        return ret;
    }

    public Set<ComplexTO> getSet() {
        Set<ComplexTO> ret = new HashSet<ComplexTO>();
        fillCollection(ret);
        return ret;
    }

    public List<VeryComplexTO> getVeryComplexList() {
        List<VeryComplexTO> ret = new ArrayList<VeryComplexTO>();
        fillVeryComplexCollection(ret);
        return ret;
    }

    // Auxiliary methods

    private void fillVeryComplexCollection(Collection<VeryComplexTO> col) {
        for (int i = 0; i < 10; i++) {
            VeryComplexTO aux = new VeryComplexTO();
            aux.setId((long) i);
            List<ComplexTO> ret = new ArrayList<ComplexTO>();
            fillCollection(ret);
            aux.setList(ret);
            aux.setSimple(getSimpleTO(new Long(i)));
            col.add(aux);
        }
    }

    private void fillCollection(Collection<ComplexTO> col) {
        for (int i = 0; i < 10; i++) {
            ComplexTO to = new ComplexTO();
            to.setId((long) i);
            to.setComplex("real-complex-" + i);
            to.setSimple(getSimpleTO(new Long(i)));
            col.add(to);
        }
    }


}
