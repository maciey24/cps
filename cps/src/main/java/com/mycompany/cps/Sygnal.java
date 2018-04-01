/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps;

import com.mycompany.cps.dzial.Dzialanie;
import com.mycompany.cps.syg.SygnalGenerator;
import com.thoughtworks.xstream.core.util.CustomObjectInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maciek
 */
public class Sygnal implements Serializable{
    private List<Punkt> list;
    private String nazwa;
    private Double wartoscSrednia, wartoscSredniaBezwzgledna, mocSrednia, wariancja, wartoscSkuteczna;

    public Double getWartoscSrednia() {
        return wartoscSrednia;
    }

    public void setWartoscSrednia(Double wartoscSrednia) {
        this.wartoscSrednia = wartoscSrednia;
    }

    public Double getWartoscSredniaBezwzgledna() {
        return wartoscSredniaBezwzgledna;
    }

    public void setWartoscSredniaBezwzgledna(Double wartoscSredniaBezwzgledna) {
        this.wartoscSredniaBezwzgledna = wartoscSredniaBezwzgledna;
    }

    public Double getMocSrednia() {
        return mocSrednia;
    }

    public void setMocSrednia(Double mocSrednia) {
        this.mocSrednia = mocSrednia;
    }

    public Double getWariancja() {
        return wariancja;
    }

    public void setWariancja(Double wariancja) {
        this.wariancja = wariancja;
    }

    public Double getWartoscSkuteczna() {
        return wartoscSkuteczna;
    }

    public void setWartoscSkuteczna(Double wartoscSkuteczna) {
        this.wartoscSkuteczna = wartoscSkuteczna;
    }

    Sygnal(SygnalGenerator generatorSygnalu, Parametry p) {
        this.setList(generatorSygnalu.sygnal(p));
        this.setNazwa(generatorSygnalu.getNazwa());
    }
    
    public Sygnal()
    {
        this.setNazwa("");
        this.setList(new ArrayList<>());
    }
    
    public List<Punkt> getList() {
        return list;
    }

