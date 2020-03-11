package edu.sjsu.cmpe275.aop;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.sjsu.cmpe275.aop.tweet.TweetService;
import edu.sjsu.cmpe275.aop.tweet.TweetStatsService;

public class TweetTest {

    /*
     * These are dummy test cases. You may add test cases based on your own need.
     */
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
    TweetService tweeter = (TweetService) ctx.getBean("tweetService");
    TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");
    @Test
    public void testOne() {


        try {
            stats.resetStatsAndSystem();
            tweeter.follow("B", "A");
            tweeter.follow("C", "A");
            tweeter.follow("D", "A");
            tweeter.follow("E", "A");
            tweeter.follow("L", "X");
            tweeter.follow("C", "X");
            tweeter.follow("M", "X");
            tweeter.follow("N", "X");
            tweeter.tweet("A", "Apple");
            tweeter.tweet("A", "Ball");
            tweeter.block("A", "C");
            tweeter.block("X", "M");
            tweeter.tweet("X", "Dog");
            tweeter.tweet("A", "Cat");




        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
        System.out.println("Get MostBlocked Follower By Number Of Missed Tweets: "+stats.getMostBlockedFollowerByNumberOfMissedTweets());
        System.out.println("Get MostBlocked Follower By Number Of Followees: "+stats.getMostBlockedFollowerByNumberOfFollowees());
        System.out.println("Most productive user: " + stats.getMostProductiveUser());
        System.out.println("Most Followed user: " + stats.getMostFollowedUser());

        System.out.println("Most popular message: " + stats.getMostPopularMessage());
        ctx.close();

    }

    @Test
    public void testTwo() {
        try {
            stats.resetStatsAndSystem();
            tweeter.follow(" Z","B ");
            tweeter.follow("X ","A ");
            tweeter.follow("B"," C");
            tweeter.tweet(" A", "qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhgfdsazxcvbnmpqowieurytlaks");
            tweeter.tweet("A ", "hshdnjkdhdh");
            tweeter.tweet("B ", "hshdnjkdhdhjj");
            tweeter.block("B", "Z");
            tweeter.block("A", "X");
            tweeter.tweet(" A", "hshhdhdkjhjkd");
//





        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Most productive user: " + stats.getMostProductiveUser());
        System.out.println("Most popular user: " + stats.getMostFollowedUser());
        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
        System.out.println("Get MostBlocked Follower By Number Of Missed Tweets: "+stats.getMostBlockedFollowerByNumberOfMissedTweets());
        System.out.println("Get MostBlocked Follower By Number Of Followees: "+stats.getMostBlockedFollowerByNumberOfFollowees());
        System.out.println("Most productive user: " + stats.getMostProductiveUser());
        System.out.println("Most popular user: " + stats.getMostFollowedUser());
        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
        System.out.println("Most popular message: " + stats.getMostPopularMessage());
        ctx.close();

    }
    @Test
    public void testThree() {
        try {
            stats.resetStatsAndSystem();
            tweeter.follow("A", "One");
            tweeter.follow("B", "One");
            tweeter.follow("One", "A");
            tweeter.follow("One", "B");
            tweeter.follow("One", "C");
            tweeter.tweet("One", "Hello");
            tweeter.tweet("C", "Hello");
            tweeter.block("One", "B");
            tweeter.block("One", "C");
            tweeter.tweet("One", "Hello");
            tweeter.unblock("One", "C");
            tweeter.tweet("One", "Hello");
            tweeter.block("One", "C");
            tweeter.tweet("One", "Aiya");

  	  /*	stats.resetStatsAndSystem();
        tweeter.follow("A", "One");
        tweeter.follow("B", "One");
        tweeter.follow("One", "A");
        tweeter.follow("One", "B");
        tweeter.follow("One", "C");
        tweeter.tweet("One", "Hello");
        tweeter.tweet("C", "Hello"); */








        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Most productive user: " + stats.getMostProductiveUser());
        System.out.println("Most popular user: " + stats.getMostFollowedUser());
        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
        System.out.println("Get MostBlocked Follower By Number Of Missed Tweets: "+stats.getMostBlockedFollowerByNumberOfMissedTweets());
        System.out.println("Get MostBlocked Follower By Number Of Followees: "+stats.getMostBlockedFollowerByNumberOfFollowees());
        System.out.println("Most productive user: " + stats.getMostProductiveUser());
        System.out.println("Most popular user: " + stats.getMostFollowedUser());
        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
        System.out.println("Most popular message: " + stats.getMostPopularMessage());
        ctx.close();

    }
    @Test
    public void testFour() {


        try {
            stats.resetStatsAndSystem();

            tweeter.tweet("A", "Apple");
            tweeter.tweet("A", "Ball");
            tweeter.block("A", "C");
            tweeter.block("X", "M");
            tweeter.tweet("X", "Dog");
            tweeter.tweet("A", "Cat");
//
//



        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
        System.out.println("Get MostBlocked Follower By Number Of Missed Tweets: "+stats.getMostBlockedFollowerByNumberOfMissedTweets());
        System.out.println("Get MostBlocked Follower By Number Of Followees: "+stats.getMostBlockedFollowerByNumberOfFollowees());
        System.out.println("Most productive user: " + stats.getMostProductiveUser());
        System.out.println("Most Followed user: " + stats.getMostFollowedUser());
        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
        System.out.println("Most popular message: " + stats.getMostPopularMessage());
        ctx.close();

    }

    @Test
    public void testCaseN() {
        try {
            stats.resetStatsAndSystem();
            tweeter.follow("Carol", "Alex");
            tweeter.follow("Carol", "Bob");

            tweeter.follow("Daisy", "Alex");
            tweeter.tweet("Alex", "Ball");
            tweeter.tweet("Bob", "Ball");
            tweeter.block("Alex","Daisy");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Most productive user: " + stats.getMostProductiveUser());
        System.out.println("Most popular user: " + stats.getMostFollowedUser());
        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
        System.out.println("Get MostBlocked Follower By Number Of Missed Tweets: "+stats.getMostBlockedFollowerByNumberOfMissedTweets());
        System.out.println("Get MostBlocked Follower By Number Of Followees: "+stats.getMostBlockedFollowerByNumberOfFollowees());
        System.out.println("Most productive user: " + stats.getMostProductiveUser());
        System.out.println("Most Followed user: " + stats.getMostFollowedUser());
        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
        System.out.println("Most popular message: " + stats.getMostPopularMessage());
        ctx.close();

    }
}