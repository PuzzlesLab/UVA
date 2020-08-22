import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.TreeMap;

class Main {
	
	private static boolean isDigit(String s) {
		for (char c : s.toCharArray()) if (c<'0' || c>'9') return false;
		return true;
	}
	
	public static void main (String [] args) throws Exception {
		int [] values=new int [128];
		values['I']=1;
		values['V']=5;
		values['X']=10;
		values['L']=50;
		values['C']=100;
		values['D']=500;
		values['M']=1000;
		
		TreeMap<Integer, String> valuesMap=new TreeMap<>(Collections.reverseOrder());
		for (int i=0;i<values.length;i++) if (values[i]!=0) valuesMap.put(values[i], ((char)i)+"");
		valuesMap.put(4,"IV");
		valuesMap.put(9,"IX");
		valuesMap.put(40,"XL");
		valuesMap.put(90,"XC");
		valuesMap.put(400,"CD");
		valuesMap.put(900,"CM");
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			if (isDigit(s)) {
				StringBuilder sb=new StringBuilder();
				int temp=Integer.parseInt(s);
				while (temp>0) {
					for (int v : valuesMap.keySet()) {
						if (temp>=v) {
							sb.append(valuesMap.get(v));
							temp-=v;
							break;
						}
					}
				}
				System.out.println(sb.toString());
			} else {
				int sum=0;
				char [] ch=s.toCharArray();
				for (int i=0;i<ch.length;i++) {
					if (i+1<ch.length && values[ch[i+1]]>values[ch[i]]) {
						sum-=values[ch[i]];
						sum+=values[ch[i+1]];
						i++;
					} else sum+=values[ch[i]];
				}
				System.out.println(sum);
			}
		}
	}
}