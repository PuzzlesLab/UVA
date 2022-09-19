import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int H;
	private static int K;
	private static int Max;
	private static int [] MaxStamps;
	
	private static void fillSum(int [] choices, int start, int h, int currSum, boolean [] flag) {
		flag[currSum]=true;
		if (h<H) for (int k=start;k<K;k++) fillSum(choices,k,h+1,currSum+choices[k],flag);
	}

	private static void find(int k, int start, int [] stamps) {
		if (k==K) {
			boolean [] flag=new boolean[stamps[k-1]*H+1];
			fillSum(stamps,0,0,0,flag);
			int temp=1;
			for (int i=1;i<flag.length;i++) {
				if (flag[i]) temp=i;
				else break;
			}
			if (temp>Max) {
				Max=temp;
				MaxStamps=stamps.clone();
			}
		} else {
			for (int i=start;i<=K*H*2;i++) {
				stamps[k]=i;
				find(k+1,i,stamps);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			H=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());

			int [] stamps=new int [K];
			stamps[0]=1;
			Max=0;
			MaxStamps=null;
			find(1,2,stamps);
			
			StringBuilder sb=new StringBuilder();
			for (int k=0;k<K;k++) sb.append(String.format("%3d",MaxStamps[k]));
			sb.append(" ->");
			sb.append(String.format("%3d",Max));
			System.out.println(sb.toString());
		}
	}

}
