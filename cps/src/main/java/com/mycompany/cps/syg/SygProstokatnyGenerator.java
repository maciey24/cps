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
public class SygProstokatnyGenerator extends SygnalGenerator {
    public SygProstokatnyGenerator() {
        this.nazwa = "Sygnał prostokątkny";
    }

    @Override
    public ArrayList<Punkt> sygnal(Parametry p) {
        ArrayList<Punkt> res = new ArrayList<>();
        for (Double t = p.getT1(); t < (p.getT1() + p.getD()); t += p.getKrokProbkowaniaCiaglego()) {
//            t = round(t, Double.toString(Math.floor(p.getCzestProbkCiaglego())).length(), false);
            if (t < ((Math.floor((t - p.getT1()) / p.getT()) * p.getT() + p.getT1()) + p.getKw() * (p.getT()))) {
                res.add(new Punkt(t, p.getA()));
            } else {
                res.add(new Punkt(t, 0.0));
            }
        }
        return res;
    }

}
