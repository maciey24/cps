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

    static ArrayList<List<Punkt>> listaSygnalow = new ArrayList<>();
    static ArrayList<String> etykietySygnalow = new ArrayList<>();
    static String sciezkaPliku = "parametry.config";

    public static void main(String[] args) {
        Parametry p = Parametry.wczytajParametry(sciezkaPliku);
        SygnalGenerator sygnal = wybierzSygnal(p);
        dodajSygnal(sygnal, p);
        
        Wykres.rysuj("Cyfrowe przetwarzanie sygnałów, zad. 1.", "Wykres", listaSygnalow, etykietySygnalow);
    }

    private static SygnalGenerator wybierzSygnal(Parametry p){
        SygnalGenerator sygnal = null;
        switch (p.getRodzajSygnalu()) {
            case "S1":
                sygnal = new SygSzumORozkladzieJednostajnymGenerator();
                break;
            case "S2":
                sygnal = new SygSzumORozkladzieNormalnymGenerator();
                break;
            case "S3":
                sygnal = new SygSinusoidalnyGenerator();
                break;
            case "S4":
                sygnal = new SygSinusoidalnyWyprostowanyJednopolowkowoGenerator();
                break;
            case "S5":
                sygnal = new SygSinusoidalnyWyprostowanyDwupolowkowoGenerator();
                break;
            case "S6":
                sygnal = new SygProstokatnyGenerator();
                break;
            case "S7":
                sygnal = new SygProstokatnySymetrycznyGenerator();
                break;
            case "S8":
                sygnal = new SygTrojkatnyGenerator();
                break;
            case "S9":
                sygnal = new SygSkokJednostkowyGenerator();
                break;
            case "S10":
                sygnal = new SygImpulsJednostkowyGenerator();
                break;
            case "S11":
                sygnal = new SygSzumImpulsowyGenerator();
                break;
        }
        return sygnal;
    }

    private static void dodajSygnal(SygnalGenerator sygnal, Parametry p) {        
        listaSygnalow.add(sygnal.sygnal(p));
        etykietySygnalow.add(sygnal.getNazwa());
    }
}
