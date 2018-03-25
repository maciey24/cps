/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps.syg;

import com.mycompany.cps.Parametry;
import com.mycompany.cps.Punkt;
import java.util.ArrayList;

/**
 *
 * @author maciek
 */
public class SygImpulsJednostkowyGenerator extends SygnalGenerator{
    public SygImpulsJednostkowyGenerator()
    {
        this.nazwa = "Impuls jednostkowy";
    }
    
    @Override
    public ArrayList<Punkt> sygnal(Parametry p)
    {
        ArrayList<Punkt> res = new ArrayList<>();
        Double t;
        for(t = p.getT1(); t<p.getTs(); t+=p.getKrokProbkowaniaDyskretnego())
        {
//            t = round(t, Double.toString(Math.floor(p.getCzestProbkCiaglego())).length(), false);
            res.add(new Punkt(t, 0.0));
        }
        res.add(new Punkt(t, p.getA()));
        for(t+=p.getKrokProbkowaniaDyskretnego(); t<(p.getT1()+p.getD()); t+=p.getKrokProbkowaniaDyskretnego())
        {
//            t = round(t, Double.toString(Math.floor(p.getCzestProbkCiaglego())).length(), false);
            res.add(new Punkt(t,0.0));
        }
        return res;
    }
    
}