    public void setList(List<Punkt> list) {
        this.list = list;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    void zapiszDoPliku(String sciezka) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(sciezka);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    static Sygnal wczytajZPliku(String sciezka)
    {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(sciezka);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (Sygnal) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    static Sygnal dzialanie(Sygnal s1, Sygnal s2, Dzialanie d)
    {
        if(s1==null&&s2!=null) return s2;
        if(s2==null&&s1!=null) return s1;
        if(s1==null && s2 == null) return null;
        
        Sygnal res = new Sygnal();
        res.setNazwa(s1.getNazwa() + " " +d.getNazwa()+" " + s2.getNazwa());

        for(Punkt p: s1.list)
        {
            res.list.add(p.clone());
        }
        for(Punkt p: s2.list)
        {
            res.list.add(p.clone());
        }
        Comparator c = new Punkt.PunktCzasComparator();
        Collections.sort(res.list, c);
        
        for(int i = 1 ; i<res.list.size(); i++)
        {            
            if(res.list.get(i).getX().equals(res.list.get(i-1).getX()))
            {
                try {
                    res.list.get(i-1).setY(d.dz(res.list.get(i-1).getY(), res.list.get(i).getY()));
                    res.list.remove(i);
                } catch (Dzialanie.DzialanieException ex) {
                    res.list.remove(i);
                    res.list.remove(i-1);
                }
            }
        }
        return res;
    }
    
    public HashMap<Double, Integer> histogram(int liczbaPrzedzialow)
    {
        HashMap<Double, Integer> h = new HashMap<>();
        ArrayList<Double> krancePrzedzialow = new ArrayList<>();
        Double min = this.obliczMin(), max = this.obliczMax();
        Double A = max - min;
        Double krok = A/liczbaPrzedzialow;
        for(int i = 0; i<liczbaPrzedzialow; i++)
        {
            krancePrzedzialow.add(min+(i*krok));
        }
        krancePrzedzialow.add(max);
        for(int i = 0; i<krancePrzedzialow.size()-1; i++)
        {
            min = krancePrzedzialow.get(i);
            max = krancePrzedzialow.get(i+1);
            Double opis = round(min,2); //"[" + min.toString() + ";" + max.toString() + "]";
            Integer licznik = 0;
            for(Punkt p : this.getList())
            {
                if(p.getY()>=min&&p.getY()<=max)
                {
                    licznik+=1;
                }
            }
            h.put(opis, licznik);
        }
        return h;
    }
    protected static Double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    //wartoscSrednia, wartoscSredniaBezwzgledna, mocSrednia, wariancja, wartoscSkuteczna;
    public void obliczWszystkieParametry()
    {
        this.obliczWartoscSrednia();
        this.obliczWartoscSredniaBezwzgledna();
        this.obliczMocSrednia();
        this.obliczWariancje();
        this.obliczWartoscSkuteczna();
    }

    private void obliczWartoscSrednia() {
        Double suma = 0.0;
        for(Punkt p:this.getList())
        {
            suma+=p.getY();
        }
        this.setWartoscSrednia(suma/this.getList().size());
    }

    private void obliczWartoscSredniaBezwzgledna() {
        Double suma = 0.0;
        for(Punkt p:this.getList())
        {
            suma+=Math.abs(p.getY());
        }
        this.setWartoscSredniaBezwzgledna(suma/this.getList().size());
    }

    private void obliczMocSrednia() {
        Double sumaKwadratow = 0.0;
        for(Punkt p:this.getList())
        {
            sumaKwadratow+= Math.pow(p.getY(), 2.0);
        }
        this.setMocSrednia(sumaKwadratow/this.getList().size());
    }

    private void obliczWariancje() {
        this.obliczWartoscSrednia();
        Double sumaKwadratowOdchylen = 0.0;
        for(Punkt p:this.getList())
        {
            sumaKwadratowOdchylen+= Math.pow(p.getY()-this.getWartoscSrednia(), 2.0);
        }
        this.setWariancja(sumaKwadratowOdchylen/this.getList().size());
    }

    private void obliczWartoscSkuteczna() {
        this.obliczMocSrednia();
        this.setWartoscSkuteczna(Math.sqrt(this.getMocSrednia()));
    }
    
    public String parametryToString()
    {
        String parametry = "";
        parametry += "Sygnał: " + this.getNazwa() + 
                "\nwartość średnia: " + this.getWartoscSrednia() + 
                "\nwartość średnia bezwzględna: " + this.getWartoscSredniaBezwzgledna()+ 
                "\nwartość skuteczna: " + this.getWartoscSkuteczna()+ 
                "\nwariancja: " + this.getWariancja()+ 
                "\nmoc średnia: " + this.getMocSrednia()
                +"\n\n";
        return parametry;
    }

    public Double getWartosc(Double t) {
        Double krok = this.getList().get(1).getX() - this.getList().get(0).getX();
        Double t1 = this.getList().get(0).getX();
        Double tz = this.getList().get(this.getList().size()-1).getX();
        if(t<t1 || t>tz)
        {
            throw new IndexOutOfBoundsException("sygnał nie miał żadnej wartości w podanym czasie");
        }
        Double ind = Math.floor((t-t1)/krok);
        int i = ind.intValue();
        if(this.getList().get(i).getX().equals(t))
        {
            return this.getList().get(i).getY();
        }
        else
        {
            return (this.getList().get(i).getY()+this.getList().get(i+1).getY())/2.0;
        }
    }

    public Double obliczMin() {
        Double min = Double.POSITIVE_INFINITY;
        for(Punkt p : this.getList())
        {
            if(p.getY()<min)
            {
                min = p.getY();
            }
        }
        return min;
    }

    public Double obliczMax() {
        Double max = Double.NEGATIVE_INFINITY;
        for(Punkt p : this.getList())
        {
            if(p.getY()>max)
            {
                max = p.getY();
            }
        }
        return max;
    }
}
