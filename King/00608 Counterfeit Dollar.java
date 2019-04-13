import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static class Test {
		char [] left, right;
		int sumDelta; //0=even, 1=up, -1=down
		
		public Test(char [] left, char [] right, int delta) {
			this.left = left;
			this.right = right;
			this.sumDelta = delta;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			boolean [] firstPassLegit=new boolean [128];
			boolean [] exists=new boolean [128];
			Test [] tests=new Test[3];
			for (int i=0;i<3;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine().trim());
				char [] left=st.nextToken().toCharArray();
				char [] right=st.nextToken().toCharArray();
				for (char c : left) exists[c]=true;
				for (char c : right) exists[c]=true;
				int delta=0;
				switch (st.nextToken()) {
					case "up": 		delta=1;
							   		break;
					case "down": 	delta=-1;
									break;
					case "even":	delta=0;
									for (char c : left) firstPassLegit[c]=true;
									for (char c : right) firstPassLegit[c]=true;
									break;
				}
				tests[i]=new Test(left,right,delta);
			}
			char fake='a';
			String fakeText="";
			for (int i='A';i<='L';i++) if (exists[i] && !firstPassLegit[i]) {
				boolean isHeavy=true;
				boolean isLight=true;
				
				for (Test t : tests) {
					int testSum=0;
					for (char c : t.left) testSum+=(c!=i) ? 0 : 1;
					for (char c : t.right) testSum-=(c!=i) ? 0 : 1;
					isHeavy &= testSum==t.sumDelta;
					isLight &= testSum==-t.sumDelta;
					if (!isHeavy && !isLight) break;
				}
				
				if (isHeavy ^ isLight) {
					fake = (char)i;
					fakeText = isHeavy ? "heavy" : "light";
					break;
				}
			}
			
			System.out.println(fake+" is the counterfeit coin and it is "+fakeText+".");
		}
	}

}
