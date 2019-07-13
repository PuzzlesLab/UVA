import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			int [] remaining=new int [N];
			Arrays.fill(remaining, 40);
			LinkedList<Integer> queue=new LinkedList<>();
			for (int n=0;n<N;n++) queue.add(n);
			
			ArrayList<Integer> ans=new ArrayList<>();
			int withdraw=1;
			int extra=0;
			while (queue.size()>0) {
				int currStudent=queue.removeFirst();
				int dispensed=0;
				if (extra>0) dispensed=extra;
				else {
					dispensed=withdraw;
					withdraw=Math.max(1, (withdraw+1)%(K+1));
				}
				
				extra=Math.max(0, dispensed-remaining[currStudent]);
				remaining[currStudent]-=dispensed;
				
				if (remaining[currStudent]>0) queue.add(currStudent);
				else ans.add(currStudent+1);
			}
			
			StringBuilder sb=new StringBuilder();
			for (int a : ans) sb.append(String.format("%3d", a));
			System.out.println(sb.toString());
		}
	}

}