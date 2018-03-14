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
public class SygSzumORozkladzieJednostajnymGenerator extends SygnalGenerator {
    public SygSzumORozkladzieJednostajnymGenerator()
    {
        this.nazwa = "Szum o rozkładzie jednostajnym";
    }

    @Override
    public ArrayList<Punkt> sygnal(Parametry p) {
        ArrayList<Punkt> res = new ArrayList<>();
        for(Double t = p.getT1(); t<(p.getT1()+p.getD()); t+=p.getKrokProbkowaniaCiaglego())
        {
            res.add(new Punkt(t, this.rand(-p.getA(), p.getA())));
        }
        return res;
    }
    
}