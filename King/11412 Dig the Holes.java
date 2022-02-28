import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static class Context {
		char [] guess;
		int n1, n2;
	}

	public static int [] calc(boolean [] existsColor, char [] guess, char [] real) {
		int n1=0;
		int n2=0;
		for (int i=0;i<4;i++) {
			if (guess[i]==real[i]) {
				n1++;
			} else if (guess[i]!=real[i] && existsColor[guess[i]]) {
				n2++;
			}
		}
		return new int [] {n1, n2};
	}
	
	private static boolean permutate(char [] colors, boolean [] existsColor, char [] curr, int currSize, Context [] context) {
		if (currSize==curr.length) {
			boolean flag=true;
			for (int i=0;i<context.length;i++) {
				int [] temp=calc(existsColor,context[i].guess,curr);
				flag&=context[i].n1==temp[0] && context[i].n2==temp[1];
			}
			return flag;
		} else {
			for (int i=0;i<colors.length;i++) if (!existsColor[colors[i]]) {
				existsColor[colors[i]]=true;
				curr[currSize]=colors[i];
				if (permutate(colors,existsColor,curr,currSize+1,context)) return true;
				existsColor[colors[i]]=false;
			}
		}
		return false;
	}

	public static void main (String [] args) throws Exception {
		char [] colors= {'R','G','B','Y','O','V'};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			Context [] context=new Context[2];
			for (int i=0;i<context.length;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				context[i]=new Context();
				context[i].guess=st.nextToken().toCharArray();
				context[i].n1=Integer.parseInt(st.nextToken());
				context[i].n2=Integer.parseInt(st.nextToken());
			}

			boolean found=permutate(colors, new boolean [128], new char [4], 0, context);
			System.out.println(found?"Possible":"Cheat");
		}
	}

}
