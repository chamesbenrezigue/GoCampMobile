/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import Entite.Reservation;
import services.ServiceReservation;
import com.codename1.capture.Capture;
import static com.codename1.capture.Capture.capturePhoto;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import static com.codename1.io.Log.p;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;

//import com.codename1.capture;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Date;





/**
 *
 * @author Med
 */
public class AddReservation_1 extends BaseForm{
    Form f;
    Form g;
    Form previous;
    ImageViewer img1;
    String img="";
    Button btnajout,btnaff,creer;
Reservation a= new Reservation();
Component e;
Component re;
Form form;
        public AddReservation_1(Component e ,Form form,Resources res,String id){

              f = new Form("Ajout d'une Reservation",new BoxLayout(BoxLayout.Y_AXIS));
             Container c=new Container(new LayeredLayout());
              LayeredLayout li2 = (LayeredLayout) c.getLayout();
              Toolbar.setGlobalToolbar(true);
            //FORMS
            
            Form f1 = new Form("Home", BoxLayout.y());
           // Form f2 = new Form("Ajout d'un Article", BoxLayout.y());
            Form f3 = new Form("Afficher les articles", BoxLayout.y());
               


    //Label ltitre= new Label("Titre ");
    TextField nom= new TextField("", "nom");
     TextField prenom= new TextField("","prenom");
    Label lnbrplace= new Label("Titre ");
    TextField nbrplace= new TextField("", "nbrplace ");
    li2.setInsets(nom, "7mm auto auto auto");
    li2.setInsets(prenom, "30mm auto auto auto");
    li2.setInsets(nbrplace, "14mm auto auto auto");
         c.add(nom).add(prenom).add(nbrplace);
         
    
    Container c2= new Container(new LayeredLayout());
    Button submit = new Button("Continuer");
    Button cancel = new Button("");
    c2.addAll(submit,cancel);
    LayeredLayout li = (LayeredLayout) c2.getLayout();
    li.setInsets(submit, "1mm auto auto auto");
    li.setInsets(cancel, "15mm auto auto auto");
    Validator val = new Validator();
    val.addConstraint(nom, new LengthConstraint(3, "Le champ  doit avoir au moins deux chiffres"));

   f.add(c);
  
    f.add(c2);
    
    
    submit.addActionListener(l->{
        if(val.isValid()){
       Reservation a =new Reservation(0,nom.getText(), prenom.getText(), "",nbrplace.getText(),id);
       ServiceReservation sc= new ServiceReservation();
            sc.ajoutReservation(nom.getText(), prenom.getText(), nbrplace.getText(),id);
}
       LocalNotification ln = new LocalNotification();
        ln.setId("LnMessage");
            ln.setAlertTitle("Welcome");
            ln.setAlertBody("la reservation a ete ajouté");
          Display.getInstance().scheduleLocalNotification( ln,
                System.currentTimeMillis() + 10 * 1, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency);
          );
                Dialog.show("Succès", "Une notification sera envoyée au admin", "Ok", null);
                new ShowEvent(res, current).show();

    });
//       f.getToolbar().addMaterialCommandToLeftSideMenu("list of reservation",FontImage.MATERIAL_WEB, new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                  showReservation a=new showReservation(res,form);
//                  a.getF().show();
//                   
//                }
//            });
            
         

      }

 

    
        
         public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

  
}