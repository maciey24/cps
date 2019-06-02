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

/**
 * @author maciek
 */
public class Kwantyzacja {
    private BigDecimal krok;
    private ArrayList<BigDecimal> poziomy;

    public Sygnal kwantyzuj(Sygnal s, Parametry p) {
        Sygnal k = new Sygnal();
        BigDecimal min = s.obliczMin();
        BigDecimal max = s.obliczMax();
        krok = (max.subtract(min)).divide(BigDecimal.valueOf(p.getLiczbaPoziomowKwantyzacji()));

        poziomy = new ArrayList<>();
        for (int i = 0; i < p.getLiczbaPoziomowKwantyzacji(); i++) {
            poziomy.add(min.add(BigDecimal.valueOf(i).multiply(krok)));
        }
        Collections.reverse(poziomy);

        for (Punkt pkt : s.getList()) {
            k.add(new Punkt(pkt.getX(), this.wartoscPoKwantyzacji(pkt.getY(), p)));
        }
        if ("q1".equals(p.getRodzajKwantyzacji().toLowerCase())) {
            k.setNazwa("Skwantyzowany z obcięciem " + s.getNazwa().toLowerCase());
        }
        if ("q2".equals(p.getRodzajKwantyzacji().toLowerCase())) {
            k.setNazwa("Skwantyzowany z zaokrągleniem " + s.getNazwa().toLowerCase());
        }
        return k;
    }

    private BigDecimal wartoscPoKwantyzacji(BigDecimal y, Parametry p) {
        if ("q1".equals(p.getRodzajKwantyzacji().toLowerCase())) {
            for (BigDecimal poziom : poziomy) {
                if (y.compareTo(poziom) >= 0.0) {
                    return poziom;
                }
            }
        } else if ("q2".equals(p.getRodzajKwantyzacji().toLowerCase())) {
            for (BigDecimal poziom : poziomy) {
                if (((y.add(BigDecimal.valueOf(0.5).multiply(krok))).compareTo(poziom)) >= 0.0) {
                    return poziom;
                }
            }
        }
        return null;
    }
}
