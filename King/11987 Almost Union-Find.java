import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static int getParent(int [] parent, int max, int n) {
		if (parent[n]!=n) parent[n]=getParent(parent,max,parent[n]);
		return parent[n];
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			int [] parent=new int [2*N+1];
			for (int n=0;n<N;n++) {
				parent[n]=n+N;
				parent[n+N]=n+N;
			}
			
			int [] sum=new int [2*N+1];
			for (int n=N;n<sum.length;n++) sum[n]=(n-N+1);
			
			int [] noOfElements=new int [2*N+1];
			for (int n=N;n<noOfElements.length;n++) noOfElements[n]=1;
			
			StringBuilder sb=new StringBuilder();
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int op=Integer.parseInt(st.nextToken());
				int p=Integer.parseInt(st.nextToken())-1;
				int pp=getParent(parent,N,p);
				if (op==1) {
					int q=Integer.parseInt(st.nextToken())-1;
					int pq=getParent(parent,N,q);
					if (pp!=pq) {
						int min=Math.min(pp, pq);
						int max=Math.max(pp, pq);
						
						parent[max]=min;
						sum[min]+=sum[max];
						noOfElements[min]+=noOfElements[max];
					}
				} else if (op==2) {
					int q=Integer.parseInt(st.nextToken())-1;
					int pq=getParent(parent,N,q);
					if (pp!=pq) {
						sum[pp]-=(p+1);
						noOfElements[pp]--;
						
						parent[p]=pq;
						sum[pq]+=(p+1);
						noOfElements[pq]++;
					}
				} else if (op==3) {
					sb.append(noOfElements[pp]);
					sb.append(' ');
					sb.append(sum[pp]);
					sb.append('\n');
				}
			}
			System.out.print(sb.toString());
		}
	}
}