package cl.borrego.foundation.srv.to;

public enum SexEnum
{
	MALE((long) 1), FEMALE((long) 2);

	private Long value;

	private SexEnum( Long value )
	{
		this.value = value;
	}

    public Long getValue()
	{
		return value;
	}
}
