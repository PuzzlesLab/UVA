import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static class Block implements Comparable<Block> {
		int left;
		int middle;
		
		public Block(int left, int middle) {
			this.left=left;
			this.middle=middle;
		}
		
		public int compareTo(Block b) {
			if (this.left!=b.left) return this.left-b.left;
			return this.middle-b.middle;
		}
	}

	public static int search(int [] dp, Block [] blocks, int id) {
		if (id==blocks.length) return 0;
		if (dp[id]==0) {
			dp[id]=1;
			for (int i=0;i<id;i++) {
				if (blocks[i].left<=blocks[id].left && blocks[i].middle<=blocks[id].middle) {
					dp[id]=Math.max(dp[id],1+search(dp,blocks,i));
				}
			}
		}
		return dp[id];
	}

	public static void main (String [] args) throws Exception {	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine().trim());
			if (N==0) break;
			Block [] blocks=new Block[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				blocks[n]=new Block(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(blocks);
			
			int [] dp=new int [N];
			int ans=0;
			for (int i=0;i<N;i++) ans=Math.max(ans, search(dp, blocks, i));
			System.out.println(ans);
		}
		System.out.println('*');
	}
}
