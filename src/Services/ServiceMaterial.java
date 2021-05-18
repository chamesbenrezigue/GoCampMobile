/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Material;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.json.JSONObject;


/**
 *
 * @author chaima
 */
public class ServiceMaterial {
    public ArrayList<Material> materials;
    public Material M_One ;
    public static ServiceMaterial instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
        public static ServiceMaterial getInstance() {
        if (instance == null) {
            instance = new ServiceMaterial();
        }
        return instance;
    }
    
    
    public ServiceMaterial() {
         req = new ConnectionRequest();
    }
     public ArrayList<Material> parseMaterials(String jsonText){
        try {
            materials=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> materialsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)materialsListJson.get("root");
            for(Map<String,Object> obj : list){
                Material m = new Material();              
                float id = Float.parseFloat(obj.get("id").toString());
                m.setId((int)id);               
                m.setName(obj.get("name").toString());
                m.setDescription(obj.get("description").toString());
                m.setImage(obj.get("image").toString());
                m.setPrix(Float.parseFloat(obj.get("prix").toString()));
                float Nbrmatrres = Float.parseFloat(obj.get("nbrmatrres").toString());
                m.setNbrmatrres((int)Nbrmatrres);                
                float quantity = Float.parseFloat(obj.get("quantity").toString());
                m.setQuantity((int)quantity);                
                 m.setAvailability(Boolean.parseBoolean(obj.get("availability").toString()));
                 materials.add(m);                  
            }
        } catch (IOException ex) {}
        return materials;
     }
     
    
    public ArrayList<Material> getAllMaterials(){
        String url = Statics.BASE_URL+"/api/ListeMaterial";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                materials = parseMaterials(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return materials;
    }
    
        public Material getOneMaterialById(String id){
        String url = Statics.BASE_URL+"/api/RentingMaterial/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(req.getResponseData()));
                M_One = parseMaterial(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return M_One;
    }
     public Material parseMaterial(String jsonText){
            JSONParser j = new JSONParser();
            Material m = new Material();             
            String str = jsonText;
            JSONObject jsonObject = new JSONObject(str);
            System.out.println(jsonObject);
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            m = gson.fromJson(str,Material.class);
        return m;
     }
}