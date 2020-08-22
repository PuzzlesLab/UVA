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
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			s=s.trim();
			int sum=0;

			int index=0;
			int lastFound=1000;
			while (index<s.length()) {
				boolean found=false;
				for (int num: valuesMap.keySet()) if (lastFound>=num) {
					String test=valuesMap.get(num);
					if (index+test.length()<=s.length() && s.substring(index, index+test.length()).equals(test)) {
						index+=test.length();
						sum+=num;
						if (test.length()==1) lastFound=num;
						else lastFound=num-1;
						found=true;
						break;
					}
				}
				if (!found) {
					sum=-1;
					break;
				}
			}
			
			int sumTemp=sum;
			StringBuilder sb=new StringBuilder();
			while (sumTemp>0) {
				for (int num: valuesMap.keySet()) if (sumTemp>=num) {
					sb.append(valuesMap.get(num));
					sumTemp-=num;
					break;
				}
			}
			if (!sb.toString().equals(s)) sum=-1;

			if (sum<0 || sum>=4000) System.out.println("This is not a valid number");
			else System.out.println(sum);
		}
	}
}