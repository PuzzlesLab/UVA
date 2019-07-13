import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			String [] name=new String[N];
			for (int n=0;n<N;n++) name[n]=br.readLine();
			
			String s;
			ArrayList<ArrayList<Integer>> votes=new ArrayList<>(); //Slightly faster than LinkedList for the test data.
			while (true) {
				s=br.readLine();
				if (s==null || s.equals("")) break;
				
				StringTokenizer st=new StringTokenizer(s);
				ArrayList<Integer> list=new ArrayList<>();
				votes.add(list);
				for (int n=0;n<N;n++) list.add(Integer.parseInt(st.nextToken())-1);
			}
			
			String ans="";
			boolean [] kicked=new boolean [N];
			for (int i=0;i<N;i++) {
				int [] voteCount=new int [N];
				for (ArrayList<Integer> v : votes) voteCount[v.get(i)]++;
				
				int min=Integer.MAX_VALUE, max=0;
				for (int i2=0;i2<N;i2++) if (!kicked[i2]) {
					min=Math.min(min, voteCount[i2]);
					max=Math.max(max, voteCount[i2]);
				}
				
				if (min==max) {
					StringBuilder sb=new StringBuilder();
					for (int i2=0;i2<N;i2++) if (!kicked[i2]) {
						sb.append(name[i2]);
						sb.append('\n');
					}
					sb.setLength(sb.length()-1);
					ans=sb.toString();
					break;
				} else {
					for (int i2=0;i2<N;i2++) if (!kicked[i2] && voteCount[i2]==min) {
						kicked[i2]=true;
						for (ArrayList<Integer> v : votes) v.remove(Integer.valueOf(i2));
					}
					i--;
				}
			}
			
			if (testCase>0) System.out.println();
			System.out.println(ans);
		}
	}

}
