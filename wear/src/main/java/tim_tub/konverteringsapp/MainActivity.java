package tim_tub.konverteringsapp;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends WearableActivity {


    private String resultat;
    //Hämta
    public String getResultat() {
        return resultat;
    }
    //Sätta
    public void setResultat(String r) {
        this.resultat = r;
    }

    private String buffert;
    public String getBuffert() {
        return buffert;
    }
    public void setBuffert(String b) {
        this.buffert = b;
    }

    private Boolean decimalUsed;
    public Boolean getDecimalUsed() {
        return decimalUsed;
    }
    public void setDecimalUsed(Boolean d) {
        this.decimalUsed = d;
    }



    // TODO: Tiden är inställd på fel land.
    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.GERMAN);

    private BoxInsetLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        final TextView tv = (TextView) findViewById(R.id.textView);
        NumberPicker np1 = (NumberPicker) findViewById(R.id.numberPicker);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        Button buttonDecimal = (Button) findViewById(R.id.buttonDecimal);
        Button buttonC = (Button) findViewById(R.id.buttonC);


        np1.setMinValue(0);
        np1.setMaxValue(9);
        np1.setWrapSelectorWheel(true);
        setDecimalUsed(Boolean.FALSE);

    //Körs varje gång scrollistan ändras
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Skriver till variabeln "buffert"
                setBuffert("" + newVal);
            }
        });


    //Körs när man trycker på buttonPlus
        buttonPlus.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (getBuffert() != null && getResultat() == null){
                    setResultat(getBuffert());

                } else if (getBuffert() != null && getResultat() != null) {
                    if (getResultat().length() < 11) {
                    //Läser in Resultat och Buffert.
                        setResultat(getResultat() + getBuffert());
                    //Skriver ut dem ovanför scrollistan.
                        tv.setText(getResultat());
                    }
                } else if (getBuffert() == null && getDecimalUsed()) {
                //Fixar så att man kan sätta 0.0 utan att vrida hjulet.
                    setResultat(getResultat() + "0");
                    tv.setText(getResultat());
                }
                // TODO: Förhindra 000.0 vid växling av sida
            }
        });


    //Körs när man trycker på buttonDecimal
        buttonDecimal.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!getDecimalUsed()){
                //Sätter 0 även om man inte har vridit på nummerhjulet.
                    if (getResultat() == null || getResultat().equals("")) {
                        setResultat("0");
                    }

                    if (getResultat().length() < 10) {
                        setResultat(getResultat() + ".");
                        tv.setText(getResultat());
                        setDecimalUsed(Boolean.TRUE);
                    }
                }
            }
        });

    //Körs vid korttryck på buttonC
        buttonC.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String removeChar = getResultat();
                if (removeChar != null && removeChar.length() > 0 && removeChar.charAt(removeChar.length()-1)!='.') {
                    setResultat(removeChar.substring(0,removeChar.length()-1));
                    tv.setText(getResultat());
                } else if (removeChar != null && removeChar.length() > 0 && removeChar.charAt(removeChar.length()-1)=='.') {
                    setResultat(removeChar.substring(0,removeChar.length()-1));
                    setDecimalUsed(false);
                    tv.setText(getResultat());
                }
            }
        });

    //Körs vid långtryck på buttonC
        buttonC.setOnLongClickListener(new Button.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setResultat("");
                setDecimalUsed(false);
                tv.setText(getResultat());
                return true;
            }
        });


        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
    }


/*
// TODO: Fixa Ambient mode - https://developer.android.com/training/wearables/apps/always-on.html#EnableAmbient
    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }*/

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mTextView.setTextColor(getResources().getColor(android.R.color.white));
            mClockView.setVisibility(View.VISIBLE);

            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        }
        else {
            mContainerView.setBackground(null);
            mTextView.setTextColor(getResources().getColor(android.R.color.black));
            mClockView.setVisibility(View.GONE);
        }
    }
}


// TODO: Fixa röststyrning.