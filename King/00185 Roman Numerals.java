import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {
	
	private static TreeMap<Integer, String> valuesMap=new TreeMap<>(Collections.reverseOrder());
	private static void initialize() {
		valuesMap.put(1,"I");
		valuesMap.put(4,"IV");
		valuesMap.put(5,"V");
		valuesMap.put(9,"IX");
		valuesMap.put(10,"X");
		valuesMap.put(40,"XL");
		valuesMap.put(50,"L");
		valuesMap.put(90,"XC");
		valuesMap.put(100,"C");
		valuesMap.put(400,"CD");
		valuesMap.put(500,"D");
		valuesMap.put(900,"CM");
		valuesMap.put(1000,"M");
	}
	
	private static int romanToInt(String s) {
		int idx=0;
		int sum=0;
		while (idx<s.length()) {
			for (int v : valuesMap.keySet()) {
				String test=valuesMap.get(v);
				if (idx+test.length()<=s.length()) {
					String test2=s.substring(idx,idx+test.length());
					if (test.equals(test2)) {
						sum+=v;
						idx+=test.length();
						break;
					}
				}
			}
		}
		return sum;
	}
	
	private static String Op1, Op2, Op3;
	private static int Count=0;
	private static int digitCount(int n) {
		int count=0;
		while (n>0) {
			count++;
			n/=10;
		}
		return count;
	}
	
	public static void countCombi(char [] charList, int charListLevel, boolean [] flag, int [] charValue) {
		if (charListLevel==charList.length) {
			int op1v=0;
			for (char c : Op1.toCharArray()) op1v=op1v*10+charValue[c];
			if (digitCount(op1v)!=Op1.length()) return;
			int op2v=0;
			for (char c : Op2.toCharArray()) op2v=op2v*10+charValue[c];
			if (digitCount(op2v)!=Op2.length()) return;
			int op3v=0;
			for (char c : Op3.toCharArray()) op3v=op3v*10+charValue[c];
			if (digitCount(op3v)!=Op3.length()) return;
			if (op1v+op2v==op3v) {
				Count++;
			}
		} else {
			for (int i=0;i<10;i++) if (!flag[i]) {
				charValue[charList[charListLevel]]=i;
				flag[i]=true;
				countCombi(charList,charListLevel+1,flag,charValue);
				flag[i]=false;
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		initialize();
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) {
			StringTokenizer st=new StringTokenizer(s,"+");
			String op1=st.nextToken();
			st=new StringTokenizer(st.nextToken(),"=");
			String op2=st.nextToken();
			String op3=st.nextToken();

			int op1v=romanToInt(op1);
			int op2v=romanToInt(op2);
			int op3v=romanToInt(op3);

			String ans1=(op1v+op2v==op3v) ? "Correct" : "Incorrect";
			
			HashSet<Character> charSet=new HashSet<>();
			for (char c : op1.toCharArray()) charSet.add(c);
			for (char c : op2.toCharArray()) charSet.add(c);
			for (char c : op3.toCharArray()) charSet.add(c);
			char [] charList=new char[charSet.size()];
			int count=0;
			for (char c : charSet) charList[count++]=c;
			
			Op1=op1; Op2=op2; Op3=op3; Count=0;
			countCombi(charList, 0, new boolean [10], new int[128]);
			
			String ans2="";
			if (Count==0) ans2="impossible";
			else if (Count==1) ans2="valid";
			else ans2="ambiguous";
			System.out.printf("%s %s\n", ans1, ans2);
		}
	}
}