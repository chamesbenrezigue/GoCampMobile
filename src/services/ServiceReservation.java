/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entite.Event;
import Entite.Reservation;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author islem
 */
public class ServiceReservation {
    
     public ArrayList<Reservation> reservation;
    public static ServiceReservation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
        public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }
        public ServiceReservation() {
         req = new ConnectionRequest();
    }
    
    
    
   
            public boolean addReservation(Reservation r) {
      String url = Statics.BASE_URL + "/api/reserver/ajoutreservation?nom=" + r.getNom()+ "&prenom=" +r.getPrenom()+ "&id="+ r.getId()+ "&event="+r.getEvent();
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
            public void ajoutReservation(String nom,String prenom,String nbrplace,String id) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String url = "http://127.0.0.1:8000/api/CreaterReservation/"+nom+"/"+prenom+"/"+nbrplace+"/"+id;
        con.setUrl(url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            //System.out.println(str);//Affichage de la réponse serveur sur la console
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
            public ArrayList<Reservation> parseReservation(String jsonText) {
        try {
            reservation =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> associationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)associationsListJson.get("root");
            for(Map<String,Object> obj : list){
                Reservation e = new Reservation();              
                float id = Float.parseFloat(obj.get("id").toString());
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                e.setId((int)id);               
                e.setNom(obj.get("nom").toString());
                e.setPrenom(obj.get("prenom").toString());
                e.setNbrplace(obj.get("nbrplace").toString());
//                 e.setEvent(obj.get("event").toString());
            
                 
                reservation.add(e);
                
                
            }
            
            
        } catch (IOException ex) {

        }
        return reservation;
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
            
            public ArrayList<Reservation> getAll(){
        String url = Statics.BASE_URL+"/api/res/front";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               reservation = parseReservation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservation;
    }
            public void AnnulerSer(Reservation r) {
        ConnectionRequest con = new ConnectionRequest();
        
        String Url = "http://127.0.0.1:8000/api/DeleteReservation/"+r.getId();
        System.out.println(Url);
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
            
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
