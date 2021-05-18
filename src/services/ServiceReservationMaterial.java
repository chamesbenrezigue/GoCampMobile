/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entite.Material;
import Entite.ReservationMaterial;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author chaima
 */
public class ServiceReservationMaterial {
     public ArrayList<ReservationMaterial> reservation;
     public ArrayList<ReservationMaterial> reservationFilter;

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
      String url = Statics.BASE_URL + "/api/reservationMaterial/"+r.getMaterial_id()+"?dateStart=" + r.getDateStart()+ "&dateEnd=" +r.getDateEnd()+ "&user_id="+ r.getUser_id()+ "&Material_id="+r.getMaterial_id();
      System.out.println(url);
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
            
    public ArrayList<ReservationMaterial> parseReservationMaterials(String jsonText){
        try {
            reservation=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> reservationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)reservationsListJson.get("root");
            for(Map<String,Object> obj : list){
                ReservationMaterial rm = new ReservationMaterial();              
                float id = Float.parseFloat(obj.get("id").toString());
                rm.setId((int)id);     

                rm.setDateStart(obj.get("dateStart").toString());
                rm.setDateEnd(obj.get("dateEnd").toString()); 
                float idM = Float.parseFloat(obj.get("material_id").toString());
                rm.setMaterial_id((int) idM);
                 float idU = Float.parseFloat(obj.get("user_id").toString());
                rm.setUser_id((int) idU);
                   

                
                

                
                 reservation.add(rm);
           }
     } catch (IOException ex) {
 }
        return reservation;
     }
     
    
    public ArrayList<ReservationMaterial> getAllReservationsMaterials(Integer id){
        String url = Statics.BASE_URL+"/api/ListReservations/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservation = parseReservationMaterials(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservation;
    }
        public Boolean DeleteReservationsMaterial(String id){
        String url = Statics.BASE_URL+"/api/deleteReservationMaterial/"+id;
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
        
   public Boolean UpdateReservation(ReservationMaterial r) {
        String url = Statics.BASE_URL + "/api/reservation_edit?dateStart="+r.getDateStart()+"&dateEnd="+r.getDateEnd()+"&id="+r.getId();
        System.out.println(url);
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
      public ArrayList<ReservationMaterial> getReservationsMaterialsFilter(String DS, String DE,Integer id){
        String url = Statics.BASE_URL+"/api/rechercheReservations?dateStart="+DS+"&dateEnd="+DE+"&id_user="+id;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservationFilter = parseReservationMaterials(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservationFilter;
    }
   
   
        
            
}
     
