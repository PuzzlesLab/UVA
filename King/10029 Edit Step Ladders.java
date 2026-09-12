import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

class Main {

	private static class Node {
		String s;
		char [] c;
		ArrayList<Node> edges;
		int id;

		public Node(int id, String s) {
			this.id=id;
			this.s=s;
			this.c=s.toCharArray();
			this.edges=new ArrayList<>();
		}
	}
	
	private static int [] Dp;

	private static int find(Node n) {
		if (Dp[n.id]==0) {
			int max=1;
			for (int i=0;i<n.edges.size();i++) {
				max=Math.max(max,1+find(n.edges.get(i)));
			}
			Dp[n.id]=max;
		}
		return Dp[n.id];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Node> words=new ArrayList<>();
        String s;
        HashMap<String,Node> nodeMap=new HashMap<>();
        while ((s=br.readLine())!=null) {
        	Node n=new Node(words.size(),s);
        	words.add(n);
        	nodeMap.put(n.s,n);
        }
        
        for (int i=0;i<words.size();i++) {
        	Node n=words.get(i);
        	// Replace
        	for (int i2=0;i2<n.c.length;i2++) {
        		char init=n.c[i2];
            	for (int i3='a';i3<='z';i3++) if (i3!=init) {
            		n.c[i2]=(char)i3;
            		String next=new String(n.c);
            		if (nodeMap.containsKey(next) && next.compareTo(n.s)>0) n.edges.add(nodeMap.get(next));
            	}
            	n.c[i2]=init;
        	}
        	// Add
        	for (int pos=0;pos<=n.c.length;pos++) {
        		StringBuilder left=new StringBuilder();
        		StringBuilder right=new StringBuilder();
        		for (int i2=0;i2<pos;i2++) left.append(n.c[i2]);
        		for (int i2=pos;i2<n.c.length;i2++) right.append(n.c[i2]);

            	for (int i3='a';i3<='z';i3++) {
            		StringBuilder curr=new StringBuilder();
            		curr.append(left);
            		curr.append((char)i3);
            		curr.append(right);

            		String next=curr.toString();
            		if (nodeMap.containsKey(next) && next.compareTo(n.s)>0) n.edges.add(nodeMap.get(next));
            	}
        	}
        	// Remove
        	for (int pos=0;pos<n.c.length;pos++) {
        		StringBuilder curr=new StringBuilder();
        		for (int i2=0;i2<n.c.length;i2++) if (i2!=pos) curr.append(n.c[i2]);
        		String next=curr.toString();
        		if (nodeMap.containsKey(next) && next.compareTo(n.s)>0) n.edges.add(nodeMap.get(next));
        	}
        }

        Dp=new int [words.size()];
        int len=0;
        for (int i=0;i<words.size();i++) len=Math.max(len,find(words.get(i)));
        System.out.println(len);
	}

}