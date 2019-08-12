import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());

			String [] names=new String [N];
			HashMap<String, Integer> nameToIdx=new HashMap<>();
			for (int n=0;n<N;n++) {
				names[n]=br.readLine();
				nameToIdx.put(names[n], n);
			}
			
			int [][] delta=new int [N][N];
			int [] nett=new int [N];
			for (int t=0;t<T;t++) {
				st=new StringTokenizer(br.readLine());
				String n1=st.nextToken();
				String n2=st.nextToken();
				int amount=Integer.parseInt(st.nextToken());
				delta[nameToIdx.get(n1)][nameToIdx.get(n2)]=amount;
				nett[nameToIdx.get(n1)]+=amount;
				nett[nameToIdx.get(n2)]-=amount;
			}
			
			int Nmax=0;
			for (int i=1;i<N;i++) if (nett[Nmax]<nett[i]) Nmax=i;
			
			StringBuilder sb=new StringBuilder("Case #");
			sb.append(testCase++);
			sb.append('\n');
			for (int n=0;n<N;n++) if (n!=Nmax) {
				if (nett[n]<0) {
					sb.append(names[n]);
					sb.append(' ');
					sb.append(names[Nmax]);
					sb.append(' ');
					sb.append(-nett[n]);
					sb.append('\n');
			    } else if (nett[n]>0) {
					sb.append(names[Nmax]);
					sb.append(' ');
					sb.append(names[n]);
					sb.append(' ');
					sb.append(nett[n]);
					sb.append('\n');
			    }
			}
			System.out.println(sb.toString());
		}
	}

}