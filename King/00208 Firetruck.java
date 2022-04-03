import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<String> SolList;
	
	private static class Tuple {
		int n1, n2;
		public Tuple(int n1, int n2) {
			this.n1=n1;
			this.n2=n2;
		}
	}

	private static void find(int [] curr, int currSize, int [] candidates, boolean [] used, boolean [][] adjMat, int dest) {
		if (curr[currSize-1]==dest) {
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<currSize;i++) {
				sb.append(curr[i]+1);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			SolList.add(sb.toString());
		} else {
			int last=curr[currSize-1];
			for (int i=0;i<candidates.length;i++) {
				int nextC=candidates[i];
				if (!used[nextC] && adjMat[last][nextC]) {
					used[nextC]=true;
					curr[currSize]=nextC;
					find(curr,currSize+1,candidates,used,adjMat,dest);
					used[nextC]=false;
				}
			}
		}
	}
	
	private static int [] getLinkedCorners(boolean [][] adjMat, int dest) {
		boolean [] linked=new boolean [adjMat.length];
		Stack<Integer> stk=new Stack<>();
		stk.push(dest);
		linked[dest]=true;
		while (!stk.isEmpty()) {
			int curr=stk.pop();
			for (int i=0;i<adjMat[curr].length;i++) if (adjMat[curr][i] && !linked[i]) {
				stk.push(i);
				linked[i]=true;
			}
		}
		
		int linkedCount=0;
		for (int i=0;i<linked.length;i++) if (linked[i]) linkedCount++;
		
		int [] corners=new int [linkedCount];
		int temp=0;
		for (int i=0;i<linked.length;i++) if (linked[i]) corners[temp++]=i;
		return corners;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			int dest=Integer.parseInt(s)-1;
			
			ArrayList<Tuple> adj=new ArrayList<>();
			while (true) {
				s=br.readLine();
				if (s.equals("0 0")) break;
				StringTokenizer st=new StringTokenizer(s);
				adj.add(new Tuple(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1));
			}
			
			int T=0;
			for (Tuple tup: adj) T=Math.max(T, Math.max(tup.n1+1, tup.n2+1));

			SolList=new ArrayList<>();
			if (T>0) {
				T=Math.max(T,dest+1);
				boolean [][] adjMat=new boolean [T][T];
				for (Tuple tup: adj) adjMat[tup.n1][tup.n2]=adjMat[tup.n2][tup.n1]=true;

				int [] candidates=getLinkedCorners(adjMat,dest); // Without this filter, you will get TLE.
				if (candidates[0]==0) {
					boolean [] used=new boolean [T];
					used[0]=true;
					find(new int [T], 1, candidates, used, adjMat, dest);
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("CASE ");
			sb.append(testCase++);
			sb.append(":\n");
			for (String sol: SolList) {
				sb.append(sol);
				sb.append('\n');
			}
			sb.append("There are ");
			sb.append(SolList.size());
			sb.append(" routes from the firestation to streetcorner ");
			sb.append(dest+1);
			sb.append(".\n");
			System.out.print(sb.toString());
		}
	}

}
