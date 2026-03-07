import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	public static void main(String [] args) throws Exception {
		int [] Nx=new int [10001];
		int [] Ny=new int [10001];
		Arrays.fill(Nx,-1);
		Arrays.fill(Ny,-1);
		for (int x=2;x*x<Nx.length;x++) {
			for (int y=1;y<x;y++) {
				int N=x*x*x-y*y*y;
				if (N<Nx.length) {
					if (Ny[N]!=-1 && y>Ny[N]) continue;
					Nx[N]=x;
					Ny[N]=y;
				}
			}
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			
			if (Nx[N]==-1) System.out.println("No solution");
			else System.out.printf("%d %d\n",Nx[N],Ny[N]);
		}
	}

}