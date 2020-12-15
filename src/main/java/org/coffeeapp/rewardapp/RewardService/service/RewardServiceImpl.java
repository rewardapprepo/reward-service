package org.coffeeapp.rewardapp.RewardService.service;

import org.coffeeapp.rewardapp.RewardService.entity.Reward;
import org.coffeeapp.rewardapp.RewardService.exception.EntityNotFoundException;
import org.coffeeapp.rewardapp.RewardService.exception.RewardAlreadyAssignedToUserException;
import org.coffeeapp.rewardapp.RewardService.model.*;
import org.coffeeapp.rewardapp.RewardService.repository.RewardRepository;
import org.coffeeapp.rewardapp.RewardService.shared.RewardDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RewardServiceImpl implements RewardService

{
	private final ModelMapper modelMapper;
	private RewardRepository rewardRepository;
	private UserServiceClient userServiceClient;

	@Autowired
	public RewardServiceImpl( RewardRepository rewardRepository, UserServiceClient userServiceClient )
	{
		this.userServiceClient = userServiceClient;
		this.rewardRepository = rewardRepository;
		modelMapper = new ModelMapper( );
		modelMapper.getConfiguration( ).setMatchingStrategy( MatchingStrategies.STRICT );
	}

	@Override public CreateRewardModel createReward( CreateRewardModel createRewardModel )
	{
		Reward savedReward = rewardRepository.save( modelMapper.map( createRewardModel, Reward.class ) );
		return modelMapper.map( savedReward, CreateRewardModel.class );
	}

	@Override public List<RewardDto> getRewardsByUserId( Long userId )
	{
		List<RewardDto> rewards = new ArrayList<>( );

		List<Reward> userRewards = rewardRepository.findByUsersIn( Arrays.asList( userId ) );
		userRewards.stream( ).forEach( reward -> rewards.add( modelMapper.map( reward, RewardDto.class ) ) );

		return rewards;
	}

	//rewardId ekle
	@Override public List<RewardDto> getRewards( )
	{
		List<RewardDto> rewards = new ArrayList<>( );
		rewardRepository.findAll( ).forEach( reward -> {
			rewards.add( modelMapper.map( reward, RewardDto.class ) );
		} );
		return rewards;
	}

	@Override public RewardResponseModel getRewardById( Long id )
	{
		Reward reward = rewardRepository.findById( id ).orElse( null );
		if ( reward == null )
		{
			throw new EntityNotFoundException( Reward.class, "id", id.toString( ) );
		}
		List<User> users = userServiceClient.getUsersByRewardId( reward.getId( ) );
		RewardResponseModel rewardResponseModel = modelMapper.map( reward, RewardResponseModel.class );
		rewardResponseModel.setUsers( users );
		return rewardResponseModel;
	}

	@Override public RewardResponseModel assignUserToReward( AssignRewardRequestModel assignRewardRequestModel )
	{
		Reward reward = rewardRepository.findById( assignRewardRequestModel.getRewardId( ) ).orElse( null );

		if ( reward == null )
		{
			throw new EntityNotFoundException( Reward.class, "id", assignRewardRequestModel.getRewardId( ).toString( ) );
		}
		Long idOfNewUser = assignRewardRequestModel.getUserId( );

		if ( isUserAlreadyAssignedToReward( reward, idOfNewUser ) == true )
		{
			throw new RewardAlreadyAssignedToUserException( "This reward already assigned to the user" );
		}

		sendAddingRewardRequestToUserService( assignRewardRequestModel );

		addUserToRewardEntity( idOfNewUser, reward );

		return null;
	}

	private void addUserToRewardEntity( Long userId, Reward reward )
	{
		List<Long> existingUsers = reward.getUsers( );
		existingUsers.add( userId );
		reward.setUsers( existingUsers );
		rewardRepository.save( reward );
	}

	private void sendAddingRewardRequestToUserService( AssignRewardRequestModel assignRewardRequestModel )
	{
		AddRewardRequestModel addRewardRequestModel = new AddRewardRequestModel( );
		addRewardRequestModel.setRewardId( assignRewardRequestModel.getRewardId( ) );
		addRewardRequestModel.setUserId( assignRewardRequestModel.getUserId( ) );
		try
		{
			userServiceClient.addReward( addRewardRequestModel );
		}
		catch ( Exception exception )
		{
			throw new EntityNotFoundException( User.class, "id", addRewardRequestModel.getUserId( ).toString( ) );
		}
	}

	//Do refactoring
	private boolean isUserAlreadyAssignedToReward( Reward reward, Long idOfNewUser )
	{
		Long foundUser = reward.getUsers( ).stream( ).filter( userId -> userId.equals( idOfNewUser ) ).findFirst( ).orElse( null );
		return foundUser == null ? false : true;
	}

}
