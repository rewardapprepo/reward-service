package org.coffeeapp.rewardapp.RewardService.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Reward
{
	@Id
	@GeneratedValue
	private Long id;
	@Column( nullable = false, length = 50 )
	private String name;
	private Integer amount;
	private Date expiryDate;
	@ElementCollection
	private List<Long> users;

	public Long getId( )
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getName( )
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public Integer getAmount( )
	{
		return amount;
	}

	public void setAmount( Integer amount )
	{
		this.amount = amount;
	}

	public Date getExpiryDate( )
	{
		return expiryDate;
	}

	public void setExpiryDate( Date expiryDate )
	{
		this.expiryDate = expiryDate;
	}

	public List<Long> getUsers( )
	{
		return users;
	}

	public void setUsers( List<Long> users )
	{
		this.users = users;
	}
}
