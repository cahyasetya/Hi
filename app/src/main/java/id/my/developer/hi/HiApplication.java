package id.my.developer.hi;

import android.app.Application;

import id.my.developer.hi.dagger.component.DaggerPubNubComponent;
import id.my.developer.hi.dagger.component.PubNubComponent;
import id.my.developer.hi.dagger.module.PubNubModule;

/**
 * Created by light on 15/08/2017.
 */

public class HiApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
