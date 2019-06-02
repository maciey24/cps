/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps.dzial;

import com.mycompany.cps.Parametry;
import com.mycompany.cps.Punkt;
import com.mycompany.cps.Sygnal;
import com.mycompany.cps.syg.SygSincGenerator;
import com.mycompany.cps.syg.SygnalGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author maciek
 */
public class InterpolacjaSinc implements Rekonstrukcja {

    @Override
    public Sygnal odtworz(Sygnal s, final BigDecimal krok) {
        Sygnal o = new Sygnal();
        SygSincGenerator sygSincGen = new SygSincGenerator();
        BigDecimal ts = s.obliczKrokProbkowania();

        for (BigDecimal t = s.getCzasPoczatkowy(); t.compareTo(s.getCzasKoncowy()) < 0; t = t.add(krok)) {
            Punkt p = new Punkt(t, BigDecimal.valueOf(0.0));
            for (int n = 0; n < s.getList().size(); n++) {
                p.setY(BigDecimal.valueOf(p.getY().doubleValue() + s.getY(n).doubleValue() * sygSincGen.getWartosc((t.doubleValue() / ts.doubleValue() - n) - (1.0 / ts.doubleValue()) * s.getX(0).doubleValue())));
            }
            o.add(p);
        }

        o.setNazwa("Odtworzony z " + s.getNazwa().toLowerCase());
        return o;
    }
}
