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
public class Odejmowanie extends Dzialanie {

    public Odejmowanie() {
        super("-");
    }

    @Override
    public BigDecimal dz(BigDecimal d1, BigDecimal d2) {
        return d1.subtract(d2);
    }

}
