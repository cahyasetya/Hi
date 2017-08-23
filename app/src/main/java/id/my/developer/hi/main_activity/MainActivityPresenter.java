package id.my.developer.hi.main_activity;

import android.content.Context;
import android.widget.Toast;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;

import java.util.Arrays;

import javax.inject.Inject;

import id.my.developer.hi.MainActivity;
import id.my.developer.hi.SubscribeCallbackInterface;
import id.my.developer.hi.dagger.component.DaggerPubNubComponent;
import id.my.developer.hi.dagger.component.PubNubComponent;
import id.my.developer.hi.dagger.module.PubNubModule;

/**
 * Created by light on 15/08/2017.
 */

public class MainActivityPresenter implements SubscribeCallbackInterface{

    private Context context;

    @Inject
    PubNub pubNub;

    public MainActivityPresenter(Context context) {
        this.context = context;
        PubNubComponent pubNubComponent = DaggerPubNubComponent.builder().pubNubModule(new PubNubModule(this)).build();
        pubNubComponent.inject(this);
    }

    public void subscribe(String channel){
        pubNub.subscribe().channels(Arrays.asList(channel)).execute();
    }

    public void publish(String name, String message, String channel){
        pubNub.publish().message(Arrays.asList(name, message)).channel(channel).async(new PNCallback<PNPublishResult>() {
            @Override
            public void onResponse(PNPublishResult result, PNStatus status) {
                ((MainActivityView)context).onMessageSent();
            }
        });
    }

    public void unsubscribe(){
        pubNub.unsubscribe();
    }

    @Override
    public void onPNConnected() {
        Toast.makeText(context,"Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNewMessage(PNMessageResult result) {
        ((MainActivityView)context).onMessageReceived(result);
    }

    @Override
    public void onPresence() {

    }
}
