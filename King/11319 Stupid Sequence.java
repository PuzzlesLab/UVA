import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	private static final int LINES=1500;
	private static final int N=7;
	private static final long MOD=2147483647;
	private static final BigInteger MOD_BIG=BigInteger.valueOf(MOD);

	private static double [] gaussElimination(double [][] mat, int size) {
		for (int i=0;i<size-1;i++) {
			int l=i;
			for (int j=i+1;j<size;++j) if (Math.abs(mat[j][i])>Math.abs(mat[l][i])) l=j;
			for (int k=i;k<=size;k++) {
	          double temp=mat[i][k];
	          mat[i][k]=mat[l][k];
	          mat[l][k]=temp;
	        }
			for (int j=i+1;j<size;j++) for (int k=size;k>=i;k--) {
				mat[j][k]-=mat[i][k]*mat[j][i]/mat[i][i];
			}
		}

		double [] ans=new double [size];
		for (int i=size-1;i>=0;--i) {
			double sum=0;
			for (int j=i+1;j<size;++j) sum+=mat[i][j]*ans[j];
			ans[i]=(mat[i][size]-sum)/mat[i][i];
			
			if (Math.abs(ans[i]-Math.floor(ans[i]))>0.001) return null;
			ans[i]=Math.floor(ans[i]);
			if (ans[i]<0 || ans[i]>=1001) return null;
		}
		return ans;
	}
	
	private static long f(double [] a, int x) {
		long ans=0L;
		long xPow=1;
		for (int i=0;i<a.length;i++) {
			ans=(ans+(long)a[i]*xPow)%MOD;
			xPow=(xPow*x)%MOD;
		}
		return ans;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			BigInteger [] nums=new BigInteger [LINES];
			for (int i=0;i<nums.length;i++) nums[i]=new BigInteger(br.readLine());
			
			double [][] mat=new double [N][N+1];
			for (int i=0;i<mat.length;i++) {
				mat[i][0]=1;
				for (int i2=1;i2<mat[i].length-1;i2++) mat[i][i2]=mat[i][i2-1]*(i+1);
			}
			for (int i=0;i<mat.length;i++) mat[i][N]=nums[i].doubleValue();
			
			double [] ans=gaussElimination(mat,N);
			boolean flag=ans!=null;
			for (int i=6;i<LINES && flag;i++) {
				if (f(ans,i+1)!=nums[i].mod(MOD_BIG).longValue()) {
					flag=false;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			if (!flag) sb.append("This is a smart sequence!");
			else {
				for (int i=0;i<ans.length;i++) {
					sb.append((long)Math.floor(ans[i]));
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
			}
			System.out.println(sb);
			
			br.readLine(); // Empty.
		}
 	}

}
