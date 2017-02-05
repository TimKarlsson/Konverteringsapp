package tim_tub.konverteringsapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class converterActivity_core extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
    String sConvTo = "";
    String sUserInput;
    BigDecimal bdResult;

//----------------------------------------------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_core);
        ArrayAdapter<CharSequence> spinnerArrayAdapter = null;


    //Spinner1
        Spinner spinner1 = (Spinner) findViewById(R.id.convCoreSpinner1);
    //Spinner2
        Spinner spinner2 = (Spinner) findViewById(R.id.convCoreSpinner2);
        Spinner spinnerInch = (Spinner) findViewById(R.id.convCoreSpinner3Inch);
    //Kollar vilka enheter som skall laddas till spinner
        if (converterActivity_choise.convChoise == 1) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.convPowerArray, android.R.layout.simple_spinner_item);
        } else if (converterActivity_choise.convChoise == 2) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.convPreassureArray, android.R.layout.simple_spinner_item);
        } else if (converterActivity_choise.convChoise == 3) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.convSpeedArray, android.R.layout.simple_spinner_item);
        } else if (converterActivity_choise.convChoise == 4) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.convMeasureArray, android.R.layout.simple_spinner_item);

            spinner1.setOnItemSelectedListener(this);
            spinner2.setOnItemSelectedListener(this);
            spinnerInch.setOnItemSelectedListener(this);
        } else if (converterActivity_choise.convChoise == 5) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.convFlowArray, android.R.layout.simple_spinner_item);
        }

    //Anger valen för båda spinner
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerArrayAdapter);
        spinner2.setAdapter(spinnerArrayAdapter);
    }

//----------------------------------------------------------------------------------------
//Körs när spinners ändras
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

    //Visa/Dölj showInchSpinner
        if (converterActivity_choise.convChoise == 4 && (parent.getId()==R.id.convCoreSpinner1 || parent.getId()==R.id.convCoreSpinner2)) {
            hideKeyboard(view);
            showInchSpinner();
        }
        if (parent.getId()==R.id.convCoreSpinner3Inch && parent.getSelectedItemPosition() != 0) {
            EditText etInput = (EditText) findViewById(R.id.convCoreInput);
            etInput.setText("");
            //runConv(view);
        }
    }
    public void onNothingSelected(AdapterView<?> parent) {

    }


//----------------------------------------------------------------------------------------
//Visar eller döljer convInchSpinner och convInchInfo TextView. Används bara vid tum
    void showInchSpinner() {

        if (converterActivity_choise.convChoise == 4) {
            Spinner spinnerInch = (Spinner) findViewById(R.id.convCoreSpinner3Inch);
            Spinner spinner1 = (Spinner) findViewById(R.id.convCoreSpinner1);
            Spinner spinner2 = (Spinner) findViewById(R.id.convCoreSpinner2);
            TextView tvInfoInch = (TextView) findViewById(R.id.convCoreInchInfo);
            TextView tv2 = (TextView) findViewById(R.id.convCoreText2);

            ArrayAdapter<CharSequence> spinnerInchArrayAdapter = ArrayAdapter.createFromResource(this, R.array.convInchArray, android.R.layout.simple_spinner_item);
            spinnerInchArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerInch.setAdapter(spinnerInchArrayAdapter);
            //Används för att ändra layouten
            RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            Button bt = (Button) findViewById(R.id.convCoreRun);

            if (spinnerInch.getVisibility() == View.GONE) {
                rp.addRule(RelativeLayout.BELOW, R.id.convCoreSpinner3Inch);
                if (tv2.getText().equals(getString(R.string.convCoreFrån)) && spinner1.getSelectedItemPosition() == 4 && spinner2.getSelectedItemPosition() != 0) {
                    tvInfoInch.setVisibility(View.VISIBLE);
                    spinnerInch.setVisibility(View.VISIBLE);
                    bt.setLayoutParams(rp);
                } else if (tv2.getText().equals(getString(R.string.convCoreTill)) && spinner2.getSelectedItemPosition() == 4 && spinner1.getSelectedItemPosition() != 0) {
                    tvInfoInch.setVisibility(View.VISIBLE);
                    spinnerInch.setVisibility(View.VISIBLE);
                    bt.setLayoutParams(rp);
                }
            } else if (spinnerInch.getVisibility() == View.VISIBLE &&
                    (tv2.getText().equals(getString(R.string.convCoreFrån)) && spinner1.getSelectedItemPosition() != 4) ||
                    (tv2.getText().equals(getString(R.string.convCoreTill)) && spinner2.getSelectedItemPosition() != 4)){
                tvInfoInch.setVisibility(View.INVISIBLE);
                spinnerInch.setVisibility(View.GONE);
                rp.addRule(RelativeLayout.BELOW, R.id.convCoreInput);
                bt.setLayoutParams(rp);
                //TODO: Fortsätt implementera Tum listan! Utför beräkning på listan.
            }
        }

    }

