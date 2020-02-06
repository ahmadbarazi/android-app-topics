package com.example.ahmadbarazi_lab8.googleAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ahmadbarazi_lab8.Home_page;
import com.example.ahmadbarazi_lab8.R;
import com.example.ahmadbarazi_lab8.database.BookContract;
import com.example.ahmadbarazi_lab8.database.BookDBHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

public class login_auth extends AppCompatActivity {

    private TextInputLayout emailId,password;
    private Button btnSignIn,testbtn;
    private TextView tvSignUp,reset_password;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private long backPressedTime;
    private Toast backToast;
    private ProgressBar progressBar;
    private LottieAnimationView signInAnim;

    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_auth);

        BookDBHelper dbHelper = new BookDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        mFirebaseAuth = FirebaseAuth.getInstance();


        if (mFirebaseAuth.getCurrentUser() != null) {
            // User is signed in (getCurrentUser() will be null if not signed in)
            Intent intent = new Intent(this,Home_page.class);
            startActivity(intent);
            finish();
        }

        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnSignIn = findViewById(R.id.button2);
        tvSignUp = findViewById(R.id.textView);
        reset_password = findViewById(R.id.reset_password);
        progressBar = findViewById(R.id.progressBar);

//        testbtn = findViewById(R.id.testbtn);

//to check the state of the user #after register #login #logout
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){
                    Toast.makeText(login_auth.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login_auth.this, Home_page.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(login_auth.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };


//        testbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addItem();
//            }
//        });

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_auth.this,reset_password.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getEditText().getText().toString();
                String pwd = password.getEditText().getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(login_auth.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    progressBar.setVisibility(View.VISIBLE);
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(login_auth.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toasty.error(login_auth.this,"Check User Name/Password",Toast.LENGTH_SHORT,true).show();
                                progressBar.setVisibility(View.GONE);
                            }
                            else{
                                Intent intToHome = new Intent(login_auth.this, Home_page.class);
                                intToHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else{
                    Toasty.warning(login_auth.this,"Error Occurred!", Toast.LENGTH_SHORT,true).show();

                }

            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(login_auth.this, signup_auth.class);
//                intSignUp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intSignUp);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        UIUtil.hideKeyboard(this);
//        return super.onTouchEvent(event);
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)ev.getRawX(), (int)ev.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    String price="651";

    private void addItem(){
        if (emailId.getEditText().getText().toString().trim().length() == 0) {
            return;
        }

        String name = emailId.getEditText().getText().toString();
        String price="651";
        ContentValues cv = new ContentValues();
        cv.put(BookContract.BookEntry.COLUMN_NAME, name);
        cv.put(BookContract.BookEntry.COLUMN_PRICE, price);

        mDatabase.insert(BookContract.BookEntry.TABLE_NAME, null, cv);

    }




}
