/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps.syg;

import com.mycompany.cps.Parametry;
import com.mycompany.cps.Punkt;

import java.math.BigDecimal;
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
        BigDecimal t;
        for (t = p.getT1(); t.compareTo(p.getT1().add(p.getD())) < 0; t = t.add(p.getKrokProbkowaniaCiaglego())) {
            res.add(new Punkt(t, (rand(BigDecimal.valueOf(0), BigDecimal.valueOf(1)).compareTo(p.getP()) <= 0) ? p.getA() : BigDecimal.valueOf(0.0)));
        }
        return res;
    }

}
