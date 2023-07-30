import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Door {
		double x;
		double p;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			String s;
			while (true) {
				s=br.readLine();
				if (!s.trim().isEmpty()) break;
			}
			int N=Integer.parseInt(s);
			Door [] doors=new Door[N];
			boolean possible=false;
			for (int n=0;n<N;n++) {
				doors[n]=new Door();
				StringTokenizer st=new StringTokenizer(br.readLine());
				doors[n].x=Double.parseDouble(st.nextToken());
				doors[n].p=Double.parseDouble(st.nextToken());
				possible |= (doors[n].x>0);
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			if (!possible) sb.append("God! Save me");
			else {
				double ans=0.0;
				double tp=0.0;
				for (int n=0;n<N;n++) {
					ans+=Math.abs(doors[n].p*doors[n].x);
					if (doors[n].x>0) tp+=doors[n].p;
				}
				sb.append(String.format("%.2f",ans/tp));
			}
			System.out.println(sb.toString());
		}
	}

}
