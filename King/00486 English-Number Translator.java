import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		HashMap<String, Integer> valueMap=new HashMap<>();
		valueMap.put("zero",0);
		valueMap.put("one",1);
		valueMap.put("two",2);
		valueMap.put("three",3);
		valueMap.put("four",4);
		valueMap.put("five",5);
		valueMap.put("six",6);
		valueMap.put("seven",7);
		valueMap.put("eight",8);
		valueMap.put("nine",9);
		valueMap.put("ten",10);
		
		valueMap.put("eleven",11);
		valueMap.put("twelve",12);
		valueMap.put("thirteen",13);
		valueMap.put("fourteen",14);
		valueMap.put("fifteen",15);
		valueMap.put("sixteen",16);
		valueMap.put("seventeen",17);
		valueMap.put("eighteen",18);
		valueMap.put("nineteen",19);
		
		valueMap.put("twenty",20);
		valueMap.put("thirty",30);
		valueMap.put("forty",40);
		valueMap.put("fifty",50);
		valueMap.put("sixty",60);
		valueMap.put("seventy",70);
		valueMap.put("eighty",80);
		valueMap.put("ninety",90);
		
		valueMap.put("hundred",100);
		valueMap.put("thousand",1000);
		valueMap.put("million",1000000);
		
		HashSet<String> multiplier=new HashSet<>();
		multiplier.add("ten");
		multiplier.add("hundred");
		multiplier.add("thousand");
		multiplier.add("million");
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			ArrayList<String> list=new ArrayList<>();
			while (st.hasMoreTokens()) list.add(st.nextToken());
			
			boolean negative=list.get(0).equals("negative");
			if (negative) list.remove(0);
			int totalV=0;
			int lastV=0;
			for (int i=0;i<list.size();i++) {
				String word=list.get(i);
				if (multiplier.contains(word) && lastV>0) {
					int accMul=valueMap.get(word);
					int currMultiplier=valueMap.get(word);
					for (int i2=i+1;i2<list.size();i2++) {
						String nextMulWord=list.get(i2);
						if (multiplier.contains(nextMulWord)) {
							int nextMultiplier=valueMap.get(nextMulWord);
							if (nextMultiplier>currMultiplier) {
								accMul*=nextMultiplier;
								currMultiplier=nextMultiplier;
								if (i+1==i2) {
									list.remove(i2);
									i2--;
								}
							}
						}
					}
					lastV*=accMul;
					totalV+=lastV;
					lastV=0;
				} else {
					lastV=(multiplier.contains(word)) ? valueMap.get(word) : lastV+valueMap.get(word);
				}
			}
			totalV+=lastV;
			
			if (list.size()==1 && totalV==0) totalV=valueMap.get(list.get(0));
			
			if (negative) totalV*=-1;
			System.out.println(totalV);
		}
	}
}