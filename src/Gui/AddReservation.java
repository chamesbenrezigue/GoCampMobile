/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.ReservationMaterial;
import Services.ServiceReservationMaterial;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.TimeZone;
import javafx.scene.control.DatePicker;



/**
 *
 * @author chaima
 */
 
       public class AddReservation extends Form{
         Form current;
    
 
        public AddReservation(Form previous,String idM) {

        current=this;
        setTitle("Material Reservation");
        setLayout(BoxLayout.y());
     //Form hi = new Form("Picker", new BoxLayout(BoxLayout.Y_AXIS));
 Picker dateStart = new Picker();
dateStart.setType(Display.PICKER_TYPE_DATE_AND_TIME);
    Picker dateEnd = new Picker();
dateEnd.setType(Display.PICKER_TYPE_DATE_AND_TIME);
     
    Button Valid = new Button ("Valider");
  
    Valid.addActionListener(new ActionListener(){
    @Override
       public void actionPerformed(ActionEvent evt){
ReservationMaterial rm= new ReservationMaterial();
//System.out.println(dateStart.getDate());

String pattern = "yyyy/MM/dd hh:mm:ss";
SimpleDateFormat formatter = new SimpleDateFormat(pattern);
String dateS = formatter.format(dateStart.getDate());
rm.setDateStart(dateS);

String dateE = formatter.format(dateEnd.getDate());
rm.setDateEnd(dateE);

rm.setMaterial_id(Integer.valueOf(idM));
rm.setUser_id(564);
 


    if (ServiceReservationMaterial.getInstance().addReservation(rm)==false){
               Dialog.show("","Check Date Start and Date end ",new Command("OK"));
               new AddReservation(current,idM).show();
           }else{
                //VarGlobales.setusername(username.getText());
               new ListeReservation(current).show();
            }
            }
    });
    addAll(dateStart, dateEnd, Valid);
 
//addAll(dateStart,dateEnd,Valid);

}}
 

 


































//public class AddReservation extends BaseForm {
//    Form current;
//    public AddReservation(Resources res){
//    super("Newsfeed",BoxLayout.y());
//    Toolbar tb = new Toolbar (true);
//    current = this ;
//    setToolbar(tb);
//    getTitleArea().setUIID("Container");
//    setTitle("Ajout Réservation");
//    getContentPane().setScrollVisible(false);
//    
//    TextField dateStart = new TextField("", "enter date start");
//    dateStart.setUIID("TextFieldBlack");
//    addStringValue("date start", dateStart);
//    
//     TextField dateEnd = new TextField("", "date end");
//    dateEnd.setUIID("TextFieldBlack");
//    addStringValue("date end", dateEnd);
//    
//     Button btnRent = new Button("Rent");
//    addStringValue("",btnRent);
//    
//    btnRent.addActionListener((e)-> {
//        
//        if (dateStart.getText() =="" || dateEnd.getText()==""){
//            Dialog.show("Veuiller vérifier les données", "","Annuler", "OK");
//        }
//        else{
//            InfiniteProgress ip = new InfiniteProgress();;
//            final Dialog iDialog = ip.showInfiniteBlocking();
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        }
//        ReservationMaterial rm= new ReservationMaterial(format.format(new dateStart()));
//    System.out.println("data reservation=="+rm);
//        }
//    private void addStringValue(String s, Component v) {
//        add(BorderLayout.west(new Label(s,"PaddedLabel"))
//        .add(BorderLayout.CENTER,v));
//        add(createLineSeparator(0xeeeeee));
//    }
//    
//}
    
    
    
    
    


    

