/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import Entite.Material;
import services.ServiceMaterial;
import static Utils.Statics.BASE_URL;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author Rezigue
 */
public class ShowOneMaterial extends Form {
          Form current;

             
 public ShowOneMaterial(Resources res,Form previous,String idM) {


     
        Material m =new Material();
      m= ServiceMaterial.getInstance().getOneMaterialById(idM);
      
      Toolbar tb = new Toolbar();
        setToolbar(tb);
                tb.setUIID("Container");

        tb.setBackCommand("", e -> new ShowMaterial(res,previous).showBack());

                   setTitle("Materila : " +m.getName());
                   
   Container cnt1 = new Container(BoxLayout.y());
      Container cnt2 = new Container(BoxLayout.xCenter());
      cnt2.getAbsoluteX();


   

        SpanLabel SLnom = new SpanLabel("Name :       "+m.getName());
        SpanLabel SLdesc = new SpanLabel("Description :"+m.getDescription());
        SpanLabel SLpr = new SpanLabel("Prix :       "+m.getPrix());
        SpanLabel SLES = new SpanLabel("                ");

                    EncodedImage enc = EncodedImage.createFromImage(Image.createImage(2000,2000), true);
		String url = BASE_URL+"/uploads/images/products/"+m.getImage();
                        ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url));
                        img.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_ALIGNED_CENTER);
                        cnt1.add(img);

                        cnt1.add(SLnom);
                                                

                        cnt1.add(SLdesc);
                                                

                        cnt1.add(SLpr);
                        
                        cnt2.add(cnt1);
    addAll(cnt2);

}


}
