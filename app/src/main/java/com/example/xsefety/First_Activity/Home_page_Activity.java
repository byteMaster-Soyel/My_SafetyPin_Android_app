package com.example.xsefety.First_Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.xsefety.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home_page_Activity extends AppCompatActivity {

    FrameLayout id_home_page_framlayout;
    BottomNavigationView id_bottom_navigation;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        id_home_page_framlayout =(FrameLayout) findViewById(R.id.id_home_page_framlayout);
        id_bottom_navigation = (BottomNavigationView) findViewById(R.id.id_bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.id_home_page_framlayout,new Fragment_home_page()).commit();

         id_bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
              @Override
              public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    int itemId = item.getItemId();

                    if (itemId == R.id.home_btn_navigation_bar)
                    {
                        getSupportFragmentManager().beginTransaction().replace(R.id.id_home_page_framlayout,new Fragment_home_page()).addToBackStack(null).commit();
                    }
                    else if (itemId == R.id.map_btn_navigation_bar)
                    {

                        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                        startActivity(intent);
                    }
                    else if (itemId == R.id.profile_btn_navigation_bar)
                    {
                        getSupportFragmentManager().beginTransaction().replace(R.id.id_home_page_framlayout, new Fragment_Account_Details()).addToBackStack(null).commit();
                    }
                return true;
              }
         });
    }

}