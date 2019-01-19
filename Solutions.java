import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.lang.*; 
import java.util.regex.*;

public class Solution {
    static class Attractions{
        int id;
        double longitude, latitude;
        public Attractions(int id,double longitude,double latitude) {
            this.id=id;
            this.longitude=longitude;
            this.latitude=latitude;
        }
        public int getID(){
            return id;
        }
        public double getLongitude() {
            return longitude;
        }
        public double getLatitude() {
            return latitude;
        }
        
    }
    static class Customers {
        double clongitude, clatitude;
        double preferred_time;
        String preferred_transport;
        public Customers(double clongitude,double clatitude,double preferred_time, String preferred_transport) {
            this.clongitude=clongitude;
            this.clatitude=clatitude;
            this.preferred_time = preferred_time;
            this.preferred_transport = preferred_transport;
        }
        public String getpreferredTransport(){
            return preferred_transport;
        }
        
        public double getpreferredTime(){
            return preferred_time;
        }
        public double getLongitude() {
            return clongitude;
        }
        public double getLatitude() {
            return clatitude;
        }
        
    }
    
   
    static String [] atts ;
    static String [] custs ;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
         Scanner in = new Scanner(System.in);
    
        int attractions = in.nextInt();in.nextLine();
        Attractions[] arr_atts=new Attractions[attractions];
        for(int i=0;i<attractions;i++){
            String att=in.nextLine();
            atts= att.split("\\s+");
            int id=Integer.parseInt(atts[0]);
            double latitude =Double.parseDouble(atts[1]);
            double longitude =Double.parseDouble(atts[2]);            
            arr_atts[i]=new Attractions(id,longitude,latitude);
        }
        int customers = in.nextInt();in.nextLine();
        Customers[] arr_custs=new Customers[customers];
        double [] mintime = new double[attractions];
        for(int i=0;i<customers;i++){
            String cust=in.nextLine();

            custs= cust.split("\\s+");

            double clatitude =Double.parseDouble(custs[0]);
            double clongitude =Double.parseDouble(custs[1]);  
            String preferredTrans = custs[2];
            double preferredTime = Double.parseDouble(custs[3]);
            arr_custs[i]=new Customers(clongitude,clatitude,preferredTime,preferredTrans);
        }
        
        for(int i=0;i<customers;i++) {
            HashMap<Integer, Double> map = new HashMap<Integer, Double>();
            for (int j=0;j<attractions; j++) {
                double kms;
                kms = calculatedistance(arr_atts[j],arr_custs[i]);
                kms = Math.round(kms*100)/100.00;
//                System.out.println(roundOff);
                int speed =0;
                switch (arr_custs[i].getpreferredTransport()) {
                    case "metro":
                        speed = 20;
                        break;
                    case "bike":
                        speed = 15;
                        break;
                    case "foot":
                        speed = 5;
                }
                double time = (kms/speed)*60;                
                 if(time<arr_custs[i].getpreferredTime()) {
                     map.put(arr_atts[j].getID(),time);
                 }
            }
        Map<Integer, Double> hm1 = sortByValue(map); 
    
        // print the sorted hashmap 
        for (Map.Entry<Integer, Double> en : hm1.entrySet()) { 
            System.out.print(en.getKey()+" "); 
            }
        System.out.println(""); 
      } 
    }
    
    static double calculatedistance (Attractions att, Customers cus) {
    int EARTH_RADIUS = 6371;//in km
    return Math.acos((Math.sin(Math.toRadians(att.getLatitude())) * Math.sin(Math.toRadians(cus.getLatitude()))) +
                 (Math.cos(Math.toRadians(att.getLatitude())) * Math.cos(Math.toRadians(cus.getLatitude())) *
                 Math.cos(Math.toRadians(cus.getLongitude()) - Math.toRadians(att.getLongitude())))) * 
                 EARTH_RADIUS;
    }
    
        // function to sort hashmap by values 
    public static HashMap<Integer, Double> sortByValue(HashMap<Integer, Double> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<Integer, Double> > list = 
            new LinkedList<Map.Entry<Integer, Double> >(hm.entrySet()); 

        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double> >() { 
            public int compare(Map.Entry<Integer, Double> o1, 
                            Map.Entry<Integer, Double> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
        
        // put data from sorted list to hashmap 
        HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>(); 
        for (Map.Entry<Integer, Double> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 
 
}