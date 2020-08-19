import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	private static class Event {
		int id, day, month, year, priority;
		String name;
		
		public Event(int i, int d, int m, int y, int p, String n) {
			this.id=i;
			this.day=d;
			this.month=m;
			this.year=y;
			this.priority=p;
			this.name=n;
		}
	}
	
	private static class EventWrapper implements Comparable<EventWrapper> {
		Event event;
		int stars;
		int dayDiff;
		public EventWrapper(Event e, int s, int dd) {
			this.event=e;
			this.stars=s;
			this.dayDiff=dd;
		}
		public int compareTo(EventWrapper ew) {
			if (this.dayDiff==ew.dayDiff && this.dayDiff==0) return this.event.id-ew.event.id;
			else if (this.dayDiff!=ew.dayDiff) return this.dayDiff-ew.dayDiff;
			else if (this.stars!=ew.stars) return ew.stars-this.stars;
			return this.event.id-ew.event.id;
		}
	}
	
	private static int compareDate(int y1, int m1, int d1, int y2, int m2, int d2) {
		if (y1!=y2) return y1-y2;
		else if (m1!=m2) return m1-m2;
		return d1-d2;
	}
	
	//Using built-in datetime lib gives WA :x
	private static int [] DaysInMonthNormal= {0,31,28,31,30,31,30,31,31,30,31,30,31}; 
	private static int [] DaysInMonthLeap= {0,31,29,31,30,31,30,31,31,30,31,30,31};
	private static int getDayDiff(int y1, int m1, int d1, int y2, int m2, int d2) {
		int days=0;
		while (y1!=y2 || m1!=m2 || d1!=d2) {
			d1++;
			days++;
			
			if ((y1%4==0 && d1>DaysInMonthLeap[m1]) || (y1%4!=0 && d1>DaysInMonthNormal[m1])) {
				d1=1;
				m1++;
			}
			
			if (m1>=13) {
				m1=1;
				y1++;
			}
		}
		return days;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int year=Integer.parseInt(br.readLine());
		ArrayList<Event> events=new ArrayList<>();
		boolean start=true;
		
		String s;
		int ACount=0;
		while (!(s=br.readLine()).equals("#")) {
			char c=s.charAt(0);
			if (c=='A') {
				StringBuilder sb=new StringBuilder();
				int currIdx=1;
				
				while (s.charAt(currIdx)==' ') currIdx++;
				while (s.charAt(currIdx)!=' ') sb.append(s.charAt(currIdx++));
				int day=Integer.parseInt(sb.toString());
				
				sb.setLength(0);
				while (s.charAt(currIdx)==' ') currIdx++;
				while (s.charAt(currIdx)!=' ') sb.append(s.charAt(currIdx++));
				int month=Integer.parseInt(sb.toString());

				sb.setLength(0);
				while (s.charAt(currIdx)==' ') currIdx++;
				while (s.charAt(currIdx)!=' ') sb.append(s.charAt(currIdx++));
				int priority=Integer.parseInt(sb.toString());
				
				while (s.charAt(currIdx)==' ') currIdx++;
				String desc=s.substring(currIdx);
				Event e=new Event(ACount,day,month,year,priority,desc);
				events.add(e);
				try {
					LocalDateTime dt=LocalDateTime.of(year+1,month,day,0,0);
					Event e2=new Event(ACount,day,month,year+1,priority,desc);
					events.add(e2);
				} catch (Exception exp) {}
				ACount++;
			} else if (c=='D') {
				StringTokenizer st=new StringTokenizer(s); st.nextToken();
				int day=Integer.parseInt(st.nextToken());
				int month=Integer.parseInt(st.nextToken());
				StringBuilder sb=new StringBuilder("Today is:");
				sb.append(String.format("%3d%3d",day,month));
				sb.append('\n');
				
				ArrayList<EventWrapper> eventsToShow=new ArrayList<>();
				for (Event e : events) {
					int cmpDate=compareDate(year,month,day,e.year,e.month,e.day);
					if (cmpDate<=0) {
						int dayDiff=getDayDiff(year,month,day, e.year,e.month,e.day);
						if (dayDiff<=e.priority) eventsToShow.add(new EventWrapper(e,e.priority-dayDiff+1,dayDiff));
					}
				}
				
				Collections.sort(eventsToShow);
				for (EventWrapper ew : eventsToShow) {
					sb.append(String.format("%3d%3d",ew.event.day,ew.event.month));
					sb.append(' ');
					if (ew.dayDiff==0) sb.append("*TODAY*");
					else {
						for (int i=0;i<ew.stars;i++) sb.append('*');
						for (int i=ew.stars;i<7;i++) sb.append(' ');
					}
					sb.append(' ');
					sb.append(ew.event.name);
					sb.append('\n');
				}
				
				if (start) start=false;
				else System.out.println();
					
				System.out.print(sb.toString());
			}
		}
	}

}