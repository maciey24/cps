package com.mycompany.cps;

import com.mycompany.cps.Sygnal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class Histogram extends ApplicationFrame {
   
   public Histogram( Sygnal s, String applicationTitle, String chartTitle, int liczbaPrzedzialow) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Przedział",            
         "Liczba próbek",
         createDataset(s.histogram(liczbaPrzedzialow), s.getNazwa()),
         PlotOrientation.VERTICAL,
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset( HashMap<Double, Integer> histogram, String nazwa) {
      final String podpisSerii = "Histogram amplitudy próbek dla sygnału: " + nazwa;   
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  
      
      ArrayList<Double> listaOpisow = new ArrayList<>();
      for(Map.Entry<Double, Integer> e : histogram.entrySet())
      {
          listaOpisow.add(e.getKey());
      }
      Collections.sort(listaOpisow);
      
      for(Double d : listaOpisow)
      {
          dataset.addValue(histogram.get(d), podpisSerii, d);
      }
      
      return dataset; 
   }
   
   public static void rysuj(Sygnal s, String tytulOkna, String tytul, int liczbaPrzedzialow) {
       if(s == null) return;
       if(liczbaPrzedzialow==0) return;
      Histogram chart = new Histogram(s, tytulOkna, tytul + " dla " + liczbaPrzedzialow + " przedziałów. ", liczbaPrzedzialow);
      chart.pack();
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}
