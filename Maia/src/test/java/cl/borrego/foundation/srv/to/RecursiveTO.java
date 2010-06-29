package cl.borrego.foundation.srv.to;

import java.io.Serializable;


public class RecursiveTO implements Serializable
{
	private static final long serialVersionUID = -4395146424904094883L;
	private Long id;
	private RecursiveTO parent;

    public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public RecursiveTO getParent()
	{
		return parent;
	}
	public void setParent(RecursiveTO parent)
	{
		this.parent = parent;
	}


}
