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
 * @author maciek
 */
public class Parametry implements Serializable {

    public Parametry(Double czestProbkCiaglego,
                     Double krokProbkowaniaCiaglego,
                     Double krokProbkowaniaDyskr, Double A, Double T, Double t1,
                     Double d, Double t2) {
        this.czestProbkCiaglego = czestProbkCiaglego;
        this.krokProbkowaniaCiaglego = krokProbkowaniaCiaglego;
        this.krokProbkowaniaDyskretnego = krokProbkowaniaDyskr;
        this.A = A;
        this.T = T;
        this.t1 = t1;
        this.d = d;
        this.t2 = t2;
    }

    private Double czestProbkCiaglego, krokProbkowaniaCiaglego, krokProbkowaniaDyskretnego;

    private Parametry() {

    }

    public Double getKrokProbkowaniaDyskretnego() {
        return krokProbkowaniaDyskretnego;
    }

    public void setKrokProbkowaniaDyskretnego(Double krokProbkowaniaDyskretnego) {
        this.krokProbkowaniaDyskretnego = krokProbkowaniaDyskretnego;
    }

    private Integer liczbaPrzedzialowHistogramu, liczbaPoziomowKwantyzacji;

    public Integer getLiczbaPoziomowKwantyzacji() {
        return liczbaPoziomowKwantyzacji;
    }

    public void setLiczbaPoziomowKwantyzacji(Integer liczbaPoziomowKwantyzacji) {
        this.liczbaPoziomowKwantyzacji = liczbaPoziomowKwantyzacji;
    }

    private Double wartoscSrednia, wartoscSredniaBezwzgledna, wartoscSkuteczna, wariancja, mocSrednia;
    private String rodzajSygnalu, rodzajOperacji, rodzajKwantyzacji, rodzajRekonstrukcji;

    public String getRodzajRekonstrukcji() {
        return rodzajRekonstrukcji;
    }

    public void setRodzajRekonstrukcji(String rodzajRekonstrukcji) {
        this.rodzajRekonstrukcji = rodzajRekonstrukcji;
    }

    public String getRodzajKwantyzacji() {
        return rodzajKwantyzacji;
    }

    public void setRodzajKwantyzacji(String rodzajKwantyzacji) {
        this.rodzajKwantyzacji = rodzajKwantyzacji;
    }

    public String getRodzajOperacji() {
        return rodzajOperacji;
    }

    public void setRodzajOperacji(String rodzajOperacji) {
        this.rodzajOperacji = rodzajOperacji;
    }

    private String sciezkaWczytywania, sciezkaZapisywania, sciezkaWyniku;

    public String getSciezkaWyniku() {
        return sciezkaWyniku;
    }

    public void setSciezkaWyniku(String sciezkaWyniku) {
        this.sciezkaWyniku = sciezkaWyniku;
    }

    public String getSciezkaZapisywania() {
        return sciezkaZapisywania;
    }

    public void setSciezkaZapisywania(String sciezkaZapisywania) {
        this.sciezkaZapisywania = sciezkaZapisywania;
    }

    public String getSciezkaWczytywania() {
        return sciezkaWczytywania;
    }

    public void setSciezkaWczytywania(String sciezkaWczytywania) {
        this.sciezkaWczytywania = sciezkaWczytywania;
    }

    //             Amplituda, okres podstawowy, czas poczatkowy, czas trwania sygnalu, wspolczynnik wypelnienia, czas koncowy, probka koncowa, czas skoku,    //czestotliwosc (probkowania), prawdopodobienstwo
    private Double A, T, t1, d, kw, t2, n2, ts, f, p;
    private boolean czyWczytacZPliku, czyZapisacDoPliku, czyWynikDoPliku;

    public boolean isCzyWynikDoPliku() {
        return czyWynikDoPliku;
    }

    public void setCzyWynikDoPliku(boolean czyWynikDoPliku) {
        this.czyWynikDoPliku = czyWynikDoPliku;
    }

    public boolean isCzyWczytacZPliku() {
        return czyWczytacZPliku;
    }

    public void setCzyWczytacZPliku(boolean czyWczytacZPliku) {
        this.czyWczytacZPliku = czyWczytacZPliku;
    }

    public boolean isCzyZapisacDoPliku() {
        return czyZapisacDoPliku;
    }

