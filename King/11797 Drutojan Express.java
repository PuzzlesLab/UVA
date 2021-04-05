import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static class Person {
		String name;
		LinkedList<Person> next;
		int timeSpent;
		
		public Person(String n) {
			this.name=n;
			this.next=new LinkedList<>();
			this.timeSpent=0;
		}
	}

	public static void main (String [] args) throws Exception {
		String [] names= {"Ja", "Sam", "Sha", "Sid", "Tan"};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			HashMap<String, Person> pMap=new HashMap<>();
			Person [] person=new Person[names.length];
			for (int i=0;i<person.length;i++) {
				person[i]=new Person(names[i]);
				pMap.put(names[i], person[i]);
			}

			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			String firstPersonName=st.nextToken();
			Person currP=null;
			for (int i=0;i<person.length;i++) if (person[i].name.equals(firstPersonName)) {
				currP=person[i];
				break;
			}
			
			for (int i=0;i<person.length;i++) {
				st=new StringTokenizer(br.readLine());
				int K=Integer.parseInt(st.nextToken());
				for (int k=0;k<K;k++) person[i].next.add(pMap.get(st.nextToken()));
			}

			int T=0;
			while (T<N) {
				int sitTime=Math.min(N-T, M);
				currP.timeSpent+=sitTime;
				Person nextP=currP.next.removeFirst();
				currP.next.addLast(nextP);
				currP=nextP;
				T+=(sitTime+2);
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(":\n");
			for (Person p : person) {
				sb.append(p.name);
				sb.append(' ');
				sb.append(p.timeSpent);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}
}
