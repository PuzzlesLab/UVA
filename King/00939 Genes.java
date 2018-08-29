import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {
	
	private static class Person {
		private String gene;
		public String name;
		public HashSet<Person> parents;
		
		public Person (String name) {
			this.name = name;
			this.gene = "I DUNO LOL";
			this.parents = new HashSet<>();
		}
		
		public Person (String name, String gene) {
			this.name = name;
			this.gene = gene;
			this.parents = new HashSet<>();
		}
		
		public String getGene() {
			if (this.gene.equals("I DUNO LOL")) {
				int dominantCount=0;
				int recessiveCount=0;
				for (Person p : this.parents) {
					switch (p.getGene()) {
						case "dominant": dominantCount++;
										 break;
						case "recessive": recessiveCount++;
						 				 break;
					}
				}
				
				if (dominantCount==2 || (dominantCount==1 && recessiveCount==1)) this.gene="dominant";
				else if (dominantCount > 0 || recessiveCount == 2) this.gene="recessive";
				else this.gene="non-existent";
			}
			return this.gene;
		}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int count=Integer.parseInt(br.readLine());
		TreeMap<String, Person> map=new TreeMap<>();
		for (int i=0;i<count;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			String name=st.nextToken();
			String geneOrName=st.nextToken();
			if (geneOrName.equals("dominant") || geneOrName.equals("recessive") || geneOrName.equals("non-existent")) {
				if (map.containsKey(name)) map.get(name).gene=geneOrName;
				else map.put(name, new Person(name, geneOrName));
			} else {
				if (!map.containsKey(name)) map.put(name, new Person(name));
				if (!map.containsKey(geneOrName)) map.put(geneOrName, new Person(geneOrName));
				map.get(geneOrName).parents.add(map.get(name));
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for (Person p : map.values()) {
			sb.append(p.name);
			sb.append(" ");
			sb.append(p.getGene());
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

}
