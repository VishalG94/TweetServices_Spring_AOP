package edu.sjsu.cmpe275.aop.tweet;

import java.util.*;

public class TweetStatsServiceImpl implements TweetStatsService {
    /***
     * Following is a dummy implementation.
     * You are expected to provide an actual implementation based on the requirements.
     */
    private HashMap<String, HashSet<Integer>> userTweets = new HashMap<String, HashSet<Integer>>();
	private HashMap<Integer, String> messageIdList = new HashMap<Integer, String>();
	private HashMap<String, HashSet<String>> userFollowers = new HashMap<String, HashSet<String>>();
	private HashMap<String, HashSet<String>> userBlockedFollowers = new HashMap<String, HashSet<String>>();
	private HashMap<String, HashSet<String>> usersWhoBlockedFollowees = new HashMap<String, HashSet<String>>();
	private HashMap<String, Integer> fcount = new HashMap<String, Integer>();
	private HashMap<String, HashSet<String>> popularMessage = new HashMap<>();
//	private HashMap<Integer, HashSet<String>> messageDelivered = new HashMap<Integer, HashSet<String>>();
	private HashMap<String,Integer> msgLength = new HashMap<>();
	private HashMap<String, Integer> blockedTweets = new HashMap<>();
	private HashMap<String, HashSet<String>> sharedTweets = new HashMap<>();
//	PriorityQueue<Pair<String, HashSet<String>>> userFollowers = new PriorityQueue<>((a, b) -> ( a.getValue().size() == b.getValue().size() ? b.getKey().compareTo(a.getKey()) : a.getValue().size() - b.getValue().size() ));

	private static int longestTweet=0;
	private int msgId=0;

	//My Function
	public void setLongestTweet(int tweetLength) {

		// TODO Auto-generated method stub
		if(tweetLength<=140 && tweetLength>longestTweet){
			longestTweet=tweetLength;
		}
	}

	@Override
	public void resetStatsAndSystem() {
		// TODO Auto-generated method stub
		userTweets = new HashMap<String, HashSet<Integer>>();
		messageIdList = new HashMap<Integer, String>();
		userFollowers = new HashMap<String, HashSet<String>>();
		userBlockedFollowers = new HashMap<String, HashSet<String>>();
		usersWhoBlockedFollowees = new HashMap<String, HashSet<String>>();
		fcount = new HashMap<String, Integer>();
//		messageDelivered = new HashMap<Integer, HashSet<String>>();
		msgLength = new HashMap<String,Integer>();
		blockedTweets = new HashMap<String,Integer>();
		sharedTweets = new HashMap<String,HashSet<String>>();
		longestTweet=0;
		msgId=0;
	}

	@Override
	public String getMostPopularMessage() {
		// TODO Auto-generated method stub
		PriorityQueue<Map.Entry<String, HashSet<String>>> shareCount = new PriorityQueue<>((a,b) -> a.getValue().size()==b.getValue().size() ? a.getKey().compareTo(b.getKey()) : b.getValue().size()-a.getValue().size());
		// TODO Auto-generated method stub
		for(Map.Entry<String, HashSet<String>> follower: popularMessage.entrySet()) {
			shareCount.offer(follower);
		}
		if(shareCount.isEmpty()) return null;
		return shareCount.peek().getKey();
	}

	@Override
	public String getMostBlockedFollowerByNumberOfMissedTweets() {
		// TODO Auto-generated method stub
		PriorityQueue<Map.Entry<String, Integer>> blockCount = new PriorityQueue<>((a,b) -> a.getValue()==b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue()-a.getValue());
		// TODO Auto-generated method stub
		for(Map.Entry<String, Integer> follower: blockedTweets.entrySet()) {
			blockCount.offer(follower);
		}
		if(blockCount.isEmpty()) return null;
		return blockCount.peek().getKey();
	}

	@Override
	public String getMostFollowedUser() {
		PriorityQueue<Map.Entry<String, Integer>> followerQueue = new PriorityQueue<>((a,b) -> a.getValue()==b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue()-a.getValue());
		// TODO Auto-generated method stub
		for(Map.Entry<String, Integer> follower: fcount.entrySet()) {
			followerQueue.offer(follower);
		}
		if(followerQueue.isEmpty()) return null;
		return followerQueue.peek().getKey();
	}

