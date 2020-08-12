import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Main {
	
	private static HashMap<String, Character> map=new HashMap<>();
	private static String startStop="00110";
	
	private static void solve(Scanner sc, int M, int testCase) {
		int [] data=new int [M];
		for (int m=0;m<M;m++) data[m]=sc.nextInt();

		if (M%6!=5 || (M+1)/6<5) {
			System.out.printf("Case %d: bad code\n", testCase);
			return;
		}
		
		double avg=0.0;
		double min=Integer.MAX_VALUE;
		double max=Integer.MIN_VALUE;
		for (int width: data) {
			min=Math.min(width, min);
			max=Math.max(width, max);
		}
		avg=(min+max)/2;
		
		int maxMin=Integer.MAX_VALUE;
		int maxMax=Integer.MIN_VALUE;
		for (int width: data) {
			int v=0;
			if (width<avg) v=width*2;
			else v=width;
			maxMin=Math.min(maxMin,v);
			maxMax=Math.max(maxMax,v);
		}
		if (maxMax*95>maxMin*105) {
			System.out.printf("Case %d: bad code\n", testCase);
			return;
		}
		
		char [] c=new char [M];
		for (int m=0;m<M;m++) c[m]=(data[m]>=avg) ? '1' : '0';
		
		ArrayList<String> forward=new ArrayList<>();
		for (int start=0;start<M;start+=6) {
			StringBuilder sb=new StringBuilder();
			for (int x=0;x<5;x++) sb.append(c[start+x]);
			if (start+5<M && c[start+5]!='0') {
				System.out.printf("Case %d: bad code\n", testCase);
				return;
			}
			forward.add(sb.toString());
		}
		
		ArrayList<String> backward=new ArrayList<>();
		for (int start=M-1;start>0;start-=6) {
			StringBuilder sb=new StringBuilder();
			for (int x=0;x<5;x++) sb.append(c[start-x]);
			if (start-5>=0 && c[start-5]!='0') {
				System.out.printf("Case %d: bad code\n", testCase);
				return;
			}
			backward.add(sb.toString());
		}
		
		List<String> tested=null;
		if (forward.get(0).equals(startStop) && forward.get(forward.size()-1).equals(startStop)) tested=forward;
		else if (backward.get(0).equals(startStop) && backward.get(backward.size()-1).equals(startStop)) tested=backward;
		else {
			System.out.printf("Case %d: bad code\n", testCase);
			return;
		}
		
		boolean badCode=tested.size()<=2;
		for (String s : tested) badCode|=!map.containsKey(s);
		if (badCode) {
			System.out.printf("Case %d: bad code\n", testCase);
			return;
		}
		
		ArrayList<Character> chars=new ArrayList<>();
		for (int i=1;i<tested.size()-3;i++) chars.add(map.get(tested.get(i)));
		char C=map.get(tested.get(tested.size()-3));

		int calculatedC=0;
		for (int i=0;i<chars.size();i++) {
			int ci=i+1;
			int temp=(((chars.size()-ci)%10)+1);
			if (chars.get(i)=='-') temp*=10;
			else temp*=(chars.get(i)-'0');
			calculatedC+=temp;
		}
		calculatedC%=11;
		char expectedC=(char)((calculatedC==10) ? '-' : calculatedC+'0');
		
		if (C!=expectedC) {
			System.out.printf("Case %d: bad C\n", testCase);
			return;
		}
		
		chars.add(C);
		char K=map.get(tested.get(tested.size()-2));
		int calculatedK=0;
		for (int i=0;i<chars.size();i++) {
			int ci=i+1;
			int temp=(((chars.size()-ci)%9)+1);
			if (chars.get(i)=='-') temp*=10;
			else temp*=(chars.get(i)-'0');
			calculatedK+=temp;
		}
		calculatedK%=11;
		char expectedK=(char)((calculatedK==10) ? '-' : calculatedK+'0');
		
		if (K!=expectedK) {
			System.out.printf("Case %d: bad K\n", testCase);
			return;
		} else {
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<chars.size()-1;i++) sb.append(chars.get(i));
			System.out.printf("Case %d: %s\n", testCase, sb.toString());
		}
	}
	
	public static void main (String [] args) throws Exception {
		map.put("00001", '0');
		map.put("10001", '1');
		map.put("01001", '2');
		map.put("11000", '3');
		map.put("00101", '4');
		map.put("10100", '5');
		map.put("01100", '6');
		map.put("00011", '7');
		map.put("10010", '8');
		map.put("10000", '9');
		map.put("00100", '-');
		map.put("00110", 'S');
		
		Scanner sc=new Scanner(System.in);
		int testCase=1;
		while (true) {
			int M=sc.nextInt();
			if (M==0) break;
			solve(sc, M, testCase++);
		}
	}

}