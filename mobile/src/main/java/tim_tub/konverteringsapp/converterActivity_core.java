package tim_tub.konverteringsapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import tim_tub.common.converterMath;

public class converterActivity_core extends AppCompatActivity {

    converterActivity_choise caChoise = new converterActivity_choise();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_effekt);
        ArrayAdapter<CharSequence> spinnerArrayAdapter = null;

    //Spinner1
        Spinner spinner1 = (Spinner) findViewById(R.id.convSpinner1);
    //Spinner2
        Spinner spinner2 = (Spinner) findViewById(R.id.convSpinner2 );
        if (caChoise.convChoise == 1) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.arrayConvEffekt1, android.R.layout.simple_spinner_item);
            //spinnerArrayAdapter2 = ArrayAdapter.createFromResource(this, R.array.arrayConvEffekt1 , android.R.layout.simple_spinner_item);
        } else if (caChoise.convChoise == 2) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.arrayConvPreassure, android.R.layout.simple_spinner_item);
        } else if (caChoise.convChoise == 3) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.arrayConvSpeed, android.R.layout.simple_spinner_item);
        } else if (caChoise.convChoise == 4) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.arrayConvMeasure, android.R.layout.simple_spinner_item);
        } else if (caChoise.convChoise == 5) {
            spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.arrayConvFlow, android.R.layout.simple_spinner_item);
        }

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerArrayAdapter);
        //spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinnerArrayAdapter);

    }



//Ändra riktning på omvandlingen
    public void changeConvDir(View view) {
        Button direct = (Button) findViewById(R.id.convEffektDirektion);
        TextView tv2 = (TextView) findViewById(R.id.convEffektText2);
        TextView tv3 = (TextView) findViewById(R.id.convEffektText3);

/*Om texten i TextView2 är samma som string convEffektFrån eller Till(hämtad från strings.xml)
ändras texten i TextView2 och 3 samt knappen*/
        if (tv2.getText().equals(getString(R.string.convEffektFrån))) {
            tv2.setText(R.string.convEffektTill);
            tv3.setText(R.string.convEffektFrån);
            direct.setText("/\\");
        } else if (tv2.getText().equals(getString(R.string.convEffektTill))) {
            tv2.setText(R.string.convEffektFrån);
            tv3.setText(R.string.convEffektTill);
            direct.setText("\\/");
        }
    }




