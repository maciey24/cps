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
public class SygSzumImpulsowyGenerator extends SygnalGenerator {
    public SygSzumImpulsowyGenerator() {
        this.nazwa = "Szum impulsowy";
    }

    @Override
    public ArrayList<Punkt> sygnal(Parametry p) {
        ArrayList<Punkt> res = new ArrayList<>();
        Double t;
        for (t = p.getT1(); t < p.getT1() + p.getD(); t += p.getKrokProbkowaniaDyskretnego()) {
//            t = round(t, Double.toString(Math.floor(p.getCzestProbkCiaglego())).length(), false);
            res.add(new Punkt(t, (rand(0, 1) <= p.getP()) ? p.getA() : 0.0));
        }
        return res;
    }

}
