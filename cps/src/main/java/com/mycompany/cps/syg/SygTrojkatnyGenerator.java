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
public class SygTrojkatnyGenerator extends SygnalGenerator {
    public SygTrojkatnyGenerator() {
        this.nazwa = "Sygnał trójkątny";
    }

    @Override
    public ArrayList<Punkt> sygnal(Parametry p) {
        ArrayList<Punkt> res = new ArrayList<>();
        for (BigDecimal t = p.getT1(); t.compareTo(p.getT1().add(p.getD())) < 0; t = t.add(p.getKrokProbkowaniaCiaglego())) {
            if (t.compareTo(BigDecimal.valueOf(((Math.floor((t.doubleValue() - p.getT1().doubleValue()) / p.getT().doubleValue()) * p.getT().doubleValue() + p.getT1().doubleValue()) + p.getKw().doubleValue() * (p.getT().doubleValue())))) < 0) {
                res.add(new Punkt(t, BigDecimal.valueOf(((p.getA().doubleValue()) / (p.getKw().doubleValue() * p.getT().doubleValue())) * (t.doubleValue() - Math.floor((t.doubleValue() - p.getT1().doubleValue()) / p.getT().doubleValue()) * p.getT().doubleValue() - p.getT1().doubleValue()))));
            } else {
                res.add(new Punkt(t, BigDecimal.valueOf((((-p.getA().doubleValue()) / ((1.0 - p.getKw().doubleValue()) * p.getT().doubleValue())) * (t.doubleValue() - Math.floor((t.doubleValue() - p.getT1().doubleValue()) / p.getT().doubleValue()) * p.getT().doubleValue() - p.getT1().doubleValue())) + ((p.getA().doubleValue()) / ((1.0 - p.getKw().doubleValue()))))));
            }
        }
        return res;
    }

}
