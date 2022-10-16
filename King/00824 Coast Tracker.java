import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int [][] Positions={{0,1},{0,0},{1,0},{2,0},{2,1},{2,2},{1,2},{0,2}};

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("-1")) {
			boolean [][] land=new boolean [3][3];

			StringTokenizer st=new StringTokenizer(s);
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());

			for (int i=0;i<8;i++) {
				st=new StringTokenizer(br.readLine());
				int xi=Integer.parseInt(st.nextToken())-x+1;
				int yi=2-(Integer.parseInt(st.nextToken())-y+1);
				 // The proper position is rotated anti-clockwise from given. (Refer to sample input vs question's figure 3)
				land[yi][xi]=Integer.parseInt(st.nextToken())==1;
			}

			//for (int i=0;i<3;i++) System.out.println(Arrays.toString(land[i]));
			int ans=-1;
			for (int dd=5;dd<13;dd++) {
				int nd=(d+dd)%8;
				int nx=Positions[nd][0];
				int ny=Positions[nd][1];
				if (land[nx][ny]) {
					ans=nd;
					break;
				}
			}
			System.out.println(ans);
		}
	}

}
