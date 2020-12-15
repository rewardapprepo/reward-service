package org.coffeeapp.rewardapp.RewardService.repository;

import org.coffeeapp.rewardapp.RewardService.entity.Reward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends CrudRepository<Reward, Long>
{
	List<Reward> findByUsersIn( List<Long> users );
}
