import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("E")) {
			StringTokenizer st=new StringTokenizer(s);
			double T=0.0, D=0.0, H=0.0;
			boolean hasT=false, hasD=false, hasH=false;
			char c1=st.nextToken().charAt(0);
			if (c1=='D') {
				D=Double.parseDouble(st.nextToken());
				hasD=true;
			}
			else if (c1=='H') {
				H=Double.parseDouble(st.nextToken());
				hasH=true;
			}
			else if (c1=='T') {
				T=Double.parseDouble(st.nextToken());
				hasT=true;
			}

			char c2=st.nextToken().charAt(0);
			if (c2=='D') {
				D=Double.parseDouble(st.nextToken());
				hasD=true;
			}
			else if (c2=='H') {
				H=Double.parseDouble(st.nextToken());
				hasH=true;
			}
			else if (c2=='T') {
				T=Double.parseDouble(st.nextToken());
				hasT=true;
			}

			if (!hasH) {
				double e=6.11*Math.exp(5417.753*((1/273.16)-(1/(D+273.16))));
				double h=0.5555*(e-10.0);
				H=T+h;
			} else if (!hasD) {
				double e=((H-T)/0.5555)+10;
				double e2=Math.log(e/6.11);
				double e3=e2/5417.753;
				double e4=(1/273.16)-e3;
				D=1/e4 - 273.16;
			} else if (!hasT) {
				double e=6.11*Math.exp(5417.753*((1/273.16)-(1/(D+273.16))));
				double h=0.5555*(e-10.0);
				T=H-h;
			}

			System.out.printf("T %.1f D %.1f H %.1f\n",T,D,H);
		}
	}

}
