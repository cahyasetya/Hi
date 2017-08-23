package id.my.developer.hi;

import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;

/**
 * Created by light on 15/08/2017.
 */

public interface SubscribeCallbackInterface {
    void onPNConnected();
    void onNewMessage(PNMessageResult result);
    void onPresence();
}
