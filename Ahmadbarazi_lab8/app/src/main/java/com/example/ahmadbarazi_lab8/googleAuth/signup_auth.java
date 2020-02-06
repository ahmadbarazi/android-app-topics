package com.example.ahmadbarazi_lab8.googleAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadbarazi_lab8.Home_page;
import com.example.ahmadbarazi_lab8.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import es.dmoral.toasty.Toasty;

public class signup_auth extends AppCompatActivity {
    private TextInputLayout emailId, password, confpassword;
    private Button btnSignUp;
    private TextView tvSignIn;
    private FirebaseAuth mFirebaseAuth;
    private ProgressBar progressBar;
    private long backPressedTime;
    private Toast backToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_auth);

        mFirebaseAuth = FirebaseAuth.getInstance();

        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        confpassword = findViewById(R.id.editText3);
        btnSignUp = findViewById(R.id.button2);
        tvSignIn = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailId.getEditText().getText().toString().trim();
                String pwd = password.getEditText().getText().toString();
                String confpwd = confpassword.getEditText().getText().toString();

                if (email.isEmpty() && pwd.isEmpty() && confpwd.isEmpty()) {
                    emailId.setError("Please enter email id");
                    password.setError("Please enter your password");
                    confpassword.setError("password doesn't match");
                    Toasty.warning(signup_auth.this, "Fields Are Empty!", Toast.LENGTH_SHORT,true).show();

                } else if (email.isEmpty()) {
                    emailId.setError("Enter a valid email");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailId.setError("Enter a valid email");

                } else if (pwd.isEmpty()) {
                    password.setError("Please enter your password");
                } else if (!pwd.equals(confpwd)) {
                    confpassword.setError("password doesn't match");
                } else if (!(email.isEmpty() && pwd.isEmpty() && confpwd.isEmpty()) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    progressBar.setVisibility(View.VISIBLE);
                    emailId.setError(null);
                    password.setError(null);
                    confpassword.setError(null);
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(signup_auth.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                try {
                                    throw task.getException();

                                } catch (FirebaseAuthUserCollisionException email) {

                                    Toasty.warning(signup_auth.this, "Email Already Exists", Toast.LENGTH_SHORT, true).show();
                                    progressBar.setVisibility(View.GONE);

                                } catch (Exception e) {

                                    Toasty.info(signup_auth.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT,true).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            } else {
                                Intent gotoHome = new Intent(signup_auth.this, Home_page.class);
                                gotoHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(gotoHome);
                            }
                        }
                    });
                } else {
                    Toasty.warning(signup_auth.this, "Error Occurred!", Toast.LENGTH_SHORT,true).show();

                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signup_auth.this, login_auth.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        if (backPressedTime + 2000 > System.currentTimeMillis()) {
//            backToast.cancel();
//            super.onBackPressed();
//            return;
//        } else {
//            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
//            backToast.show();
//        }
//
//        backPressedTime = System.currentTimeMillis();
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        UIUtil.hideKeyboard(this);
//        return super.onTouchEvent(event);
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) ev.getRawX(), (int) ev.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
