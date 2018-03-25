/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author maciek
 */
public class Punkt implements Serializable {
    private ArrayList<Double> wspolrzedne;

    public void setWspolrzedne(ArrayList<Double> wspolrzedne) {
        this.wspolrzedne = wspolrzedne;
    }

    public ArrayList<Double> getWspolrzedne() {
        return wspolrzedne;
    }
    
    public Punkt()
    {
        this.wspolrzedne = new ArrayList<>();
    }
    public Punkt clone()
    {
        Punkt res = new Punkt();
        res.wspolrzedne = new ArrayList<>();
        for(Double d : wspolrzedne)
        {
            res.wspolrzedne.add(d);
        }
        return res;
    }
    
    public Punkt(List<Double> listaWspolrzednych)
    {
        this.wspolrzedne = (ArrayList<Double>) listaWspolrzednych;
    }
    
    public Punkt(int x, int y)
    {
        this.wspolrzedne = new ArrayList<>();
        this.wspolrzedne.add(Double.parseDouble(String.valueOf(x)));
        this.wspolrzedne.add(Double.parseDouble(String.valueOf(y)));
    }
    
    public Punkt(Double x, Double y)
    {
        this.wspolrzedne = new ArrayList<>();
        this.wspolrzedne.add(x);
        this.wspolrzedne.add(y);
    }
    
    public Punkt(int x, int y, int z)
    {
        this.wspolrzedne = new ArrayList<>();
        this.wspolrzedne.add(Double.parseDouble(String.valueOf(x)));
        this.wspolrzedne.add(Double.parseDouble(String.valueOf(y)));
        this.wspolrzedne.add(Double.parseDouble(String.valueOf(z)));
    }
    
    public Punkt(Double x, Double y, Double z)
    {
        this.wspolrzedne = new ArrayList<>();
        this.wspolrzedne.add(x);
        this.wspolrzedne.add(y);
        this.wspolrzedne.add(z);
    }
    public static class PunktCzasComparator implements Comparator<Punkt>
    {
        @Override
        public int compare(Punkt o1, Punkt o2) {
            return Double.compare(o1.getX(), o2.getX());
        }
    }
    
    public Double getX()
    {
        return this.getWspolrzedne().get(0);
    }
    
    public Double getY()
    {
        return this.getWspolrzedne().get(1);
    }
    
    public void setY(Double wartosc)
    {
        this.getWspolrzedne().set(1, wartosc);
    }
}
