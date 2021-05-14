import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean first=true;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			TreeMap<Integer, Integer> occurence=new TreeMap<>();
			for (int n=1;n<=N;n++) for (int m=1;m<=M;m++) occurence.put(n+m, occurence.getOrDefault(n+m,0)+1);
			
			int max=0;
			for (int v: occurence.values()) max=Math.max(max,v);
			
			StringBuilder sb=new StringBuilder();
			if (first) first=false;
			else sb.append('\n');
			
			for (Entry<Integer,Integer> entry: occurence.entrySet()) if (entry.getValue()==max) {
				sb.append(entry.getKey());
				sb.append('\n');
			}
			
			System.out.print(sb.toString());
		}
	}
}