package org.coffeeapp.rewardapp.RewardService.service;

import org.coffeeapp.rewardapp.RewardService.entity.Reward;
import org.coffeeapp.rewardapp.RewardService.model.AssignRewardRequestModel;
import org.coffeeapp.rewardapp.RewardService.model.CreateRewardModel;
import org.coffeeapp.rewardapp.RewardService.model.RewardResponseModel;
import org.coffeeapp.rewardapp.RewardService.shared.RewardDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RewardService
{
	CreateRewardModel createReward( CreateRewardModel createRewardModel );

	List<RewardDto> getRewardsByUserId( Long id );

	List<RewardDto> getRewards( );

	RewardResponseModel getRewardById( Long id );

	RewardResponseModel assignUserToReward( AssignRewardRequestModel assignRewardRequestModel );
}
