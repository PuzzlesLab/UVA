import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static int [][] Dp;
	private static boolean [][] Multi;

	private static int compute(int n, int flag) {
		if (Dp[n][flag]==-1) {
			int ans=flag;
			boolean mul=false;
			for (int i=0;i<AdjList[n].size();i++) {
				int next=AdjList[n].get(i);

				if (flag==0) {
					int left=compute(next,0);
					int right=compute(next,1);
					ans+=Math.max(left,right);
					
					if (left==right) mul=true;
					else if (left>right) mul|=Multi[next][0];
					else if (right>left) mul|=Multi[next][1];
				} else {
					ans+=compute(next,0);
					mul|=Multi[next][0];
				}
			}
			Multi[n][flag]=mul;
			Dp[n][flag]=ans;
		}
		return Dp[n][flag];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=br.readLine()).equals("0")) {
        	int N=Integer.parseInt(s);
        	HashMap<String,Integer> nameMap=new HashMap<>();
        	int nameIdx=0;
        	nameMap.put(br.readLine(),nameIdx++);
        	
        	String [][] relations=new String [N-1][2];
        	for (int n=0;n<N-1;n++) {
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		relations[n][0]=st.nextToken();
        		relations[n][1]=st.nextToken();
        		
        		if (!nameMap.containsKey(relations[n][0])) nameMap.put(relations[n][0],nameIdx++);
        		if (!nameMap.containsKey(relations[n][1])) nameMap.put(relations[n][1],nameIdx++);
        	}

        	AdjList=new ArrayList [N];
        	for (int n=0;n<N;n++) AdjList[n]=new ArrayList<>();
        	for (int n=0;n<relations.length;n++) {
        		AdjList[nameMap.get(relations[n][1])].add(nameMap.get(relations[n][0]));
        	}
        	
        	Dp=new int [N][2];
        	for (int n=0;n<N;n++) Arrays.fill(Dp[n],-1);
        	Multi=new boolean [N][2];
        	
        	int ans1=compute(0,0);
        	int ans2=compute(0,1);
        	boolean mul=true;
        	if (ans1>ans2) mul=Multi[0][0];
        	else if (ans1<ans2) mul=Multi[0][1];

        	System.out.printf("%d %s\n",Math.max(ans1,ans2),mul?"No":"Yes");
        }
	}

}