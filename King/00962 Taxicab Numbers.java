import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

class Main {

	public static void main(String[] args) throws Exception {
		int max=(int)Math.floor(Math.cbrt(1000000000+100000+1));
		ArrayList<Integer> cubes=new ArrayList<>();
		for (int i=1;i<=max;i++) cubes.add(i*i*i);

		HashMap<Integer,Integer> sumCount=new HashMap<>();
		HashSet<Integer> candidateAns=new HashSet<>();
		for (int i=0;i<cubes.size();i++) for (int i2=i;i2<cubes.size();i2++) {
			int sum=cubes.get(i)+cubes.get(i2);
			sumCount.put(sum,sumCount.getOrDefault(sum,0)+1);
			if (sumCount.get(sum)>=2) candidateAns.add(sum);
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int n1=Integer.parseInt(s);
			int n2=n1+Integer.parseInt(br.readLine());

			ArrayList<Integer> ans=new ArrayList<>();
			for (int i: candidateAns) if (i>=n1 && i<=n2) ans.add(i);
			Collections.sort(ans);

			StringBuilder sb=new StringBuilder();
			if (ans.isEmpty()) sb.append("None\n");
			else {
				for (int num: ans) {
					sb.append(num);
					sb.append('\n');
				}
			}
			System.out.print(sb.toString());
		}
	}

}
