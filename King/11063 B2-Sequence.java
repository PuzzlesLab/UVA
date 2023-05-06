import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int [] nums=new int [N];
			StringTokenizer st=new StringTokenizer(br.readLine());
			boolean seqOK=true;
			for (int n=0;n<N;n++) {
				nums[n]=Integer.parseInt(st.nextToken());
				if (nums[n]<1) seqOK=false;
				if (n>0 && nums[n]<=nums[n-1]) seqOK=false;
			}
			
			HashSet<Integer> exists=new HashSet<>();
			boolean hasDup=false;
			if (seqOK) {
				for (int i=0;i<N && !hasDup;i++) {
					for (int j=i;j<N && !hasDup;j++) {
						int sum=nums[i]+nums[j];
						if (exists.contains(sum)) {
							hasDup=true;
							break;
						}
						exists.add(sum);
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc);
			sb.append(": It is ");
			if (!seqOK || hasDup) sb.append("not ");
			sb.append("a B2-Sequence.\n");
			System.out.println(sb.toString());
			
			tc++;
			br.readLine(); // blank.
		}
	}
}
