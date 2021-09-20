import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static String solve(int N, int M, int K) {
		int nbound=N-1, mbound=N+M-1;
		int curr=0, currSacr=0, currSize=N+M;
		int [] removeId= {-1,-1};

		while (currSize>0) {
			curr=(curr+K-1)%currSize;
			removeId[currSacr]=(curr<=nbound)?0:1;
			if (curr<=nbound) nbound--;
			mbound--;
			currSize--;
			
			currSacr=(currSacr+1)%2;
			if (currSacr==0) {
				if (removeId[0]==removeId[1]) nbound++;
				mbound++;
				currSize++;
				
				if (currSize==1) {
					return removeId[0]==removeId[1] ? "Gared":"Keka";
				}
			}
		}
		
		return N==0 ? "Keka" : "Gared";
	}
	
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			System.out.println(solve(N,M,K));
		}
	}

}
