package id.borneo.of.hard.hardofborneo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.borneo.of.hard.hardofborneo.R;
import id.borneo.of.hard.hardofborneo.helper.SQLiteHandler;
import id.borneo.of.hard.hardofborneo.helper.SessionManage;
import id.borneo.of.hard.hardofborneo.session.Controller;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmail;
    private EditText mPassword;
    private TextView btnRegister;

    private static final String TAG = Register.class.getSimpleName();
    private ProgressDialog pDialog;
    private SessionManage session;
    private SQLiteHandler db;
    private Button mEmailSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form

        initAction();

    }

    private void initAction() {
        mEmail = (AutoCompleteTextView) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        btnRegister = (TextView) findViewById(R.id.btnRegister);
        mEmailSignIn = (Button) findViewById(R.id.email_sign_in_button);

        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Register.class);
                startActivity(i);
            }
        });

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManage(getApplicationContext());

        if (session.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, Dashboard.class);
            startActivity(intent);
            finish();
        }

        mEmailSignIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(!email.isEmpty() && !password.isEmpty() ) {
                    checkLogin(email,password);
                } else {
                    Toast.makeText(getApplicationContext(),"Email & Password Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkLogin(final String email, final String password) {

        String tag_string_req = "req_login";
        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST, getString(R.string.url_base) + "/login.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email, uid, created_at);

                        // Launch main activity
                        Intent intent = new Intent(LoginActivity.this,
                                Dashboard.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }

        };
        Controller.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void hideDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.dismiss();
    }
}

