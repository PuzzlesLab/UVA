import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			int M=Integer.parseInt(br.readLine());
			ArrayList<int []> list=new ArrayList<>();
			String s;
			while (!(s=br.readLine()).equals("0 0")) {
				StringTokenizer st=new StringTokenizer(s);
				int [] ints=new int [] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
				if (ints[1]>=0 && ints[0]<M) list.add(ints);
			}
			list.add(new int [] {M+2, M+2});
			Collections.sort(list, (a,b) -> {
				if (a[0]==b[0]) return a[1]-b[1];
				return a[0]-b[0];
			});

			ArrayList<int []> ans=new ArrayList<>();
			int end=0;
			int index=0;
			while (index<list.size() && end<M) {
				ArrayList<int []> candidates=new ArrayList<>();
				for (;index<list.size();index++) {
					int [] curr=list.get(index);
					if (curr[0]<=end) candidates.add(curr);
					else if (curr[1]>=end) break;
				}
				if (candidates.size()>0) {
					Collections.sort(candidates, (a,b) -> {
						if (a[1]==b[1]) return b[0]-a[0];
						return a[1]-b[1];
					});
					
					int [] selected=candidates.get(candidates.size()-1);
					ans.add(selected);
					end=Math.min(selected[1], M);
				} else end=M+2;
			}
			
			if (testCase>0) System.out.println();
			if (end!=M) System.out.println("0");
			else {
				StringBuilder sb=new StringBuilder();
				sb.append(ans.size());
				sb.append('\n');
				for (int [] sol : ans) {
					sb.append(sol[0]);
					sb.append(' ');
					sb.append(sol[1]);
					sb.append('\n');
				}
				System.out.print(sb.toString());
			}
			
		}
	}

}
