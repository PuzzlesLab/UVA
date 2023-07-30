import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int ev1=Integer.parseInt(st.nextToken());
			int ev2=Integer.parseInt(st.nextToken());
			int at=Integer.parseInt(st.nextToken());
			double d=Integer.parseInt(st.nextToken());
			
			// https://en.wikipedia.org/wiki/Gambler's_ruin
			double p1Round=Math.ceil(ev1/d);
			double p2Round=Math.ceil(ev2/d);
			if (at==3) { // Fair chance
				System.out.printf("%.1f\n",(p1Round/(p1Round+p2Round))*100);
			} else {
				double p=at/6.0;
				double q=1-p;
				double ratio=q/p;
				double ans=(1-Math.pow(ratio,p1Round))/(1-Math.pow(ratio,p1Round+p2Round));
				System.out.printf("%.1f\n",ans*100);
			}

		}
	}

}
