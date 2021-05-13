/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Material;
import Services.ServiceMaterial;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import static java.util.Collections.list;




/**
 *
 * @author chaima
 */

public class ShowForm extends Form {
    
         Form current;
    SpanLabel lb;

        
        
 
     public void showForm() {
    setTitle("List of materials");

//    SpanLabel hi = new SpanLabel("List");
//   Container list = new Container(BoxLayout.y());
//   list.setScrollable(true);
//   for (int iter = 0 ; iter < 1000 ; iter++){
//       MultiButton mb = new MultiButton("list entry" + iter);
//       mb.setTextLine2("Further details");
//       list.add(mb);
//   }
//     hi.setText(ServiceMaterial.getInstance().getAllMaterials().toString());
//
//hi.add(CENTER, list);

Image dd = null;
try {
    dd = Image.createImage("/dd.png");
} catch(IOException err) {
    Log.e(err);
}
int fiveMM = Display.getInstance().convertToPixels(5);
final Image finalDuke = dd.scaledWidth(fiveMM);
Toolbar.setGlobalToolbar(true);
Form hi = new Form("Search", BoxLayout.y());
hi.add(new InfiniteProgress());
Display.getInstance().scheduleBackgroundTask(()-> {
    // this will take a while...
        String mat = ServiceMaterial.getInstance().getAllMaterials().toString();
});
     }}
 
//        for(Material m : mat) {
//            MultiButton mb = new MultiButton();
//            m.setTextLine1(m.getDisplayName());
//            m.setTextLine2(m.getDescription());
//            Image pic = c.getPhoto();
//            if(pic != null) {
//                m.setIcon(fill(pic, finalDuke.getWidth(), finalDuke.getHeight()));
//            } else {
//                m.setIcon(finalDuke);
//            }
//            hi.add(m);
//        }
//        hi.revalidate();
//    });
//});
//
//hi.getToolbar().addSearchCommand(e -> {
//    String text = (String)e.getSource();
//    if(text == null || text.length() == 0) {
//        // clear search
//        for(Component cmp : hi.getContentPane()) {
//            cmp.setHidden(false);
//            cmp.setVisible(true);
//        }
//        hi.getContentPane().animateLayout(150);
//    } else {
//        text = text.toLowerCase();
//        for(Component cmp : hi.getContentPane()) {
//            MultiButton mb = (MultiButton)cmp;
//            String line1 = mb.getTextLine1();
//            String line2 = mb.getTextLine2();
//            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
//                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
//            mb.setHidden(!show);
//            mb.setVisible(show);
//        }
//        hi.getContentPane().animateLayout(150);
//    }
//}, 4);
//
//hi.show();
//  
//    }
// 
//   
//
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//
////    Form SF = new Form ("liste", new BorderLayout());
////    Container list = new Container(BoxLayout.y());
////    list.setScrollabley(true);
////    for(int iter =0 ; iter < 1000 ; iter++){
////    MultiButton mb= new MultiButton("list enty" + iter);
////    mb.setTextLine2("Further details");
////    list.add(mb);
////}
////    SF.add(CENTER, list);
////    SF.show();
//
//
//}
//    

