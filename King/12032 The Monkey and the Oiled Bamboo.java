import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static boolean verifyK(int k, int [] diff, int startIndex) {
		for (int i=startIndex;i<diff.length;i++) {
			if (diff[i]>k) return false;
			else if (diff[i]==k) k--;
		}
		return true;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int [] nums=new int [Integer.parseInt(br.readLine())+1];
			nums[0]=0;
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=1;i<nums.length;i++) nums[i]=Integer.parseInt(st.nextToken());
			int [] diff=new int [nums.length-1];
			int maxDiff=-1;
			int maxIndex=-1;
			for (int i=0;i<nums.length-1;i++) {
				diff[i]=nums[i+1]-nums[i];
				if (diff[i]>maxDiff) maxIndex=i;
				maxDiff=Math.max(diff[i], maxDiff);
			}
			int min=0;
			int max=maxDiff+diff.length+1;

			int mid=0;
			while (min<=max) {
				mid=(min+max)/2;
				if (verifyK(mid,diff,maxIndex)) {
					if (max-min<=1) break;
					else max=mid;
				} else min=mid+1;
			}

			sb.append("Case ");
			sb.append(testCase);
			sb.append(": ");
			sb.append(mid);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}

}