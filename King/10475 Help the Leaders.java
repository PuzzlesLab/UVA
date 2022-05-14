import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	// global locals
	private static String [] topics;
	private static boolean [] used;
	private static String [] currAns;
	private static HashMap<String, HashSet<String>> blockedPairs;
	private static HashMap<String, Integer> currBlocked;
	private static ArrayList<String> ans;
	
	private static void find(int topicStart, int ansIdx) {
		if (ansIdx==currAns.length) {
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<currAns.length;i++) {
				sb.append(currAns[i]);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			ans.add(sb.toString());
		} else {
			for (int i=topicStart;i<topics.length;i++) if (!used[i] && !currBlocked.containsKey(topics[i])) {
				currAns[ansIdx]=topics[i];
				used[i]=true;
				if (blockedPairs.containsKey(topics[i])) {
					for (String toBlock: blockedPairs.get(topics[i])) {
						currBlocked.put(toBlock,currBlocked.getOrDefault(toBlock,0)+1);
					}
				}
				find(i+1,ansIdx+1);
				if (blockedPairs.containsKey(topics[i])) {
					for (String toBlock: blockedPairs.get(topics[i])) {
						int count=currBlocked.get(toBlock);
						if (count==1) currBlocked.remove(toBlock);
						else currBlocked.put(toBlock,count-1);
					}
				}
				used[i]=false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int T=Integer.parseInt(st.nextToken());
			int P=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			topics=new String[T];
			blockedPairs=new HashMap<>();
			for (int t=0;t<T;t++) topics[t]=br.readLine().toUpperCase();
			for (int p=0;p<P;p++) {
				st=new StringTokenizer(br.readLine());
				String s1=st.nextToken().toUpperCase();
				String s2=st.nextToken().toUpperCase();
				if (!blockedPairs.containsKey(s1)) blockedPairs.put(s1,new HashSet<>());
				blockedPairs.get(s1).add(s2);
				if (!blockedPairs.containsKey(s2)) blockedPairs.put(s2,new HashSet<>());
				blockedPairs.get(s2).add(s1);
			}
			Arrays.sort(topics,(a,b) -> {
				int diff=b.length()-a.length();
				if (diff!=0) return diff;
				else return a.compareTo(b);
			});
			
			used=new boolean[T];
			currAns=new String[S];
			currBlocked=new HashMap<>();
			ans=new ArrayList<>();
			find(0,0);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Set ");
			sb.append(testCase);
			sb.append(":\n");
			for (String combi: ans) {
				sb.append(combi);
				sb.append('\n');
			}
			System.out.println(sb.toString());
		}
	}

}