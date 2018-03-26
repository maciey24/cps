/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps;

//import com.mycompany.cps.dzial.Dodawanie;
import com.mycompany.cps.dzial.*;
import com.mycompany.cps.syg.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maciek
 */
public class zadJ {

    static ArrayList<Sygnal> listaSygnalow = new ArrayList<>();
    static ArrayList<String> etykietySygnalow = new ArrayList<>();
    static String sciezkaParametrow1 = "syg1.config";
    static String sciezkaParametrow2 = "syg2.config";

    public static void main(String[] args) {
        Parametry p1 = Parametry.wczytajParametry(sciezkaParametrow1);
        Parametry p2 = Parametry.wczytajParametry(sciezkaParametrow2);
        
        Sygnal wynik = null;
        SygnalGenerator sg1 = wybierzSygnal(p1);
        SygnalGenerator sg2 = wybierzSygnal(p2);
        Sygnal s1 = stworzSygnal(p1, sg1);
        Sygnal s2 = stworzSygnal(p2, sg2);
         
        Dzialanie d = wybierzDzialanie(p2);
        
        if(d!=null) 
        {
            wynik = Sygnal.dzialanie(s1, s2, d);
        }
        
        dodajSygnal(s1);
        dodajSygnal(s2);
        dodajSygnal(wynik);
        Wykres.rysuj("Cyfrowe przetwarzanie sygnałów, zad. 1.", "Wykres", listaSygnalow);
        
        Histogram.rysuj(s1, "Cyfrowe przetwarzanie sygnałów, zad. 1.", "Histogram amplitudy sygnału", p1.getLiczbaPrzedzialowHistogramu());
        Histogram.rysuj(s2, "Cyfrowe przetwarzanie sygnałów, zad. 1.", "Histogram amplitudy sygnału", p2.getLiczbaPrzedzialowHistogramu());
        Histogram.rysuj(wynik, "Cyfrowe przetwarzanie sygnałów, zad. 1.", "Histogram amplitudy sygnału", p2.getLiczbaPrzedzialowHistogramu());
        
        for(Sygnal s: listaSygnalow)
        {
            wypiszParametry(s);
        }
        
        if(p1.isCzyZapisacDoPliku())
        {
            if(s1!=null) 
            {
                s1.zapiszDoPliku(p1.getSciezkaZapisywania());
            }
        }
        if(p2.isCzyZapisacDoPliku())
        {
            if(s2!=null) 
            {
                s2.zapiszDoPliku(p2.getSciezkaZapisywania());
            }
        }
        if(p2.isCzyWynikDoPliku())
        {
            if(wynik!=null) 
            {
                wynik.zapiszDoPliku(p2.getSciezkaWyniku());
            }
        }
        
        
    }

    private static SygnalGenerator wybierzSygnal(Parametry p) {
        if(p == null) return null;
        if(p.isCzyWczytacZPliku()) return null;
        if("0".equals(p.getRodzajSygnalu())) return null;
        
        SygnalGenerator generatorSygnalu = null;
        switch (p.getRodzajSygnalu()) {
            case "S1":
                generatorSygnalu = new SygSzumORozkladzieJednostajnymGenerator();
                break;
            case "S2":
                generatorSygnalu = new SygSzumORozkladzieNormalnymGenerator();
                break;
            case "S3":
                generatorSygnalu = new SygSinusoidalnyGenerator();
                break;
            case "S4":
                generatorSygnalu = new SygSinusoidalnyWyprostowanyJednopolowkowoGenerator();
                break;
            case "S5":
                generatorSygnalu = new SygSinusoidalnyWyprostowanyDwupolowkowoGenerator();
                break;
            case "S6":
                generatorSygnalu = new SygProstokatnyGenerator();
                break;
            case "S7":
                generatorSygnalu = new SygProstokatnySymetrycznyGenerator();
                break;
            case "S8":
                generatorSygnalu = new SygTrojkatnyGenerator();
                break;
            case "S9":
                generatorSygnalu = new SygSkokJednostkowyGenerator();
                break;
            case "S10":
                generatorSygnalu = new SygImpulsJednostkowyGenerator();
                break;
            case "S11":
                generatorSygnalu = new SygSzumImpulsowyGenerator();
                break;
        }
        return generatorSygnalu;
    }

    private static void dodajSygnal(Sygnal s) {
        if (s==null) return;
        listaSygnalow.add(s);
    }

    private static Dzialanie wybierzDzialanie(Parametry p) {
        if(p == null) return null;
        if("0".equals(p.getRodzajSygnalu())) return null;
        if("0".equals(p.getRodzajOperacji())) return null;
                
        Dzialanie dz = null;
        switch (p.getRodzajOperacji()) {
            case "+":
                dz = new Dodawanie();
                break;
            case "-":
                dz = new Odejmowanie();
                break;
            case "/":
                dz = new Dzielenie();
                break;
            case "*":
                dz = new Mnozenie();
                break;
        }
        return dz;
    }

    private static Sygnal stworzSygnal(Parametry p, SygnalGenerator sg) {
        Sygnal s = null;
        if(p.isCzyWczytacZPliku())
        {
            s = Sygnal.wczytajZPliku(p.getSciezkaWczytywania());
        }
        else if(!"0".equals(p.getRodzajSygnalu()))
        {
            s = new Sygnal(sg, p);
        }
        return s;
    }
    
    private static void wypiszParametry(Sygnal s) {
        if (s==null) return;
        s.obliczWszystkieParametry();
        System.out.println(s.parametryToString());
    }
    
}
