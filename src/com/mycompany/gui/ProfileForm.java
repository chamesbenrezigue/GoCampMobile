/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import Entite.User;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static com.mycompany.gui.SignInForm.Userconnected;
import services.ServiceUser;


/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {

    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);


        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
                            
                    )
                )
        ));
        System.out.println(Userconnected);

        TextField FirstName = new TextField(Userconnected.getFirstName());
        FirstName.setUIID("TextFieldBlack");
        addStringValue("First Name", FirstName);
        
        TextField LastName = new TextField(Userconnected.getLastName());
        LastName.setUIID("TextFieldBlack");
        addStringValue("Last Name", LastName);

        TextField email = new TextField(Userconnected.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);        


        
        TextField Adress = new TextField(Userconnected.getAdress());
        Adress.setUIID("TextFieldBlack");
        addStringValue("Adress", Adress);
        
        TextField Sexe = new TextField(Userconnected.getSexe());
        Sexe.setUIID("TextFieldBlack");
        addStringValue("Sexe", Sexe);
        
         TextField Phone= new TextField(String.valueOf(Userconnected.getPhone()));
        Phone.setUIID("TextFieldBlack");
        addStringValue("Phone", Phone);
        
         Button Valid = new Button ("Valider");
  
     Button btnVal = new Button ("valider");
       btnVal.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent evt) {
                   User u =new User();
       u.setId(Userconnected.getId());
        u.setEmail(email.getText());
        u.setPhone(Integer.valueOf(Phone.getText()));
        u.setFirstName(FirstName.getText());
        u.setLastName(LastName.getText());
        u.setSexe(Sexe.getText());
        u.setAdress(Adress.getText());
        if(ServiceUser.getInstance().EditProfil(u)==false){
             Dialog.show("","Error",new Command("OK"));
        }else{
    Userconnected.setEmail(email.getText());
        Userconnected.setPhone(Integer.valueOf(Phone.getText()));
        Userconnected.setFirstName(FirstName.getText());
        Userconnected.setLastName(LastName.getText());
        Userconnected.setSexe(Sexe.getText());
                           Userconnected.setAdress(Adress.getText());

                 Dialog.show("update valider","Succees",new Command("OK"));
                 new ProfileForm(res).show();
        }
           
             }
       });
     
add(btnVal);
 
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
