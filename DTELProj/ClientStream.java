package com.hobbs.dtel;

import android.content.Context;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

public class ClientStream {

    private static SyncHttpClient syncClient = new SyncHttpClient();

    public static void post(
            Context context,
            String url,
            RequestParams params,
            JsonHttpResponseHandler responseHandler) {

        syncClient.post(context, url, params, responseHandler);
    }
}
