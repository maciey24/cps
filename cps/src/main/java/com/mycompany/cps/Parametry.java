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
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author maciek
 */
public class Parametry implements Serializable {

    private BigDecimal A;  //Amplituda
    private BigDecimal T;  //okres podstawowy,
    private BigDecimal t1; //czas poczatkowy,
    private BigDecimal d;  //czas trwania sygnalu,
    private BigDecimal kw; //wspolczynnik wypelnienia,
    private BigDecimal t2; //czas koncowy,
    private BigDecimal n2; //probka koncowa,
    private BigDecimal ts; //czas skoku,
    private BigDecimal f;  //czestotliwosc (probkowania),
    private BigDecimal p;  //prawdopodobienstwo
    private boolean czyWczytacZPliku, czyZapisacDoPliku, czyWynikDoPliku;
    private String rodzajSygnalu, rodzajOperacji, rodzajKwantyzacji, rodzajRekonstrukcji;

    public Parametry(BigDecimal czestProbkCiaglego,
                     BigDecimal krokProbkowaniaCiaglego,
                     BigDecimal krokProbkowaniaDyskr, BigDecimal A, BigDecimal T, BigDecimal t1,
                     BigDecimal d, BigDecimal t2) {
        this.czestProbkCiaglego = czestProbkCiaglego;
        this.krokProbkowaniaCiaglego = krokProbkowaniaCiaglego;
        this.krokProbkowaniaDyskretnego = krokProbkowaniaDyskr;
        this.A = A;
        this.T = T;
        this.t1 = t1;
        this.d = d;
        this.t2 = t2;
    }

    private BigDecimal czestProbkCiaglego, krokProbkowaniaCiaglego, krokProbkowaniaDyskretnego;

    private Parametry() {

    }

    public BigDecimal getKrokProbkowaniaDyskretnego() {
        return krokProbkowaniaDyskretnego;
    }

    public void setKrokProbkowaniaDyskretnego(BigDecimal krokProbkowaniaDyskretnego) {
        this.krokProbkowaniaDyskretnego = krokProbkowaniaDyskretnego;
    }

    private Integer liczbaPrzedzialowHistogramu, liczbaPoziomowKwantyzacji;

    public Integer getLiczbaPoziomowKwantyzacji() {
        return liczbaPoziomowKwantyzacji;
    }

    public void setLiczbaPoziomowKwantyzacji(Integer liczbaPoziomowKwantyzacji) {
        this.liczbaPoziomowKwantyzacji = liczbaPoziomowKwantyzacji;
    }

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

    public BigDecimal getP() {
        return p;
    }

    public void setP(BigDecimal p) {
        this.p = p;
    }

    public BigDecimal getF() {
        return f;
    }

    public void setF(BigDecimal f) {
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
        czestProbkCiaglego = BigDecimal.valueOf(10000.0);
        liczbaPrzedzialowHistogramu = 5;
        this.A = BigDecimal.valueOf(1.0);
        this.T = BigDecimal.valueOf(2 * Math.PI);
        t1 = BigDecimal.valueOf(0.5);
        d = BigDecimal.valueOf(7.0);
        kw = BigDecimal.valueOf(0.5);
        this.setTs(BigDecimal.valueOf(3.5));
        this.setF(BigDecimal.valueOf(1.0));
        this.setRodzajSygnalu("S3");
        this.setP(BigDecimal.valueOf(0.05));
        this.setCzyWczytacZPliku(false);
        this.setCzyZapisacDoPliku(true);
        this.setRodzajOperacji("+");
        this.setSciezkaWczytywania("s0.bin");
        this.setSciezkaZapisywania("s0.bin");
        this.setSciezkaWyniku("wynik.bin");
    }

    public BigDecimal getCzestProbkCiaglego() {
        return czestProbkCiaglego;
    }

    public void setCzestProbkCiaglego(BigDecimal czestProbkCiaglego) {
        this.czestProbkCiaglego = czestProbkCiaglego;
    }

