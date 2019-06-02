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

import static java.lang.Math.sin;

/**
 * @author maciek
 */
public class SygSincGenerator extends SygnalGenerator {
    public SygSincGenerator() {
        this.nazwa = "Sygnał SinC";
    }

    @Override
    public ArrayList<Punkt> sygnal(Parametry p) {
        ArrayList<Punkt> res = new ArrayList<>();
        for (BigDecimal t = p.getT1(); t.compareTo((p.getT1().add(p.getD()))) <= 0; t = t.add(p.getKrokProbkowaniaCiaglego())) {
            if (t.equals(BigDecimal.valueOf(0.0))) {
                res.add(new Punkt(t.divide(p.getKrokProbkowaniaDyskretnego()) , BigDecimal.valueOf(1.0)));
            } else {
                res.add(new Punkt(t.divide(p.getKrokProbkowaniaDyskretnego()), BigDecimal.valueOf(sin(t.doubleValue())).divide(t)));
            }
        }
        return res;
    }

    public BigDecimal getWartosc(BigDecimal x) {
        if (x.equals(BigDecimal.valueOf(0.0))) {
            return BigDecimal.valueOf(1.0);
        } else {
            return BigDecimal.valueOf(sin(Math.PI * x.doubleValue()) / (Math.PI * x.doubleValue()));
        }
    }
    public Double getWartosc(Double x) {
        if (x.equals(0.0)) {
            return 1.0;
        } else {
            return (sin(Math.PI * x.doubleValue()) / (Math.PI * x.doubleValue()));
        }
    }
}
