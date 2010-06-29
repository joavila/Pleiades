package cl.borrego.foundation.srv.to;

import java.io.Serializable;


public class ComplexTO implements Serializable
{
	private static final long serialVersionUID = -3178385062633962810L;
	private Long id;
	private String complex;
	private SimpleTO simple;

    public String getComplex()
	{
		return complex;
	}
	public void setComplex(String complex)
	{
		this.complex = complex;
	}
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public SimpleTO getSimple()
	{
		return simple;
	}
	public void setSimple(SimpleTO simple)
	{
		this.simple = simple;
	}


}
