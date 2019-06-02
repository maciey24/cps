/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps.syg;

import com.mycompany.cps.Parametry;
import com.mycompany.cps.Punkt;

import static java.lang.Math.*;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author maciek
 */
public class SygSinusoidalnyWyprostowanyJednopolowkowoGenerator extends SygnalGenerator {
    public SygSinusoidalnyWyprostowanyJednopolowkowoGenerator() {
        this.nazwa = "Sygnał sinusoidalny wyprostowany jednopołówkowo";
    }

    @Override
    public ArrayList<Punkt> sygnal(Parametry p) {
        ArrayList<Punkt> res = new ArrayList<>();
        for (BigDecimal t = p.getT1(); t.compareTo((p.getT1().add(p.getD()))) <= 0; t = t.add(p.getKrokProbkowaniaCiaglego())) {
            res.add(new Punkt(t, BigDecimal.valueOf(0.5).multiply(p.getA()).multiply((BigDecimal.valueOf(sin(((2.0 * PI) / p.getT().doubleValue()) * (t.subtract(p.getT1())).doubleValue())).add(BigDecimal.valueOf(abs(sin(((2.0 * PI) / p.getT().doubleValue()) * (t.subtract(p.getT1())).doubleValue()))))))));
        }
        return res;
    }

}
