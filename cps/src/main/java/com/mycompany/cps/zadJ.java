/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maciek
 */
public class zadJ {

    static Double czestProbk = 0.01;
    static Double gornaGranica = Math.PI*2;

    static ArrayList<List<Punkt>> listyPunktow = new ArrayList<>();
    static ArrayList<String> etykiety = new ArrayList<>();

    public static void main(String[] args) {
        etykiety.add("sin");
        etykiety.add("cos");
        
        ArrayList punkty = new ArrayList();
        for (Double i = 0.0; i < gornaGranica; i = i + czestProbk) {
            punkty.add(new Punkt(i, Math.sin(i)));
        }
        listyPunktow.add(punkty);
        
        punkty = new ArrayList();
        for (Double i = 0.0; i < gornaGranica; i = i + czestProbk) {
            punkty.add(new Punkt(i, Math.cos(i)));
        }
        listyPunktow.add(punkty);

        
        Wykres.rysuj("Tytuł okna", "Wykres", listyPunktow, etykiety);
    }

}
