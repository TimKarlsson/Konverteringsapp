package tim_tub.konverteringsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;



public class infoActivity_qualityInfo extends AppCompatActivity {

    Boolean zoomed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_qualityinfo);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

    }


    public void infoZoomImg(View view) {
        ImageView ivBolt = (ImageView) findViewById(R.id.infoQualityPicture);
        LinearLayout.LayoutParams boltParams = (LinearLayout.LayoutParams) ivBolt.getLayoutParams();
        final float scale = getResources().getDisplayMetrics().density;
        int dpInPxBig  = (int) (200 * scale);
        int dpInPxSmall  = (int) (50 * scale);


        if (!zoomed) {
            boltParams.width = dpInPxBig;
            boltParams.height = dpInPxBig;
            zoomed = true;
        } else {
            boltParams.width = dpInPxSmall;
            boltParams.height = dpInPxSmall;
            zoomed = false;
        }


        ivBolt.setLayoutParams(boltParams);

    }
}
