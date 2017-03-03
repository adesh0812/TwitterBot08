/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twittterbot08;

import java.util.ArrayList;
import java.util.List;
import twitter4j.DirectMessage;
import twitter4j.IDs;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Adesh Srivastava
 */
public class TwittterBot08
{

    public static final String consumerKey="--";
    public static final String consumerSecret="------";
    public static final String accessToken="---";
    public static final String accessTokenSecret="------";
      
    public static Twitter twitter;
    
    /**
     * @param consumerKey
     * @param consumerSecret
     * @param accessToken
     * @param accessTokenSecret
     * @return 
     */
    
    public Twitter intializeToken(String consumerKey,String consumerSecret,String accessToken,String accessTokenSecret)
    {
        
        ConfigurationBuilder configurationBuilder=new ConfigurationBuilder();
        
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        Configuration configuration=configurationBuilder.build();
        
        if(configuration!=null)
            System.out.println("Configuration is Authenticated!");
        
        TwitterFactory twitterFactory =new TwitterFactory(configuration);
        Twitter twitter=twitterFactory.getInstance();
        
        return twitter;
    }
    
    public DirectMessage sendDMFromBot(String screenName,String text) throws TwitterException
    {
        DirectMessage msg=twitter.sendDirectMessage(screenName, text);
        return msg;
    }
    
    public ArrayList<Long> getFollowerIDs(String screenName) throws TwitterException
    {
        ArrayList<Long> result=new ArrayList();
        long cursor=-1; 
        IDs ids;
        do
        {
            ids=twitter.getFollowersIDs(screenName, cursor);
            for (long id:ids.getIDs())
            {
                result.add(id);
            }
        }
        while((cursor=ids.getNextCursor())!=0);
        return result;
    }
    
    public boolean statusUpdate(String text) throws TwitterException
    {
        Status status=twitter.updateStatus(text);
        return true;
    }
    
}
