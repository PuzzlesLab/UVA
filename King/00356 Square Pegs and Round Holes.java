import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean first=true;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int ans1=0;
			int ans2=0;
			double r=N-0.5;
			for (int i=0;i<N;i++) {
				for (int i2=0;i2<N;i2++) {
					double low=Math.sqrt(i*i+i2*i2);
					double up=Math.sqrt((i+1)*(i+1)+(i2+1)*(i2+1));
					if (r>=low && r>=up) ans2++;
					if (r>=low && r<up) ans1++;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			if (first) first=false;
			else sb.append('\n');
			sb.append("In the case n = ");
			sb.append(N);
			sb.append(", ");
			sb.append(ans1*4);
			sb.append(" cells contain segments of the circle.\nThere are ");
			sb.append(ans2*4);
			sb.append(" cells completely contained in the circle.");
			System.out.println(sb.toString());
		}
	}

}