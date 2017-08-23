package id.my.developer.hi.main_activity;

import com.pubnub.api.models.consumer.pubsub.PNMessageResult;

/**
 * Created by light on 15/08/2017.
 */

public interface MainActivityView {
    void onMessageReceived(PNMessageResult result);
    void onMessageSent();
}
