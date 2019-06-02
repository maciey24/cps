/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author maciek
 */
public class Punkt implements Serializable {
    private ArrayList<BigDecimal> wspolrzedne;

    public void setWspolrzedne(ArrayList<BigDecimal> wspolrzedne) {
        this.wspolrzedne = wspolrzedne;
    }

    public ArrayList<BigDecimal> getWspolrzedne() {
        return wspolrzedne;
    }

    public Punkt() {
        this.wspolrzedne = new ArrayList<>();
    }

    public Punkt clone() {
        Punkt res = new Punkt();
        res.wspolrzedne = new ArrayList<>();
        for (BigDecimal d : wspolrzedne) {
            res.wspolrzedne.add(d);
        }
        return res;
    }

    public Punkt(List<BigDecimal> listaWspolrzednych) {
        this.wspolrzedne = (ArrayList<BigDecimal>) listaWspolrzednych;
    }

    public Punkt(int x, int y) {
        this.wspolrzedne = new ArrayList<>();
        this.wspolrzedne.add(BigDecimal.valueOf(x));
        this.wspolrzedne.add(BigDecimal.valueOf(y));
    }

    public Punkt(Double x, Double y) {
        this.wspolrzedne = new ArrayList<>();
        this.wspolrzedne.add(BigDecimal.valueOf(x));
        this.wspolrzedne.add(BigDecimal.valueOf(y));
    }

    public Punkt(BigDecimal x, BigDecimal y) {
        this.wspolrzedne = new ArrayList<>();
        this.wspolrzedne.add(x);
        this.wspolrzedne.add(y);
    }

    public Punkt(int x, int y, int z) {
        this.wspolrzedne = new ArrayList<>();
        this.wspolrzedne.add(BigDecimal.valueOf(x));
        this.wspolrzedne.add(BigDecimal.valueOf(y));
        this.wspolrzedne.add(BigDecimal.valueOf(z));
    }

    public Punkt(Double x, Double y, Double z) {
        this.wspolrzedne = new ArrayList<>();
        this.wspolrzedne.add(BigDecimal.valueOf(x));
        this.wspolrzedne.add(BigDecimal.valueOf(y));
        this.wspolrzedne.add(BigDecimal.valueOf(z));
    }

    public Punkt(BigDecimal x, BigDecimal y, BigDecimal z) {
        this.wspolrzedne = new ArrayList<>();
        this.wspolrzedne.add(x);
        this.wspolrzedne.add(y);
        this.wspolrzedne.add(z);
    }

    public static class PunktCzasComparator implements Comparator<Punkt> {
        @Override
        public int compare(Punkt o1, Punkt o2) {
//            return Double.compare(o1.getX().doubleValue(), o2.getX().doubleValue());
            return (o1.getX().compareTo(o2.getX()));
        }
    }

    public BigDecimal getX() {
        return this.getWspolrzedne().get(0);
    }

    public BigDecimal getY() {
        return this.getWspolrzedne().get(1);
    }

    public void setY(BigDecimal wartosc) {
        this.getWspolrzedne().set(1, wartosc);
    }

    @Override
    public String toString() {
        return "Punkt{" +
                "wspolrzedne=" + wspolrzedne +
                '}';
    }
}
