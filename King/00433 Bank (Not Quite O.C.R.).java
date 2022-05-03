import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final char [][] ValidDigits = {
		" _ | ||_|".toCharArray(),
		"     |  |".toCharArray(),
		" _  _||_ ".toCharArray(),
		" _  _| _|".toCharArray(),
		"   |_|  |".toCharArray(),
		" _ |_  _|".toCharArray(),
		" _ |_ |_|".toCharArray(),
		" _   |  |".toCharArray(),
		" _ |_||_|".toCharArray(),
		" _ |_| _|".toCharArray(),
	};
	private static int Count=0;
	private static String Ans="";

	private static boolean missingFrom(char [] ori, char [] actual) {
		boolean flag=false;
		for (int i=0;i<ori.length;i++) {
			if (ori[i]==' ' && actual[i]!=' ') return false;
			else if (ori[i]!=' ' && actual[i]!=' ' && actual[i]!=ori[i]) return false;
			else if (ori[i]!=' ' && actual[i]==' ') flag=true;
		}
		return flag;
	}

	private static boolean match(char [] ori, char [] actual) {
		for (int i=0;i<ori.length;i++) if (ori[i]!=actual[i]) return false;
		return true;
	}

	private static void find(char [][] digits, int [] assumed, int curr, int errorCount) {
		if (curr==assumed.length) {
			int checksum=0;
			for (int i=0;i<assumed.length;i++) checksum+=((assumed.length-i)*assumed[i]);
			if (checksum%11!=0) return;
					
			Count++;
			if (Count!=1) return;

			StringBuilder sb=new StringBuilder();
			for (int i=0;i<assumed.length;i++) sb.append(assumed[i]);
			Ans=sb.toString();
			return;
		}
		
		for (int test=0;test<ValidDigits.length;test++) {
			if (match(ValidDigits[test],digits[curr])) {
				assumed[curr]=test;
				find(digits,assumed,curr+1,errorCount);
			}
			
			if (errorCount==0 && missingFrom(ValidDigits[test],digits[curr])) {
				assumed[curr]=test;
				find(digits,assumed,curr+1,errorCount+1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			char [][] digits=new char [9][9];
			for (int i=0;i<digits.length;i++) digits[i]=new char[9];

			int [] lengths=new int [9];
			for (int x=0;x<3;x++) {
				char [] line=br.readLine().toCharArray();
				for (int y=0;y<line.length;y++) {
					int nth=y/3;
					digits[nth][lengths[nth]++]=line[y];
				}
			}

			Count=0;
			Ans="";
			find(digits,new int [9],0,0);
			
			if (Count==0) Ans="failure";
			else if (Count>1) Ans="ambiguous";
			
			System.out.println(Ans);
		}
	}

}
