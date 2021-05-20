/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import Entite.Materiel;
import static Utils.Statics.BASE_URL;
import com.codename1.ui.Command;
import com.codename1.ui.util.Resources;
import static com.mycompany.gui.SignInForm.Userconnected;
import services.ServiceProduit;

import java.io.IOException;
import java.util.ArrayList;

public class ListeProduits extends BaseForm {

    Form current;

    public ListeProduits(Form previous,Resources res) {

                              super.addSideMenu(res);

        
        setTitle("Liste des Produits");

        System.out.println(ServiceProduit.getInstance().getAllProduits());
        ArrayList<Materiel> Produits = new ArrayList();
        Produits = ServiceProduit.getInstance().getAllProduits();
        for (Materiel e : Produits) {

            Container cnt1 = new Container(BoxLayout.y());
            Container cnt2 = new Container(BoxLayout.x());
            //Button rent = new Button ("Rent");

            SpanLabel SLnom = new SpanLabel("Nom produit :" + e.getNom());

            EncodedImage enc = EncodedImage.createFromImage(Image.createImage(300, 300), true);
            String url = BASE_URL + "/picture/" + e.getPhoto();
            ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/") + 1, url.length()), url));
            img.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);

            SpanLabel SLprix = new SpanLabel("Prix :" + e.getPrix());
            Button rent = new Button("Achat");
            
            rent.addPointerPressedListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println(ServiceProduit.getInstance().ConfirmerAchat(String.valueOf(Userconnected.getId())));
                    if(ServiceProduit.getInstance().ConfirmerAchat(String.valueOf(Userconnected.getId()))==false){
                                      Dialog.show("","Erreur ",new Command("OK"));

                    }
                    else {
                     Dialog.show("","check your mail ",new Command("OK"));

                    }
                }
            });
            Button description = new Button("voir Description");
            cnt1.add(img);
            cnt1.add(SLnom);
            cnt1.add(SLprix);
            cnt1.add(rent);
            cnt1.add(description);

            cnt2.add(cnt1);


            description.addPointerPressedListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form f2 = new Form(BoxLayout.y());

                    Toolbar tb = f2.getToolbar();

                           tb.setBackCommand("", e -> new ListeProduits(previous,res).showBack());


                    SpanLabel sp1 = new SpanLabel("Nom :" + e.getNom());
                    SpanLabel sp2 = new SpanLabel("Prix :" + e.getPrix());
                    SpanLabel sp3 = new SpanLabel("Description :" + e.getDescription());
                    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
                    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                    EncodedImage placeholder;
                    placeholder = EncodedImage.createFromImage(Image.createImage(p.getWidth() * 3, p.getWidth() * 3), false);
                    System.out.println("ééééééééééééééééééééééééééééééééééééé");
                    System.out.println("photo :" + e.getPhoto());
                    ImageViewer sp4 = new ImageViewer(URLImage.createToStorage(placeholder, e.getPhoto(), e.getPhoto()));
                    System.out.println(sp3.toString());
                    f2.add(sp1);
                    f2.add(sp2);
                    f2.add(sp3);
                    f2.add(sp4);
                    f2.show();

                }
            });

            add(cnt2);

        }

        
    }
    //add();

//        
//      
//        serviceProduit es = new serviceProduit();
//        ArrayList<Produit> list = es.getAllProduit();
//
//        {
//           
//            for (Produit r : list) {
//
//          
// 
//                Container c3 = new Container(BoxLayout.y());
//                SpanLabel cat= new SpanLabel("nom_produit :" + r.getNom_produit());
//                  SpanLabel cat3= new SpanLabel("prix :" + r.getPrix());
//                  SpanLabel cat2= new SpanLabel("Image :" + r.getImage());
//                  SpanLabel cat4= new SpanLabel("description:" + r.getDescription());
//                 
//               
//               
//                     
//                       c3.add(cat);
//                       c3.add(cat3);
//                       c3.add(cat2);
//                       c3.add(cat4);
//       
//                         Button Delete =new Button("Delete","LoginButton");
//         c3.add(Delete);
//            Delete.getAllStyles().setBgColor(0xF36B08);
//            Delete.addActionListener(e -> {
//               Dialog alert = new Dialog("Warning");
//                SpanLabel message = new SpanLabel("Are you sure you want to delete your produit?\nThis action once done cannot be reverted!");
//                alert.add(message);
//                Button ok = new Button("Proceed");
//                Button cancel = new Button(new Command("Cancel"));
//                //User clicks on ok to delete account
//                ok.addActionListener(new ActionListener() {
//                  
//                    public void actionPerformed(ActionEvent evt) {
//                       es.Delete(r.getId());
//                     
//                        alert.dispose();
//                        refreshTheme();
//                    }
//                    
//                }
//                
//                
//                );
//
//                alert.add(cancel);
//                alert.add(ok);
//                alert.showDialog();
//                
//                new ListProduit(previous).show();
//              
//                
//             
//            });
//                       
//                        
//           System.out.println("");
//              
//  add(c3);
//              
//            
//          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
//                , e-> previous.showBack()); // Revenir vers l'interface précédente
//                
//            }
//          
//        }
//     
//    }
}
