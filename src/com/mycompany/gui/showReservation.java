/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import Entite.Reservation;
import services.ServiceEvent;
import services.ServiceReservation;
import static Utils.Statics.BASE_URL;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author islem
 */
public class showReservation extends BaseForm {
    Form form;
      
    Form f;
    SpanLabel lb;

       public showReservation(Resources res,Form pervious) {
                                         super.addSideMenu(res);

        
        f = new Form("list of reservation "); 
             //   setTitle("List of reserver");
        ArrayList<Reservation> Reservation = new ArrayList();
        Reservation =ServiceReservation.getInstance().getAll();
         Button ss = new Button("capture d'ecran");
                ss.addActionListener(e1 -> {
                    Image screenshot = Image.createImage(f.getWidth(), f.getHeight());
                    f.revalidate();
                    f.setVisible(true);
                    f.paintComponent(screenshot.getGraphics(), true);
                    String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "capture.png";
                    try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                        ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                    } catch (IOException err) {
                        Log.e(err);
                    }
                });
                for (Reservation e :Reservation ) {
   Container cnt1 = new Container(BoxLayout.y());
   Container cnt2 = new Container(BoxLayout.x());
  
   //Button rent = new Button ("Rent");
  
   
        SpanLabel SLnom = new SpanLabel("nom :"+e.getNom());
        SpanLabel SLdesc = new SpanLabel("prenom :"+e.getPrenom());
                SpanLabel SLqut = new SpanLabel("nbrplace :"+e.getNbrplace());
                    Button Supprimer = new Button("supprimer");
        Supprimer.addActionListener((t)
        -> {
        ServiceReservation z = new ServiceReservation();
        z.AnnulerSer(e);
        System.out.println("ok");
        this.f.showBack();
        }
        );
                        cnt1.add(SLnom);
                        cnt1.add(SLdesc);
                        cnt1.add(SLqut);
                        cnt1.add(Supprimer);
     cnt2.add(cnt1);  
     f.add(cnt2);
           
    
}
                f.add(ss);
                addAll(f);
//                f.getToolbar().addMaterialCommandToLeftSideMenu("Add Reservation",FontImage.MATERIAL_WEB, new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                  AddReservation_1 a=new AddReservation_1(m,f,res);
//                  a.getF().show();
//                }
//                });
          
            
 }
        //add();

 




}