//Körs när man tryckt på knapp convEffektRun "Omvandla"
    public void runConv(View view) {
        TextView tvResultat = (TextView) findViewById(R.id.convEffektResultat);
        TextView tv2 = (TextView) findViewById(R.id.convEffektText2);
        EditText etInput = (EditText) findViewById(R.id.convEffektInput);
        Spinner sp1 = (Spinner) findViewById(R.id.convSpinner1);
        Spinner sp2 = (Spinner) findViewById(R.id.convSpinner2);


        String sp1String = sp1.getSelectedItem().toString();
        String sp2String = sp2.getSelectedItem().toString();
        String defaultSpinner = getResources().getStringArray(R.array.arrayConvEffekt1)[0];
        String userInput = etInput.getText().toString();

        int convFrom;
        int convTo;


        //Felhantering
        String error1 = "";
        String error2 = "";
        String error3 = "";
        String error4 = "";
        Boolean iErr1 = false;
        Boolean iErr2 = false;
        Boolean iErr3 = false;
        Boolean iErr4 = false;

//Err1 - Kollar om fält 1 är tomt.
        if (userInput.equals("")) {
            error1 = getString(R.string.convEffektErr1) + "\n";
            iErr1 = true;
        }

//Err2 - Kolla om spinner1 inte är vald och riktning på konvertering.
        if (sp1String.equals(defaultSpinner)) {
            if (tv2.getText().equals(getString(R.string.convEffektFrån))) {
                error2 = getString(R.string.convEffektErr2) + "\n";
            } else {
                error2 = getString(R.string.convEffektErr3) + "\n";
            }
            iErr2 = true;
        }

//Err3 - Kolla om spinner2 inte är vald.
        if (sp2String.equals(defaultSpinner)) {
            if (tv2.getText().equals(getString(R.string.convEffektFrån))) {
                error3 = getString(R.string.convEffektErr3) + "\n";
            } else {
                error3 = getString(R.string.convEffektErr2) + "\n";
            }
            iErr3 = true;
        }

//Err4 - Kollar om spinner1 och spinner2 har samma val.
        if (sp1String.equals(sp2String) && !sp1String.equals(defaultSpinner)) {
            error4 = getString(R.string.convEffektErr4) + "\n";
            iErr4 = true;
        }

//Kollar så att inga fel är satta och sen utför konverteringen.
        if ((!iErr1) && (!iErr2) && (!iErr3) && (!iErr4)) {
            tvResultat.setTextColor(Color.parseColor("#000000"));
            converterMath cm = new converterMath();
            BigDecimal result = new BigDecimal(userInput);


            // Vänder ordningen på sp1 och sp2 ifall konv. riktningen är bytt.
            if (tv2.getText().equals(getString(R.string.convEffektFrån))) {
                convFrom = sp1.getSelectedItemPosition();
                convTo = sp2.getSelectedItemPosition();
            } else {
                convFrom = sp2.getSelectedItemPosition();
                convTo = sp1.getSelectedItemPosition();
            }

    //Effekt
            if (caChoise.convChoise == 1) {
                //HK <-> KW
                if (convFrom == 1 && convTo == 2) {
                    result = result.multiply(cm.hkTillKw);
                } else if (convFrom == 2 && convTo == 1) {
                    result = result.multiply(cm.kwTillHk);
                }
            }

    //Tryck
            if (caChoise.convChoise == 2) {
                //Bar <-> Psi
                if (convFrom == 1 && convTo == 2) {
                    result = result.multiply(cm.barTillPsi);
                } else if (convFrom == 2 && convTo == 1) {
                    result = result.multiply(cm.psiTillBar);
                }
            }

    //Hastighet
            if (caChoise.convChoise == 3) {
                // Km/h <-> Mph
                if (convFrom == 1 && convTo == 2) {
                    result = result.multiply(cm.kmhTillMph);
                } else if (convFrom == 2 && convTo == 1) {
                    result = result.multiply(cm.mphTillKmh);
                }
            }
    //Mått
            if (caChoise.convChoise == 4) {
                //dm<->cm
                if (convFrom == 1 && convTo == 2) {
                    result = result.multiply(cm.dmTillCm);
                } else if (convFrom == 2 && convTo == 1) {
                    result = result.multiply(cm.tumTillCm);
                }
                //dm<->mm
                if (convFrom == 1 && convTo == 3) {
                    result = result.multiply(cm.dmTillMm);
                } else if (convFrom == 3 && convTo == 1) {
                    result = result.multiply(cm.tumTillMm);
                }
                //dm<->Tum
                if (convFrom == 1 && convTo == 4) {
                    result = result.multiply(cm.dmTillTum);
                } else if (convFrom == 4 && convTo == 1) {
                    result = result.multiply(cm.tumTillDm);
                }

                // cm<->mm
                if (convFrom == 2 && convTo == 3) {
                    result = result.multiply(cm.cmTillMm);
                } else if (convFrom == 3 && convTo == 2) {
                    result = result.multiply(cm.tumTillMm);
                }
                // cm<->Tum
                if (convFrom == 2 && convTo == 4) {
                    result = result.multiply(cm.cmTillTum);
                } else if (convFrom == 4 && convTo == 2) {
                    result = result.multiply(cm.tumTillCm);
                }
                //mm<->Tum
                if (convFrom == 3 && convTo == 4) {
                    result = result.multiply(cm.mmTillTum);
                } else if (convFrom == 4 && convTo == 3) {
                    result = result.multiply(cm.tumTillMm);
                }
            }
            result = result.setScale(3, RoundingMode.CEILING);
        //Skriver ut resultatet i conEffektResultat.
            tvResultat.setText(String.valueOf(result));
            //tvResultat.setText(result + "\n" + caChoise.convChoise);
        } else {
            tvResultat.setTextColor(Color.parseColor("#FF0000"));
            tvResultat.setText(error1 + error2 + error3 + error4);
        }

    //Scrollar ned sidan vid behov för att visa resultatet
        final ScrollView scroll = (ScrollView) findViewById(R.id.activity_converter_effekt);
        scroll.postDelayed(new Runnable() {
            public void run() {scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 100L);
    }
}