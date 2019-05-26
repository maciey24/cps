/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps.dzial;

import java.math.BigDecimal;

/**
 * @author maciek
 */
public abstract class Dzialanie {
    String nazwa = "";

    public Dzialanie(String nazwa) {
        this.setNazwa(nazwa);
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    //TODO: przerobic na dzialanie na punktach a nie na Double
    public BigDecimal dz(BigDecimal d1, BigDecimal d2) throws DzialanieException {
        return null;
    }

    public static class DzialanieException extends Exception {

        public DzialanieException() {
        }

        public DzialanieException(String message) {
            super(message);
        }
    }

}
