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

import static java.lang.Math.*;

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
        for (BigDecimal t = p.getT1(); t.compareTo(p.getT1().add(p.getD())) < 0; t = t.add(p.getKrokProbkowaniaCiaglego())) {
            res.add(new Punkt(t, p.getA().multiply(BigDecimal.valueOf(sin(((2.0 * PI) / p.getT().doubleValue()) * (t.subtract(p.getT1())).doubleValue())))));
        }
        return res;
    }
}
