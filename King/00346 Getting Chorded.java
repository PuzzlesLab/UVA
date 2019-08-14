import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	private static int indexOf(String [] ary, int start, String target) {
		for (int i=start;i<ary.length;i++) if (ary[i].equals(target)) return i;
		return -1;
	}
	
	public static void main (String [] args) throws Exception {
		String [] notes= {"C","C#","D","D#","E","F","F#","G","G#","A","A#","B","C","C#","D","D#","E","F","F#","G","G#","C","C#","D","D#","E","F","F#","G","G#"};
		HashMap<String, String> extraNameLookup=new HashMap<>();
		extraNameLookup.put("AB", "G#");
		extraNameLookup.put("BB", "A#");
		extraNameLookup.put("CB", "B");
		extraNameLookup.put("DB", "C#");
		extraNameLookup.put("EB", "D#");
		extraNameLookup.put("FB", "E");
		extraNameLookup.put("GB", "F#");

		extraNameLookup.put("B#", "C");
		extraNameLookup.put("E#", "F");
		
		int [][] testCombination= {{0,1,2},{0,2,1},{1,0,2},{1,2,0},{2,0,1},{2,1,0}};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			String n1=st.nextToken();
			String n2=st.nextToken();
			String n3=st.nextToken();
			
			String n1Real=extraNameLookup.getOrDefault(n1.toUpperCase(), n1.toUpperCase());
			String n2Real=extraNameLookup.getOrDefault(n2.toUpperCase(), n2.toUpperCase());
			String n3Real=extraNameLookup.getOrDefault(n3.toUpperCase(), n3.toUpperCase());
			
			String ans="";
			String [] str= {n1Real,n2Real,n3Real};
			int lowestIndex=88;
			for (int [] comb : testCombination) {
				//Find different arrangement of keys.
				int index1=indexOf(notes,0,str[comb[0]]);
				if (index1==-1) continue;
				int index2=indexOf(notes,index1,str[comb[1]]);
				if (index2==-1) continue;
				int index3=indexOf(notes,index2,str[comb[2]]);
				if (index3==-1) continue;
				
				int delta1=index2-index1, delta2=index3-index2;
				if (delta1==4 && delta2==3 && index1<lowestIndex) { //Ensure we have the lowest key.
					ans="a "+str[comb[0]].toUpperCase()+" Major chord";
					lowestIndex=index1;
				}
				else if (delta1==3 && delta2==4 && index1<lowestIndex) {
					ans="a "+str[comb[0]].toUpperCase()+" Minor chord";
					lowestIndex=index1;
				}
				if (ans.length()>0) break;
			}
			if (ans.length()==0) ans="unrecognized";
			
			System.out.printf("%s %s %s is %s.\n", n1, n2, n3, ans);
		}
	}

}