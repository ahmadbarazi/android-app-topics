package com.example.ahmadbarazi_lab8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ahmadbarazi_lab8.googleAuth.login_auth;
//import com.example.ahmadbarazi_lab8.login.Login;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class Home_page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "AccountFragment";
    FirebaseAuth mFirebaseAuth;
    private RecyclerView mRecyclerView, mRecyclerView2, mRecyclerView3, mRecyclerView4;
    private BookAdapter mAdapter;
    private BookAdapter mAdapter2;
    private BookAdapter mAdapter3;
    private BookAdapter mAdapter4;
    private RecyclerView.LayoutManager mLayoutManager, mLayoutManager2, mLayoutManager3, mLayoutManager4;
    private DrawerLayout drawer;
    private Button signout;
    private long backPressedTime;
    private Toast backToast;
    private SearchView searchbar;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mFirebaseAuth = FirebaseAuth.getInstance();
        signout = findViewById(R.id.signOut);



//        Button logout = findViewById(R.id.nav_share);
        setupFirebaseListener();
        searchbar = findViewById(R.id.search);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        ArrayList<ArrayList<BookItem>> arrayz = new ArrayList<ArrayList<BookItem>>();
        ArrayList<BookItem> a = new ArrayList<>();
        a.add(new BookItem(R.drawable.ra7ala, "Physics", "15$"));
        arrayz.add(a);


        ArrayList<BookItem> BookList = new ArrayList<>();
        BookList.add(new BookItem(R.drawable.police, "يوليسيس", "15$"));
        BookList.add(new BookItem(R.drawable.ktabyh, "كتابيه", "15$"));
        BookList.add(new BookItem(R.drawable.aaoalmsfly, "عوالم سفلية", "10$"));
        BookList.add(new BookItem(R.drawable.java, "Java", "30$"));
        BookList.add(new BookItem(R.drawable.kotlin, "Kotlin", "43$"));
        BookList.add(new BookItem(R.drawable.police, "يوليسيس", "15$"));
        BookList.add(new BookItem(R.drawable.ktabyh, "كتابيه", "15$"));
        BookList.add(new BookItem(R.drawable.aaoalmsfly, "عوالم سفلية", "10$"));
        BookList.add(new BookItem(R.drawable.java, "Java", "30$"));
        BookList.add(new BookItem(R.drawable.kotlin, "Kotlin", "43$"));
        BookList.add(new BookItem(R.drawable.police, "يوليسيس", "15$"));
        BookList.add(new BookItem(R.drawable.ktabyh, "كتابيه", "15$"));
        BookList.add(new BookItem(R.drawable.aaoalmsfly, "عوالم سفلية", "10$"));
        BookList.add(new BookItem(R.drawable.java, "Java", "30$"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new BookAdapter(BookList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Home_page.this, layoutTests.class);
                startActivity(intent);
            }
        });
//--------------------------------------------------------------------------------------------------------------

        ArrayList<BookItem> Awarded = new ArrayList<>();
        Awarded.add(new BookItem(R.drawable.police, "يوليسيس", "15$"));
        Awarded.add(new BookItem(R.drawable.ktabyh, "كتابيه", "15$"));
        Awarded.add(new BookItem(R.drawable.aaoalmsfly, "عوالم سفلية", "10$"));
        Awarded.add(new BookItem(R.drawable.java, "Java", "30$"));
        Awarded.add(new BookItem(R.drawable.kotlin, "Kotlin", "43$"));

        mRecyclerView2 = findViewById(R.id.recyclerView2);
        mRecyclerView2.setHasFixedSize(true);
        mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter2 = new BookAdapter(Awarded);

        mRecyclerView2.setLayoutManager(mLayoutManager2);
        mRecyclerView2.setAdapter(mAdapter2);

        mAdapter2.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Home_page.this, layoutTests.class);
                startActivity(intent);
            }
        });

//--------------------------------------------------------------------------------------------------------------
        ArrayList<BookItem> Random = new ArrayList<>();
        Random.add(new BookItem(R.drawable.police, "يوليسيس", "15$"));
        Random.add(new BookItem(R.drawable.ktabyh, "كتابيه", "15$"));
        Random.add(new BookItem(R.drawable.aaoalmsfly, "عوالم سفلية", "10$"));
        Random.add(new BookItem(R.drawable.java, "Java", "30$"));
        Random.add(new BookItem(R.drawable.kotlin, "Kotlin", "43$"));
        Random.add(new BookItem(R.drawable.ktabyh, "كتابيه", "15$"));
        Random.add(new BookItem(R.drawable.aaoalmsfly, "عوالم سفلية", "10$"));
        Random.add(new BookItem(R.drawable.java, "Java", "30$"));
        Random.add(new BookItem(R.drawable.kotlin, "Kotlin", "43$"));


        mRecyclerView3 = findViewById(R.id.recyclerView3);
        mRecyclerView3.setHasFixedSize(true);
        mLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter3 = new BookAdapter(Random);

        mRecyclerView3.setLayoutManager(mLayoutManager3);
        mRecyclerView3.setAdapter(mAdapter3);

        mAdapter3.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Home_page.this, layoutTests.class);
                startActivity(intent);
            }
        });

//--------------------------------------------------------------------------------------------------------------

        ArrayList<BookItem> Islamic = new ArrayList<>();
        Islamic.add(new BookItem(R.drawable.police, "يوليسيس", "15$"));
        Islamic.add(new BookItem(R.drawable.ktabyh, "كتابيه", "15$"));
        Islamic.add(new BookItem(R.drawable.aaoalmsfly, "عوالم سفلية", "10$"));
        Islamic.add(new BookItem(R.drawable.java, "Java", "30$"));
        Islamic.add(new BookItem(R.drawable.kotlin, "Kotlin", "43$"));

        mRecyclerView4 = findViewById(R.id.recyclerView4);
        mRecyclerView4.setHasFixedSize(true);
        mLayoutManager4 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter4 = new BookAdapter(Islamic);

        mRecyclerView4.setLayoutManager(mLayoutManager4);
        mRecyclerView4.setAdapter(mAdapter4);

        mAdapter4.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Home_page.this, layoutTests.class);
                startActivity(intent);
            }
        });


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
            }
        });


        //Search content
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                mAdapter2.getFilter().filter(newText);
                mAdapter3.getFilter().filter(newText);
                mAdapter4.getFilter().filter(newText);

                return false;
            }
        });


    }

    //Navbar
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        switch (menuItem.getItemId()) {
//            case R.id.nav_homepage:
//                Toast.makeText(Home_page.this, "Share", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.nav_profile:
//               Intent intent =new Intent(Home_page.this,Profile_fragment.class);
//               startActivity(intent);
//                break;
//        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchbar.hasWindowFocus()) {
            UIUtil.hideKeyboard(this);
            searchbar.clearFocus();
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        } else if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    public void signOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent goToSignIn = new Intent(Home_page.this, login_auth.class);
        startActivity(goToSignIn);
    }

    private void setupFirebaseListener() {
        Log.d(TAG, "setupFirebaseListener: setting up the auth state listener.");
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged: signed_in: " + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                    Toasty.success(Home_page.this, "Signed out Successfully", Toast.LENGTH_SHORT, true).show();
//                    Toast.makeText(Home_page.this, "Signed out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Home_page.this, login_auth.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
        }
    }

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
