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
public class SygProstokatnyGenerator extends SygnalGenerator {
    public SygProstokatnyGenerator() {
        this.nazwa = "Sygnał prostokątkny";
    }

    @Override
    public ArrayList<Punkt> sygnal(Parametry p) {
        ArrayList<Punkt> res = new ArrayList<>();
        for (BigDecimal t = p.getT1(); t.compareTo(p.getT1().add(p.getD())) < 0; t = t.add(p.getKrokProbkowaniaCiaglego())) {
            if (t.compareTo(((((BigDecimal.valueOf(Math.floor((t.subtract(p.getT1())).doubleValue())).divide(p.getT())).multiply(p.getT())).add(p.getT1())).add(p.getKw().multiply(p.getT())))) < 0) {
                res.add(new Punkt(t, p.getA()));
            } else {
                res.add(new Punkt(t, BigDecimal.valueOf(0.0)));
            }
        }
        return res;
    }

}
