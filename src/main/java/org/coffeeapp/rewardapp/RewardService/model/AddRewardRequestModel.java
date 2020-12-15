package org.coffeeapp.rewardapp.RewardService.model;

public class AddRewardRequestModel
{
	private Long userId;
	private Long rewardId;

	public Long getUserId( )
	{
		return userId;
	}

	public void setUserId( Long userId )
	{
		this.userId = userId;
	}

	public Long getRewardId( )
	{
		return rewardId;
	}

	public void setRewardId( Long rewardId )
	{
		this.rewardId = rewardId;
	}
}
