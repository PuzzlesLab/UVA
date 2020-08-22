import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.TreeMap;

class Main {
	
	public static void main (String [] args) throws Exception {
		TreeMap<Integer, String> valuesMap=new TreeMap<>(Collections.reverseOrder());
		valuesMap.put(1,"I");
		valuesMap.put(4,"IV");
		valuesMap.put(5,"V");
		valuesMap.put(9,"IX");
		valuesMap.put(10,"X");
		valuesMap.put(40,"XL");
		valuesMap.put(50,"L");
		valuesMap.put(90,"XC");
		valuesMap.put(100,"C");
		valuesMap.put(400,"CD");
		valuesMap.put(500,"D");
		valuesMap.put(900,"CM");
		valuesMap.put(1000,"M");
		
		int [] matches=new int [128];
		matches['I']=1;
		matches['V']=2;
		matches['X']=2;
		matches['L']=2;
		matches['C']=2;
		matches['D']=3;
		matches['M']=4;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int num=Integer.parseInt(s);
			StringBuilder sb=new StringBuilder();
			while (num>0) {
				for (int v: valuesMap.keySet()) if (num>=v) {
					sb.append(valuesMap.get(v));
					num-=v;
					break;
				}
			}

			int count=0;
			for (char c : sb.toString().toCharArray()) count+=matches[c];
			System.out.println(count);
		}
	}
}