import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Records{
	float attr1;
	String attr2;
	int classlabel;
}

public class nb {
	static Records rc[]=new Records[20];
	
	static double[] Handling_numeric(int size)
	{   double[] mean_var=new double[4];
		int count0=0,count1=0,sum0=0,sum1=0;
		
		for(int i=0;i<size;i++)
		{
			if(rc[i].classlabel==0)
			{
				sum0+=rc[i].attr1;
				count0++;
			}
			else
			{
				sum1+=rc[i].attr1;
				count1++;
			}
		}
		
	mean_var[0]=(double)sum0/count0;
	mean_var[1]=(double)sum1/count1;
		
		sum0=sum1=0;
		for(int i=0;i<size;i++)
		{
			if(rc[i].classlabel==0)
			{
				sum0+=Math.pow((rc[i].attr1-mean_var[0]),2);
			}
			else
			{
				sum1+=Math.pow((rc[i].attr1-mean_var[1]),2);
			}
		}
		
	mean_var[2]=(double)sum0/((count0)*(count0-1));
	mean_var[3]=(double)sum1/((count1)*(count1-1));
	    return mean_var;
	}
	
	static double[] Handling_cat(int size,int class0,int class1)
	{ 
		 double[] prob_cat=new double[10];
		 int c10=0,c11=0,c20=0,c21=0,c30=0,c31=0;
         

	        for(int i=0;i<size;i++)
           {
	        	if((rc[i].attr2.equals("S"))&&(rc[i].classlabel==0))
	        		c10++;
	        	if(rc[i].attr2.equals("S")&&(rc[i].classlabel==1))
	        		c11++;
	        	if((rc[i].attr2.equals("M"))&&(rc[i].classlabel==0))
	                c20++;
	        	if((rc[i].attr2.equals("M"))&&(rc[i].classlabel==1))
	        		c21++;
	            if((rc[i].attr2.equals("L"))&&(rc[i].classlabel==0))
	            	c30++;
	            if((rc[i].attr2.equals("L"))&&(rc[i].classlabel==1))
	                c31++;
           }
	        
	     double prob_S_0=(double)c10/class0;
	     double prob_S_1=(double)c11/class1;
	     double prob_M_0=(double)c20/class0;
	     double prob_M_1=(double)c21/class1;
	     double prob_L_0=(double)c30/class0;
	     double prob_L_1=(double)c31/class1;
	     
	     prob_cat[0]=prob_S_0;
	     prob_cat[1]=prob_S_1;
	     prob_cat[2]=prob_M_0;
	     prob_cat[3]=prob_M_1;
	     prob_cat[4]=prob_L_0;
	     prob_cat[5]=prob_L_1;
	    
	     
	    return prob_cat;
	}
	
	public static void main(String agrs[])throws IOException, FileNotFoundException
	{
		BufferedReader CSV=new BufferedReader(new FileReader("C://Users//Desktop//naivebayesian.csv"));
		String data=CSV.readLine();
		data=CSV.readLine();
		int i=0;
		while(data!=null)
		{
			rc[i]=new Records();
			String[] dataArray=data.split(",");
			rc[i].attr1=Float.parseFloat(dataArray[0]);
			rc[i].attr2=dataArray[1];
			rc[i].classlabel=Integer.parseInt(dataArray[2]);
			System.out.println(rc[i].attr1+" "+rc[i].attr2+" "+rc[i].classlabel);
			data=CSV.readLine();
			i++;
		}
		 
		System.out.println("");
		
		//for handling numeric attribute
		double[] mean_var=new double[4];
		mean_var=Handling_numeric(i);
		System.out.println("mean for class     0 ="+mean_var[0]);
		System.out.println("varience for class 0 ="+mean_var[2]);
		System.out.println("mean for class     1 ="+mean_var[1]);
		System.out.println("varience for class 1 ="+mean_var[3]);
		System.out.println("");
		
		//for handling categorical attribute
		double[] prob_cat=new double[10];
		int class0=0,class1=0;
		for(int j=0;j<i;j++)
		{
			if(rc[j].classlabel==0)
				class0++;
			else
				class1++;
		}
		prob_cat=Handling_cat(i,class0,class1);
		System.out.println("Probability of attribute value = S and class 0 is "+prob_cat[0]);
        System.out.println("Probability of attribute value = S and class 1 is "+prob_cat[1]);
        System.out.println("Probability of attribute value = M and class 0 is "+prob_cat[2]);
        System.out.println("Probability of attribute value = M and class 1 is "+prob_cat[3]);
        System.out.println("Probability of attribute value = L and class 0 is "+prob_cat[4]);
        System.out.println("Probability of attribute value = L and class 1 is "+prob_cat[5]);
	 
        //for classifying new tuple
        Scanner input=new Scanner(System.in);
        System.out.println("\n enter new value for attr1:");
        float newval1=input.nextFloat();
        System.out.println("\n enter new value for attr2:");
        String newval2=input.next();
        
        double prob_0=0,prob_1=0;
        switch(newval2)
       {
            case "S": prob_0 = prob_cat[0];
                      prob_1 = prob_cat[1];
                      break;
            case "M": prob_0 = prob_cat[2];
                      prob_1 = prob_cat[3];
                      break;
            case "L":prob_0 = prob_cat[4];
                      prob_1 = prob_cat[5];
                      break;
            default: System.out.println("Wrong entry");
        }
        
        double gauss_0=0,gauss_1=0;
        gauss_0=1*Math.exp(-(((newval1-mean_var[0])*(newval1-mean_var[0]))/(2*mean_var[2])))/Math.sqrt(2*3.14*mean_var[2]);
        
        gauss_1=1*Math.exp(-(((newval1-mean_var[1])*(newval1-mean_var[1]))/(2*mean_var[3])))/Math.sqrt(2*3.14*mean_var[3]);
	
        double prob_new_class0=gauss_0*prob_0;
        double prob_new_class1=gauss_0*prob_1;
        System.out.println("");
        System.out.println("class 0 proabiltiy : "+prob_new_class0);
        System.out.println("class 1 probabiltiy:"+prob_new_class1);
        
        
        if(prob_new_class1>prob_new_class0)
        	System.out.println("new tuple belongs to class 1");
        else
        	System.out.println("new tuple belongs to class 0");
	}

}
