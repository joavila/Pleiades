package cl.borrego.foundation.srv.to;


public enum StateEnum
{
	INCOMPLETE_DATA( (long) 1 ),
	APPROVED( (long) 2 ),
	REJECTED( (long) 3 ),
	ACCEPTED( (long) 4 ),
	CANCELLED( (long) 5 );

	private Long value;

	private StateEnum( Long value )
	{
		this.value = value;
	}

    public Long getValue()
	{
		return value;
	}
	
}
