package uva;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	private static class Note {
		public String name1, name2;
		public String [] names;
		public Note (String n1, String n2) {
			this.name1=n1;
			this.name2=n2;
			this.names=new String[this.name2==null? 1 : 2];
			this.names[0]=n1;
			if (this.name2!=null) this.names[1]=this.name2;
		}
	}
	
	public static void main (String [] args) throws Exception {
		Note [] notes= {new Note("C","B#"), new Note("C#","Db"),new Note("D",null),
						new Note("D#","Eb"),new Note("E","Fb"),new Note("F","E#"),
						new Note("F#","Gb"),new Note("G",null),new Note("G#","Ab"),
						new Note("A",null),new Note("A#","Bb"),new Note("B","Cb")};
		HashMap<String, Integer> noteIndex=new HashMap<>();
		for (int i=0;i<notes.length;i++) {
			Note nt=notes[i];
			if (nt.name1!=null) noteIndex.put(nt.name1,i);
			if (nt.name2!=null) noteIndex.put(nt.name2,i);
		}
		
		String [] majorScaleStarts= {"C","Db","D","Eb","E","F","Gb","G","Ab","A","Bb","B"};
		int [] delta= {2,2,1,2,2,2,1};
		HashMap<String, ArrayList<String>> majorScalesSharp=new HashMap<>();
		HashMap<String, ArrayList<String>> majorScalesBase=new HashMap<>();
		for (String start : majorScaleStarts) {
			int idx=noteIndex.get(start);
			Note currNote=notes[idx];
			for (String name : currNote.names) {
				if (name.length()==1 || (name.length()>1 && name.charAt(1)=='#')) { //sharp
					ArrayList<String> list=new ArrayList<>();
					
					boolean [] usedCharSet=new boolean [128];
					boolean fail=false;
					
					for (int d : delta) {
						String n1=notes[idx].name1;
						String n2=notes[idx].name2;
						
						if (n1.equals(name) || name.equals(n2)) list.add(name);
						else if (n1.length()>1 && n1.charAt(1)=='#' && !usedCharSet[n1.charAt(0)]) list.add(notes[idx].name1);
						else if (n2!=null && n2.length()>1 && n2.charAt(1)=='#' && !usedCharSet[n2.charAt(0)]) list.add(notes[idx].name2);
						else if (n1.length()==1 && !usedCharSet[n1.charAt(0)]) list.add(notes[idx].name1);
						else if (n2!=null && n2.length()==1 && !usedCharSet[n2.charAt(0)]) list.add(notes[idx].name2);
						else fail=true;
						
						idx=(idx+d)%notes.length;
						if (fail) break;
						else usedCharSet[list.get(list.size()-1).charAt(0)]=true;
					}
					
					if (!fail) majorScalesBase.put(name, list);
				}
				
				 idx=noteIndex.get(start);
				 currNote=notes[idx];
				if (name.length()==1 || (name.length()>1 && name.charAt(1)=='b')) { //base
					ArrayList<String> list=new ArrayList<>();
					
					boolean [] usedCharSet=new boolean [128];
					boolean fail=false;
					for (int d : delta) {
						String n1=notes[idx].name1;
						String n2=notes[idx].name2;

						
						if (n1.equals(name) || name.equals(n2)) list.add(name);
						else if (n1.length()>1 && n1.charAt(1)=='b' && !usedCharSet[n1.charAt(0)]) list.add(notes[idx].name1);
						else if (n2!=null && n2.length()>1 && n2.charAt(1)=='b' && !usedCharSet[n2.charAt(0)]) list.add(notes[idx].name2);
						else if (n1.length()==1 && !usedCharSet[n1.charAt(0)]) list.add(notes[idx].name1);
						else if (n2!=null && n2.length()==1 && !usedCharSet[n2.charAt(0)]) list.add(notes[idx].name2);
						else fail=true;
						
						idx=(idx+d)%notes.length;
						if (fail) break;
						else usedCharSet[list.get(list.size()-1).charAt(0)]=true;
					}
					
					if (!fail) majorScalesSharp.put(name, list);
				}
			}
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			String note=s;
			String [] tests=br.readLine().split(";");
			
			StringBuilder sb=new StringBuilder("Key of ");
			sb.append(note);
			sb.append('\n');
			
			ArrayList<ArrayList<String>> possibleLists=new ArrayList<>();
			if (majorScalesSharp.containsKey(note)) possibleLists.add(majorScalesSharp.get(note));
			if (majorScalesBase.containsKey(note)) possibleLists.add(majorScalesBase.get(note));
			
			for (String test : tests) {
				StringTokenizer st=new StringTokenizer(test);
				String n=st.nextToken();
				String direction=st.nextToken();
				String distS=st.nextToken();
				
				ArrayList<String> foundList=null;
				for (ArrayList<String> pl : possibleLists) if (pl.contains(n)) {
					foundList=pl;
					break;
				}
				
				sb.append(n);
				sb.append(": ");
				if (foundList==null) sb.append("invalid note for this key");
				else {
					int dist=0;
					switch (distS) {
						case "SECOND": dist=1; break;
						case "THIRD": dist=2; break;
						case "FOURTH": dist=3; break;
						case "FIFTH": dist=4; break;
						case "SIXTH": dist=5; break;
						case "SEVENTH": dist=6; break;
						case "OCTAVE": dist=7; break;
					}
					
					sb.append(direction);
					sb.append(' ');
					sb.append(distS);
					sb.append(" > ");
					int idx=Math.floorMod(foundList.indexOf(n)+(direction.equals("UP") ? dist : -dist), foundList.size());
					sb.append(foundList.get(idx));
				}

				sb.append('\n');
			}
			System.out.println(sb.toString());
		}
		
	}

}