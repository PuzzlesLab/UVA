import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	public static int search(char [][] choices, boolean [] used, int [] seq, int size) {
		if (size==seq.length) {
			ArrayList<Character> solution=new ArrayList<>();
			for (int curr=0;curr<seq.length;curr++) {
				char [] currText=choices[seq[curr]];
				boolean match=true;
				int matchIdx=0;
				for (int solIdx=Math.max(0,solution.size()-currText.length);solIdx<solution.size();solIdx++) {
					match=true;
					for (int i=0;i<currText.length && (solIdx+i)<solution.size();i++) {
						match &= (solution.get(solIdx+i)==currText[i]);
						if (match) matchIdx=i+1;
						else {
							matchIdx=0;
							break;
						}
					}
					if (match) break;
				}
				for (int i=matchIdx;i<currText.length;i++) solution.add(currText[i]);
			}
			return solution.size();
		} else {
			int ans=Integer.MAX_VALUE;
			for (int i=0;i<choices.length;i++) if (!used[i]) {
				used[i]=true;
				seq[size]=i;
				ans=Math.min(ans, search(choices,used,seq,size+1));
				used[i]=false;
			}
			return ans;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			char [][] choices=new char [N][];
			for (int n=0;n<N;n++) choices[n]=br.readLine().toCharArray();
			
			int ans = search(choices, new boolean [N], new int [N], 0);
			System.out.printf("Case %d: %d\n", testCase, ans);
		}
	}

}
