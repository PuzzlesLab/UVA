import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int MaxCustomer=0;
	private static String Solution="";
	
	private static class Area {
		boolean [] tower;
		int customer;
		public Area(int maxT) {
			this.tower=new boolean [maxT];
		}
	}
	
	private static void permutate(int [] towerCustomer, Area [] areas, int [] currTowers, int currTowerCount) {
		if (currTowerCount == currTowers.length) {
			int customers=0;
			for (int tower : currTowers) customers+=towerCustomer[tower];
			for (Area a : areas) {
				for (int tower : currTowers) if (a.tower[tower]) {
					customers+=a.customer;
					break;
				}
			}
			if (customers>MaxCustomer) {
				MaxCustomer=customers;
				StringBuilder sb=new StringBuilder();
				for (int tower : currTowers) {
					sb.append(' ');
					sb.append(tower+1);
				}
				Solution=sb.toString();
			}
		} else {
			int startIndex=currTowerCount>0 ? currTowers[currTowerCount-1]+1 : 0;
			for (int i=startIndex;i<towerCustomer.length;i++) {
				currTowers[currTowerCount]=i;
				permutate(towerCustomer,areas,currTowers,currTowerCount+1);
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] towerCustomer=new int [N];
			for (int n=0;n<N;n++) towerCustomer[n]=Integer.parseInt(st.nextToken());
			
			int M=Integer.parseInt(br.readLine());
			Area [] areas=new Area[M];
			for (int m=0;m<M;m++) {
				areas[m]=new Area(N);
				st=new StringTokenizer(br.readLine());
				int TM=Integer.parseInt(st.nextToken());
				for (int tm=0;tm<TM;tm++) areas[m].tower[Integer.parseInt(st.nextToken())-1]=true;
				areas[m].customer=Integer.parseInt(st.nextToken());
				
				for (int tm=0;tm<areas[m].tower.length;tm++) if (areas[m].tower[tm]) towerCustomer[tm]-=areas[m].customer;
			}
			
			MaxCustomer=0;
			Solution="";
			permutate(towerCustomer,areas,new int [T], 0);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case Number  ");
			sb.append(testCase++);
			sb.append('\n');
			sb.append("Number of Customers: ");
			sb.append(MaxCustomer);
			sb.append('\n');
			sb.append("Locations recommended:");
			sb.append(Solution);
			sb.append('\n');
			System.out.println(sb.toString());
		}
	}

}