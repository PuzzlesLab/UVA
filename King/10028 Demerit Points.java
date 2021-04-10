import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution {
	
/* 
Test Case :
1

19820508
19830606 1
19830606 1
19830607 1
19891212 15

 */
	public static class Date {
		int [] values;

		public Date(String dateStr) {
			this.values=new int[3];
			this.values[0]=Integer.parseInt(dateStr.substring(0,4));
			this.values[1]=Integer.parseInt(dateStr.substring(4,6));
			this.values[2]=Integer.parseInt(dateStr.substring(6,8));
		}
		
		public Date(Date d) {
			this.values=new int[3];
			this.values[0]=d.values[0];
			this.values[1]=d.values[1];
			this.values[2]=d.values[2];
		}
		
		public int compareTo(Date d) {
			return this.toString().compareTo(d.toString());
		}
		
		public String toString() {
			return String.format("%04d-%02d-%02d",this.values[0],this.values[1],this.values[2]);
		}
	}

	public static class Event {
		Date date;
		int point;

		public Event(Date d, int point) {
			this.date=d;
			this.point=point;
		}
	}

	private static void addPrint(StringBuilder sb, Date d, int merit, int demerit) {
		sb.append(d.values[0]);
		sb.append('-');
		sb.append(String.format("%02d", d.values[1]));
		sb.append('-');
		sb.append(String.format("%02d", d.values[2]));
		sb.append(' ');
		if (demerit>0) {
			sb.append(Math.abs(demerit));
			sb.append(" demerit point(s).");
		} else if (merit>0) {
			sb.append(Math.abs(merit));
			sb.append(" merit point(s).");
		} else sb.append("No merit or demerit points.");
		sb.append('\n');
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			Date startDate=new Date(br.readLine());
			
			ArrayList<Event> events=new ArrayList<>();
			String s;
			while (true) {
				s=br.readLine();
				if (s==null || s.length()==0) break;
				
				StringTokenizer st=new StringTokenizer(s);
				events.add(new Event(new Date(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			int currMerit=0;
			int currDemerit=0;
			StringBuilder sb=new StringBuilder();
			addPrint(sb,startDate,currMerit,currDemerit);
			Date lastOffenceDate=new Date(startDate);
			for (int i=0;i<events.size();i++) {
				Event event=events.get(i);

				while (currDemerit>0) {
					Date grantDate=new Date(lastOffenceDate);
					grantDate.values[0]++;
					if (grantDate.compareTo(event.date)>0) break;
					currDemerit=Math.min(currDemerit/2, Math.max(0, currDemerit-2));
					lastOffenceDate.values[0]++;
					addPrint(sb,lastOffenceDate,currMerit,currDemerit);
				}
				
				while (true) {
					Date grantDate=new Date(lastOffenceDate);
					grantDate.values[0]+=2;
					if (grantDate.compareTo(event.date)>0) break;
					currMerit++;
					lastOffenceDate.values[0]+=2;
					addPrint(sb,lastOffenceDate,currMerit,currDemerit);
				}
				
				currDemerit+=event.point;
				if (currDemerit>2*currMerit) {
					currDemerit-=2*currMerit;
					currMerit=0;
				} else if (currDemerit<=2*currMerit) {
					currMerit=(currMerit*2 - currDemerit)/2;
					currDemerit=0;
				}
				addPrint(sb,event.date,currMerit,currDemerit);
				lastOffenceDate=new Date(event.date);
			}

			while (currDemerit>0) {
				currDemerit=Math.min(currDemerit/2, Math.max(0, currDemerit-2));
				lastOffenceDate.values[0]++;
				addPrint(sb,lastOffenceDate,currMerit,currDemerit);
			}
			
			while (currMerit<5) {
				currMerit++;
				lastOffenceDate.values[0]+=2;
				addPrint(sb,lastOffenceDate,currMerit,currDemerit);
			}

			if (testCase>0) System.out.println();
			System.out.print(sb.toString());
		}
	}
}
