package app.ewtc.masterung.rmutsvservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import app.ewtc.masterung.rmutsvservice.fragment.ServiceFragment;

public class MyServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

//        Receive Value From Main
        String[] strings = getIntent().getStringArrayExtra("Login");
        Log.d("9novV1", "Login(1) ==> " + strings[1]);

//        Add Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentServiceFragment, ServiceFragment.serviceInstance(strings)).commit();
        }


    }   // Main Method

}   // Main Class
