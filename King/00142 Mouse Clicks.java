import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class UI {
		char id;
		int [] points;
		boolean visible;
		
		public UI (char id, int [] p) {
			this.id=id;
			this.points=p;
			this.visible=true;
		}
		
		public boolean inWindow(int x, int y) {
			return this.points[0]<=x && this.points[2]>=x && this.points[1]<=y && this.points[3]>=y;
		}
		
		private int distSq(int x, int y) {
			int dx=x-this.points[0];
			int dy=y-this.points[1];
			return dx*dx+dy*dy;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int iconId=1;
		int windowId='A';
		ArrayList<UI> icons=new ArrayList<>();
		ArrayList<UI> windows=new ArrayList<>();
		boolean updateVisibility=false;
		while (!(s=br.readLine()).equals("#")) {
			StringTokenizer st=new StringTokenizer(s);
			char c=st.nextToken().charAt(0);
			if (c=='I') {
				icons.add(new UI((char)(iconId++),new int [] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())}));
			} else if (c=='R') {
				windows.add(new UI((char)(windowId++),new int [] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())}));
			} else if (c=='M') {
				if (!updateVisibility) {
					for (int i=0;i<icons.size();i++) {
						UI icon=icons.get(i);
						boolean flag=true;
						for (int i2=0;i2<windows.size();i2++) if (windows.get(i2).inWindow(icon.points[0],icon.points[1])) {
							flag=false;
							break;
						}
						if (!flag) icon.visible=false;
					}
					updateVisibility=true;
				}

				UI window=null;
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				for (int i=windows.size()-1;i>=0;i--) {
					if (windows.get(i).inWindow(x, y)) {
						window=windows.get(i);
						break;
					}
				}
				
				if (window!=null) System.out.println(window.id);
				else {
					int MAX_DIST=Integer.MAX_VALUE>>1;
					ArrayList<Integer> iconAns=new ArrayList<>();
					for (int i=0;i<icons.size();i++) if (icons.get(i).visible) {
						int dist=icons.get(i).distSq(x,y);
						if (dist<=MAX_DIST) {
							if (dist<MAX_DIST) {
								iconAns.clear();
								MAX_DIST=dist;
							}
							iconAns.add(i);
						}
					}
					
					StringBuilder sb=new StringBuilder();
					for (int i=0;i<iconAns.size();i++) {
						int idx=iconAns.get(i);
						sb.append(String.format("%3d",(int)icons.get(idx).id));
					}
					System.out.println(sb);
				}
			}
		}
	}

}