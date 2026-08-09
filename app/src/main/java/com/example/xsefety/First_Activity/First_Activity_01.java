package com.example.xsefety.First_Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.xsefety.R;

public class First_Activity_01 extends AppCompatActivity {

    FrameLayout framelayout_first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first01);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        framelayout_first = (FrameLayout) findViewById (R.id.framelayout_first);


        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_first,new Fragment_sign_in()).commit();





    }
}