/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps;

import com.mycompany.cps.syg.SygnalGenerator;
import com.thoughtworks.xstream.core.util.CustomObjectInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maciek
 */
public class Sygnal implements Serializable{
    private List<Punkt> list;
    private String nazwa;

    Sygnal(SygnalGenerator generatorSygnalu, Parametry p) {
        this.setList(generatorSygnalu.sygnal(p));
        this.setNazwa(generatorSygnalu.getNazwa());
    }
    
    public List<Punkt> getList() {
        return list;
    }

    public void setList(List<Punkt> list) {
        this.list = list;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    void zapiszDoPliku(String sciezka) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(sciezka);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    static Sygnal wczytajZPliku(String sciezka)
    {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(sciezka);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (Sygnal) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Sygnal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