//----------------------------------------------------------------------------------------
//Körs när man tryckt på knapp convCoreRun "Omvandla"
//TODO: Lägg till långtryck för att konvertera direkt?
    public void runConv(View view) {

        TextView tvResult = (TextView) findViewById(R.id.convCoreResultat);
        TextView tv2 = (TextView) findViewById(R.id.convCoreText2);
        EditText etInput = (EditText) findViewById(R.id.convCoreInput);
        Spinner sp1 = (Spinner) findViewById(R.id.convCoreSpinner1);
        Spinner sp2 = (Spinner) findViewById(R.id.convCoreSpinner2);
        Spinner spinnerInch = (Spinner) findViewById(R.id.convCoreSpinner3Inch);
        //Hämtar input från EditText.
        sUserInput = etInput.getText().toString();

        String sp1String = sp1.getSelectedItem().toString();
        String sp2String = sp2.getSelectedItem().toString();

        if (spinnerInch.getVisibility() == View.VISIBLE && spinnerInch.getSelectedItemPosition() != 0 && sUserInput.equals("")) {
            sUserInput = converterActivity_calculations.convertInch(spinnerInch, sUserInput);
        } else if (sUserInput.equals("")) {
            //Sätter 1 i input om inget är valt.
            sUserInput = etInput.getHint().toString();
        } else {
            ////Hämtar input från EditText.
            //sUserInput = etInput.getText().toString();
            spinnerInch.setSelection(0);
        }

    //Kollar efter fält som inte är angivna.
        checkForErrors(sp1String, sp2String, tv2);

    //Kollar så att inga fel är satta och sen utför konverteringen.
        if ((!bErr1) && (!bErr2) && (!bErr3) && (!bErr4)) {
        //Kollar konverteringsriktningen och enheter
            setConvFromAndTo(tv2, sp1, sp2);

        //Konverterar och skriver ut resultatet
            bdResult = new BigDecimal(sUserInput);
            printResult(bdResult);
        } else {
        //Visar eventuella felmeddelanden
            tvResult.setTextColor(Color.parseColor("#FF0000"));
            tvResult.setText(error1 + error2 + error3 + error4);
        }

    //Gömmer keyboard
        hideKeyboard(view);
    //Scrollar ned sidan till botten vid behov
        scrollDown();
    }

//----------------------------------------------------------------------------------------
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
        showInchSpinner();
        runConv(view);
    }

//----------------------------------------------------------------------------------------
//Felhantering
    void checkForErrors(String sp1String, String sp2String, TextView tv2) {

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
        if (sUserInput.equals(".")) {
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

//----------------------------------------------------------------------------------------
//Sätter enheterna från och till
    void setConvFromAndTo (TextView tv2, Spinner sp1, Spinner sp2) {

        // Vänder ordningen på sp1 och sp2 ifall konv. riktningen är bytt.
        if (tv2.getText().equals(getString(R.string.convCoreFrån))) {
            iConvFrom = sp1.getSelectedItemPosition();
            iConvTo = sp2.getSelectedItemPosition();
            sConvTo = sp2.getSelectedItem().toString();
        } else {
            iConvFrom = sp2.getSelectedItemPosition();
            iConvTo = sp1.getSelectedItemPosition();
            sConvTo = sp1.getSelectedItem().toString();
        }
    }

//----------------------------------------------------------------------------------------
//Döljer knappsatsen.
    void hideKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e){        }
    }

//----------------------------------------------------------------------------------------
//Scrollar ned till botten av sidan.
    void scrollDown() {
        final ScrollView scroll = (ScrollView) findViewById(R.id.activity_converter_effekt);
        scroll.postDelayed(new Runnable() {
            public void run() {scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 100L);
    }

//----------------------------------------------------------------------------------------
//Skriver ut resultatet
    void printResult(BigDecimal bdResult) {

        TextView tvResult = (TextView) findViewById(R.id.convCoreResultat);
    //Kör begärd uträkning.
        bdResult = converterActivity_calculations.convertUnits(iConvFrom, iConvTo, bdResult);
    //Minskar till 3 decimaler och avrundar.
        bdResult = bdResult.setScale(3, RoundingMode.HALF_UP);
//TODO: Minska antalet decimaler om resultatet är *.000
    //Skriver ut resultatet i convCoreResultat.
        tvResult.setTextColor(Color.parseColor("#000000"));
        tvResult.setText(String.valueOf(bdResult) + " " + sConvTo);
    }
}

//Buggar
//TODO: När "Till:" spinnern väljs så nollas spinnerInch!
//TODO: Dölj tangentbord när man tryckt enter på EditText (Bara med textIsSelectable="true" på convCoreResultat)