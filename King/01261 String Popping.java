import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {

	private static HashMap<String,Boolean> Dp;
	
	private static boolean compute(String s) {
		if (s.length()==0) return true;
		if (s.length()==1) return false;

		if (!Dp.containsKey(s)) {
			boolean flag=false;
			for (int i=0;i<s.length();) {
				char c=s.charAt(i);
				int i2=i+1;
				while (i2<s.length() && c==s.charAt(i2)) {
					i2++;
				}
				if (i2<s.length() && i2>i+1) {
					StringBuilder next=new StringBuilder();
					next.append(s.substring(0,i));
					next.append(s.substring(i2));
					flag|=compute(next.toString());
					if (flag) break;
				} else if (i==0 && i2==s.length()) {
					flag=true;
					break;
				}
				i=i2;
			}
			Dp.put(s,flag);
		}
		return Dp.get(s);
	}

	public static void main(String[] args) throws Exception {
		Dp=new HashMap<>();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			String s=br.readLine();
			// Dp can be reused in all test cases, don't recreate!
			System.out.println(compute(s)?1:0);
		}
	}

}