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
public class SygSkokJednostkowyGenerator extends SygnalGenerator{
    public SygSkokJednostkowyGenerator()
    {
        this.nazwa = "Skok jednostkowy";
    }
    
    @Override
    public ArrayList<Punkt> sygnal(Parametry p)
    {
        ArrayList<Punkt> res = new ArrayList<>();
        Double t;
        for(t = p.getT1(); t<p.getTs(); t+=p.getKrokProbkowaniaCiaglego())
        {
//            t = round(t, Double.toString(Math.floor(p.getCzestProbkCiaglego())).length(), false);
            res.add(new Punkt(t, 0.0));
        }
        res.add(new Punkt(t, 0.5*p.getA()));
        for(t+=p.getKrokProbkowaniaCiaglego(); t<(p.getT1()+p.getD()); t+=p.getKrokProbkowaniaCiaglego())
        {
//            t = round(t, Double.toString(Math.floor(p.getCzestProbkCiaglego())).length(), false);
            res.add(new Punkt(t, p.getA()));
        }
        return res;
    }
    
}
