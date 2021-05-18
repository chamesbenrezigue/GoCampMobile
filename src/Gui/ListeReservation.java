/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Material;
import Entities.ReservationMaterial;
import Services.ServiceMaterial;
import Services.ServiceReservationMaterial;
import static Utils.Statics.BASE_URL;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import java.util.ArrayList;

/**
 *
 * @author Rezigue
 */
public class ListeReservation extends Form{
             Form current;
             
 public ListeReservation(Form previous) {
                               setTitle("List of Reservation ");
        
Toolbar tb = new Toolbar();
        setToolbar(tb);
                tb.setUIID("Container");

        tb.setBackCommand("", e -> new ShowMaterial(previous).showBack());
        ArrayList<ReservationMaterial> Reservations = new ArrayList();
        Reservations =ServiceReservationMaterial.getInstance().getAllReservationsMaterials();
        //Search
         Picker dateStart = new Picker();
         dateStart.setType(Display.PICKER_TYPE_DATE_AND_TIME);
         Picker dateEnd = new Picker();
         dateEnd.setType(Display.PICKER_TYPE_DATE_AND_TIME);
     
    Button Valid = new Button ("Valider");
    addAll(dateStart,dateEnd,Valid);
    Valid.addActionListener(new ActionListener(){
    @Override
       public void actionPerformed(ActionEvent evt){
           String pattern = "yyyy/MM/dd hh:mm:ss";
SimpleDateFormat formatter = new SimpleDateFormat(pattern);
String dateS = formatter.format(dateStart.getDate());
String dateE = formatter.format(dateEnd.getDate());
new  ListReservationFiltre(current,dateS,dateE).show();
       }
    });
                for (ReservationMaterial r :Reservations ) {
                    
    Material m = new Material();

   m =ServiceMaterial.getInstance().getOneMaterialById(String.valueOf(r.getMaterial_id()));

   Container cnt1 = new Container(BoxLayout.y());
   Container cnt2 = new Container(BoxLayout.x());
   
   Button delete = new Button ("Delete",String.valueOf(r.getId()));
 Button update = new Button ("Update",String.valueOf(r.getId()));
        SpanLabel SLnom = new SpanLabel("Name :"+m.getName());
        SpanLabel SLdesc = new SpanLabel("Description :"+m.getDescription());
        SpanLabel SLpr = new SpanLabel("Prix :"+m.getPrix());
        SpanLabel SLds = new SpanLabel("Date Start  :"+r.getDateStart());
        SpanLabel SLde = new SpanLabel("Date End  :"+r.getDateEnd());

                    EncodedImage enc = EncodedImage.createFromImage(Image.createImage(500,500), true);
		String url = BASE_URL+"/uploads/images/products/"+m.getImage();
                        ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url));
                        img.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
                        cnt1.add(img);
                        cnt1.add(SLnom);
                        cnt1.add(SLdesc);
                        cnt1.add(SLpr);
                        cnt1.add(SLds);
                        cnt1.add(SLde);
                        cnt1.add(delete);
                       cnt1.add(update);

                        cnt2.add(cnt1);         
                         
                                                  add(cnt2);

                    
                    delete.addActionListener(new ActionListener(){
    @Override
       public void actionPerformed(ActionEvent evt){
           if (ServiceReservationMaterial.getInstance().DeleteReservationsMaterial(delete.getUIID())==true){
               Dialog.show("","the reservation is cancelled",new Command("OK"));
               new ListeReservation(current).show(); 
            }
           else{
                              Dialog.show("","Erreur",new Command("OK"));

               
           }
       }
    });
         update.addActionListener(new ActionListener(){
    @Override
       public void actionPerformed(ActionEvent evt){
               new UpReservation(current,update.getUIID()).show(); 
            }
   });

     }
}
}
