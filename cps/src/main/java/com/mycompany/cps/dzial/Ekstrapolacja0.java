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
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author maciek
 */
public class Ekstrapolacja0 implements Rekonstrukcja{
    private Double krok;

    @Override
    public Sygnal odtworz(Sygnal s, Parametry p)
    {
        Sygnal o = new Sygnal();
        krok = p.getKrokProbkowaniaCiaglego();
        Double liczbaProbekWCzasieProbkowania = (s.getList().get(1).getX() - s.getList().get(0).getX())/krok;
        
        for(int i = 0; i<s.getList().size(); i++)
        {
            o.getList().add(new Punkt(s.getList().get(i).getWspolrzedne()));
            for(int j=1; j<liczbaProbekWCzasieProbkowania; j++)
            {
                o.getList().add(new Punkt(s.getList().get(i).getX()+(j*krok), s.getList().get(i).getY()));
            }
        }

        o.setNazwa("Odtworzony z "+s.getNazwa().toLowerCase());
        return o;
    }
}
