/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import Entite.Material;
import services.ServiceMaterial;
import static Utils.Statics.BASE_URL;
import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author chaima
 */
public class ShowMaterial extends BaseForm{
      Form current;
    SpanLabel lb;

    
        
    public ShowMaterial(Resources res,Form current) {

        
                setTitle("List of materials");
                              super.addSideMenu(res);


        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceMaterial.getInstance().getAllMaterials().toString());
        System.out.println(ServiceMaterial.getInstance().getAllMaterials());
        ArrayList<Material> materials = new ArrayList();
        materials =ServiceMaterial.getInstance().getAllMaterials();
                for (Material m :materials ) {
                    
   Container cnt1 = new Container(BoxLayout.y());
   Container cnt2 = new Container(BoxLayout.x());
   String idM = String.valueOf(m.getId());
   Button rent = new Button ("Rent",idM);
   Button show = new Button ("Show",idM);


        SpanLabel SLnom = new SpanLabel("Name :"+m.getName());
        SpanLabel SLdesc = new SpanLabel("Description :"+m.getDescription());
        SpanLabel SLpr = new SpanLabel("Prix :"+m.getPrix());

                    EncodedImage enc = EncodedImage.createFromImage(Image.createImage(500,500), true);
		String url = BASE_URL+"/uploads/images/products/"+m.getImage();
                        ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url));
                        img.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
                        cnt1.add(img);
                        cnt1.add(SLnom);
                        cnt1.add(SLdesc);
                        cnt1.add(SLpr);
                        cnt1.add(rent);
                        cnt1.add(show);
                        cnt2.add(cnt1);

    

    rent.addActionListener(new ActionListener(){
    @Override
       public void actionPerformed(ActionEvent evt){
               new AddReservation(res, current,rent.getUIID()).show();
            }
    });
        show.addActionListener(new ActionListener(){
    @Override
       public void actionPerformed(ActionEvent evt){
               new ShowOneMaterial(res,current,show.getUIID()).show();
            }
    });
    
    add(cnt2);
}

 }
}
         
   
