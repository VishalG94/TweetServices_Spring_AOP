package edu.sjsu.cmpe275.aop.tweet.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.tweet.TweetStatsServiceImpl;

import java.io.IOException;

@Aspect
@Order(2)
public class StatsAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */
	private static int longestTweet;
	public void setLongestTweet(int messageLen){
		this.longestTweet=messageLen;
	}
	public int getLongestTweet(String message){
		return longestTweet;
	}
	@Autowired TweetStatsServiceImpl stats;
	
//	@After("execution(public * edu.sjsu.cmpe275.aop.tweet.TweetService.*(..))")
//	public void dummyAfterAdvice(JoinPoint joinPoint) {
//		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
//		//stats.resetStats();
//	}
//
//	@Before("execution(public void edu.sjsu.cmpe275.aop.tweet.TweetService.follow(..))")
//	public void dummyBeforeAdvice(JoinPoint joinPoint) {
//		System.out.printf("Before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
//	}




	@AfterReturning(pointcut = "execution(public void edu.sjsu.cmpe275.aop.tweet.TweetService.tweet(..))")
	public void afterTweet(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		stats.addTweet((String) args[0], (String) args[1]);
	}

	@AfterReturning(pointcut = "execution(public void edu.sjsu.cmpe275.aop.tweet.TweetService.follow(..))")
	public void afterFollow(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		stats.addFollower((String) args[0], (String) args[1]);
	}

	@AfterReturning(pointcut = "execution(public void edu.sjsu.cmpe275.aop.tweet.TweetService.block(..))")
	public void afterBlock(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		stats.blockFollower((String) args[0], (String) args[1]);
	}

	@AfterReturning(pointcut = "execution(public void edu.sjsu.cmpe275.aop.tweet.TweetService.unblock(..))")
	public void afterUnblock(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		stats.unblockFollower((String) args[0], (String) args[1]);
	}



//	@Before("execution(public void edu.sjsu.cmpe275.aop.tweet.TweetStatsServiceImpl.addTweet(..))")
//	public void addtweetBeforeAdvice(JoinPoint joinPoint)	throws IllegalArgumentException, IOException {
//		System.out.printf("Before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
//		Object args[] = joinPoint.getArgs();
//		if(args[0]==null || args[1]==null || (String)args[0] == "" || (String)args[1]== ""){
//			throw new IllegalArgumentException("Invalid Argument, Please provide valid data!");
//		}
//		if( ((String)args[1]).length()>140){
//			throw new IllegalArgumentException("Invalid Argument, Tweet cannot be more than 140 characters.");
//		}
//	}
//
//	@After("execution(public void edu.sjsu.cmpe275.aop.tweet.TweetStatsServiceImpl.addTweet(..))")
//	public void addtweetAfterAdvice(JoinPoint joinPoint)	throws IllegalArgumentException, IOException {
//		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
//		Object args[] = joinPoint.getArgs();
//
//		String message = (String) args[1];
//		if(message.length()>=longestTweet){
//			longestTweet=message.length();
//		}
//	}
//
//	@Before("execution(public void edu.sjsu.cmpe275.aop.tweet.TweetStatsServiceImpl.addFollower(..))")
//	public void addFollowerBeforeAdvice(JoinPoint joinPoint)	throws IllegalArgumentException, IOException {
//		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
//		Object args[] = joinPoint.getArgs();
//
//		String message = (String) args[1];
//		if(message.length()>=longestTweet){
//			longestTweet=message.length();
//		}
//	}
}
