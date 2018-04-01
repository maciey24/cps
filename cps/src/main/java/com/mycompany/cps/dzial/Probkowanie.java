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

/**
 *
 * @author maciek
 */
public class Probkowanie {
    private Double krok;

    public Sygnal probkuj(Sygnal s, Parametry p)
    {
        Sygnal k = new Sygnal();
        this.krok = 1.0/p.getF();
        
        for(Double t = s.getList().get(0).getX(); 
                t<=s.getList().get(s.getList().size()-1).getX(); 
                t+=krok)
        {
            k.getList().add(new Punkt(t, s.getWartosc(t)));
        }
        k.setNazwa("Spróbkowany "+s.getNazwa().toLowerCase());
        return k;
    }
}
