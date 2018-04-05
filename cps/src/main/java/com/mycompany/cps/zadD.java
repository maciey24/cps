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
public class zadD {

    static ArrayList<Sygnal> listaSygnalow = new ArrayList<>();
    static ArrayList<String> etykietySygnalow = new ArrayList<>();
    static String sciezkaParametrow1 = "syg1.config";

    public static void main(String[] args) {
        Parametry p1 = Parametry.wczytajParametry(sciezkaParametrow1);
        
        SygnalGenerator sg1 = wybierzSygnal(p1);
        Sygnal s1 = stworzSygnal(p1, sg1);
        
        Probkowanie pr = new Probkowanie();
        Sygnal sprobkowany = pr.probkuj(s1, p1);
        
        Kwantyzacja kw = new Kwantyzacja();
        Sygnal skwantyzowany = kw.kwantyzuj(sprobkowany, p1);
        
        Rekonstrukcja rek = wybierzRodzajRekonstrukcji(p1);
        Sygnal odtworzony = rek.odtworz(skwantyzowany, p1);
        
        dodajSygnal(skwantyzowany);
        dodajSygnal(odtworzony);
        dodajSygnal(sprobkowany);
        dodajSygnal(s1);
        Wykres.rysuj("Cyfrowe przetwarzanie sygnałów, zad. 1.", "Wykres", listaSygnalow);
//        Histogram.rysuj(s1, "Cyfrowe przetwarzanie sygnałów, zad. 1.", "Histogram amplitudy sygnału", p1.getLiczbaPrzedzialowHistogramu());
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

    private static Rekonstrukcja wybierzRodzajRekonstrukcji(Parametry p) {
        if(p == null) return null;
        
        Rekonstrukcja r = null;
        
        switch (p.getRodzajRekonstrukcji()) {
            case "R1":
                r = new Ekstrapolacja0();
                break;
            case "R2":
                r = new Ekstrapolacja1();
                break;
            case "R3":
                throw new UnsupportedOperationException("nie obslugiwane");
//                r = new EkstrapolacjaSinC();
//                break;
        
        }
        return r;
    }
    
}
