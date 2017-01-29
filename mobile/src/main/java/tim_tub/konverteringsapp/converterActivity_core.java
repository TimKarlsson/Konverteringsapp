package tim_tub.konverteringsapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class converterActivity_core extends AppCompatActivity {

    String error1 = "";
    String error2 = "";
    String error3 = "";
    String error4 = "";
    Boolean bErr1 = false;
    Boolean bErr2 = false;
    Boolean bErr3 = false;
    Boolean bErr4 = false;

    int iConvFrom;
    int iConvTo;
    String sConvTo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_core);
        ArrayAdapter<CharSequence> spinnerArrayAdapter = null;

    //Spinner1
        Spinner spinner1 = (Spinner) findViewById(R.id.convCoreSpinner1);
    //Spinner2
        Spinner spinner2 = (Spinner) findViewById(R.id.convCoreSpinner2);
    //Kollar vilka enheter som skall laddas till spinner
        if (converterActivity_choise.convChoise == 1) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.convPowerArray, android.R.layout.simple_spinner_item);
        } else if (converterActivity_choise.convChoise == 2) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.convPreassureArray, android.R.layout.simple_spinner_item);
        } else if (converterActivity_choise.convChoise == 3) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.convSpeedArray, android.R.layout.simple_spinner_item);
        } else if (converterActivity_choise.convChoise == 4) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.convMeasureArray, android.R.layout.simple_spinner_item);
        } else if (converterActivity_choise.convChoise == 5) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.convFlowArray, android.R.layout.simple_spinner_item);
        }
    //Anger valen för båda spinner
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerArrayAdapter);
        spinner2.setAdapter(spinnerArrayAdapter);

    }



//Körs när man tryckt på knapp convCoreRun "Omvandla"
    public void runConv(View view) {
        TextView tvResultat = (TextView) findViewById(R.id.convCoreResultat);
        TextView tv2 = (TextView) findViewById(R.id.convCoreText2);
        EditText etInput = (EditText) findViewById(R.id.convCoreInput);
        Spinner sp1 = (Spinner) findViewById(R.id.convCoreSpinner1);
        Spinner sp2 = (Spinner) findViewById(R.id.convCoreSpinner2);

        String sp1String = sp1.getSelectedItem().toString();
        String sp2String = sp2.getSelectedItem().toString();
        String userInput = etInput.getText().toString();

        //Kollar efter fält som inte är angivna
        checkForErrors(userInput, sp1String, sp2String, tv2);


//Kollar så att inga fel är satta och sen utför konverteringen.
        if ((!bErr1) && (!bErr2) && (!bErr3) && (!bErr4)) {
            tvResultat.setTextColor(Color.parseColor("#000000"));
            BigDecimal result = new BigDecimal(userInput);

    // Vänder ordningen på sp1 och sp2 ifall konv. riktningen är bytt.
//TODO: Flytta till changeConvDir??
            if (tv2.getText().equals(getString(R.string.convCoreFrån))) {
                iConvFrom = sp1.getSelectedItemPosition();
                iConvTo = sp2.getSelectedItemPosition();
                sConvTo = sp2String;
            } else {
                iConvFrom = sp2.getSelectedItemPosition();
                iConvTo = sp1.getSelectedItemPosition();
                sConvTo = sp1String;
            }

        //Kör begärd uträkning.
            result = converterActivity_calculations.calculation(iConvFrom, iConvTo, result);
        //Minskar till 3 decimaler och avrundar.
            result = result.setScale(3, RoundingMode.HALF_UP);
//TODO: Minska antalet decimaler om resultatet är *.000
        //Skriver ut resultatet i convCoreResultat.
            tvResultat.setText(String.valueOf(result) + " " + sConvTo);
        } else {
            tvResultat.setTextColor(Color.parseColor("#FF0000"));
            tvResultat.setText(error1 + error2 + error3 + error4);
        }

    //Gömmer keyboard
        hideKeyboard(view);
    //Scrollar ned sidan vid behov för att visa resultatet
        scrollDown();
    }


//Körs när man trycker på pilknappen. Ändra riktning på omvandlingen.
    public void changeConvDir(View view) {
        ImageButton directionButton = (ImageButton) findViewById(R.id.convCoreButtonDirektion);
        TextView tv2 = (TextView) findViewById(R.id.convCoreText2);
        TextView tv3 = (TextView) findViewById(R.id.convCoreText3);

    /*Om texten i TextView2 är samma som string convCoreFrån eller Till(hämtad från strings.xml)
    ändras texten i TextView2 och 3 samt knappen*/
        if (tv2.getText().equals(getString(R.string.convCoreFrån))) {
            directionButton.setRotation(180);
            tv2.setText(R.string.convCoreTill);
            tv3.setText(R.string.convCoreFrån);
        } else if (tv2.getText().equals(getString(R.string.convCoreTill))) {
            directionButton.setRotation(0);
            tv2.setText(R.string.convCoreFrån);
            tv3.setText(R.string.convCoreTill);
        }
//TODO: Kör inte om fel finns.
        runConv(view);
    }


//Felhantering
    void checkForErrors(String userInput, String sp1String, String sp2String, TextView tv2) {
        //Nollar alla fel
        error1 = "";
        error2 = "";
        error3 = "";
        error4 = "";
        bErr1 = false;
        bErr2 = false;
        bErr3 = false;
        bErr4 = false;

    //Err1 - Kollar om fält 1 är tomt.
        if (userInput.equals("")) {
            error1 = getString(R.string.convCoreErr1) + "\n";
            bErr1 = true;
        }

    //Err2 - Kolla om spinner1 inte är vald och riktning på konvertering.
        if (sp1String.equals(getResources().getStringArray(R.array.convPowerArray)[0])) {
            if (tv2.getText().equals(getString(R.string.convCoreFrån))) {
                error2 = getString(R.string.convCoreErr2) + "\n";
            } else {
                error2 = getString(R.string.convCoreErr3) + "\n";
            }
            bErr2 = true;
        }

    //Err3 - Kolla om spinner2 inte är vald.
        if (sp2String.equals(getResources().getStringArray(R.array.convPowerArray)[0])) {
            if (tv2.getText().equals(getString(R.string.convCoreFrån))) {
                error3 = getString(R.string.convCoreErr3) + "\n";
            } else {
                error3 = getString(R.string.convCoreErr2) + "\n";
            }
            bErr3 = true;
        }

    //Err4 - Kollar om spinner1 och spinner2 har samma val.
        if (sp1String.equals(sp2String) && !sp1String.equals(getResources().getStringArray(R.array.convPowerArray)[0])) {
            error4 = getString(R.string.convCoreErr4) + "\n";
            bErr4 = true;
        }
    }


//Döljer knappsatsen.
    void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

//Scrollar ned till botten av sidan.
    void scrollDown() {
        final ScrollView scroll = (ScrollView) findViewById(R.id.activity_converter_effekt);
        scroll.postDelayed(new Runnable() {
            public void run() {scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 100L);
    }

}