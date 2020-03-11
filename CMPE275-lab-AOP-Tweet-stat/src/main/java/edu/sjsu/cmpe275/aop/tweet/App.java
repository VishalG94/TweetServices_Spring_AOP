package edu.sjsu.cmpe275.aop.tweet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        /***
         * Following is a dummy implementation of App to demonstrate bean creation with Application context.
         * You may make changes to suit your need, but this file is NOT part of the submission.
         */

    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");

        try {
//            tweeter.follow("B", "A");
//            tweeter.follow("A", "B");
//            tweeter.follow("C", "A");
//            tweeter.follow("B", "A");
//            tweeter.follow("C", "A");
//            tweeter.follow("B", "C");
//            tweeter.block("A", "B");
//            tweeter.tweet("A", "Hi");
//            tweeter.tweet("A", "Bye");
//            tweeter.tweet("B", "Bye");
//            tweeter.tweet("B", "Hi");
//            tweeter.tweet("C", "Bye");
//            tweeter.tweet("B", "Tata");
//            tweeter.tweet("C", "Tata");

//            tweeter.follow("bob", "alex");
//            tweeter.follow("carl", "bob");
//            tweeter.tweet("alex", "First Tweet");
//            tweeter.block("alex", "bob");
//            tweeter.block("Carl", "bob");
//            tweeter.block("alex", "Carl");
//            tweeter.tweet("alex", "second tweet");
//            tweeter.tweet("bob", "second tweet");
            tweeter.follow("Farha", "Farhan");
            tweeter.follow("Farhan", "Farha");
            tweeter.follow("Farha", "Farhat");
            tweeter.follow("Farhat", "Farha");
            tweeter.follow("Farhan", "Farhat");
            tweeter.follow("Farhat", "Farhan");
            tweeter.tweet("Farha", "Well");
            tweeter.block("Farha", "Farhan");
            tweeter.tweet("Farha", "Well");
            tweeter.unblock("Farha", "Farhan");
            tweeter.tweet("Farha", "Wello");
            tweeter.tweet("Farha", "Wello");
            tweeter.tweet("Farha", "Aello");
            tweeter.tweet("Farhat", "Aello");
            tweeter.tweet("Farhat", "Well");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("");System.out.println("");System.out.println("");
//        System.out.println("Most productive user: " + stats.getMostProductiveUser());
//        System.out.println("");
//        System.out.println("Most popular user: " + stats.getMostFollowedUser());
//        System.out.println("");
//        System.out.println("Most popular message: " + stats.getMostPopularMessage());
          System.out.println("Most popular message: " + stats.getMostPopularMessage());
//        System.out.println("getMostBlockedFollowerByNumberOfFollowees: "+stats.getMostBlockedFollowerByNumberOfFollowees());
//        System.out.println("");
//        System.out.println("");

        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
        ctx.close();
    }
}
