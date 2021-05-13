/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.ReservationMaterial;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author chaima
 */
public class ServiceReservationMaterial {
     public ArrayList<ReservationMaterial> reservation;
    public static ServiceReservationMaterial instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
        public static ServiceReservationMaterial getInstance() {
        if (instance == null) {
            instance = new ServiceReservationMaterial();
        }
        return instance;
    }
        private ServiceReservationMaterial() {
         req = new ConnectionRequest();
    }
    
    
    
    //http://127.0.0.1:8000/fronttt/reservation/2?dateStart=2021-05-1918:12:09&dateEnd=2021-05-2518:12:09&user_id=2&Material_id=2
            public boolean addReservation(ReservationMaterial r) {
      String url = Statics.BASE_URL + "/api/reservationMaterial/"+r.getMaterial_id()+"?dateStart=" + r.getDateStart()+ "&DateEnd=" +r.getDateEnd()+ "&user_id="+ r.getUser_id()+ "&Material_id="+r.getMaterial_id();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
}
            
//    public ArrayList<ReservationMaterial> parseReservationMaterials(String jsonText){
//        try {
//            reservation=new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> reservationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));            
//            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)reservationsListJson.get("root");
//            for(Map<String,Object> obj : list){
//                ReservationMaterial rm = new ReservationMaterial();              
//                float id = Float.parseFloat(obj.get("id").toString());
//                rm.setId((int)id);               
//               
//                float user_id = Float.parseFloat(obj.get("user_id").toString());
//                rm.setUser_id((int)user_id);                
//                float material_id = Float.parseFloat(obj.get("material_id").toString());
//                rm.setMaterial_id((int)material_id);                
//         
//String DateConverter = obj.get("dateStart").toString().substring(obj.get("dateStart").toString().indexOf("timestamp")+ 10 , obj.get("obj").toString().lastIndexOf("}"));
//                 Date currentTime = new Date (Double.valueOf(DateConverter).longValue() * 100);
//                 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                 String dateString = formatter.format(currentTime);
//                 rm.setDateStart(dateString);
//                 
//  String DateConverter1 = obj.get("dateEnd").toString().substring(obj.get("dateEnd").toString().indexOf("timestamp")+ 10 , obj.get("obj").toString().lastIndexOf("}"));
//                 Date currentTime1 = new Date (Double.valueOf(DateConverter).longValue() * 100);
//                 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//                 String dateString1 = formatter.format(currentTime);
//                 rm.setDateEnd(dateString1);
////                 
//                 reservation.add(rm);
//           }
//     } catch (IOException ex) {
// }
//        return reservation;
//     }
     
    
//    public ArrayList<ReservationMaterial> getAllReservationsMaterials(){
//        String url = Statics.BASE_URL+"/fronttt/material_reservation";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                reservation = parseReservationMaterials(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return reservation;
//    }
//        
            
}
     
//            Public ArrayList<ReservationMaterial>affichageReservations(){
//             ArrayList<ReservationMaterial> result = new ArrayList<>(); 
//            String url=Statics.BASE_URL+"/fronttt/material_reservation";
//            req.setUrl(url);
//             req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                try {
//                    JSONParser jsonp;
//                    jsonp = new JSONParser();            
//                    Map<String,Object> mapReservations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                List<Map<String,Object>> listofMaps=(List<Map<String,Object>>) mapReservations.get("root");
//                for(Map<String, Object> obj : listofMaps){
//                    ReservationMaterial = new ReservationMaterial();
//                }
//                } catch (IOException ex) {
//                    Logger.getLogger(ServiceReservationMaterial.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            }
//}