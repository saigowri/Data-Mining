package stratified;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

class Record {
	int attr1;
	String attr2;
}

public class Stratify {
	static Record[] rc = new Record[10];
	public static void main(String args[]) throws IOException
	{
		BufferedReader csv = new BufferedReader(new FileReader("/home/temp.csv"));
		Map<String,ArrayList<Record>> hm = new HashMap<String,ArrayList<Record>>(); 
		int i=0;
		String data = csv.readLine();
		
		while(data!=null){
			
		    rc[i] = new Record();
	    	String[] dataEntry = data.split(",");
	    	rc[i].attr1 = Integer.parseInt(dataEntry[0]);
	    	rc[i].attr2 = dataEntry[1];
	    	data = csv.readLine();
	    	i++;
		}
		
		//adding classes
		
		hm.put("Hot City",new ArrayList<Record>());
		hm.put("Cool City",new ArrayList<Record>());
		
		
		//adding data to hashmap
		int j;
		for(j=0;j<i;j++){
			String c = "";
			if(rc[j].attr1 > 25)
				c="Hot City";
			else
				c="Cool City";
			ArrayList<Record> temp = hm.get(c);
			temp.add(rc[j]);
			hm.put(c, temp);
		}
		
		//traverse through different classes
		for(String key:hm.keySet()){
			Random r = new Random();
			if(hm.get(key).isEmpty())
				continue;
			//taking out one sample from the class
			Record randomSample = hm.get(key).get(r.nextInt(hm.get(key).size()));
			System.out.println("Class : "+key+" "+randomSample.attr1+" "+randomSample.attr2);
		}
		
		System.out.println("OK");
		
		
	}
}