	@Override
	public int getLengthOfLongestTweet() {
		// TODO Auto-generated method stub
		System.out.println("userTweets: "+userTweets);
		System.out.println("messageIdList: "+messageIdList);
		System.out.println("userFollowers: "+userFollowers);
		System.out.println("userBlockedFollowers: "+userBlockedFollowers);
		System.out.println("usersWhoBlockedFollowees: "+ usersWhoBlockedFollowees);
		System.out.println("fcount ;"+fcount);
		System.out.println("msgLength: "+ msgLength);
		System.out.println("blockedTweets: "+blockedTweets);
		System.out.println("sharedTweets: "+ sharedTweets);
		System.out.println("popularMessage: "+ popularMessage);


		return longestTweet;
	}

	//Iterate over userTweets and get the length of all messages combined.
	@Override
	public String getMostProductiveUser() {
		// TODO Auto-generated method stub
		PriorityQueue<Map.Entry<String, Integer>> msgLenQueue = new PriorityQueue<>((a,b) -> a.getValue()==b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue()-a.getValue());
		for(Map.Entry<String, Integer> follower: msgLength.entrySet()) {
			msgLenQueue.offer(follower);
		}
		if(msgLenQueue.isEmpty()) return null;
		return msgLenQueue.peek().getKey();
	}

	@Override
	public String getMostBlockedFollowerByNumberOfFollowees() {
//		PriorityQueue<Map.Entry<String, HashSet<String>>> blockedFollowee = new PriorityQueue<>((a,b) -> a.getValue().size()==b.getValue().size() ? a.getKey().compareTo(b.getKey()) : b.getValue().size()-a.getValue().size());
		int res = 0;
		String result = null;
		if(usersWhoBlockedFollowees.isEmpty()) return null;

		for(Map.Entry<String, HashSet<String>> curr: usersWhoBlockedFollowees.entrySet()) {
			int count=0;
			String blockedUsr = curr.getKey();
			for(String x: curr.getValue()){
				if(userFollowers.containsKey(x)){
					count +=userFollowers.get(x).contains(blockedUsr)?1:0;
				}
			}

			if(count>res){
				res = count;
				result = blockedUsr;
			}
		}

		return result;
	}



	public void tweetData(String user, String message, int msgId){

		HashSet<Integer> userMessagesIds;
		int userMsgLen = 0;

		if(userTweets.containsKey(user)){
			userMessagesIds=  userTweets.get(user);
		}else{
			userMessagesIds=new HashSet<Integer>();
		}

		userMessagesIds.add(msgId);

		if(msgLength.containsKey(user)){
			userMsgLen = msgLength.get(user);
		}

		messageIdList.put(msgId, message);
		userTweets.put(user, userMessagesIds);
		userMsgLen +=message.length();

		msgLength.put(user, userMsgLen);

		//Do not share the tweet with blocked followers.
		if(userFollowers.containsKey(user)){
			HashSet<String> followers = userFollowers.get(user);

			for(String follower: followers){
				boolean isBlocked = false;
				if(userBlockedFollowers.containsKey(user)){
					HashSet<String> blockedUsers = userBlockedFollowers.get(user);
					if(blockedUsers.contains(follower)){
						int blockedMsg=0;
						isBlocked=true;
						if(blockedTweets.containsKey(follower)){
							blockedMsg=blockedTweets.get(follower);
						}
						++blockedMsg;
						blockedTweets.put(follower,blockedMsg);
					}else{
						HashSet<String> msgShared= new HashSet<>();
						if(popularMessage.containsKey(message)){
							msgShared= popularMessage.get(message);
//							msgShared= popularMessage.get(message);
						}
						msgShared.add(follower);
						popularMessage.put(message, msgShared);
					}

				}else{
					HashSet<String> msgShared= new HashSet<String>();
					if(popularMessage.containsKey(message)){
						msgShared= popularMessage.get(message);
					}
					msgShared.add(follower);
					popularMessage.put(message, msgShared);
				}
//				if(!isBlocked){
//					HashSet<String> msgShared= new HashSet<String>();
//					if(popularMessage.containsKey(message)){
//						msgShared= popularMessage.get(message);
//					}
//					msgShared.add(user);
//					popularMessage.put(message, msgShared);
//
////					HashSet<String> shareList;
////					if(sharedTweets.containsKey(follower)){
////						sharedMsg=sharedTweets.get(follower);
////					}
////					++sharedMsg;
////					sharedTweets.put(follower,sharedMsg);
//				}
			}
		}




	}

	public void addTweet(String user, String message){
		try{
			tweetData(user, message, ++msgId);
			setLongestTweet(message.length());
		}catch (Exception e){
			System.out.println(e);
		}

	}

	public void addFollower(String follower, String user){
		HashSet<String> userFollowersList;
		if(userFollowers.containsKey(user)){
			userFollowersList = userFollowers.get(user);
		}else{
			userFollowersList=new HashSet<String>();
		}

		if(!userFollowersList.contains(follower)){
			userFollowersList.add(follower);
			userFollowers.put(user, userFollowersList);
			fcount.put(user, userFollowersList.size());
		}
	}

