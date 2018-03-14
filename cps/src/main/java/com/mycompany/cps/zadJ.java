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
        Sygnal sygnal = wybierzSygnal(p);
        
        dodajSygnal(sygnal, p);
        
        Wykres.rysuj("Tytuł okna", "Wykres", listaSygnalow, etykietySygnalow);
    }

    private static Sygnal wybierzSygnal(Parametry p){
        Sygnal sygnal = null;
        switch (p.getRodzajSygnalu()) {
            case "S1":
                break;
            case "S2":
                break;
            case "S3":
                sygnal = new SygSinusoidalny();
                break;
            case "S4":
                break;
            case "S5":
                break;
            case "S6":
                break;
            case "S7":
                break;
            case "S8":
                break;
            case "S9":
                break;
            case "S10":
                break;
            case "S11":
                break;
        }
        return sygnal;
    }

    private static void dodajSygnal(Sygnal sygnal, Parametry p) {        
        listaSygnalow.add(sygnal.sygnal(p));
        etykietySygnalow.add(sygnal.getNazwa());
    }
}
