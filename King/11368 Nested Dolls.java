import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Doll implements Comparable<Doll> {
		int w, h;

		public int compareTo(Doll d) {
			if (this.h==d.h) return this.w-d.w;
			return d.h-this.h;
		}
		
		public String toString() {
			return String.format("%d:%d",this.w,this.h);
		}
	}

	private static int idxGreater(int [] seq, int size, int w) {
		int min=0;
		int max=size-1;
		while (min<max) {
			int mid=(min+max)/2;
			if (w>=seq[mid]) min=mid+1;
			else max=mid;
		}
		return min;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int M=Integer.parseInt(br.readLine());
			Doll [] dolls=new Doll[M];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int m=0;m<M;m++) {
				dolls[m]=new Doll();
				dolls[m].w=Integer.parseInt(st.nextToken());
				dolls[m].h=Integer.parseInt(st.nextToken());
			}
			Arrays.sort(dolls);

			int [] seq=new int [M];
			int seqSize=0;
			seq[seqSize++]=dolls[0].w;
			for (int m=1;m<M;m++) { // O(N^2) classic DP seems too slow for this. Use greedy+binary search instead.
				int idx=idxGreater(seq,seqSize,dolls[m].w);
				if (seq[idx]>dolls[m].w) seq[idx]=dolls[m].w;
				else seq[seqSize++]=dolls[m].w;
			}
			System.out.println(seqSize);
		}
	}

}
