import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			String [][] pairs=new String [N][2];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				pairs[n][0]=st.nextToken();
				pairs[n][1]=st.nextToken();
			}

			String from=br.readLine();
			String to=br.readLine();
			HashMap<String,Integer> step=new HashMap<>();
			LinkedList<String> q=new LinkedList<>();
			q.add(from);
			step.put(from,0);
			while (!q.isEmpty()) {
				String curr=q.removeFirst();
				if (curr.equals(to)) break;
				
				for (int n=0;n<N;n++) {
					String nS=curr.replace(pairs[n][0],pairs[n][1]);
					// Assume 1 <= a < b <= 10. Replacing only cause the new string to become longer.
					// Useless proceeding further if too long.
					if (nS.length()>to.length() || step.containsKey(nS)) continue;
					step.put(nS,step.get(curr)+1);
					q.add(nS);
				}
			}
			System.out.println(step.getOrDefault(to,-1));
		}
	}

}