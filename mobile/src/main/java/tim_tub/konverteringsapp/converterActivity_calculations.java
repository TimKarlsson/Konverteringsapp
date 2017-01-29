package tim_tub.konverteringsapp;

import java.math.BigDecimal;

import tim_tub.common.converterMath;


/**
 * Created by Tim on 2017-01-29.
 *
 */

class converterActivity_calculations {

    static BigDecimal calculation(int iConvFrom, int iConvTo, BigDecimal result) {

    //Effekt
        if (converterActivity_choise.convChoise == 1) {
            //HK <-> KW
            if (iConvFrom == 1 && iConvTo == 2) {
                result = result.multiply(converterMath.hkTillKw);
            } else if (iConvFrom == 2 && iConvTo == 1) {
                result = result.multiply(converterMath.kwTillHk);
            }
        }

    //Tryck
        if (converterActivity_choise.convChoise == 2) {
            //Bar <-> Psi
            if (iConvFrom == 1 && iConvTo == 2) {
                result = result.multiply(converterMath.barTillPsi);
            } else if (iConvFrom == 2 && iConvTo == 1) {
                result = result.multiply(converterMath.psiTillBar);
            }
        }

    //Mått
        if (converterActivity_choise.convChoise == 4) {
            //dm<->cm
            if (iConvFrom == 1 && iConvTo == 2) {
                result = result.multiply(converterMath.dmTillCm);
            } else if (iConvFrom == 2 && iConvTo == 1) {
                result = result.multiply(converterMath.cmTillDm);
            }
            //dm<->mm
            if (iConvFrom == 1 && iConvTo == 3) {
                result = result.multiply(converterMath.dmTillMm);
            } else if (iConvFrom == 3 && iConvTo == 1) {
                result = result.multiply(converterMath.mmTillDm);
            }
            //dm<->Tum
            if (iConvFrom == 1 && iConvTo == 4) {
                result = result.multiply(converterMath.dmTillTum);
            } else if (iConvFrom == 4 && iConvTo == 1) {
                result = result.multiply(converterMath.tumTillDm);
            }

            // cm<->mm
            if (iConvFrom == 2 && iConvTo == 3) {
                result = result.multiply(converterMath.cmTillMm);
            } else if (iConvFrom == 3 && iConvTo == 2) {
                result = result.multiply(converterMath.mmTillCm);
            }
            // cm<->Tum
            if (iConvFrom == 2 && iConvTo == 4) {
                result = result.multiply(converterMath.cmTillTum);
            } else if (iConvFrom == 4 && iConvTo == 2) {
                result = result.multiply(converterMath.tumTillCm);
            }
            //mm<->Tum
            if (iConvFrom == 3 && iConvTo == 4) {
                result = result.multiply(converterMath.mmTillTum);
            } else if (iConvFrom == 4 && iConvTo == 3) {
                result = result.multiply(converterMath.tumTillMm);
            }
        }

    //Hastighet
        if (converterActivity_choise.convChoise == 3) {
            // Km/h <-> Mph
            if (iConvFrom == 1 && iConvTo == 2) {
                result = result.multiply(converterMath.kmhTillMph);
            } else if (iConvFrom == 2 && iConvTo == 1) {
                result = result.multiply(converterMath.mphTillKmh);
            }
            // Km/h <-> Knop
            if (iConvFrom == 1 && iConvTo == 3) {
                result = result.multiply(converterMath.kmhTillKnop);
            } else if (iConvFrom == 3 && iConvTo == 1) {
                result = result.multiply(converterMath.knopTillKmh);
            }
            // Km/h <-> m/s
            if (iConvFrom == 1 && iConvTo == 4) {
                result = result.multiply(converterMath.kmhTillMs);
            } else if (iConvFrom == 4 && iConvTo == 1) {
                result = result.multiply(converterMath.msTillKmh);
            }

            // Mph <-> Knop
            if (iConvFrom == 2 && iConvTo == 3) {
                result = result.multiply(converterMath.mphTillKnop);
            } else if (iConvFrom == 3 && iConvTo == 2) {
                result = result.multiply(converterMath.knopTillMph);
            }
            // Mph <-> M/S
            if (iConvFrom == 2 && iConvTo == 4) {
                result = result.multiply(converterMath.mphTillMs);
            } else if (iConvFrom == 4 && iConvTo == 2) {
                result = result.multiply(converterMath.msTillMph);
            }
            // M/S <-> Knop
            if (iConvFrom == 4 && iConvTo == 3) {
                result = result.multiply(converterMath.msTillKnop);
            } else if (iConvFrom == 3 && iConvTo == 4) {
                result = result.multiply(converterMath.knopTillMs);
            }



        }


        return result;
    }

}
