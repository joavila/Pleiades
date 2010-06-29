package cl.borrego.foundation.srv.to;

import java.io.Serializable;
import java.util.List;


public class VeryComplexTO implements Serializable
{
	private static final long serialVersionUID = 8985982392898992191L;
	private Long id;
	private SimpleTO simple;
	private List<ComplexTO> list;

    public Long getId()
	{
		return id;
	}
	
	public void setId( Long id )
	{
		this.id = id;
	}
	
	public List< ComplexTO > getList()
	{
		return list;
	}
	
	public void setList( List< ComplexTO > list)
	{
		this.list = list;
	}
	
	public SimpleTO getSimple()
	{
		return simple;
	}
	
	public void setSimple( SimpleTO simple )
	{
		this.simple = simple;
	}


}
