import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			
			int xMin=Integer.MIN_VALUE>>1;
			int xMax=Integer.MAX_VALUE>>1;
			int yMin=Integer.MIN_VALUE>>1;
			int yMax=Integer.MAX_VALUE>>1;
			int zMin=Integer.MIN_VALUE>>1;
			int zMax=Integer.MAX_VALUE>>1;

			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int z=Integer.parseInt(st.nextToken());
				int e=Integer.parseInt(st.nextToken());
				
				xMin=Math.max(xMin,x);
				xMax=Math.min(xMax,x+e);
				yMin=Math.max(yMin,y);
				yMax=Math.min(yMax,y+e);
				zMin=Math.max(zMin,z);
				zMax=Math.min(zMax,z+e);
			}

			int dx=xMax-xMin;
			int dy=yMax-yMin;
			int dz=zMax-zMin;
			System.out.println((dx>0 && dy>0 && dz>0) ? dx*dy*dz : 0);
		}
	}

}
