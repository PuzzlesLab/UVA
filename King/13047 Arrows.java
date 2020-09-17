import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static int lr(char [] ch, char wood) {
		int max=0; int size=0; boolean hasArrow=false;
		for (int i=0;i<ch.length;i++) {
			char c=ch[i];
			if (c=='<') {
				size=1;
				max=Math.max(max,size);
				hasArrow=true;
			} else if (c==wood && hasArrow) {
				size++;
				max=Math.max(max,size);
			} else {
				size=0;
				hasArrow=false;
			}
		}
		return max;
	}
	
	public static int rl(char [] ch, char wood) {
		int max=0; int size=0; boolean hasArrow=false;
		for (int i=ch.length-1;i>=0;i--) {
			char c=ch[i];
			if (c=='>') {
				size=1;
				max=Math.max(max,size);
				hasArrow=true;
			} else if (c==wood && hasArrow) {
				size++;
				max=Math.max(max,size);
			} else {
				size=0;
				hasArrow=false;
			}
		}
		return max;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			char [] ch=br.readLine().toCharArray();
			int max=0;
			
			max=Math.max(lr(ch, '-'),max);
			max=Math.max(lr(ch, '='),max);
			max=Math.max(rl(ch, '-'),max);
			max=Math.max(rl(ch, '='),max);

			if (max==0) max=-1;
			System.out.printf("Case %d: %d\n", testCase, max);
		}
		
	}
}