/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps;

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
    static String sciezkaPliku = "parametry.config";

    public static void main(String[] args) {
        Parametry p = Parametry.wczytajParametry(sciezkaPliku);
        Sygnal s1;
        if(p.isCzyWczytacZPliku())
        {
            s1 = Sygnal.wczytajZPliku(p.getSciezkaWczytywania());
        }
        else
        {
            SygnalGenerator generatorSygnalu = wybierzSygnal(p);
            s1 = new Sygnal(generatorSygnalu, p);
        }
        dodajSygnal(s1);
        if(p.isCzyZapisacDoPliku())
        {
            s1.zapiszDoPliku(p.getSciezkaZapisywania());
        }
        
        Wykres.rysuj("Cyfrowe przetwarzanie sygnałów, zad. 1.", "Wykres", listaSygnalow);
    }
    
    private static SygnalGenerator wybierzSygnal(Parametry p){
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
        listaSygnalow.add(s);
    }
}
