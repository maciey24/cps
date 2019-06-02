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
public class SygImpulsJednostkowyGenerator extends SygnalGenerator {
    public SygImpulsJednostkowyGenerator() {
        this.nazwa = "Impuls jednostkowy";
    }

    @Override
    public ArrayList<Punkt> sygnal(Parametry p) {
        ArrayList<Punkt> res = new ArrayList<>();
        BigDecimal t;
        for (t = p.getT1(); t.compareTo(p.getTs()) < 0; t= t.add(p.getKrokProbkowaniaCiaglego())) {
            res.add(new Punkt(t, BigDecimal.valueOf(0.0)));
        }
        res.add(new Punkt(t, p.getA()));
        for (t=t.add(p.getKrokProbkowaniaCiaglego()); t.compareTo(p.getT1().add(p.getD())) <= 0; t=t.add(p.getKrokProbkowaniaCiaglego())) {
            res.add(new Punkt(t, BigDecimal.valueOf(0.0)));
        }
        return res;
    }

}
