
package services;

import Entite.User;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rezigue
 */
public class ServiceUser {
                   public  User userCo = new User();

         public ArrayList<User> users;
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
        public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
        private ServiceUser() {
         req = new ConnectionRequest();
    }
    
    
    
    
            public boolean addUser(User u) {
        String url = Statics.BASE_URL + "/api/register?firstName=" + u.getFirstName()+"&lastName="+u.getLastName()+"&email=" + u.getEmail() + "&password=" + u.getPassword();
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
            
            
            
            
             public Boolean VerifUser(String email, String password) {
        User u = new User();
        String url = Statics.BASE_URL + "/api/login?email=" + email + "&password=" + password;
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
//       NetworkManager.getInstance().addToQueueAndWait(req);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//           @Override
//        public void actionPerformed(NetworkEvent evt) {
//               User users = parseTasks(new String(req.getResponseData()));
//              req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//       //System.out.println(users);
//      // System.out.println(u.getPassword());
//        if (u.getPassword().equals("faux")) {
//           return false;
//       } else {
//           return true;
//       }
    }
             public User UserConnecter(String email, String password) {
        User u = new User();
        String url = Statics.BASE_URL + "/api/login?email=" + email + "&password=" + password;
        System.out.println(url);
        req.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(req);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
        public void actionPerformed(NetworkEvent evt) {
               User users = parseTasks(new String(req.getResponseData()));
              req.removeResponseListener(this);
              u.setId(users.getId());
              u.setEmail(users.getEmail());
              u.setPassword(users.getPassword());
              u.setFirstName(users.getFirstName());
              u.setLastName(users.getLastName());
              u.setPhone(users.getPhone());
              u.setAdress(users.getAdress());
              u.setSexe(users.getSexe());


        }
        });
             
             return u;
             }
             
             
             
             
  public User parseTasks(String jsonText) {
             try {
                 userCo= new User();
                 JSONParser j = new JSONParser();
                 String [ ] monTableau;
                 Map<String,Object> UserJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));                             
               float id = Float.parseFloat(UserJson.get("id").toString());
                userCo.setId((int)id);
                 userCo.setLastName(UserJson.get("lastName").toString());
                userCo.setFirstName(UserJson.get("firstName").toString());
                 userCo.setPassword(UserJson.get("password").toString());
               userCo.setEmail(UserJson.get("email").toString());
               userCo.setAdress(UserJson.get("adress").toString());
               userCo.setSexe(UserJson.get("sexe").toString());
                 float phone = Float.parseFloat(UserJson.get("phone").toString());
               userCo.setPhone((int)phone);


                 return userCo;
             } catch (IOException ex) {
             }
             return userCo;
  }
  public Boolean EditProfil(User userEP) {
        String url = Statics.BASE_URL + "/api/profil_edit?email="+userEP.getEmail()+"&firstName="+userEP.getFirstName()+"&lastName="+userEP.getLastName()+"&sexe="+userEP.getSexe()+"&adress="+userEP.getAdress()+"&phone="+userEP.getPhone()+"&id="+userEP.getId() ;
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
    public Boolean ResetPassword(String password,String NewPassword,String id) {
        String url = Statics.BASE_URL + "/api/Password_Reset?password="+password+"&NewPassword="+NewPassword+"&id="+id ;
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

}

        

