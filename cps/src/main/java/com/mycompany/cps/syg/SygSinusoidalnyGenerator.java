/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps.syg;

import com.mycompany.cps.Parametry;
import com.mycompany.cps.Punkt;

import java.util.ArrayList;

/**
 * @author maciek
 */
public class SygSinusoidalnyGenerator extends SygnalGenerator {
    public SygSinusoidalnyGenerator() {
        this.nazwa = "Sygnał sinusoidalny";
    }

    @Override
    public ArrayList<Punkt> sygnal(Parametry p) {
        ArrayList<Punkt> res = new ArrayList<>();
        for (Double t = p.getT1(); t < (p.getT1() + p.getD()); t += p.getKrokProbkowaniaCiaglego()) {
//            t = round(t, Double.toString(Math.floor(p.getCzestProbkCiaglego())).length(), false);
            res.add(new Punkt(t, p.getA() * Math.sin(((2.0 * Math.PI) / p.getT()) * (t - p.getT1()))));
        }
        return res;
    }

}
