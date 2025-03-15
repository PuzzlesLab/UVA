import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class House {
		char c;
		double k;
		
		public House(String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.c=st.nextToken().charAt(0);
			this.k=Integer.parseInt(st.nextToken());
		}
		
		public double area() {
			if (this.c=='S' || this.c=='C') return this.k*this.k;
			return (Math.sqrt(3)/4)*this.k*this.k;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			int N=Integer.parseInt(br.readLine());
			House [] houses=new House [N];
			for (int n=0;n<N;n++) houses[n]=new House(br.readLine());
			
			int [] cornerIdx=new int [4];
			int ci=0;
			for (int n=0;n<N;n++) if (houses[n].c=='C') cornerIdx[ci++]=n;
			
			double width=0;
			for (int n=cornerIdx[0];n<=cornerIdx[1];n++) width+=houses[n].k;
			double height=0;
			for (int n=cornerIdx[1];n<=cornerIdx[2];n++) height+=houses[n].k;
			double sol=width*height;
			
			for (int n=0;n<N;n++) sol-=houses[n].area();
			System.out.printf("%.4f\n",sol);
		}
 	}

}