    public BigDecimal getKrokProbkowaniaCiaglego() {
        return krokProbkowaniaCiaglego;
    }

    public void setKrokProbkowaniaCiaglego(BigDecimal krokProbkowaniaCiaglego) {
        this.krokProbkowaniaCiaglego = krokProbkowaniaCiaglego;
    }

    public Integer getLiczbaPrzedzialowHistogramu() {
        return liczbaPrzedzialowHistogramu;
    }

    public void setLiczbaPrzedzialowHistogramu(Integer liczbaPrzedzialowHistogramu) {
        this.liczbaPrzedzialowHistogramu = liczbaPrzedzialowHistogramu;
    }

    public String getRodzajSygnalu() {
        return rodzajSygnalu;
    }

    public void setRodzajSygnalu(String rodzajSygnalu) {
        this.rodzajSygnalu = rodzajSygnalu;
    }

    public BigDecimal getA() {
        return A;
    }

    public void setA(BigDecimal A) {
        this.A = A;
    }

    public BigDecimal getT() {
        return T;
    }

    public void setT(BigDecimal T) {
        this.T = T;
    }

    public BigDecimal getT1() {
        return t1;
    }

    public void setT1(BigDecimal t1) {
        this.t1 = t1;
    }

    public BigDecimal getD() {
        return d;
    }

    public void setD(BigDecimal d) {
        this.d = d;
    }

    public BigDecimal getKw() {
        return kw;
    }

    public void setKw(BigDecimal kw) {
        this.kw = kw;
    }

    public BigDecimal getT2() {
        return t2;
    }

    public void setT2(BigDecimal t2) {
        this.t2 = t2;
    }

    public BigDecimal getTs() {
        return ts;
    }

    public void setTs(BigDecimal ts) {
        this.ts = ts;
    }

    public BigDecimal getN2() {
        return n2;
    }

    public void setN2(BigDecimal n2) {
        this.n2 = n2;
    }

    private void ustawKrokProbkowania() {
        this.krokProbkowaniaCiaglego = BigDecimal.valueOf(1.0).divide(czestProbkCiaglego);
        this.krokProbkowaniaDyskretnego = BigDecimal.valueOf(1.0).divide(this.getF());
    }

    private void asercjaPoczatkowa() throws ParametryAsercjaException {
        if ("0".equals(this.getRodzajSygnalu())) return;

        if (this.getD().compareTo(BigDecimal.valueOf(0.0)) < 0) {
            throw new ParametryAsercjaException("czas trwania sygnału powinien być liczbą rzeczywistą większą od 0!");
        }
        if (this.getKw().compareTo(BigDecimal.valueOf(0.0)) < 0 || this.getKw().compareTo(BigDecimal.valueOf(1.0)) > 0) {
            throw new ParametryAsercjaException("współczynnik wypełnienia sygnału powinien być liczbą z przedziału [0.0 ; 1.0]");
        }
        if (this.getRodzajSygnalu().charAt(0) != 'S' || !(Integer.parseInt(this.getRodzajSygnalu().substring(1)) > 0 && Integer.parseInt(this.getRodzajSygnalu().substring(1)) < 12)) {
            throw new ParametryAsercjaException("rodzaj sygnału powinien być określony przez ciąg znaków: [S][1-11]");
        }
        if (("S9".equals(this.getRodzajSygnalu())
                || "S10".equals(this.getRodzajSygnalu())
        ) &&
                (this.getTs().compareTo(this.getT1()) < 0.0 ||
                        this.getTs().compareTo(this.getT1().add(this.getD())) > 0.0)) {
            throw new ParametryAsercjaException("czas skoku musi zawierać się w przedziale od czasu początkowego do czasu końca trwania sygnału");
        }
        if (("S11".equals(this.getRodzajSygnalu()))
                &&
                (this.getP().compareTo(BigDecimal.valueOf(0.0)) < 0.0 ||
                        this.getTs().compareTo(BigDecimal.valueOf(1.0).add(this.getD())) > 0.0)) {
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
