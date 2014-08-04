package com.intellio.tesa;

import android.accounts.AccountManager;
import android.content.Context;

import com.intellio.tesa.authenticator.ApiKeyProvider;
import com.intellio.tesa.authenticator.AuthenticatorActivity;
import com.intellio.tesa.authenticator.LogoutService;
import com.intellio.tesa.authenticator.SignupActivity;
import com.intellio.tesa.core.TesaService;
import com.intellio.tesa.core.Constants;
import com.intellio.tesa.core.PostFromAnyThreadBus;
import com.intellio.tesa.core.RestAdapterRequestInterceptor;
import com.intellio.tesa.core.RestErrorHandler;
import com.intellio.tesa.core.UserAgentProvider;
import com.intellio.tesa.ui.ImagePagerActivity;
import com.intellio.tesa.ui.MainActivity;
import com.intellio.tesa.ui.NavigationDrawerFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intellio.tesa.ui.ProductActivity;
import com.intellio.tesa.ui.ProductListFragment;
import com.intellio.tesa.ui.UserListFragment;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Dagger module for setting up provides statements.
 * Register all of your entry points below.
 */
@Module(
        complete = false,

        injects = {
                TesaApplication.class,
                AuthenticatorActivity.class,
                SignupActivity.class,
                MainActivity.class,
                ProductActivity.class,
                UserListFragment.class,
                ProductListFragment.class,
                NavigationDrawerFragment.class
               , ImagePagerActivity.class


        }
)
public class TesaModule {

    @Singleton
    @Provides
    Bus provideOttoBus() {
        return new PostFromAnyThreadBus();
    }

/*    @Provides
    @Singleton
    LogoutService provideLogoutService(final Context context, final AccountManager accountManager) {
        return new LogoutService(context, accountManager);
    }*/

    @Provides
    TesaService provideTesaService(RestAdapter restAdapter) {
        return new TesaService(restAdapter);
    }

    @Provides
    TesaServiceProvider provideTesaServiceProvider(RestAdapter restAdapter, ApiKeyProvider apiKeyProvider) {
        return new TesaServiceProvider(restAdapter, apiKeyProvider);
    }

    @Provides
    ApiKeyProvider provideApiKeyProvider(AccountManager accountManager) {
        return new ApiKeyProvider(accountManager);
    }

    @Provides
    Gson provideGson() {
        /**
         * GSON instance to use for all request  with date format set up for proper parsing.
         * <p/>
         * You can also configure GSON with different naming policies for your API.
         * Maybe your API is Rails API and all json values are lower case with an underscore,
         * like this "first_name" instead of "firstName".
         * You can configure GSON as such below.
         * <p/>
         *
         * public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd")
         *         .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
         */
        return new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    }

    @Provides
    RestErrorHandler provideRestErrorHandler(Bus bus) {
        return new RestErrorHandler(bus);
    }

    @Provides
    RestAdapterRequestInterceptor provideRestAdapterRequestInterceptor(UserAgentProvider userAgentProvider) {
        return new RestAdapterRequestInterceptor(userAgentProvider);
    }

    @Provides
    RestAdapter provideRestAdapter(RestErrorHandler restErrorHandler, RestAdapterRequestInterceptor restRequestInterceptor, Gson gson) {
        return new RestAdapter.Builder()
                .setEndpoint(Constants.Http.URL_BASE)
                .setErrorHandler(restErrorHandler)
                .setRequestInterceptor(restRequestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();
    }

}
