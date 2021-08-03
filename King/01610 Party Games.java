import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			String [] names=new String [N];
			for (int n=0;n<N;n++) names[n]=br.readLine();
			Arrays.sort(names);
			String s1=names[N/2-1];
			String s2=names[N/2];
			char [] name1=s1.toCharArray();
			char [] name2=s2.toCharArray();
			
			StringBuilder ans=new StringBuilder();
			if (name1.length<=name2.length) {
				for (int i=0;i<name1.length;i++) {
					if (name1[i]==name2[i] || i==name1.length-1) ans.append(name1[i]);
					else {
						ans.append((char)(name1[i]+1));
						break;
					}
				}
			} else {
				for (int i=0;i<name2.length;i++) {
					if (name1[i]==name2[i]) ans.append(name1[i]);
					else break;
				}
				for (int i=ans.length();i<name1.length;i++) {
					if (i<name1.length && i<name2.length) {
						StringBuilder try1=new StringBuilder(ans);
						try1.append((char)(name1[i]+1));
						if (try1.toString().compareTo(s2)<0) ans=try1;
						else ans.append(name1[i]);
					} else {
						if (name1[i]=='Z') ans.append(name1[i]);
						else {
							StringBuilder try1=new StringBuilder(ans);
							try1.append(s1.substring(i,s1.length()));
							
							StringBuilder try2=new StringBuilder(ans);
							try2.append((char)(name1[i]+1));
							
							if (try2.toString().compareTo(s2)>=0) ans.append(name1[i]);
							else ans=(try2.length() < try1.length()) ? try2 : try1;
						}
					}
					if (ans.toString().compareTo(s1)>=0 && ans.toString().compareTo(s2)<0) break;
				}
			}

			System.out.println(ans);
		}
	}
}
