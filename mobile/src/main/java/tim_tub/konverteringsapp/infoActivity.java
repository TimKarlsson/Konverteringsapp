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

    public void torque(View view) {
        Intent infoMomentActivity = new Intent(this, infoActivity_moment.class);
        startActivity(infoMomentActivity);
    }

    public void quality(View view) {
        Intent infoQualityActivity = new Intent(this, infoActivity_qualityInfo.class);
        startActivity(infoQualityActivity);
    }
}
