import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static int END_MASK;
	private static long [][][] Dp;
	private static int NodeMax;
	private static HashMap<Integer,Node> NodeMap;
	
	private static class Node {
		int id;
		Node [] next;
		int mask;
		Node fail;
		
		public Node() {
			this.id=NodeMax;
			NodeMap.put(this.id,this);
			NodeMax++;

			this.next=new Node[26];
		}

		public void add(String s, int m) {
			Node curr=this;
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if (curr.next[c-'a']==null) {
					curr.next[c-'a']=new Node();
				}
				curr=curr.next[c-'a'];
			}
			curr.mask|=1<<m;
		}
		
		public void makeFail() {
			LinkedList<Node> q=new LinkedList<>();
			for (int i=0;i<this.next.length;i++) {
				if (this.next[i]!=null) {
					this.next[i].fail=this;
					q.addLast(this.next[i]);
				} else this.next[i]=this;
			}

			while (!q.isEmpty()) {
				Node curr=q.removeFirst();
				for (int i=0;i<curr.next.length;i++) {
					Node n=curr.next[i];
					if (n==null) curr.next[i]=curr.fail.next[i];
					else {
						n.fail=curr.fail.next[i];
						n.mask|=n.fail.mask;
						q.addLast(n);
					}
				}
			}
		}

	}

	// AC automata
	private static long compute(Node n, int len, int mMask) {
		if (Dp[n.id][len][mMask]==-1) {
			long ans=0;
			if (len==N) ans=(mMask==END_MASK)?1:0;
			else for (int i=0;i<n.next.length;i++) ans+=compute(n.next[i],len+1,mMask|n.next[i].mask);
			Dp[n.id][len][mMask]=ans;
		}
		return Dp[n.id][len][mMask];
	}

	private static void dump(Node n, int len, int mMask, StringBuilder sb, char [] currS) {
		if (len==N) {
			sb.append(new String(currS));
			sb.append('\n');
			return;
		}
		
		for (int i=0;i<n.next.length;i++) if (Dp[n.next[i].id][len+1][mMask|n.next[i].mask]>0) {
			currS[len]=(char)(i+'a');
			dump(n.next[i],len+1,mMask|n.next[i].mask,sb,currS);
		}
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        int tc=1;
        while (!(s=br.readLine()).equals("0 0")) {
        	StringTokenizer st=new StringTokenizer(s);
        	N=Integer.parseInt(st.nextToken());
        	int M=Integer.parseInt(st.nextToken());
        	
        	NodeMap=new HashMap<>();
        	NodeMax=0;
        	Node root=new Node();
        	for (int m=0;m<M;m++) root.add(br.readLine(),m);
        	root.makeFail();

        	END_MASK=(1<<M)-1;
        	Dp=new long [NodeMax][N+1][END_MASK+2];
        	for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++) Arrays.fill(Dp[i][i2],-1);

        	long ans=compute(root,0,0);
        	StringBuilder sb=new StringBuilder();
        	sb.append("Case ");
        	sb.append(tc++);
        	sb.append(": ");
        	sb.append(ans);
        	sb.append(" suspects\n");
        	if (ans<=42) {
        		dump(root,0,0,sb,new char [N]);
        	}
        	System.out.print(sb.toString());
        }
	}

}