	public boolean checkIfAlreadyBlocked(String user, String follower){
		if (userBlockedFollowers.containsKey(user)) {
			return userBlockedFollowers.get(user).contains(follower) && usersWhoBlockedFollowees.get(follower).contains(user);
		}
		return false;
	}

	public void blockFollower(String user, String follower){
		HashSet<String> userBlockedList = new HashSet<>();
		HashSet<String> userBlockedBy=new HashSet<>();


		if(userBlockedFollowers.containsKey(user)){
			userBlockedList = userBlockedFollowers.get(user);
		}


		// To maintain whom the user has blocked.
		if(userBlockedList.isEmpty()){
			userBlockedList.add(follower);
			userBlockedFollowers.put(user, userBlockedList);
		}else if(!userBlockedList.contains(follower)){
			userBlockedList.add(follower);
			userBlockedFollowers.put(user, userBlockedList);
		}

		// To maintain who all has blocked the user.
		if(usersWhoBlockedFollowees.containsKey(follower)){
			userBlockedBy = userBlockedFollowers.get(follower);
		}
		if(userBlockedBy==null){
			userBlockedBy=new HashSet<String>();
				userBlockedBy.add(user);
				usersWhoBlockedFollowees.put(follower, userBlockedBy);

//			}
		}else if(!userBlockedBy.contains(user)){
			userBlockedBy.add(user);
			usersWhoBlockedFollowees.put(follower, userBlockedBy);
		}

//		if(isBlockedNow)
//		{
//			int userBlockedCnt=0;
//			if(usersWhoBlockedFollowees.containsKey(follower)){
//				userBlockedCnt = usersWhoBlockedFollowees.get(follower);
//			}
//			++userBlockedCnt;
//			usersWhoBlockedFollowees.put(follower, userBlockedCnt);
//		}

	}

	public void unblockFollower(String user, String follower){
		HashSet<String> userBlockedList;
//		boolean isBlockedNow=false;

		if(userBlockedFollowers.containsKey(user)){
			userBlockedList = userBlockedFollowers.get(user);
			if(userBlockedList.contains(follower)){
				userBlockedList.remove(follower);
				if(userBlockedList.isEmpty()){
					userBlockedFollowers.remove(user);
				}else{
					userBlockedFollowers.put(user, userBlockedList);
				}

			}
		}
		HashSet<String> userBlockedBy;
		if(usersWhoBlockedFollowees.containsKey(user)){
			userBlockedBy = usersWhoBlockedFollowees.get(user);
			if(userBlockedBy.contains(follower)){
				userBlockedBy.remove(follower);
				if(userBlockedBy.isEmpty()){
					usersWhoBlockedFollowees.remove(user);
				}else{
					usersWhoBlockedFollowees.put(user, userBlockedBy);
				}

			}
		}
//		if(usersWhoBlockedFollowees.containsKey(follower)){
//			userBlockedCnt = usersWhoBlockedFollowees.get(follower);
//			--userBlockedCnt;
//			if(userBlockedCnt==0){
//				usersWhoBlockedFollowees.remove(follower);
//			}
//			usersWhoBlockedFollowees.put(follower, userBlockedCnt);
//		}

	}

	//	public void addFollower(String user, String follower){
//		HashSet<String> userFollowersList;
//		if(userFollowers.containsKey(user)){
//			userFollowersList = userFollowers.get(user);
//		}else{
//			userFollowersList=new HashSet<String>();
//		}
//
//		if(!userFollowersList.contains(follower)){
//			userFollowersList.add(follower);
//			userFollowers.put(user, userFollowersList);
//		}
//	}

//	public void addFollower(String user, String follower){
//		HashSet<String> userFollowersList =new HashSet<String>();
//		Iterator itr = userFollowers.iterator();
//		Pair<String, HashSet<String>> pair;
//		while (itr.hasNext()) {
//			pair = (Pair<String, HashSet<String>>) itr.next();
//			if(pair.getKey().equals(user)){
//				userFollowersList = pair.getValue();
//
//				if(!userFollowersList.contains(follower)){
//					userFollowersList.add(follower);
//					userFollowers.remove(itr);
//					Pair<String, HashSet<String>> newPair = new Pair<>(user, userFollowersList);
//					userFollowers.add(newPair);
//				}
//
//				break;
//			}
//		}



//		if(userFollowers.contains(user)){
//			userFollowersList = userFollowers.get(user);
//		}else{
//			userFollowersList=new HashSet<String>();
//		}


//	}
}



