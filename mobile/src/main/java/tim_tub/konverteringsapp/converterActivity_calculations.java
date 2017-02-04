package tim_tub.konverteringsapp;

import android.widget.Spinner;

import java.math.BigDecimal;

import tim_tub.common.converterMath;


/**
 * Created by Tim on 2017-01-29.
 *
 */

class converterActivity_calculations {

    static BigDecimal convertUnits(int iConvFrom, int iConvTo, BigDecimal bdResult) {

    //Effekt
        if (converterActivity_choise.convChoise == 1) {
            //HK <-> KW
            if (iConvFrom == 1 && iConvTo == 2) {
                bdResult = bdResult.multiply(converterMath.hkTillKw);
            } else if (iConvFrom == 2 && iConvTo == 1) {
                bdResult = bdResult.multiply(converterMath.kwTillHk);
            }
        }

    //Tryck
        if (converterActivity_choise.convChoise == 2) {
            //Bar <-> Psi
            if (iConvFrom == 1 && iConvTo == 2) {
                bdResult = bdResult.multiply(converterMath.barTillPsi);
            } else if (iConvFrom == 2 && iConvTo == 1) {
                bdResult = bdResult.multiply(converterMath.psiTillBar);
            }
        }

    //Mått
        if (converterActivity_choise.convChoise == 4) {
            //dm<->cm
            if (iConvFrom == 1 && iConvTo == 2) {
                bdResult = bdResult.multiply(converterMath.dmTillCm);
            } else if (iConvFrom == 2 && iConvTo == 1) {
                bdResult = bdResult.multiply(converterMath.cmTillDm);
            }
            //dm<->mm
            if (iConvFrom == 1 && iConvTo == 3) {
                bdResult = bdResult.multiply(converterMath.dmTillMm);
            } else if (iConvFrom == 3 && iConvTo == 1) {
                bdResult = bdResult.multiply(converterMath.mmTillDm);
            }
            //dm<->Tum
            if (iConvFrom == 1 && iConvTo == 4) {
                bdResult = bdResult.multiply(converterMath.dmTillTum);
            } else if (iConvFrom == 4 && iConvTo == 1) {
                bdResult = bdResult.multiply(converterMath.tumTillDm);
            }

            // cm<->mm
            if (iConvFrom == 2 && iConvTo == 3) {
                bdResult = bdResult.multiply(converterMath.cmTillMm);
            } else if (iConvFrom == 3 && iConvTo == 2) {
                bdResult = bdResult.multiply(converterMath.mmTillCm);
            }
            // cm<->Tum
            if (iConvFrom == 2 && iConvTo == 4) {
                bdResult = bdResult.multiply(converterMath.cmTillTum);
            } else if (iConvFrom == 4 && iConvTo == 2) {
                bdResult = bdResult.multiply(converterMath.tumTillCm);
            }
            //mm<->Tum
            if (iConvFrom == 3 && iConvTo == 4) {
                bdResult = bdResult.multiply(converterMath.mmTillTum);
            } else if (iConvFrom == 4 && iConvTo == 3) {
                bdResult = bdResult.multiply(converterMath.tumTillMm);
            }
        }

    //Hastighet
        if (converterActivity_choise.convChoise == 3) {
            // Km/h <-> Mph
            if (iConvFrom == 1 && iConvTo == 2) {
                bdResult = bdResult.multiply(converterMath.kmhTillMph);
            } else if (iConvFrom == 2 && iConvTo == 1) {
                bdResult = bdResult.multiply(converterMath.mphTillKmh);
            }
            // Km/h <-> Knop
            if (iConvFrom == 1 && iConvTo == 3) {
                bdResult = bdResult.multiply(converterMath.kmhTillKnop);
            } else if (iConvFrom == 3 && iConvTo == 1) {
                bdResult = bdResult.multiply(converterMath.knopTillKmh);
            }
            // Km/h <-> m/s
            if (iConvFrom == 1 && iConvTo == 4) {
                bdResult = bdResult.multiply(converterMath.kmhTillMs);
            } else if (iConvFrom == 4 && iConvTo == 1) {
                bdResult = bdResult.multiply(converterMath.msTillKmh);
            }

            // Mph <-> Knop
            if (iConvFrom == 2 && iConvTo == 3) {
                bdResult = bdResult.multiply(converterMath.mphTillKnop);
            } else if (iConvFrom == 3 && iConvTo == 2) {
                bdResult = bdResult.multiply(converterMath.knopTillMph);
            }
            // Mph <-> M/S
            if (iConvFrom == 2 && iConvTo == 4) {
                bdResult = bdResult.multiply(converterMath.mphTillMs);
            } else if (iConvFrom == 4 && iConvTo == 2) {
                bdResult = bdResult.multiply(converterMath.msTillMph);
            }
            // M/S <-> Knop
            if (iConvFrom == 4 && iConvTo == 3) {
                bdResult = bdResult.multiply(converterMath.msTillKnop);
            } else if (iConvFrom == 3 && iConvTo == 4) {
                bdResult = bdResult.multiply(converterMath.knopTillMs);
            }
        }
        return bdResult;
    }

    static String convertInch(Spinner spSpinnerInch, String sUserInput) {
        switch (spSpinnerInch.getSelectedItemPosition()) {
            case 1:
                // 1/8"
                sUserInput = "0.125";
                break;
            case 2:
                // 3/16"
                sUserInput = "0.1875";
                break;
            case 3:
                // 1/4"
                sUserInput = "0.25";
                break;
            case 4:
                // 5/16"
                sUserInput = "0.3125";
                break;
            case 5:
                // 3/8"
                sUserInput = "0.375";
                break;
            case 6:
                // 7/16"
                sUserInput = "0.4375";
                break;
            case 7:
                // 1/2"
                sUserInput = "0.5";
                break;
            case 8:
                // 9/16"
                sUserInput = "0.5625";
                break;
            case 9:
                // 5/8"
                sUserInput = "0.625";
                break;
            case 10:
                // 3/4"
                sUserInput = "0.75";
                break;
            case 11:
                // 7/8"
                sUserInput = "0.875";
                break;
            case 12:
                // 1"
                sUserInput = "1";
                break;
            case 13:
                // 1 1/8"
                sUserInput = "1.125";
                break;
            case 14:
                // 1 1/4"
                sUserInput = "1.25";
                break;
            case 15:
                // 1 3/8"
                sUserInput = "1.375";
                break;
            case 16:
                // 1 1/2"
                sUserInput = "1.5";
                break;
            case 17:
                // 1 5/8"
                sUserInput = "1.625";
                break;
            case 18:
                // 1 3/4"
                sUserInput = "1.75";
                break;
            case 19:
                // 1 7/8"
                sUserInput = "1.875";
                break;
            case 20:
                // 2"
                sUserInput = "2";
                break;
            case 21:
                // 2 1/4"
                sUserInput = "2.25";
                break;
            case 22:
                // 2 1/2"
                sUserInput = "2.5";
                break;
            case 23:
                // 2 3/4"
                sUserInput = "2.75";
                break;
            case 24:
                // 3"
                sUserInput = "3";
                break;
        }
        return sUserInput;
    }

}
