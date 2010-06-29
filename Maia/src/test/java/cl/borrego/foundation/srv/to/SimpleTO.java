package cl.borrego.foundation.srv.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SimpleTO implements Serializable
{
	private static final long serialVersionUID = 1725489018203837208L;
	private Long id;
	private String name;
	private String surname;
	private Boolean adopted;
	private Date dateOfBirth;
	private boolean accepts;
	private SexEnum sex;
	private StateEnum state;
	private List<SexEnum> list;


    public List<SexEnum> getList()
	{
		return list;
	}
	public void setList( List<SexEnum> list)
	{
		this.list = list;
	}
	public StateEnum getState()
	{
		return state;
	}
	public void setState( StateEnum state)
	{
		this.state = state;
	}
	public SexEnum getSex()
	{
		return sex;
	}
	public void setSex( SexEnum sex)
	{
		this.sex = sex;
	}
	public boolean isAccepts()
	{
		return accepts;
	}
	public void setAccepts( boolean accepts)
	{
		this.accepts = accepts;
	}
	public Boolean getAdopted()
	{
		return adopted;
	}
	public void setAdopted(Boolean adopted)
	{
		this.adopted = adopted;
	}
	public String getSurname()
	{
		return surname;
	}
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}


}
