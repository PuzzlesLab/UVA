import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int D=Integer.parseInt(st.nextToken());
			
			int [] rPos=new int[D];
			Arrays.fill(rPos,-1);
			ArrayList<Integer> quotient=new ArrayList<>();

			int R=N;
			int repeat=-1;
			int cPos=0;
			while (R>0) {
				R*=10;
				int q=R/D;
				R=R%D;

				if (rPos[R]==-1 || quotient.get(rPos[R])!=q) {
					quotient.add(q);
					rPos[R]=cPos;
				} else {
					repeat=cPos-rPos[R];
					break;
				}
				cPos++;
			}

			ArrayList<String> ans=new ArrayList<>();
			StringBuilder sb=new StringBuilder();
			sb.append('.');
			for (int i=0;i<quotient.size();i++) {
				sb.append(quotient.get(i));
				if (sb.length()==50) {
					ans.add(sb.toString());
					sb=new StringBuilder();
				}
			}
			if (sb.length()>0) ans.add(sb.toString());

			if (repeat==-1) ans.add("This expansion terminates.");
			else {
				sb=new StringBuilder();
				sb.append("The last ");
				sb.append(repeat);
				sb.append(" digits repeat forever.");
				ans.add(sb.toString());
			}
			
			sb=new StringBuilder();
			for (int i=0;i<ans.size();i++) {
				sb.append(ans.get(i));
				sb.append('\n');
			}
			System.out.println(sb);
		}
	}

}
