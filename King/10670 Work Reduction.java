import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{

	public static class Agency {
		String name;
		int A, B;
		public Agency(String s) {
			StringTokenizer st=new StringTokenizer(s,":");
			this.name=st.nextToken();
			st=new StringTokenizer(st.nextToken(),",");
			this.A=Integer.parseInt(st.nextToken());
			this.B=Integer.parseInt(st.nextToken());
		}
	}
	
	public static class Result implements Comparable<Result> {
		Agency a;
		int cost;
		public int compareTo(Result r) {
			return (this.cost!=r.cost) ? this.cost-r.cost : this.a.name.compareTo(r.a.name);
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int L=Integer.parseInt(st.nextToken());
			
			Agency [] agencies=new Agency [L];
			for (int l=0;l<L;l++) agencies[l]=new Agency(br.readLine());
			
			Result [] results=new Result[L];
			for (int l=0;l<L;l++) {
				Agency agency=agencies[l];
				results[l]=new Result();
				results[l].a=agency;
				int curr=N;
				int cost=0;
				while (curr>M) {
					if (curr/2>=M) {
						int c1=agency.B;
						int c2=(curr-curr/2)*agency.A;
						curr/=2;
						if (c1<c2) cost+=c1;
						else cost+=c2;
					} else {
						cost+=(curr-M)*agency.A;
						curr=M;
					}
				}
				results[l].cost=cost;
			}
			
			Arrays.sort(results);
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append('\n');
			for (Result r: results) {
				sb.append(r.a.name);
				sb.append(' ');
				sb.append(r.cost);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}