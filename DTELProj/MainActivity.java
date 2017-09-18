package com.hobbs.dtel;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    final static String TAG = "x";

    String[] creds = new String[3];
    boolean selectEditTextName = true;
    public String message;
    private Context mContext;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = getApplicationContext();
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText plusEmail = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);

        setUpListener(name, plusEmail, password);



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public boolean setUpListener(EditText user, EditText email, EditText pass) {
        final EditText fixer = user;
        if (selectEditTextName) {
            user.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    creds[0] = null;
                    creds[0] = fixer.getText().toString();
                    //Log.i("Name: ", creds[0]);
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                        Log.i(TAG, "Enter pressed");
                    }
                    return false;
                }
            });
            selectEditTextName = false;
        } else {
            user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    creds[0] = null;
                    creds[0] = fixer.getText().toString();
                    //Log.i("Name: ", creds[0]);
                }
            });
        }

        final EditText fixDis = email;
        fixDis.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                creds[0] = fixer.getText().toString();
                Log.i("Name: ", creds[0]);
                creds[1] = null;
                creds[1] = fixDis.getText().toString();
                //Log.i("Email: ", creds[1]);
            }
        });
        final EditText holder = pass;
        holder.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                creds[1] = fixDis.getText().toString();
                Log.i("Email: ", creds[1]);
                creds[2] = null;
                creds[2] = holder.getText().toString();
                //Log.i("Password: ", creds[2]);
            }
        });

        pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                creds[2] = null;
                creds[2] = holder.getText().toString();
                //Log.i("password: ", creds[2]);
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.i(TAG, "Enter pressed");
                }
                Log.i("Creds: ", creds[0] + " " + creds[1] + " " + creds[2] + " ");


                JSONObject jo = new JSONObject();
                try {
                    jo.put("user_name", creds[0]);
                    jo.put("user_email", creds[1]);
                    jo.put("user_password", creds[2]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONArray ja = new JSONArray();
                ja.put(jo);
                message = jo.toString();
                Log.i("JSON: ", message);
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            CallAPI call = new CallAPI();
                            call.execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();

                return false;
            }
        });
        return true;
    }

    public class CallAPI extends AsyncTask<Void, Void, Boolean> {

        public CallAPI() {
            //set context variables if required
        }

        private String jsonResponse;
        @Override
        protected Boolean doInBackground(Void... voids) {
            RequestParams reqParams = new RequestParams();
            reqParams.put("Name", creds[0]);
            reqParams.put("Email", creds[1]);
            reqParams.put("Password", creds[2]);
            Log.i("params", reqParams.toString());

            SharedPreferences settings = PreferenceManager
                    .getDefaultSharedPreferences(mContext);
            final SharedPreferences.Editor editor = settings.edit();

            ClientStream.post(
                    mContext,
                    "http://mrmqesbmj88vrrzqn-mock.stoplight-proxy.io/api/users",
                    reqParams,
                    new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            // called when response HTTP status is "200 OK"
                            Log.i("response: ", response.toString());

                            try {
                                String token = response.getString("user_token");
                                Log.i("token: ", token);
                                editor.putString("user_token", token);
                                editor.apply();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers,
                                              Throwable throwable, JSONObject response) {

                        }
                    });

            return true;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

}
