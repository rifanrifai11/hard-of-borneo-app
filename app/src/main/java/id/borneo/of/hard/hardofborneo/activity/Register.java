package id.borneo.of.hard.hardofborneo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import id.borneo.of.hard.hardofborneo.R;
import id.borneo.of.hard.hardofborneo.helper.SQLiteHandler;
import id.borneo.of.hard.hardofborneo.helper.SessionManage;
import id.borneo.of.hard.hardofborneo.utility.BaseActivity;

public class Register extends BaseActivity implements View.OnClickListener {

    private TextView btnLogin;
    private EditText mEmail;
    private EditText mPassword;
    private TextView btnRegister;

    private static final String TAG = "Register";
    private ProgressDialog pDialog;
    private SessionManage session;
    private SQLiteHandler db;
    private Button mEmailSignIn,verifyButtom;
    private boolean loggedIn = false;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnLogin = (TextView) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, LoginActivity.class);
                startActivity(i);
            }
        });

        initAction();
        mAuth = FirebaseAuth.getInstance();
    }

    private void initAction() {
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        findViewById(R.id.btnSignUp).setOnClickListener(this);
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    // [END on_start_check_user]

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    private void signOut() {
        mAuth.signOut();
        //updateUI(null);
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Required.");
            valid = false;
        } else {
            mEmail.setError(null);
        }

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Required.");
            valid = false;
        } else {
            mPassword.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            Intent intent = new Intent(Register.this,Dashboard.class);
            startActivity(intent);

        } else {
            Toast.makeText(Register.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnSignUp) {
            Intent in = new Intent(Register.this, Dashboard.class);
            startActivity(in);
        }
//        else if (i == R.id.email_sign_in_button) {
//            signIn(mEmail.getText().toString(), mPassword.getText().toString());
//        }
//        else if (i == R.id.sign_out_button) {
//            signOut();
//        } else if (i == R.id.verify_email_button) {
//            sendEmailVerification();
//        }
    }

}
