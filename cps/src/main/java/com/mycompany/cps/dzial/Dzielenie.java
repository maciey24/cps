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
public class Dzielenie extends Dzialanie {

    public Dzielenie() {
        super("/");
    }


    @Override
    public BigDecimal dz(BigDecimal d1, BigDecimal d2) throws DzielenieException {
        if (!d2.equals(0.0)) {
            return d1.divide(d2);
        } else throw new DzielenieException("Dzielenie przez zero");
    }

    private static class DzielenieException extends DzialanieException {

        public DzielenieException() {
        }

        private DzielenieException(String message) {
            super(message);
        }
    }

}
