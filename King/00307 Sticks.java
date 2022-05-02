import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static int [] Lengths;
	private static int SolStickLength;
	private static int SolStickCount;

	private static boolean find(boolean [] used, int usedSticks, int startIdx, int currL) {
		// The last stick will always be valid if reached here. N(last) + N*(M-1)=T
		if (usedSticks>=Lengths.length-1) return true;
		HashSet<Integer> usedNums=new HashSet<>();
		for (int i=startIdx;i<Lengths.length;i++) if (!used[i] && !usedNums.contains(Lengths[i])) {
			int testL=currL+Lengths[i];
			if (testL==SolStickLength) {
				used[i]=true;
				if (find(used,usedSticks+1,0,0)) return true;
				used[i]=false;
				return false;
			} else if (testL<SolStickLength) {
				used[i]=true;
				usedNums.add(Lengths[i]);
				if (find(used,usedSticks+1,i+1,testL)) return true;
				used[i]=false;
			}
			if (currL==0) return false; // No stick.
		}
		return false;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Lengths=new int [N];
			int totalLength=0;
			int minLength=0;
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) {
				Lengths[n]=Integer.parseInt(st.nextToken());
				totalLength+=Lengths[n];
				minLength=Math.max(minLength,Lengths[n]);
			}
			Arrays.sort(Lengths);

			//Reverse array
			for (int i=0;i<N/2;i++) {
				int temp=Lengths[i];
				Lengths[i]=Lengths[N-1-i];
				Lengths[N-1-i]=temp;
			}

			int ans=-1;
			for (SolStickLength=minLength;SolStickLength<=totalLength;SolStickLength++) if (totalLength%SolStickLength==0) {
				SolStickCount=totalLength/SolStickLength;
				if (SolStickCount>N) continue;

				if (find(new boolean [N],0,0,0)) {
					ans=SolStickLength;
					break;
				}
			}
			System.out.println(ans);
		}
	}

}
