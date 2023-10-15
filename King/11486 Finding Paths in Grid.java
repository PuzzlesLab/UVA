import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [] StatePos=new int [128];
	private static int StatePosCount=0;
	private static int [] ColD={-1,1};
	private static long [][] AdjMat;
	private static final int MOD=1000000007;

	private static long [][] matMulti(long [][] a, long [][] b, long mod) {
		long [][] result=new long [a.length][a.length];
		for (int i=0;i<a.length;i++) for (int k=0;k<a.length;k++) {
			if (a[i][k]==0) continue;
			for (int j=0;j<a.length;j++) {
				result[i][j]+=((a[i][k]%mod)*(b[k][j]%mod))%mod;
				result[i][j]%=mod;
			}
		}
		return result;
	}

	private static long [][] matPow(long [][] base, long p, long mod) {
		long [][] ans=new long [base.length][base.length];
		for (int i=0;i<base.length;i++) ans[i][i]=1;
		while (p>0) {
			if ((p&1)==1) ans=matMulti(ans,base,mod);
			base=matMulti(base,base,mod);
			p>>=1;
		}
		return ans;
	}

	private static void genState(int p, int len, int curr) {
		if (p==4 && len==7) {
			StatePos[curr]=StatePosCount++;
			return;
		}
		if (len>7) return; // Invalid state.

		int next=curr<<1;
		genState(p,len+1,next); // Don't put player
		genState(p+1,len+1,next|1); // Put player
	}

	private static void findAdj(int curr) {
		int [] pos=new int [4];
		int posIdx=0;

		int temp=curr;
		for (int i=0;i<7;i++) {
			if ((temp&1)==1) pos[posIdx++]=i;
			temp>>=1;
		}

		// Every player can only move -1 / +1 col, and can't run into other player.
		for (int p0di=0;p0di<ColD.length;p0di++) {
			int p0=pos[0]+ColD[p0di];
			if (p0<0 || p0>=7) continue;
			for (int p1di=0;p1di<ColD.length;p1di++) {
				int p1=pos[1]+ColD[p1di];
				if (p1<0 || p1>=7 || p0==p1) continue;
				for (int p2di=0;p2di<ColD.length;p2di++) {
					int p2=pos[2]+ColD[p2di];
					if (p2<0 || p2>=7 || p0==p2 || p1==p2) continue;
					for (int p3di=0;p3di<ColD.length;p3di++) {
						int p3=pos[3]+ColD[p3di];
						if (p3<0 || p3>=7 || p0==p3 || p1==p3 || p2==p3) continue;

						int next=(1<<p0)|(1<<p1)|(1<<p2)|(1<<p3);
						AdjMat[StatePos[curr]][StatePos[next]]++;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// 7C4 = 35 states per row!
		Arrays.fill(StatePos,-1);
		genState(0,0,0);
		AdjMat=new long [StatePosCount][StatePosCount];
		for (int i=0;i<StatePos.length;i++) if (StatePos[i]!=-1) findAdj(i);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			int N=Integer.parseInt(br.readLine());
			int row=0;
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<7;i++) {
				int p=Integer.parseInt(st.nextToken());
				if (p>0) row|=(1<<i);
			}

			long [] base=new long [StatePosCount];
			base[StatePos[row]]=1;
			long [][] result=matPow(AdjMat,N-1,MOD);

			long ans=0;
			for (int i=0;i<result.length;i++) {
				for (int i2=0;i2<result.length;i2++) {
					ans+=((result[i][i2]%MOD)*(base[i2]%MOD))%MOD;
					ans%=MOD;
				}
			}
			System.out.println(ans);
		}
	}

}
