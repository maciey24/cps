/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps.syg;

import com.mycompany.cps.Parametry;
import com.mycompany.cps.Punkt;

import static java.lang.Math.sqrt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author maciek
 */
public abstract class SygnalGenerator {
    protected String nazwa = "";

    public abstract ArrayList<Punkt> sygnal(Parametry p);

    public String getNazwa() {
        return this.nazwa;
    }

    /**
     * Generator zmiennej losowej o rozkladzie rownomiernym,
     * wartosci oczekiwanej e oraz wariancji v.
     **/
    static Double my_rand(double e, double v) {
        return sqrt(12.0 * v) * ((rand(0.0, 100.0) - 50.0) / 100.0) + e;
    }

    /**
     * Generator zmiennej losowej o rozkladzie normalnym,
     * wartosci oczekiwanej e oraz wariancji v.
     **/
    static Double my_Gauss(double e, double v) {
        int n = 10;
        double x = 0.0;
        for (int i = 0; i < n; i++) {
            x += my_rand(0.0, 1.0);
        }
        return x * sqrt(v / (double) n) + e;
    }

    static Double rand(double min, double max) {
        Random r = new Random();
        return min + r.nextDouble() * (max - min);
    }

    //to jest jedyna niezbyt poprawna funkcja chyba
    static Double randGauss(double min, double max) {
        Random r = new Random();
        do {
            Double res = r.nextGaussian();
            if (res <= max && res >= min) {
                return res;
            }
        } while (true);
    }

//    protected static Double round(double value, int places, boolean br) {
//        if (places < 0) throw new IllegalArgumentException();
//        BigDecimal bd = new BigDecimal(Double.toString(value));
//        bd = bd.setScale(places, RoundingMode.HALF_UP);
//        if(br) 
//            return bd.doubleValue();
//        else
//            return round(bd.doubleValue(), places-1, true);
//    }
}
