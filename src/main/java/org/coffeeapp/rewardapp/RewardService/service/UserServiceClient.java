package org.coffeeapp.rewardapp.RewardService.service;

import org.coffeeapp.rewardapp.RewardService.model.AddRewardRequestModel;
import org.coffeeapp.rewardapp.RewardService.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient( name = "user-ws" )
public interface UserServiceClient
{
	@PostMapping( value = "/users/add-reward" )
	public List<User> addReward( @Valid @RequestBody AddRewardRequestModel addRewardRequestModel );

	@GetMapping( value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE } )
	public List<User> getUsersByRewardId( @RequestParam Long rewardId );
}