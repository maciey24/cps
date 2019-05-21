/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps.dzial;

/**
 * @author maciek
 */
public class Dodawanie extends Dzialanie {

    public Dodawanie() {
        super("+");
    }

    @Override
    public Double dz(Double d1, Double d2) {
        return d1 + d2;
    }

}
