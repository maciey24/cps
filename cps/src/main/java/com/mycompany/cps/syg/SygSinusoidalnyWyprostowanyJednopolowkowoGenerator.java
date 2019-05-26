/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps.syg;

import com.mycompany.cps.Parametry;
import com.mycompany.cps.Punkt;

import static java.lang.Math.*;

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
        for (Double t = p.getT1(); t < (p.getT1() + p.getD()); t += p.getKrokProbkowaniaCiaglego()) {
//            t = round(t, Double.toString(Math.floor(p.getCzestProbkCiaglego())).length(), false);
            res.add(new Punkt(t, 0.5 * p.getA() * (sin(((2.0 * Math.PI) / p.getT()) * (t - p.getT1())) + Math.abs(sin(((2.0 * Math.PI) / p.getT()) * (t - p.getT1()))))));
        }
        return res;
    }

}
