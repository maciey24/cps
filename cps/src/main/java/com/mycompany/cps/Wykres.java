/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cps;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

@SuppressWarnings("serial")
public class Wykres extends ApplicationFrame {

    public Wykres(String tytulOkna, String tytul, List<List<Punkt>> listaSerii, List<String> etykiety) {
        super(tytulOkna);
        JFreeChart xylineChart = ChartFactory.createScatterPlot(
                tytul,
                "x",
                "y",
                createDataset(listaSerii, etykiety),
                PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 600));
        setContentPane(chartPanel);
    }

    public Wykres(String tytulOkna, String tytul, List<Sygnal> listaSygnalow) {
        super(tytulOkna);
        JFreeChart xylineChart = ChartFactory.createScatterPlot(
                tytul,
                "x",
                "y",
                createDataset(listaSygnalow),
                PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 600));
        setContentPane(chartPanel);
    }
//
//    public Wykres(String applicationTitle, String chartTitle, String osx, String osy, String ser1, String ser2, List<Punkt> pnts) {
//        super(applicationTitle);
//        JFreeChart xylineChart = ChartFactory.createScatterPlot(
//                chartTitle,
//                osx,
//                osy,
//                createDataset(pnts),
//                PlotOrientation.VERTICAL,
//                true, true, false);
//        ChartPanel chartPanel = new ChartPanel(xylineChart);
//        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
//        setContentPane(chartPanel);
//    }

    public Wykres(String applicationTitle, String chartTitle, List<Double> lst1, List<Double> lst2, String s1, String s2, String osy, String osx) {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createScatterPlot(
                chartTitle,
                osy,
                osx,
                createDataset(lst1, lst2, s1, s2),
                PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

//   private XYDataset createDataset(List<Point> points)
//   {
//	  final XYSeries g1 = new XYSeries( "G1" );
//	  final XYSeries g2 = new XYSeries( "G2" );
//	  for (Point p : points) {
//		  if(p.tag <= 0.5)
//			  g1.add(p.val.get(0), p.val.get(1));
//		  else if (p.tag > 0.5)
//			  g2.add(p.val.get(0), p.val.get(1));
//	  }
//	  final XYSeriesCollection dataset = new XYSeriesCollection( );          
//      dataset.addSeries( g1 );
//      dataset.addSeries( g2 );
//      return dataset;
//   }   
//    private XYDataset createDataset(List<Punkt> points) {
//        ArrayList<Double> seriesTags = new ArrayList<>();
//        for (Point p : points) {
//            if (!seriesTags.contains(p.tag)) {
//                seriesTags.add(p.tag);
//            }
//        }
//        ArrayList<XYSeries> seriesList = new ArrayList<>();
//        for (int i = 0; i < seriesTags.size(); i++) {
//            String s = "seria " + i;
//            seriesList.add(new XYSeries(s));
//        }
//        for (Punkt p : points) {
//            seriesList.get(seriesTags.indexOf(p.tag)).add(p.val.get(0), p.val.get(1));
////              
////		  if(p.tag <= 0.5)
////			  g1.add(p.val.get(0), p.val.get(1));
////		  else if (p.tag > 0.5)
////			  g2.add(p.val.get(0), p.val.get(1));
//        }
//        final XYSeriesCollection dataset = new XYSeriesCollection();
//        for (XYSeries x : seriesList) {
//            dataset.addSeries(x);
//        }
//        return dataset;
//    }

    static private XYDataset createDataset(List<Double> lst1, List<Double> lst2, String s1, String s2) {
        final XYSeries g1 = new XYSeries(s1);
        if (s2 == null) {
            s2 = " ";
        }
        final XYSeries g2 = new XYSeries(s2);
        for (int i = 0; i < lst1.size(); i++) {
            g1.add(i, lst1.get(i));
        }
        if (lst2 != null) {
            for (int i = 0; i < lst2.size(); i++) {
                g2.add(i, lst2.get(i));
            }
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(g1);
        dataset.addSeries(g2);
        return dataset;
    }

    static private XYDataset createDataset(List<Sygnal> listaSygnalow) {

        XYSeriesCollection dataset = new XYSeriesCollection();
        for (Sygnal syg : listaSygnalow) {
            XYSeries s = new XYSeries(syg.getNazwa());
            for (Punkt p : syg.getList()) {
                s.add(p.getWspolrzedne().get(0), p.getWspolrzedne().get(1));
            }
            dataset.addSeries(s);
        }
        return dataset;
    }


    static private XYDataset createDataset(List<List<Punkt>> listaSeriiPunktow, List<String> etykiety) {

        XYSeriesCollection dataset = new XYSeriesCollection();
        for (List<Punkt> seria : listaSeriiPunktow) {
            int i = listaSeriiPunktow.indexOf(seria);
            XYSeries s = new XYSeries(etykiety.get(i));
            for (Punkt p : seria) {
                s.add(p.getWspolrzedne().get(0), p.getWspolrzedne().get(1));
            }
            dataset.addSeries(s);
        }
        return dataset;
    }

    static void rysuj(String tytulOkna, String tytul, List<Sygnal> listaSygnalow) {
        Wykres chart = new Wykres(tytulOkna, tytul, listaSygnalow);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
