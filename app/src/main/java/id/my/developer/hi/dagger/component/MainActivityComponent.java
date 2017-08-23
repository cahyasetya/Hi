package id.my.developer.hi.dagger.component;

import dagger.Component;
import id.my.developer.hi.MainActivity;
import id.my.developer.hi.dagger.module.MainActivityModule;
import id.my.developer.hi.dagger.scope.MainActivityScope;

/**
 * Created by light on 16/08/2017.
 */

@MainActivityScope
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
