/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps.syg;

import com.mycompany.cps.Parametry;
import com.mycompany.cps.Punkt;
import java.util.ArrayList;

/**
 *
 * @author maciek
 */
public interface Sygnal {
    ArrayList<Punkt> sygnal(Parametry p);
    String getNazwa();
}
