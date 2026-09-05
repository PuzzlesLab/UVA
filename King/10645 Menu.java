import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static class Solution implements Comparable<Solution> {
		double benefit;
		int cost, d;
		int parRemD, parRemM, parPD, parPDC;
		
		public Solution(double b, int c, int d) {
			this.benefit=b;
			this.cost=c;
			this.d=d;
		}
		
		public Solution(Solution s) {
			this.benefit=s.benefit;
			this.cost=s.cost;
			this.d=s.d;
			this.setParent(s.parRemD, s.parRemM, s.parPD, s.parPDC);
		}

		public void setParent(int remD, int remM, int pD, int pPDC) {
			this.parRemD=remD;
			this.parRemM=remM;
			this.parPD=pD;
			this.parPDC=pPDC;
		}

		public int compareTo(Solution s) {
			if (this.benefit!=s.benefit) return Double.compare(this.benefit,s.benefit);
			return s.cost-this.cost;
		}
	}

	private static int [] DishCost;
	private static int [] DishBenefit;
	private static Solution [][][][] Dp;

	private static Solution compute(int remDay, int remMoney, int pDish, int pDishC) {
		if (remDay==0) return new Solution(0,0,0);

		if (Dp[remDay][remMoney][pDish][pDishC]==null) {
			Solution ans=new Solution(-100000000.0,0,0);
			for (int n=1;n<DishCost.length;n++) {
				if (remMoney<DishCost[n]) continue;

				double benefit=DishBenefit[n];
				int npDishC=pDish!=n?1:pDishC+1;
				if (npDishC==2) benefit/=2.0;
				else if (npDishC>2) benefit=0.0;

				Solution currAns=compute(remDay-1,remMoney-DishCost[n],n,npDishC);
				currAns.d=n;
				currAns.benefit+=benefit;
				currAns.cost+=DishCost[n];

				if (currAns.compareTo(ans)>0) {
					ans=currAns;
					ans.setParent(remDay-1,remMoney-DishCost[n],n,npDishC);
				}
			}
			Dp[remDay][remMoney][pDish][pDishC]=ans;
		}

		return new Solution(Dp[remDay][remMoney][pDish][pDishC]);
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=br.readLine()).equals("0 0 0")) {
        	StringTokenizer st=new StringTokenizer(s);
        	int K=Integer.parseInt(st.nextToken());
        	int N=Integer.parseInt(st.nextToken())+1;
        	int M=Integer.parseInt(st.nextToken());

        	DishCost=new int [N];
        	DishBenefit=new int [N];
        	for (int n=1;n<DishCost.length;n++) {
        		st=new StringTokenizer(br.readLine());
        		DishCost[n]=Integer.parseInt(st.nextToken());
        		DishBenefit[n]=Integer.parseInt(st.nextToken());
        	}

        	Dp=new Solution[K+1][M+1][N+1][K+1];
        	Solution ans=compute(K,M,1,0);
        	if (ans.benefit<0) System.out.println("0.0\n");
        	else {
        		StringBuilder sb=new StringBuilder();
        		sb.append(String.format("%.1f\n",ans.benefit));
        		Solution temp=ans;
        		while (temp!=null) {
        			sb.append(temp.d);
        			sb.append(' ');
        			temp=Dp[temp.parRemD][temp.parRemM][temp.parPD][temp.parPDC];
        		}
        		sb.setLength(sb.length()-1);
            	System.out.println(sb);
        	}
        }
	}

}