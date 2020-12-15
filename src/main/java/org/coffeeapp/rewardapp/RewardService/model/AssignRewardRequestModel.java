package org.coffeeapp.rewardapp.RewardService.model;

public class AssignRewardRequestModel
{
	private Long rewardId;
	private Long userId;

	public Long getRewardId( )
	{
		return rewardId;
	}

	public void setRewardId( Long rewardId )
	{
		this.rewardId = rewardId;
	}

	public Long getUserId( )
	{
		return userId;
	}

	public void setUserId( Long userId )
	{
		this.userId = userId;
	}
}
