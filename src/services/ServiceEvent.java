/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entite.Event;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;



/**
 *
 * @author islem
 */
public class ServiceEvent {
    public ArrayList<Event> Events;
    public static ServiceEvent instance=null;
    public boolean resultOK;
    private final ConnectionRequest req;
    
        public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }
    
    
    public ServiceEvent() {
         req = new ConnectionRequest();
    }
     public ArrayList<Event> parseEvents(String jsonText) {
        try {
            Events=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> associationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)associationsListJson.get("root");
            for(Map<String,Object> obj : list){
                Event e = new Event();              
                float id = Float.parseFloat(obj.get("id").toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                e.setId((int)id);               
                e.setTitre(obj.get("title").toString());
                e.setDescription(obj.get("description").toString());
                e.setImage(obj.get("image").toString());
                e.setType(obj.get("type").toString());
                try {
                    e.setStart(sdf.parse(obj.get("start").toString()));
                    e.setEnd(sdf.parse(obj.get("end").toString()));
                } catch (ParseException ex) {
                   
                }
                
                e.setPrix(Double.parseDouble(obj.get("prix").toString()));
                 
                Events.add(e);
                
                
            }
            
            
        } catch (IOException ex) {

        }
        return Events;
     }
     
    
    public ArrayList<Event> getAllEvents(){
        String url = Statics.BASE_URL+"/api/listEvent";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Events;
    }

     
}