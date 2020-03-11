package edu.sjsu.cmpe275.aop.tweet.aspect;

import edu.sjsu.cmpe275.aop.tweet.TweetStatsServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class ValidationAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */

//	@Before("execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.retweet(..))")
//	public void dummyBeforeAdvice(JoinPoint joinPoint) {
//		System.out.printf("Permission check before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
//	}
	@Autowired TweetStatsServiceImpl stats;
	@Before("execution(* edu.sjsu.cmpe275.aop.tweet.TweetService.unblock(..))")
	public void beforeUnblockPreReq(JoinPoint joinPoint) {

		if(!stats.checkIfAlreadyBlocked(joinPoint.getArgs()[0].toString(),joinPoint.getArgs()[1].toString()))
		{
			throw new UnsupportedOperationException("Unblock not possible since, user is not blocked.s");
		}


	}
	@Before("execution(public void edu.sjsu.cmpe275.aop.tweet.TweetService.tweet(..))")
	public void beforeTweet(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		String user = (String) args[0];
		String follower = (String) args[1];
		if(args[0]==null || args[1]==null || user == "" || follower== ""){
			throw new IllegalArgumentException("Invalid input, Please provide valid inputs!");
		}
		if( ((String)args[1]).length()>140){
			throw new IllegalArgumentException("Invalid input, Tweet cannot be more than 140 characters.");
		}
	}

	@Before("execution(public void edu.sjsu.cmpe275.aop.tweet.TweetService.follow(..))")
	public void beforeFollow(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		String user = (String) args[0];
		String follower = (String) args[1];
		if(args[0]==null || args[1]==null || user == "" || follower== ""){
			throw new IllegalArgumentException("Invalid input, Please provide valid inputs!");
		}
		if(user.equals(follower)){
			throw new UnsupportedOperationException("Follower cannot follow himself!");
		}
	}

	@Before("execution(public void edu.sjsu.cmpe275.aop.tweet.TweetService.block(..))")
	public void beforeBlock(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		String user = (String) args[0];
		String follower = (String) args[1];
		if(args[0]==null || args[1]==null || user == "" || follower== ""){
			throw new IllegalArgumentException("Invalid input, Please provide valid inputs!");
		}
		if(user.equals(follower)){
			throw new UnsupportedOperationException("Follower cannot follow himself!");
		}
	}

	@Before("execution(public void edu.sjsu.cmpe275.aop.tweet.TweetService.unblock(..))")
	public void beforeUnblock(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		String user = (String) args[0];
		String follower = (String) args[1];
		if(args[0]==null || args[1]==null || user == "" || follower== ""){
			throw new IllegalArgumentException("Invalid input, Please provide valid inputs!");
		}
		if(user.equals(follower)){
			throw new UnsupportedOperationException("Follower cannot follow himself!");
		}

	}
	
}
