/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps.dzial;

import com.mycompany.cps.Parametry;
import com.mycompany.cps.Punkt;
import com.mycompany.cps.Sygnal;
import com.mycompany.cps.syg.SygnalGenerator;

import java.math.BigDecimal;

/**
 * @author maciek
 */
public class Probkowanie {
    private BigDecimal krok;

    public Sygnal probkuj(Sygnal s, Parametry p) {
        Sygnal k = new Sygnal();
        this.krok = new BigDecimal(1.0).divide(p.getF());

        for (BigDecimal t = s.getCzasPoczatkowy();
             t.compareTo(s.getCzasKoncowy())<=0;
             t=t.add(krok)) {
            k.getList().add(new Punkt(t, s.getWartosc(t)));
        }
        k.setNazwa("Spróbkowany " + s.getNazwa().toLowerCase());
        return k;
    }
}
