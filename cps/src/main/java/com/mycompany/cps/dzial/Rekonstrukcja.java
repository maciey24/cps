/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps.dzial;

import com.mycompany.cps.Parametry;
import com.mycompany.cps.Sygnal;

import java.math.BigDecimal;

/**
 * @author maciek
 */
public interface Rekonstrukcja {
    public abstract Sygnal odtworz(Sygnal s, BigDecimal krok);
}
