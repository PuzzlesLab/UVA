import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

class zz {

	public static void main (String [] args) throws Exception {
		char [][] fingerList= {
			{'q','a','z'},
			{'w','s','x'},
			{'e','d','c'},
			{'r','f','v','t','g','b'},
			{' '},
			{' '},
			{'y','h','n','u','j','m'},
			{'i','k',','},
			{'o','l','.'},
			{'p',';','/'}
		};
		HashSet<Character> fingerSet []=new HashSet [10];
		for (int i=0;i<fingerSet.length;i++) {
			fingerSet[i]=new HashSet<>();
			for (int i2=0;i2<fingerList[i].length;i2++) fingerSet[i].add(fingerList[i][i2]);
		}
		
		Scanner sc=new Scanner(System.in);
		String s;
		while (sc.hasNext()) {
			int F=Integer.parseInt(sc.next());
			int N=Integer.parseInt(sc.next());

			boolean [] usable=new boolean [10];
			Arrays.fill(usable, true);
			for (int f=0;f<F;f++) usable[Integer.parseInt(sc.next())-1]=false;
			
			boolean [] typable=new boolean [128];
			for (int i=0;i<usable.length;i++) if (usable[i]) {
				for (Character c: fingerSet[i]) typable[c]=true;
			}

			HashSet<String> ansSet=new HashSet<>();
			int maxLen=0;
			for (int n=0;n<N;n++) {
				s=sc.next();
				
				int count=0;
				for (int i=0;i<s.length();i++) {
					if (typable[s.charAt(i)]) count++;
					else break;
				}
				
				if (count==s.length()) {
					if (count<maxLen) continue;
					if (count>maxLen) {
						maxLen=count;
						ansSet.clear();
					}
					ansSet.add(s);
				}
			}
			ArrayList<String> ansList=new ArrayList<>(ansSet);
			Collections.sort(ansList);
			
			StringBuilder sb=new StringBuilder();
			sb.append(ansList.size());
			sb.append('\n');
			for (int i=0;i<ansList.size();i++) {
				sb.append(ansList.get(i));
				sb.append('\n');
			}
			System.out.print(sb);
		}
	}
}