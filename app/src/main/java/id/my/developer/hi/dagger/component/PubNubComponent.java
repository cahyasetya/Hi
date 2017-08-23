package id.my.developer.hi.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import id.my.developer.hi.dagger.module.PubNubModule;
import id.my.developer.hi.main_activity.MainActivityPresenter;

/**
 * Created by light on 15/08/2017.
 */
@Singleton
@Component(modules = PubNubModule.class)
public interface PubNubComponent {
    void inject(MainActivityPresenter activity);
}
