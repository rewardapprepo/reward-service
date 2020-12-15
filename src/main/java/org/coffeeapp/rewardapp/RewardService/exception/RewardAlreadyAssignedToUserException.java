package org.coffeeapp.rewardapp.RewardService.exception;

public class RewardAlreadyAssignedToUserException extends RuntimeException
{
	public RewardAlreadyAssignedToUserException( String message )
	{
		super( message );
	}
}
