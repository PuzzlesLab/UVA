import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public Tuple(Tuple t) {
			this.x=t.x;
			this.y=t.y;
		}
	}

	public static void main(String[] args) throws Exception {
		final int MAX=10000;
		final int [][] deltas= {
			{-1,0}, // Left
			{-1,1}, // Left+Up
			{0,1}, // Up
			{1,0}, // Right
			{1,-1}, //Right+Down
			{0,-1}, // Down
		};
		final int [] sizeDelta= {0,0,0,0,1,0}; //Right needs to be longer by 1 unit.

		Tuple [] position=new Tuple [MAX+1];
		position[1]=new Tuple(0,0);
		position[2]=new Tuple(1,-1);
		int maxSize=1;
		int curr=3;

		while (curr<=MAX) {
			for (int direct=0;direct<6;direct++) {
				for (int size=0;size<maxSize+sizeDelta[direct];size++) {
					position[curr]=new Tuple(position[curr-1]);
					position[curr].x+=deltas[direct][0];
					position[curr].y+=deltas[direct][1];
					curr++;
					if (curr>MAX) break;
				}
				if (curr>MAX) break;
			}
			maxSize++;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			Tuple pA=position[a];
			Tuple pB=position[b];
			int dx=pA.x-pB.x;
			int dy=pA.y-pB.y;
			int ans=(dx<0 ^ dy<0) ? Math.max(Math.abs(dx),Math.abs(dy)) : Math.abs(dx)+Math.abs(dy);
			
			StringBuilder sb=new StringBuilder();
			sb.append("The distance between cells ");
			sb.append(a);
			sb.append(" and ");
			sb.append(b);
			sb.append(" is ");
			sb.append(ans);
			sb.append('.');
			System.out.println(sb.toString());
		}
	}

}