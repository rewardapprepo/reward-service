package org.coffeeapp.rewardapp.RewardService.model;

import java.util.Date;

public class CreateRewardModel
{
	private String name;
	private Integer amount;
	private Date expiryDate;

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
}
