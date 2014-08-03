
package com.intellio.tesa;

import android.accounts.AccountsException;
import android.app.Activity;

import com.intellio.tesa.authenticator.ApiKeyProvider;
import com.intellio.tesa.core.TesaService;

import java.io.IOException;

import retrofit.RestAdapter;

/**
 * Provider for a {@link com.intellio.tesa.core.TesaService} instance
 */
public class TesaServiceProvider {

    private RestAdapter restAdapter;
    private ApiKeyProvider keyProvider;

    public TesaServiceProvider(RestAdapter restAdapter, ApiKeyProvider keyProvider) {
        this.restAdapter = restAdapter;
        this.keyProvider = keyProvider;
    }

    /**
     * Get service for configured key provider
     * <p/>
     * This method gets an auth key and so it blocks and shouldn't be called on the main thread.
     *
     * @return tesa service
     * @throws IOException
     * @throws AccountsException
     */
    public TesaService getService(final Activity activity)
            throws IOException, AccountsException {
        // The call to keyProvider.getAuthKey(...) is what initiates the login screen. Call that now.
        keyProvider.getAuthKey(activity);


        return new TesaService(restAdapter);
    }
}
