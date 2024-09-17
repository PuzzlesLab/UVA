import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Tree {
		double x, y, z;

		public Tree(double x, double y, double z) {
			this.x=x;
			this.y=y;
			this.z=z;
		}
		
		public double distBetween(Tree t) {
			double dx=this.x-t.x;
			double dy=this.y-t.y;
			double dz=this.z-t.z;
			dx*=dx;
			dy*=dy;
			dz*=dz;
			return Math.sqrt(dx+dy+dz);
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Tree> trees=new ArrayList<>();
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int z=Integer.parseInt(st.nextToken());
			if (x==0 && y==0 && z==0) break;
			trees.add(new Tree(x,y,z));
		}

		int [] count=new int [10];
		int [] nearest=new int [trees.size()];
		Arrays.fill(nearest,10);
		for (int i=0;i<trees.size();i++) for (int i2=i+1;i2<trees.size();i2++) {
			int d=(int)Math.floor(trees.get(i).distBetween(trees.get(i2)));
			if (d<count.length) {
				nearest[i]=Math.min(nearest[i],d);
				nearest[i2]=Math.min(nearest[i2],d);
			}
		}
		for (int i=0;i<nearest.length;i++) if (nearest[i]<count.length) count[nearest[i]]++;
		
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<count.length;i++) sb.append(String.format("%4d",count[i]));
		System.out.println(sb);
 	}

}
