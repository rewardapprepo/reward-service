package org.coffeeapp.rewardapp.RewardService.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.http.HttpStatus;
import org.coffeeapp.rewardapp.RewardService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class UserClientException implements ErrorDecoder
{

	private Environment environment;

	@Autowired
	public UserClientException( Environment environment )
	{
		this.environment = environment;
	}

	@Override public Exception decode( String s, Response response )
	{
		switch ( response.status( ) )
		{
		case HttpStatus
			.SC_NOT_FOUND:
			return new EntityNotFoundException( User.class, "id" + "id");
		default:
			return new Exception( response.reason( ) );
		}
	}
}
