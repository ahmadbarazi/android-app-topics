package com.example.ahmadbarazi_lab8.googleAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ahmadbarazi_lab8.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import es.dmoral.toasty.Toasty;

public class reset_password extends AppCompatActivity {

    private TextInputLayout email;
    private Button reset_password;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email = findViewById(R.id.email_reset);
        reset_password = findViewById(R.id.reset);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = email.getEditText().getText().toString();
                if (useremail.isEmpty()) {
                    email.setError("please enter your email");
                    email.requestFocus();
                } else {
                    UIUtil.hideKeyboard(reset_password.this);
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toasty.info(reset_password.this,"Please check your email",Toast.LENGTH_LONG,true).show();
//                                Toast.makeText(reset_password.this, "Please check your email", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(reset_password.this, login_auth.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                String msg = task.getException().getMessage();
                                Toast.makeText(reset_password.this, "Error " + msg, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        UIUtil.hideKeyboard(this);
//        return super.onTouchEvent(event);
//    }
//}

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