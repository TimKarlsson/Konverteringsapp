package tim_tub.common;

import java.math.BigDecimal;

/**
 * Created by Tim on 2017-01-10.
 */

/*
    import tim_tub.common.converterMath;
    converterMath xxx = new converterMath();
*/

public class converterMath {
//Effekt
    public static final BigDecimal hkTillKw = new BigDecimal(0.73549875);		                                        //Omräkning HK till KW (Engelska HP 0.745699872)
    public static final BigDecimal kwTillHk = new BigDecimal(1.359621617);			                                    //Omräkning KW till HK




//Tryck
    public static final BigDecimal barTillPsi = new BigDecimal(14.5037738);                                             //Omräkning Bar till Psi
    public static final BigDecimal barTillMPA = new BigDecima(0.1);
    
    public static final BigDecimal psiTillBar = new BigDecimal(0.0689475729);                                           //Omräkning Psi till Bar
    public static final BigDecimal psiTillMPA = new BigDecimal(0.00689475729);
    
    public static final BigDecimal MPATillpsi = new BigDecimal(145.037738007);
    



//Mått
    // Millimeter->
    public static final BigDecimal mmTillCm = new BigDecimal(0.1);
    public static final BigDecimal mmTillDm = new BigDecimal(0.01);
    public static final BigDecimal mmTillTum = new BigDecimal(0.03937007874);
    // Centimeter->
    public static final BigDecimal cmTillMm = new BigDecimal(10);
    public static final BigDecimal cmTillDm = new BigDecimal(0.1);
    public static final BigDecimal cmTillTum = new BigDecimal(0.3937007874);
    // Decimeter->
    public static final BigDecimal dmTillMm = new BigDecimal(100);
    public static final BigDecimal dmTillCm = new BigDecimal(10);
    public static final BigDecimal dmTillTum = new BigDecimal(3.937007874);
    // Tum->
    public static final BigDecimal tumTillMm = new BigDecimal(25.4);
    public static final BigDecimal tumTillCm = new BigDecimal(2.54);
    public static final BigDecimal tumTillDm = new BigDecimal(0.254);



//Hastighet
    public static final BigDecimal kmhTillMph = new BigDecimal(0.621371192);
    public static final BigDecimal kmhTillKnop = new BigDecimal(0.5399568035);
    public static final BigDecimal kmhTillMs = new BigDecimal(0.277777778);

    public static final BigDecimal mphTillKmh = new BigDecimal(1.609344);
    public static final BigDecimal mphTillKnop = new BigDecimal(0.8689762419);
    public static final BigDecimal mphTillMs = new BigDecimal(0.44704);

    public static final BigDecimal msTillKmh = new BigDecimal(3.6);
    public static final BigDecimal msTillMph = new BigDecimal(2.2369362921);
    public static final BigDecimal msTillKnop = new BigDecimal(1.9438444924);

    public static final BigDecimal knopTillKmh = new BigDecimal(1.8519995164);
    public static final BigDecimal knopTillMph = new BigDecimal(1.150779448);
    public static final BigDecimal knopTillMs = new BigDecimal(0.5144443101);
}
