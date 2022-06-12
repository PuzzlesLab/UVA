import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main {

	private static class Evidence implements Comparable<Evidence> {
		int score, hour;
		String desc;
		
		public Evidence(String s) {
			StringBuilder sb=new StringBuilder();
			int temp=0;
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if (Character.isDigit(c)) sb.append(c);
				else {
					if (temp==0) this.score=Integer.parseInt(sb.toString());
					else if (temp==1) {
						this.hour=Integer.parseInt(sb.toString());
						this.desc=s.substring(i+1);
						break;
					}
					sb.setLength(0);
					temp++;
				}
			}
		}

		public int compareTo(Evidence t) {
			if (this.hour!=t.hour) return this.hour-t.hour;
			if (this.score!=t.score) return t.score-this.score;
			return this.desc.compareTo(t.desc);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine(); // Useless
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			ArrayList<Evidence> evidList=new ArrayList<>();
			String s;
			while ((s=br.readLine())!=null) {
				if (s.trim().isEmpty()) break;
				Evidence evid=new Evidence(s);
				if (evid.hour<=N) evidList.add(evid); // Only consider evidence fits in the time limit.
			}

			StringBuilder sb=new StringBuilder();
			if (testCase>0) sb.append('\n');
			if (evidList.isEmpty()) sb.append("There is not enough time to present any evidence. Drop the charges.");
			else {
				Evidence [] candidates=evidList.toArray(new Evidence[evidList.size()]);
				int [] dp=new int [N+1];
				boolean [][] used=new boolean [candidates.length+1][N+1];
				
				for (int i=candidates.length-1;i>=0;i--) {
					for (int j=N;j>=candidates[i].hour;j--) {
						if (dp[j-candidates[i].hour]+candidates[i].score>dp[j]) {
							used[i][j]=true;
							dp[j]=dp[j-candidates[i].hour]+candidates[i].score;
						}
					}
				}
				
				ArrayList<Evidence> usedEvid=new ArrayList<>();
				int remTime=N;
				for (int i=0;i<candidates.length;i++) if (used[i][remTime]) {
					usedEvid.add(candidates[i]);
					remTime-=candidates[i].hour;
				}
				Collections.sort(usedEvid);

				int ansScore=0, ansHour=0;

				sb.append("Score  Time  Description\n");
				sb.append("-----  ----  -----------\n");
				for (Evidence evid: usedEvid) {
					sb.append(String.format("%3d", evid.score));
					sb.append("    ");
					sb.append(String.format("%3d", evid.hour));
					sb.append("   ");
					sb.append(evid.desc);
					sb.append('\n');
					
					ansScore+=evid.score;
					ansHour+=evid.hour;
				}

				sb.append("\nTotal score: ");
				sb.append(ansScore);
				sb.append(" points\n");
				sb.append(" Total time: ");
				sb.append(ansHour);
				sb.append(" hours");
			}
			System.out.println(sb.toString());
		}
	}

}