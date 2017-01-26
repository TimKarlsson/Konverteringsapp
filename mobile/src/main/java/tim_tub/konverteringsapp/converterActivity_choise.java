package tim_tub.konverteringsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class converterActivity_choise extends AppCompatActivity {
    public static int convChoise = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

    }



    public void effekt(View view) {
        Intent converterActivity = new Intent(this, converterActivity_core.class);
        convChoise = 1;
        startActivity(converterActivity);
    }

    public void preassure(View view) {
        Intent converterActivity = new Intent(this, converterActivity_core.class);
        convChoise = 2;
        startActivity(converterActivity);
    }

    public void speed(View view) {
        Intent converterActivity = new Intent(this, converterActivity_core.class);
        convChoise = 3;
        startActivity(converterActivity);
    }

    public void measure(View view) {
        Intent converterActivity = new Intent(this, converterActivity_core.class);
        convChoise = 4;
        startActivity(converterActivity);
    }

    public void flow(View view) {
        Intent converterActivity = new Intent(this, converterActivity_core.class);
        convChoise = 5;
        startActivity(converterActivity);
    }
}
