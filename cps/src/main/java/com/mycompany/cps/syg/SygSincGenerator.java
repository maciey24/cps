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
public class SygSincGenerator extends SygnalGenerator{
    public SygSincGenerator()
    {
        this.nazwa = "Sygnał SinC";
    }
    
    @Override
    public ArrayList<Punkt> sygnal(Parametry p)
    {
        ArrayList<Punkt> res = new ArrayList<>();
        for(Double t = p.getT1(); t<(p.getT1()+p.getD()); t+=p.getKrokProbkowaniaCiaglego())
        {
            if(t.compareTo(0.0)==0.0)
            {
                res.add(new Punkt(t/p.getKrokProbkowaniaDyskretnego(), 1.0));
            }
            else 
            {
                res.add(new Punkt(t/p.getKrokProbkowaniaDyskretnego(), Math.sin(t)/t));
            }
        }
        return res;
    }
    
    public Double getWartosc(Double x)
    {
        if(x.compareTo(0.0)==0.0)
        {
            return 1.0;
        }
        else
        {
            return Math.sin(Math.PI*x)/(Math.PI*x);
        }
    }
}
