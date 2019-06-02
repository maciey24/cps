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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author maciek
 */
public class Ekstrapolacja1 implements Rekonstrukcja {

    @Override
    public Sygnal odtworz(Sygnal s, final BigDecimal krok) {
        Sygnal o = new Sygnal();
        int liczbaProbekWCzasieProbkowania = s.obliczKrokProbkowania().divide(krok).intValue();

        for (int i = 0; i < s.getLiczbaProbek()-1; i++) {
            if (i < s.getLiczbaProbek()) {
                o.add(new Punkt(s.getWspolrzedne(i)));
                BigDecimal dY = (s.getY(i + 1).subtract(s.getY(i))).divide(BigDecimal.valueOf(liczbaProbekWCzasieProbkowania));
                for (int j = 1; j < liczbaProbekWCzasieProbkowania; j++) {
                    o.add(new Punkt(s.getX(i).add(BigDecimal.valueOf(j).multiply(krok)), s.getY(i).add(BigDecimal.valueOf(j).multiply(dY))));
                }
            }
        }

        o.setNazwa("Odtworzony z " + s.getNazwa().toLowerCase());
        return o;
    }
}