    public void setCzyZapisacDoPliku(boolean czyZapisacDoPliku) {
        this.czyZapisacDoPliku = czyZapisacDoPliku;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getF() {
        return f;
    }

    public void setF(Double f) {
        this.f = f;
    }

    static Parametry wczytajParametry(String sciezkaPliku) {
        XStream xStream = new XStream();
        String zawartoscPliku = null;

        try {
            byte[] encoded = Files.readAllBytes(Paths.get(sciezkaPliku));
            zawartoscPliku = new String(encoded, "UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(Parametry.class.getName()).log(Level.SEVERE, null, ex);
            Parametry p = new Parametry();
            p.initJakiesParametry();
            p.zapiszParametry(sciezkaPliku);
            System.err.println("Nie znaleziono pliku z parametrami! Zostały wczytane domyślne ustawienia programu");
            return p;
        }
        Parametry p = (Parametry) xStream.fromXML(zawartoscPliku);
        p.ustawKrokProbkowania();
        p.setRodzajKwantyzacji(p.getRodzajKwantyzacji().toUpperCase());
        p.setRodzajSygnalu(p.getRodzajSygnalu().toUpperCase());
        p.setRodzajRekonstrukcji(p.getRodzajRekonstrukcji().toUpperCase());
        try {
            p.asercjaPoczatkowa();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
        return p;
    }

    void zapiszParametry(String sciezkaPliku) {
        XStream xstream = new XStream();
        String zawartoscPliku = xstream.toXML(this);
        try (PrintWriter out = new PrintWriter(sciezkaPliku)) {
            out.println(zawartoscPliku);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parametry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void initJakiesParametry() {
        czestProbkCiaglego = 10000.0;
        liczbaPrzedzialowHistogramu = 5;
        this.A = 1.0;
        this.T = 2 * Math.PI;
        t1 = 0.5;
        d = 7.0;
        kw = 0.5;
        this.setTs(3.5);
        this.setF(1.0);
        this.setRodzajSygnalu("S3");
        this.setP(0.05);
        this.setCzyWczytacZPliku(false);
        this.setCzyZapisacDoPliku(true);
        this.setRodzajOperacji("+");
        this.setSciezkaWczytywania("s0.bin");
        this.setSciezkaZapisywania("s0.bin");
        this.setSciezkaWyniku("wynik.bin");
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

    public Double getTs() {
        return ts;
    }

    public void setTs(Double ts) {
        this.ts = ts;
    }

    public Double getN2() {
        return n2;
    }

    public void setN2(Double n2) {
        this.n2 = n2;
    }

    private void ustawKrokProbkowania() {
        this.krokProbkowaniaCiaglego = 1.0 / czestProbkCiaglego;
        this.krokProbkowaniaDyskretnego = 1.0 / this.getF();
    }

    private void asercjaPoczatkowa() throws ParametryAsercjaException {
        if ("0".equals(this.getRodzajSygnalu())) return;

        if (this.getD().compareTo(0.0) < 0) {
            throw new ParametryAsercjaException("czas trwania sygnału powinien być liczbą rzeczywistą większą od 0!");
        }
        if (this.getKw().compareTo(0.0) < 0 || this.getKw().compareTo(1.0) > 0) {
            throw new ParametryAsercjaException("współczynnik wypełnienia sygnału powinien być liczbą z przedziału [0.0 ; 1.0]");
        }
        if (this.getRodzajSygnalu().charAt(0) != 'S' || !(Integer.parseInt(this.getRodzajSygnalu().substring(1)) > 0 && Integer.parseInt(this.getRodzajSygnalu().substring(1)) < 12)) {
            throw new ParametryAsercjaException("rodzaj sygnału powinien być określony przez ciąg znaków: [S][1-11]");
        }
        if (("S9".equals(this.getRodzajSygnalu())
                || "S10".equals(this.getRodzajSygnalu())
        ) &&
                (this.getTs().compareTo(this.getT1()) < 0.0 ||
                        this.getTs().compareTo(this.getT1() + this.getD()) > 0.0)) {
            throw new ParametryAsercjaException("czas skoku musi zawierać się w przedziale od czasu początkowego do czasu końca trwania sygnału");
        }
        if (("S11".equals(this.getRodzajSygnalu()))
                &&
                (this.getP().compareTo(0.0) < 0.0 ||
                        this.getTs().compareTo(1.0 + this.getD()) > 0.0)) {
            throw new ParametryAsercjaException("Prawdopodobieństwo musi być liczbą rzeczywistą z przedziału [0 ; 1]");
        }
        if (!"+".equals(this.getRodzajOperacji())
                && !"-".equals(this.getRodzajOperacji())
                && !"*".equals(this.getRodzajOperacji())
                && !"/".equals(this.getRodzajOperacji())
                && !"0".equals(this.getRodzajOperacji())
        ) {
            throw new ParametryAsercjaException("należy określić operację na sygnałach za pomocą jednego ze znaków: \"+\", \"-\", \"*\" albo \"/\".");
        }
    }

    private static class ParametryAsercjaException extends Exception {

        public ParametryAsercjaException(String message) {
            super(message);
        }
    }
}
