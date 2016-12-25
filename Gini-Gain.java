import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


class Record_C{
    int attr1;
    int attr2;
    int attr3;
    int attr4;
    int attr5;
    int attr6;
    int attr7;
    int Class;
   
};
public class g
    {
        static Record_C[] rc = new Record_C[10];
        public static void main(String args[])throws FileNotFoundException, IOException
    {
             BufferedReader CSV =
            new BufferedReader(new FileReader("C://Users//Desktop//gini.csv"));

      String data = CSV.readLine();
      data = CSV.readLine();
      int i=0;
      System.out.println("input:");
      while((data != null))
      {
          rc[i]= new Record_C();

          String[] dataArray = data.split(",");
            rc[i].attr1=Integer.parseInt(dataArray[0]);
            rc[i].attr2=Integer.parseInt(dataArray[1]);
            rc[i].attr3=Integer.parseInt(dataArray[2]);
            rc[i].attr4=Integer.parseInt(dataArray[3]);
            rc[i].attr5=Integer.parseInt(dataArray[4]);
            rc[i].attr6=Integer.parseInt(dataArray[5]);
            rc[i].attr7=Integer.parseInt(dataArray[6]);
            rc[i].Class=Integer.parseInt(dataArray[7]);
            System.out.println(" "+rc[i].attr1+" "+rc[i].attr2+" "+" "+rc[i].attr3+" "+rc[i].attr4+" "+rc[i].attr5+" "+rc[i].attr6+" "+rc[i].attr7+" "+rc[i].Class);
            data=CSV.readLine();
            i++;
      
    }
      double total1=0;
      double total2=0;
      double total3=0;
      double total4=0;
      double total5=0;
      double total6=0;
      double total7=0;
      double g1=0,e1=0;
      double g2=0,e2=0;
      double g3=0,e3=0;
      double g4=0,e4=0;
      double g5=0,e5=0;
      double g6=0,e6=0;
      double g7=0,e7=0;
      double class1[]=new double[2];
      int k = 0;
 
      for(int j=0;j<i;j++)
     {
       //class1[k]=rc[j].attr1+rc[j].attr2+rc[j].attr3+rc[j].attr4+rc[j].attr5+rc[j].attr6+rc[j].attr7;

      total1+=rc[j].attr1;
      total2+=rc[j].attr2;
      total3+=rc[j].attr3;
      total4+=rc[j].attr4;
      total5+=rc[j].attr5;
      total6+=rc[j].attr6;
      total7+=rc[j].attr7;
      k++;
      }

      for(int j=0;j<i;j++)
      {
      g1+=Math.pow((rc[j].attr1)/total1,2);
      g2+=Math.pow((rc[j].attr2)/total2,2);
      g3+=Math.pow((rc[j].attr3)/total3,2);
      g4+=Math.pow((rc[j].attr4)/total4,2);
      g5+=Math.pow((rc[j].attr5)/total5,2); 
      g6+=Math.pow((rc[j].attr6)/total6,2);
      g7+=Math.pow((rc[j].attr7)/total7,2);
      }
      for(int j=0;j<i;j++)
      {
    	  double a=(rc[j].attr1)/total1;
    	  double b=(rc[j].attr2)/total2;
    	  double c=(rc[j].attr3)/total3;
    	  double d=(rc[j].attr4)/total4;
    	  double e=(rc[j].attr5)/total5;
    	  double f=(rc[j].attr6)/total6;
    	  double g=(rc[j].attr7)/total7;
      e1+=a*(Math.log(a))/Math.log(2);
      e2+=b*(Math.log(b))/Math.log(2);
      e3+=c*(Math.log(c))/Math.log(2);
      e4+=d*(Math.log(d))/Math.log(2);
      e5+=e*(Math.log(e))/Math.log(2);
      e6+=f*(Math.log(f))/Math.log(2);
      e7+=g*(Math.log(g))/Math.log(2);
      }
      double gini1=1-g1;
      double gini2=1-g2;
      double gini3=1-g3;
      double gini4=1-g4;
      double gini5=1-g5;
      double gini6=1-g6;
      double gini7=1-g7;
      
      double entropy1=-1*e1;
      double entropy2=-1*e2;
      double entropy3=-1*e3;
      double entropy4=-1*e4;
      double entropy5=-1*e5;
      double entropy6=-1*e6;
      double entropy7=-1*e7;
      
      double wtgini1=((total1)/(total1+total2))*gini1+((total2)/(total1+total2))*gini2;
      double wtgini2=((total3)/(total3+total4))*gini3+((total4)/(total3+total4))*gini4;
      double wtgini3=((total5)/(total5+total6+total7))*gini5+((total6)/(total5+total6+total7))*gini6+((total7)/(total5+total6+total7))*gini7;
      
      double wtentropy1=((total1)/(total1+total2))*entropy1+((total2)/(total1+total2))*entropy2;
      double wtentropy2=((total3)/(total3+total4))*entropy3+((total4)/(total3+total4))*entropy4;
      double wtentropy3=((total5)/(total5+total6+total7))*entropy5+((total6)/(total5+total6+total7))*entropy6+((total7)/(total5+total6+total7))* entropy7;
      
      
      double parent_entropy=-1*((0.5*(Math.log(0.5)/Math.log(2)))+(0.5*(Math.log(0.5)/Math.log(2))));
      double parent_gini=1-(0.5*0.5)-(0.5*0.5);
      
      
      double gain1=(parent_entropy-wtentropy1);
      double gain2=(parent_entropy-wtentropy2);
      double gain3=(parent_entropy-wtentropy3);
     
      System.out.println();
      System.out.println("parent entropy:"+parent_entropy);
      System.out.println("parent gini:"+parent_gini);
      System.out.println("Weighted gini index of numerical attributes:"+wtgini1+" and gain:"+gain1);
      System.out.println("Weighted gini index of binomial attributes:"+wtgini2+" and gain:"+gain2);
      System.out.println("Weighted gini index of multi way split    :"+wtgini3+" and gain:"+gain3);
      System.out.println("\n\n Best attribute to split:");

      if(wtgini1<wtgini2 && wtgini1<wtgini3){
         System.out.println("Numerical way is best to split according to gini index");
      }else if(wtgini2<wtgini3 && wtgini2<wtgini1){
          System.out.println("Binary split is best to according to gini index");
      }else{
          System.out.println("Multiway split is best to according to gini index");
      }
      
      if(gain1>gain2 && gain1>gain3){
          System.out.println("Numerical way is best to split according to information gain");
       }else if(gain2>gain3 && gain2>gain1){
           System.out.println("Binary split is best to split according to information gain");
       }else{
           System.out.println("Multiway split is best to split according to information gain");
       }
}
    }
