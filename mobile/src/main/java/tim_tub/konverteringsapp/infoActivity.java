package tim_tub.konverteringsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class infoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

    }

    public void moment(View view) {
        Intent infoMomentActivity = new Intent(this, infoActivity_moment.class);
        startActivity(infoMomentActivity);
    }
}
