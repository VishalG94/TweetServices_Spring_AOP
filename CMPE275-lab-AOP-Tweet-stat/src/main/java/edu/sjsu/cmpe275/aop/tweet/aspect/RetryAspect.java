package edu.sjsu.cmpe275.aop.tweet.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.aspectj.lang.annotation.Around;

import java.io.IOException;

@Aspect
@Order(0)
public class RetryAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     * @throws Throwable 
     */

	@Around("execution(* edu.sjsu.cmpe275.aop.tweet.TweetService.*(..))")
	public Object tweetAround(ProceedingJoinPoint joinPoint) throws Throwable {
//		System.out.printf("@Around: Current Function %s\n" , joinPoint.getSignature().getName());
		Object obj;
		try {
			obj = joinPoint.proceed();

		} catch (IOException try1) {
			System.out.println("First attempt.");
			try {
				obj = joinPoint.proceed();
			} catch (IOException try2) {
				System.out.println("Trying to reconnect 2nd attempt");
				try {
					obj = joinPoint.proceed();
				} catch (IOException try3) {
					System.out.println("Trying to reconnect 3rd attempt");
					try {
						obj = joinPoint.proceed();
					} catch (IOException try4
					) {
						throw new IOException("Unable to run the function "+joinPoint.getSignature().getName()+" due to network Error!");
					}
				}
			}
		}
		return obj;
	}

}
