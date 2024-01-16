import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	// Original problem statement is broken, mirror : https://vj.csgrandeur.cn/54be2e3f5311b42293c0c3b3f93dfc03?v=1705353933

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		final long [] fib=new long [45];
		fib[0]=1;
		fib[1]=2;
		for (int i=2;i<fib.length;i++) fib[i]=fib[i-2]+fib[i-1];
		HashMap<Integer,Integer> fibPos=new HashMap<>();
		for (int i=0;i<fib.length;i++) fibPos.put((int)fib[i],i);

		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int [] nums=new int [Integer.parseInt(br.readLine())];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=0;n<nums.length;n++) nums[n]=Integer.parseInt(st.nextToken());

			String s=br.readLine();
			ArrayList<Character> chList=new ArrayList<>();
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if (c>='A' && c<='Z') chList.add(c);
			}

			char [] ans=new char [100];
			Arrays.fill(ans,' ');
			for (int n=0;n<nums.length;n++) ans[fibPos.get(nums[n])]=chList.get(n);
			int len=ans.length;
			while (len>0) {
				if (ans[len-1]!=' ') break;
				len--;
			}

			StringBuilder sb=new StringBuilder();
			for (int i=0;i<len;i++) sb.append(ans[i]);
			System.out.println(sb);
		}
	}
	  
}