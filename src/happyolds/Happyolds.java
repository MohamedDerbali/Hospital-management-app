/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyolds;

import Config.ConnexionDB;
import Entities.Action;
import Entities.Fiche_medicale;
import Entities.Maison;
import Entities.Prestation_sante;
import Entities.Resident;
import Entities.User;
import Services.ServiceAction;
import Services.ServiceMaison;
import Services.ServicePrestationSante;
import Services.ServiceResident;
import Services.ServiceUser;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static jdk.nashorn.internal.objects.NativeArray.map;

 
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author YAFET
 */
public class Happyolds {
    
     public static   Map<Integer,List<Fiche_medicale>> prestationMap = new HashMap<>();


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for (double d = 0.0; d<10; d +=0.1) {
     System.out.println((float)d);
}
     /* for(int i=0; i<10 ;i++)
      {
          for(float j=1;j<=10;j++)
          {
            System.out.println(i +j/10);
          }
      }*/
        /*
        java.util.Date uDate = new java.util.Date();
        System.out.println("Time in java.util.Date is : " + uDate);
        java.sql.Date sDate = convertUtilToSql(uDate);
        System.out.println("Time in java.sql.Date is : " + sDate);
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY - hh:mm:ss");
        System.out.println("Using a dateFormat date is : " + df.format(uDate));
   
     //   System.out.println( LocalDate.now());
            

    }
    private static java.sql.Date convertUtilToSql() {
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }*/


}
}
