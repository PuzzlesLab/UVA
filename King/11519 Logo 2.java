import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		double x, y;

		public Tuple(double x, double y) {
			this.x=x;
			this.y=y;
		}
	}
	
	private static class Step {
		char c;
		int v;
	}
	
	private static Tuple getPoint(Step [] steps) {
		Tuple t=new Tuple(0,0);
		double deg=0.0;
		
		for (int n=0;n<steps.length;n++) {
			if (steps[n].c=='f') {
				t.x+=steps[n].v*Math.cos(Math.toRadians(deg));
				t.y+=steps[n].v*Math.sin(Math.toRadians(deg));
			} else if (steps[n].c=='b') {
				t.x-=steps[n].v*Math.cos(Math.toRadians(deg));
				t.y-=steps[n].v*Math.sin(Math.toRadians(deg));
			} else if (steps[n].c=='l') deg+=steps[n].v;
			else if (steps[n].c=='r') deg-=steps[n].v;
		}
		
		return t;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			Step [] steps=new Step[N];
			Step qStep=null;
			for (int n=0;n<steps.length;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				steps[n]=new Step();
				steps[n].c=st.nextToken().charAt(0);
				String s=st.nextToken();
				if (s.charAt(0)=='?') qStep=steps[n];
				else steps[n].v=Integer.parseInt(s);
			}
			
			if (qStep.c=='f' || qStep.c=='b') {
				Tuple t=getPoint(steps);
				System.out.println((int)(Math.hypot(t.x,t.y)+0.01));
			} else {
				for (int testDeg=0;testDeg<360;testDeg++) {
					qStep.v=testDeg;
					Tuple t=getPoint(steps);
					if (Math.abs(t.x)<0.01 && Math.abs(t.y)<0.01) {
						System.out.println(testDeg);
						break;
					}
				}
			}
		}
 	}
}
