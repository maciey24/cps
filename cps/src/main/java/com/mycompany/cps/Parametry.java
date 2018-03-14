/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps;

import com.thoughtworks.xstream.XStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maciek
 */
public class Parametry implements Serializable {
    private Double czestProbkCiaglego, krokProbkowaniaCiaglego;
    private Integer liczbaPrzedzialowHistogramu;
    private Double wartoscSrednia, wartoscSredniaBezwzgledna, wartoscSkuteczna, wariancja, mocSrednia;
    private String rodzajSygnalu;
    //Amplituda, okres podstawowy, czas poczatkowy, czas trwania sygnalu, wspolczynnik wypelnienia, czas koncowy;
    private Double A, T, t1, d, kw, t2, n2;

    static Parametry wczytajParametry(String sciezkaPliku) {
        XStream xStream = new XStream();
        String zawartoscPliku = null;
        
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(sciezkaPliku));
            zawartoscPliku =  new String(encoded, "UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(Parametry.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parametry p = (Parametry) xStream.fromXML(zawartoscPliku);
        p.ustawKrokProbkowania();
        return p;
    }
    
    void zapiszParametry(String sciezkaPliku)
    {
        XStream xstream = new XStream();
        String zawartoscPliku = xstream.toXML(this);
        try (PrintWriter out = new PrintWriter(sciezkaPliku)) {
            out.println(zawartoscPliku);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parametry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void initJakiesParametry()
    {
        czestProbkCiaglego = 100000.0;
        krokProbkowaniaCiaglego = 1.0/czestProbkCiaglego;
        liczbaPrzedzialowHistogramu = 5;
        this.A = 1.0;
        this.T = 2*Math.PI;
        t1 = 0.5;
        d = 6.0;
        kw = 0.5;
    }

    public Double getCzestProbkCiaglego() {
        return czestProbkCiaglego;
    }

    public void setCzestProbkCiaglego(Double czestProbkCiaglego) {
        this.czestProbkCiaglego = czestProbkCiaglego;
    }

    public Double getKrokProbkowaniaCiaglego() {
        return krokProbkowaniaCiaglego;
    }

    public void setKrokProbkowaniaCiaglego(Double krokProbkowaniaCiaglego) {
        this.krokProbkowaniaCiaglego = krokProbkowaniaCiaglego;
    }

    public Integer getLiczbaPrzedzialowHistogramu() {
        return liczbaPrzedzialowHistogramu;
    }

    public void setLiczbaPrzedzialowHistogramu(Integer liczbaPrzedzialowHistogramu) {
        this.liczbaPrzedzialowHistogramu = liczbaPrzedzialowHistogramu;
    }

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

    public Double getWartoscSkuteczna() {
        return wartoscSkuteczna;
    }

    public void setWartoscSkuteczna(Double wartoscSkuteczna) {
        this.wartoscSkuteczna = wartoscSkuteczna;
    }

    public Double getWariancja() {
        return wariancja;
    }

    public void setWariancja(Double wariancja) {
        this.wariancja = wariancja;
    }

    public Double getMocSrednia() {
        return mocSrednia;
    }

    public void setMocSrednia(Double mocSrednia) {
        this.mocSrednia = mocSrednia;
    }

    public String getRodzajSygnalu() {
        return rodzajSygnalu;
    }

    public void setRodzajSygnalu(String rodzajSygnalu) {
        this.rodzajSygnalu = rodzajSygnalu;
    }

    public Double getA() {
        return A;
    }

    public void setA(Double A) {
        this.A = A;
    }

    public Double getT() {
        return T;
    }

    public void setT(Double T) {
        this.T = T;
    }

    public Double getT1() {
        return t1;
    }

    public void setT1(Double t1) {
        this.t1 = t1;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public Double getKw() {
        return kw;
    }

    public void setKw(Double kw) {
        this.kw = kw;
    }

    public Double getT2() {
        return t2;
    }

    public void setT2(Double t2) {
        this.t2 = t2;
    }

    public Double getN2() {
        return n2;
    }

    public void setN2(Double n2) {
        this.n2 = n2;
    }

    private void ustawKrokProbkowania() {
        this.krokProbkowaniaCiaglego= 1.0/czestProbkCiaglego;
    }
}

