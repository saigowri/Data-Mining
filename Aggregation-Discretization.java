import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Records{
    int attr1;    
    String attr2;
}
public class Prog1 {
    static Records[] rc=new Records[10];
    public static void main(String args[]) throws FileNotFoundException, IOException
    {
        BufferedReader CSV=new BufferedReader(new FileReader("C://Users//Desktop//aggregate.csv"));
        String data=CSV.readLine();
        data=CSV.readLine();
        int i=0;
        
        System.out.println("Dataset:");
        while(data!=null)
        {
            rc[i]=new Records();
            String[] dataArray=data.split(",");
            rc[i].attr1=Integer.parseInt(dataArray[0]);
            rc[i].attr2=dataArray[1];
            
            System.out.println(rc[i].attr1+" "+rc[i].attr2);
            data=CSV.readLine();
            i++;
        }
        
        //finding aggregate for numeric attribute
        int aggregate =0;
        
        for(int j=0;j<i;j++)
        aggregate += rc[j].attr1;
        
        aggregate=aggregate/i;
        System.out.println("Aggregate is: "+aggregate);
        
        //performing discretization for numeric attribute
        int interval_size;
        Scanner input=new Scanner(System.in);
        System.out.println("\n Enter interval size:");
        interval_size=input.nextInt();
        
        for(int j=0;j<i;j++)
        {
            int base=(int) (rc[j].attr1/interval_size);
            System.out.println(rc[j].attr1+" "+rc[j].attr2+" range["+(base*interval_size)+","+(base*interval_size+interval_size)+")");
        }
    }
}
