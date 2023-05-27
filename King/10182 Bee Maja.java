import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX_N=100001;
		int [] ax=new int [MAX_N];
		int [] ay=new int [MAX_N];

		int x=0, y=0;
		int layer=1;
		int direction=0;
		int rem=1;
		// DOWN -> DOWN-LEFT (SIZE-1) -> LEFT -> UP -> UP-LEFT -> RIGHT (AND SIZE++)
		for (int n=2;n<MAX_N;n++) {
			while (rem==0) { // Handle first layer DOWN-LEFT size==0
				direction=(direction+1)%6;
				if (direction==0) layer++;
				rem=layer;
				if (direction==1) rem=layer-1;
			}

			if (direction==0) y++;
			else if (direction==1) {
				x--;
				y++;
			}
			else if (direction==2) x--;
			else if (direction==3) y--;
			else if (direction==4) {
				x++;
				y--;
			}
			else if (direction==5) x++;

			ax[n]=x;
			ay[n]=y;
			rem--;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			System.out.printf("%d %d\n",ax[N],ay[N]);
		}
	}

}
