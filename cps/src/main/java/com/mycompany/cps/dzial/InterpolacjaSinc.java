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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author maciek
 */
public class InterpolacjaSinc implements Rekonstrukcja {
    private Double krok;

    @Override
    public Sygnal odtworz(Sygnal s, Double krok) {
        Sygnal o = new Sygnal();
        SygSincGenerator sygSincGen = new SygSincGenerator();
        Parametry pSinc = new Parametry(1000.0, 0.001, 1.0, 1.0, Math.PI, -10.0, 20.0, 10.0);
        Double ts = s.obliczKrokProbkowania();
        this.krok = krok;

        for (Double t = s.getList().get(0).getX(); t < s.getList().get(s.getList().size() - 1).getX(); t += krok) {
            Punkt p = new Punkt(t, 0.0);
            for (int n = 0; n < s.getList().size(); n++) {
                p.setY(p.getY() + s.getList().get(n).getY() * sygSincGen.getWartosc((t / ts - n) - (1.0 / ts) * s.getList().get(0).getX()));
            }
            o.getList().add(p);
        }
        Comparator c = new Punkt.PunktCzasComparator();
        Collections.sort(o.getList(), c);

        o.setNazwa("Odtworzony z " + s.getNazwa().toLowerCase());
        return o;
    }
}
