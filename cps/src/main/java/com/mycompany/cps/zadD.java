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
 * @author maciek
 */
public class zadD extends zadJ{

    static String sciezkaParametrow1 = "syg1.config";

    public static void main(String[] args) {
        Sygnal wejsciowy, sprobkowany, skwantyzowany, odtworzony = null;
        Parametry p1 = Parametry.wczytajParametry(sciezkaParametrow1);

        SygnalGenerator sg1 = wybierzSygnal(p1);
        wejsciowy = stworzSygnal(p1, sg1);

        Probkowanie pr = new Probkowanie();
        sprobkowany = pr.probkuj(wejsciowy, p1);

        Kwantyzacja kw = new Kwantyzacja();
        skwantyzowany = kw.kwantyzuj(sprobkowany, p1);

        try {
            Rekonstrukcja rek = wybierzRodzajRekonstrukcji(p1.getRodzajRekonstrukcji());
            odtworzony = rek.odtworz(skwantyzowany, p1.getKrokProbkowaniaCiaglego());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        dodajSygnal(skwantyzowany);
        dodajSygnal(odtworzony);
        dodajSygnal(sprobkowany);
        dodajSygnal(wejsciowy);
        Wykres.rysuj("Cyfrowe przetwarzanie sygnałów, zad. 2.", "Wykres", listaSygnalow);
        miaryPodobienstwa(wejsciowy, odtworzony);
//        Histogram.rysuj(wejsciowy, "Cyfrowe przetwarzanie sygnałów, zad. 1.", "Histogram amplitudy sygnału", p1.getLiczbaPrzedzialowHistogramu());
    }

    private static Rekonstrukcja wybierzRodzajRekonstrukcji(String p) {
        if (p == null) return null;
        p = p.toLowerCase();

        Rekonstrukcja r = null;

        switch (p) {
            case "r1":
                r = new Ekstrapolacja0();
                break;
            case "r2":
                r = new Ekstrapolacja1();
                break;
            case "r3":
                r = new InterpolacjaSinc();
                break;
        }
        if (r != null) return r;
        else
            throw new UnsupportedOperationException("nie określono rodzaju rekonstrukcji");
    }

    private static void miaryPodobienstwa(Sygnal s, Sygnal o) {
        if (s == null || o == null) return;
        System.out.println("MSE: " + Sygnal.obliczMSE(s, o));
        System.out.println("SNR: " + Sygnal.obliczSNR(s, o));
        System.out.println("PSNR: " + Sygnal.obliczPSNR(s, o));
        System.out.println("MD: " + Sygnal.obliczMD(s, o));

    }

}
