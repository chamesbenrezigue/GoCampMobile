/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import Entite.Event;
import services.ServiceEvent;
import static Utils.Statics.BASE_URL;
import com.codename1.components.ImageViewer;

import com.codename1.components.SpanLabel;
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
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author islem
 */
public class ShowEvent extends BaseForm{
    Component m;
    Form form;
      
    Form f;
    SpanLabel lb;
    Form f2;

       public ShowEvent(Resources res,Form current) {
                                         super.addSideMenu(res);

                setTitle("List of Events");
        System.out.println(ServiceEvent.getInstance().getAllEvents());
        ArrayList<Event> Events = new ArrayList();
        Events =ServiceEvent.getInstance().getAllEvents();
                for (Event e :Events ) {
                    
   Container cnt1 = new Container(BoxLayout.y());
   Container cnt2 = new Container(BoxLayout.x());
   //Button rent = new Button ("Rent");
        SpanLabel SLnom = new SpanLabel("Titre :"+e.getTitre());
        //SpanLabel SLnom = new SpanLabel("Prix :"+e.getPrix());
        SpanLabel SLdesc = new SpanLabel("Description :"+e.getDescription());
                SpanLabel SLqut = new SpanLabel("Type :"+e.getType());
                    EncodedImage enc = EncodedImage.createFromImage(Image.createImage(200,200), true);
		String url = BASE_URL+"/uploads/"+e.getImage();
                        ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url));
                        img.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
                        Button rent = new Button ("Rent",String.valueOf(e.getId()));
                        cnt1.add(img);
                        cnt1.add(SLnom);
                        cnt1.add(SLdesc);
                        cnt1.add(SLqut);
                        cnt1.add(rent);
        cnt2.add(cnt1);
         rent.addActionListener(new ActionListener(){
    @Override
       public void actionPerformed(ActionEvent evt){

               AddReservation_1 Reserver=new AddReservation_1( m ,form,res,rent.getUIID());
               Reserver.getF().show();
            }
    });
    
    add(cnt2);
}
 }
        //add();
       
       
}