import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Record{
    int attr1;
    String attr2;
} 
public class Mode {
    static Record[] rc=new Record[10];
    public static void main(String args[]) throws FileNotFoundException, IOException
    {
        BufferedReader CSV1=new BufferedReader(new FileReader("C://Users//Desktop//missing.csv"));
        String data=CSV1.readLine();
        data=CSV1.readLine();
        int i=0;
        		
        Map<String, Integer> map = new HashMap<String, Integer>();
        while(data!=null)
        {
            rc[i]=new Record();
            String[] dataArray=data.split(",");
            rc[i].attr1=Integer.parseInt(dataArray[0]);
            rc[i].attr2=dataArray[1];
            if (!map.containsKey(rc[i].attr2))
                map.put(rc[i].attr2, 1);
            else
                map.put(rc[i].attr2, map.get(rc[i].attr2)+1);
           
            System.out.println(rc[i].attr1+" "+rc[i].attr2);
            data=CSV1.readLine();
            i++;
        }
//(a)for numeric attribute replacing missing value by mean
        int mean=0;
        System.out.println();
        System.out.println("Numeric:");
        
        for(int j=0;j<i;j++)
          {
        	  if(rc[j].attr1!=-1)
        		  mean+=rc[j].attr1; 
         }
          mean=mean/(i);
          System.out.println("Mean ="+mean);
      
        for(int j=0;j<i;j++)
        {
      	  if((rc[j].attr1!=-1))
      	  {   System.out.println(rc[j].attr1);}
      	else
      		{	
      		  rc[j].attr1=mean;
      		  
      		  System.out.println(rc[j].attr1);} 
        }
        
 //(b)for categorical attribute replacing missing value by mode
        int max=0;
        String mode="";
        System.out.println();
        System.out.println("Categorical:");
        
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            System.out.println(entry.getKey()+" "+entry.getValue());
            if(entry.getValue()>max){
                max=entry.getValue();
                mode=entry.getKey();
            }
        }
        
         System.out.println("Mode ="+mode);
       
        for(int j=0;j<i;j++)
        {
        	
      	  if(rc[j].attr2.equals("?"))
      	  {
      		  rc[j].attr2=mode;
      		  System.out.println(rc[j].attr2);}
      	  else
      	  { System.out.println(rc[j].attr2);
      		  
      	  }
      	  
         }
    }
}

 
