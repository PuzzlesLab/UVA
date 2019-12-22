import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	public static int [][] cornerCoor={{0,0},{0,4},{4,0},{4,4}};
	public static int [][] midLineCoor={{2,0},{2,1},{2,2},{2,3},{2,4}};
	public static int [][] diagCoor={{0,0},{1,1},{2,2},{3,3},{4,4},{0,4},{1,3},{3,1},{4,0}};
	
	public static boolean [][] isCornerCoor=new boolean [5][5];
	public static boolean [][] isMidLineCoor=new boolean [5][5];
	public static boolean [][] isDiagCoor=new boolean [5][5];
	
	public static void initCoors() {
		for (int [] c : cornerCoor) isCornerCoor[c[0]][c[1]]=true;
		for (int [] c : midLineCoor) isMidLineCoor[c[0]][c[1]]=true;
		for (int [] c : diagCoor) isDiagCoor[c[0]][c[1]]=true;
	}
	
	public static void main (String [] args) throws Exception {
		initCoors();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int t=1;t<=T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int L=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] balls=new int [N];
			for (int n=0;n<N;n++) balls[n]=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] earns= {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			if (t>1) sb.append('\n');
			sb.append("Case ");
			sb.append(t);
			sb.append(":\n");
			for (int l=0;l<L;l++) {
				HashMap<Integer, int []> map=new HashMap<>();
				int [][] tickets=new int [5][5];
				for (int i=0;i<tickets.length;i++) {
					st=new StringTokenizer(br.readLine());
					for (int i2=0;i2<tickets[i].length;i2++) {
						int b=Integer.parseInt(st.nextToken());
						map.put(b, new int [] {i, i2});
						tickets[i][i2]=b;
					}
				}
				
				int earn=0;
				boolean [][] flag=new boolean [5][5];
				int [] earnFulfilled=new int [4];
				for (int i=0;i<balls.length;i++) {
					int b=balls[i];
					if (map.containsKey(b)) {
						int [] coo=map.get(b);
						int x=coo[0];
						int y=coo[1];
						
						if (!flag[x][y]) {
							flag[x][y]=true;

							if (isCornerCoor[x][y] && i<35) earnFulfilled[0]++;
							if (isMidLineCoor[x][y] && i<40) earnFulfilled[1]++;
							if (isDiagCoor[x][y] && i<45) earnFulfilled[2]++;
							earnFulfilled[3]++;
						}
					}
				}
				
				int [] lengths= {cornerCoor.length, midLineCoor.length, diagCoor.length, 25};
				for (int i=0;i<earns.length;i++) if (earnFulfilled[i]==lengths[i]) earn+=earns[i];

				sb.append(earn);
				sb.append('\n');
			}

		}
		System.out.print(sb.toString());
	}

}