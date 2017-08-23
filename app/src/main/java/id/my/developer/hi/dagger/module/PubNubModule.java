package id.my.developer.hi.dagger.module;

import android.util.Log;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.my.developer.hi.SubscribeCallbackInterface;
import id.my.developer.hi.main_activity.MainActivityPresenter;

/**
 * Created by light on 15/08/2017.
 */
@Module
public class PubNubModule {
    private SubscribeCallbackInterface callback;

    public PubNubModule(SubscribeCallbackInterface callback) {
        this.callback = callback;
    }

    @Provides
    @Singleton
    PNConfiguration providesPNConfiguration(){
        PNConfiguration pnConfiguration=new PNConfiguration().setSubscribeKey("sub-c-e404a84e-819c-11e7-8979-5e3a640e5579").setPublishKey("pub-c-c9af8dea-ef67-46ed-8d17-c7ac44259457");
        return pnConfiguration;
    }

    @Provides
    @Singleton
    PubNub providesPubNub(PNConfiguration pnConfiguration, SubscribeCallback subscribeCallback){
        PubNub pubNub = new PubNub(pnConfiguration);
        pubNub.addListener(subscribeCallback);
        return pubNub;
    }

    @Provides
    @Singleton
    SubscribeCallback providesSubscribeCallback(){
        return new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                if(status.getOperation()!=null){
                    switch (status.getOperation()){
                        case PNSetStateOperation:
                        case PNUnsubscribeOperation:
                            switch (status.getCategory()){
                                case PNConnectedCategory:
                                    callback.onPNConnected();
                                    break;
                                default:
                                    Log.d("UNKNOWNERROR", "UNKNOWNERROR");
                            }
                    }
                }else{
                    Log.d("UNKNOWNERROR", "UNKNOWNERROR");
                }
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                callback.onNewMessage(message);
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        };
    }
}
