import java.io.*;
import java.util.Scanner;

public class RuleGeneration {

	public static void main(String[] args) throws IOException {
 
		String[] frequent_item = new String[4];
		String[] rules = new String[50];
 
		InputStreamReader r=new InputStreamReader(System.in);  
		BufferedReader br=new BufferedReader(r);  
		
		double conf,sup;
		
		//Read items
		System.out.println("Enter the frequent itemsets");
		for(int i=0;i<4;i++)
			frequent_item[i]=br.readLine();
 
		//generate rules from frequent itemset
		rules = Generate_subsets_rules(frequent_item);
 
		Scanner scan = new Scanner( System.in );
		
		//identify strong rules given cinfidence ans support
		for(int k=0;k<rules.length;k++)
		{
			System.out.println("Enter the support and confidence of the rule "+rules[k]);
			sup = scan.nextDouble();
			conf=scan.nextDouble();
				
			if(sup>=0.5 && conf>=0.6)
				System.out.println(rules[k]+"  is a strong rule");
			else
				System.out.println(rules[k]+" is not a strong rule");
		}
        
       
    }
	
    static String[] Generate_subsets_rules(String[] frequent)
    {
    	String[] rules= new String[50];
        
    	System.out.println("1-subset rules\n");
        
    	int k=0;
        
    	for(int i=0;i<4;i++)
        {rules[k] =frequent[i]+"->"+"{";
            for(int j=0;j<4;j++ )
                if(i!=j)
                rules[k]+=frequent[j]+",";
            rules[k]+="}";
            System.out.println(rules[k]);
            k++;
        }
      
    	System.out.println("2-subset rules\n");
        for(int i=0;i<4;i++)
        {  
            for(int j=i+1;j<4;j++)
        {    rules[k]=frequent[i]+","+frequent[j];
                    rules[k]+="->"+"{";
         for(int l=0;l<4;l++)
         {  if((l!=i) && (l!=j))
             {
                 rules[k]+=frequent[l]+",";
             }
         }
         rules[k]+="}";
         System.out.println(rules[k]);
         k++;
        }
         }
          
        System.out.println("3-subset rules\n");
        for(int i=0;i<4;i++)
        { rules[k]="{";
            for(int j=0;j<4;j++)
            {
              if(i!=j)
                  rules[k]+=frequent[j]+",";
            }
            rules[k]+="}"+"->"+frequent[i];
            System.out.println(rules[k]);
            k++;
        }
        return rules;
        
      
    }
}

 
