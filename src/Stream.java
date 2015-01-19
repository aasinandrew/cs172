import java.util.Date;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public final class Stream {
    public static void main(String[] args) throws TwitterException {
    	
    	//authenticate stream
    	ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("htQrouJTZMdgRn6EvijRvfDkC");
		cb.setOAuthConsumerSecret("89TqaYw0uyvr0A0FaavR9WIvLASJkGR2T6kRMXvgRqM9PkbHnS");
		cb.setOAuthAccessToken("2887968636-nMSmcXxzuq6bLm5vl247H9EmMHuT4IjDCZ4KXko");
		cb.setOAuthAccessTokenSecret("IZONMOMRlgzqla4QTsQWChMPyLVmrbVtw24m8J823kmVA");
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        
        
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
            	User user = status.getUser();
                String username = status.getUser().getScreenName();
                System.out.println("@" + username);
                Date date = status.getCreatedAt();
                System.out.println(date);
                String profileLocation = user.getLocation();
                System.out.println(profileLocation);
                String content = status.getText();
                System.out.println(content +"\n");
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                //System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                //System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                //System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        
        twitterStream.addListener(listener);
        twitterStream.sample();
    }
}
