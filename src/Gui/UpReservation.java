/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.ReservationMaterial;
import Services.ServiceReservationMaterial;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author chaima
 */
public class UpReservation extends Form {
     Form current;
             
 public UpReservation(Form previous,String id) {
     
  setTitle("Update Reservation ");
   Picker dateStart = new Picker();
dateStart.setType(Display.PICKER_TYPE_DATE_AND_TIME);
    Picker dateEnd = new Picker();
dateEnd.setType(Display.PICKER_TYPE_DATE_AND_TIME);
     
    Button Valid = new Button ("Valider");
        Valid.addActionListener(new ActionListener(){
    @Override
       public void actionPerformed(ActionEvent evt){
           ReservationMaterial RM = new ReservationMaterial();
           String pattern = "yyyy/MM/dd hh:mm:ss";
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            String dateS = formatter.format(dateStart.getDate());
            RM.setDateStart(dateS);

            String dateE = formatter.format(dateEnd.getDate());
            RM.setDateEnd(dateE);
            RM.setId(Integer.valueOf(id));
            if(ServiceReservationMaterial.getInstance().UpdateReservation(RM)==false){
                               Dialog.show("","Error",new Command("OK"));
                               new UpReservation(current,id).show();   
            }
            else{
                               Dialog.show("","the reservation is updated",new Command("OK"));
                               new ListeReservation(current).show();

            }
           
       }
        });
            addAll(dateStart, dateEnd, Valid);

 }
                               
                               
}
