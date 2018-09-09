import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		StringBuilder asb=new StringBuilder();
		while ((s=br.readLine())!=null) {
			HashMap<Integer, Integer> intCount=new HashMap<>();
			HashMap<String, Integer> intPos=new HashMap<>();
			
			StringTokenizer st=new StringTokenizer(s);
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<n;i++) {
				int num=Integer.parseInt(st.nextToken());
				intCount.put(num, intCount.getOrDefault(num,0)+1);
				
				StringBuilder sb=new StringBuilder();
				sb.append(intCount.get(num));
				sb.append("_");
				sb.append(num);
				intPos.put(sb.toString(), i+1);
			}
			
			for (int i=0;i<m;i++) {
				st=new StringTokenizer(br.readLine());
				
				StringBuilder sb=new StringBuilder();
				sb.append(st.nextToken());
				sb.append("_");
				sb.append(st.nextToken());
					
				asb.append(intPos.getOrDefault(sb.toString(), 0));
				asb.append('\n');
			}
		}
		System.out.print(asb.toString());
	}

}