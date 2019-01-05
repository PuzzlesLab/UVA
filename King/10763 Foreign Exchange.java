import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class uva {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			HashMap<String, Integer> map=new HashMap<>();
			int count=Integer.parseInt(s);
			for (int i=0;i<count;i++) {
				s=br.readLine();
				map.put(s,map.getOrDefault(s,0)+1);
				
				StringTokenizer st=new StringTokenizer(s);
				String l=st.nextToken();
				String r=st.nextToken();
				
				StringBuilder sb=new StringBuilder();
				sb.append(r);
				sb.append(' ');
				sb.append(l);
				s=sb.toString();
				map.put(s,map.getOrDefault(s,0)-1);
			}
			
			boolean flag=true;
			for (int v : map.values()) if (v!=0) {
				flag=false;
				break;
			}
			
			if (flag) System.out.println("YES");
			else System.out.println("NO");
		}
	}

}