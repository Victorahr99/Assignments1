import java.util.*;

public class RW {
    
    public static void main (String args[]) {
        
        Don object1 = new Don();
        Bella object2 = new Bella();
        double total1 = 0;
        double total2 = 0;
        double average1 = 0;
        double average2 = 0;
        int j = 0;
        double[] numbers2 = new double[100];
        
        while(j<2000){
            
            total1 = total1 + object1.location(100);
            total2 = total2 + object2.location(100);
            
            j++;
        }
        
        average1 = total1 / 2000;
        average2 = total2 / 2000;
        
        System.out.println(object1.name() + "\n2000 experiments, walking 100 steps, the maximum height achieved"
        + "\nhas average value: " + average1);
        System.out.println(object2.name() + "\n2000 experiments, walking 100 steps, the maximum height achieved"
        + "\nhas average value: " + average2);
        
        total1 = 0;
        total2 = 0;
        average1 = 0;
        average2 = 0;
        j = 0;
        double test = object2.topfloor();
        
        //System.out.println(test);
        
        while(j<2000){
            
            total1 = total1 + object1.topfloor();
            total2 = total2 + object2.topfloor();
            j++;
        }
        
        average1 = total1 / 2000;
        average2 = total2 / 2000;
        
        System.out.println(object1.name() + "\n2000 experiments, the time needed to reach the top floor"
        + "\nhas average value: " + average1);
        System.out.println(object2.name() + "\n2000 experiments, the time needed to reach the top floor"
        + "\nhas average value: " + average2);
        
    }
}