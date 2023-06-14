import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {

	private static String perform(String s) {
		int [] count=new int [128];
		for (int i=0;i<s.length();i++) count[s.charAt(i)]++;

		StringBuilder sb=new StringBuilder();
		for (int i='0';i<='9';i++) if (count[i]>0) {
			sb.append(count[i]);
			sb.append((char)i);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("-1")) {
			HashMap<String,Integer> position=new HashMap<>();
			position.put(s,0);
			
			String temp=s;
			int start=-1;
			int end=-1;
			for (int i=1;i<=15;i++) {
				temp=perform(temp);
				if (position.containsKey(temp)) {
					start=position.get(temp);
					end=i;
					break;
				} else position.put(temp,i);
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append(s);

			if (start==-1) sb.append(" can not be classified after 15 iterations");
			else {
				int length=end-start;
				if (length==1) {
					sb.append(" is self-inventorying");
					if (start>0) {
						sb.append(" after ");
						sb.append(start);
						sb.append(" steps");
					}
				} else {
					sb.append(" enters an inventory loop of length ");
					sb.append(end-start);
				}
			}

			System.out.println(sb.toString());
		}
	}

}
