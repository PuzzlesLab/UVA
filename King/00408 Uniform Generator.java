import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int step=Integer.parseInt(st.nextToken());
			int mod=Integer.parseInt(st.nextToken());
			
			boolean good=true;
			boolean [] flag=new boolean [mod];
			int currV=0;
			for (int i=0;i<mod && good;i++) {
				currV=(currV+step)%mod;
				if (flag[currV]) good=false;
				else flag[currV]=true;
			}
			
			int stepL=String.valueOf(step).length();
			int modL=String.valueOf(mod).length();
			StringBuilder sb=new StringBuilder();
			while (sb.length()<10-stepL) sb.append(' ');
			sb.append(step);
			while (sb.length()<20-modL) sb.append(' ');
			sb.append(mod);
			sb.append("    ");
			if (good) sb.append("Good Choice");
			else sb.append("Bad Choice");
			sb.append('\n');
			
			System.out.println(sb.toString());
		}
	}

}