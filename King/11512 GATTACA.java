import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			String s=br.readLine()+"$";
			String [] ss=new String [s.length()];
			for (int i=0;i<s.length();i++) ss[i]=s.substring(i);
			Arrays.sort(ss);
			
			int [] lcp=new int [ss.length];
			int maxLCP=0;
			int maxLCPIdx=-1;
			for (int i=1;i<lcp.length;i++) {
				int count=0;
				for (int i2=0;i2<ss[i-1].length() && i2<ss[i].length();i2++) {
					if (ss[i-1].charAt(i2)==ss[i].charAt(i2)) count++;
					else break;
				}
				lcp[i]=count;
				if (lcp[i]>maxLCP) {
					maxLCP=lcp[i];
					maxLCPIdx=i;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			if (maxLCP>0) {
				int count=1;
				for (int i=maxLCPIdx;i<lcp.length;i++) {
					if (lcp[i]==maxLCP) count++;
					else break;
				}
				sb.append(ss[maxLCPIdx-1].substring(0,maxLCP));
				sb.append(' ');
				sb.append(count);
			} else sb.append("No repetitions found!");
			System.out.println(sb);
		}
	}

}