package id.my.developer.hi.dagger.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import id.my.developer.hi.adapter.ChatAdapter;
import id.my.developer.hi.dagger.scope.MainActivityScope;
import id.my.developer.hi.main_activity.MainActivityPresenter;
import id.my.developer.hi.model.Chat;

/**
 * Created by light on 16/08/2017.
 */
@Module
public class MainActivityModule {

    private Context context;

    public MainActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @MainActivityScope
    List<Chat> providesChatList(){
        return new ArrayList<Chat>();
    }

    @Provides
    @MainActivityScope
    ChatAdapter providesChatAdapter(List<Chat> chatList){
        return new ChatAdapter(context,chatList);
    }

    @Provides
    @MainActivityScope
    MainActivityPresenter providesMainActivityPresenter(){
        return new MainActivityPresenter(context);
    }

    @Provides
    @MainActivityScope
    LinearLayoutManager providesLayoutManager(){
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }
}
