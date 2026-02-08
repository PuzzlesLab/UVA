import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			int K=Integer.parseInt(br.readLine());
			ArrayList<Integer> list=new ArrayList<>();
			for (int k=1;k<=K;k++) list.add(k);
			
			StringBuilder sb=new StringBuilder();
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int k=0;k<K;k++) {
				int v=list.remove(Integer.parseInt(st.nextToken()));
				sb.append(v);
				sb.append(' ');
			}
			if (sb.length()>0) sb.setLength(sb.length()-1);
			System.out.println(sb);
		}
	}

}
