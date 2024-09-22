import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Cabbage {
		long x, y, z;
		
		public Cabbage(long x, long y, long z) {
			this.x=x;
			this.y=y;
			this.z=z;
		}
	}
	
	private static long [][] Coeff= {
		{-1,-1,-1},{-1,-1,1},{-1,1,-1},{-1,1,1},
		{1,-1,-1},{1,-1,1},{1,1,-1},{1,1,1},
	};

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			Cabbage [] c=new Cabbage[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				c[n]=new Cabbage(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()));
			}

			long maxDist=0;
			for (int i=0;i<Coeff.length;i++) {
				long min=Long.MAX_VALUE;
				long max=Long.MIN_VALUE;
				for (int n=0;n<N;n++) {
					long sum=Coeff[i][0]*c[n].x+Coeff[i][1]*c[n].y+Coeff[i][2]*c[n].z;
					min=Math.min(min,sum);
					max=Math.max(max,sum);
				}
				maxDist=Math.max(maxDist,max-min);
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc);
			sb.append(": ");
			sb.append(maxDist);
			System.out.println(sb.toString());
		}
 	}

}
