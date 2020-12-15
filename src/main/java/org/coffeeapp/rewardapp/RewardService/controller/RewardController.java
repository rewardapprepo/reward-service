package org.coffeeapp.rewardapp.RewardService.controller;

import org.coffeeapp.rewardapp.RewardService.model.AssignRewardRequestModel;
import org.coffeeapp.rewardapp.RewardService.model.CreateRewardModel;
import org.coffeeapp.rewardapp.RewardService.model.RewardResponseModel;
import org.coffeeapp.rewardapp.RewardService.service.RewardService;
import org.coffeeapp.rewardapp.RewardService.shared.RewardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( "/rewards" )
public class RewardController
{
	RewardService rewardService;

	@Autowired
	public RewardController( RewardService rewardService )
	{
		this.rewardService = rewardService;
	}

	@PostMapping(
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public ResponseEntity<CreateRewardModel> createUser( @Valid @RequestBody CreateRewardModel createRewardModel )
	{
		return ResponseEntity.status( HttpStatus.CREATED ).body( rewardService.createReward( createRewardModel ) );
	}

	@PostMapping(
		value = "/assign-user",
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public ResponseEntity<RewardDto> assignUserToReward( @Valid @RequestBody AssignRewardRequestModel assignRewardModel )
	{
		rewardService.assignUserToReward( assignRewardModel );
		return ResponseEntity.status( HttpStatus.ACCEPTED ).body( null );
	}

	@GetMapping( params = { "userId" }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public ResponseEntity<List<RewardDto>> getRewardsByUserId( @RequestParam Long userId )
	{
		return ResponseEntity.status( HttpStatus.OK ).body( rewardService.getRewardsByUserId( userId ) );
	}

	@GetMapping( produces = { MediaType.APPLICATION_JSON_VALUE } )
	public ResponseEntity<List<RewardDto>> getRewards( )
	{
		return ResponseEntity.status( HttpStatus.OK ).body( rewardService.getRewards( ) );
	}

	@GetMapping( value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE } )
	public ResponseEntity<RewardResponseModel> getRewardById( @PathVariable( "id" ) Long id )
	{
		return ResponseEntity.status( HttpStatus.OK ).body( rewardService.getRewardById( id ) );
	}
}
