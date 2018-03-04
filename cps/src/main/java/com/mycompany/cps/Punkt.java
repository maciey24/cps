/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maciek
 */
public class Punkt {
    private ArrayList<Double> wspolrzedne;

    public void setWspolrzedne(ArrayList<Double> wspolrzedne) {
        this.wspolrzedne = wspolrzedne;
    }

    public ArrayList<Double> getWspolrzedne() {
        return wspolrzedne;
    }
    
    Punkt()
    {
        this.wspolrzedne = new ArrayList<>();
    }
    
    Punkt(List<Double> listaWspolrzednych)
    {
        this.wspolrzedne = (ArrayList<Double>) listaWspolrzednych;
    }
    
    Punkt(int x, int y)
    {
        this.wspolrzedne = new ArrayList<>();
        this.wspolrzedne.add(Double.parseDouble(String.valueOf(x)));
        this.wspolrzedne.add(Double.parseDouble(String.valueOf(y)));
    }
    
    Punkt(Double x, Double y)
    {
        this.wspolrzedne = new ArrayList<>();
        this.wspolrzedne.add(x);
        this.wspolrzedne.add(y);
    }
    
    
}
