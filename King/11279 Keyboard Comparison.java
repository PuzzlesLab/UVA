import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	private static double [] Qwerty2HDist;
	private static double [] Qwerty1HDist;
	private static double [] DvorakDist;
	
	private static class Coordinate {
		int x, y;
		public Coordinate(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public static double distanceBetween(Coordinate c1, Coordinate c2) {
		double dx=c1.x-c2.x, dy=c1.y-c2.y;
		return Math.sqrt(dx*dx+dy*dy);
	}

	private static void initialize2HQwerty() {
		Coordinate [] qwertyCoor=new Coordinate[128];
		
		String [] qwertyStrLower={"`1234567890-=", " qwertyuiop[]\\", " asdfghjkl;'", " zxcvbnm,./"};
		char [][] qwertyLower=new char [4][];
		for (int i=0;i<4;i++) {
			qwertyLower[i]=new char[qwertyStrLower[i].length()+1];
			for (int i2=0;i2<qwertyStrLower[i].length();i2++) {
				qwertyLower[i][i2+1]=qwertyStrLower[i].charAt(i2);
				qwertyCoor[qwertyLower[i][i2+1]]=new Coordinate(i,i2+1);
			}
		}

		String [] qwertyStrUpper={"~!@#$%^&*()_+", " QWERTYUIOP{}|", " ASDFGHJKL:\"", " ZXCVBNM<>?"};
		char [][] qwertyUpper=new char [4][];
		qwertyUpper[0]=qwertyStrUpper[0].toCharArray();
		for (int i=0;i<4;i++) {
			qwertyUpper[i]=new char[qwertyStrUpper[i].length()+1];
			for (int i2=0;i2<qwertyStrUpper[i].length();i2++) {
				qwertyUpper[i][i2+1]=qwertyStrUpper[i].charAt(i2);
				qwertyCoor[qwertyUpper[i][i2+1]]=new Coordinate(i,i2+1);
			}
		}
		
		char [] qwerty2HFingers="ASDFJKL;asdfjkl:".toCharArray();

		double [] dist=new double [128];
		for (int i=0;i<qwertyCoor.length;i++) if (qwertyCoor[i]!=null) {
			double minDist=Double.MAX_VALUE;
			for (char c: qwerty2HFingers) minDist=Math.min(minDist,distanceBetween(qwertyCoor[i],qwertyCoor[c]));
			dist[i]=minDist*2;
		}
		dist[' ']=0.0;
		Qwerty2HDist=dist;
	}
	
	private static void initialize1HQwerty() {
		Coordinate [] qwertyCoor=new Coordinate[128];
		
		String [] qwertyStrLower={"`1234567890-=", " qwertyuiop[]\\", " asdfghjkl;'", " zxcvbnm,./"};
		char [][] qwertyLower=new char [4][];
		for (int i=0;i<4;i++) {
			qwertyLower[i]=new char[qwertyStrLower[i].length()+1];
			for (int i2=0;i2<qwertyStrLower[i].length();i2++) {
				qwertyLower[i][i2+1]=qwertyStrLower[i].charAt(i2);
				qwertyCoor[qwertyLower[i][i2+1]]=new Coordinate(i,i2+1);
			}
		}

		String [] qwertyStrUpper={"~!@#$%^&*()_+", " QWERTYUIOP{}|", " ASDFGHJKL:\"", " ZXCVBNM<>?"};
		char [][] qwertyUpper=new char [4][];
		qwertyUpper[0]=qwertyStrUpper[0].toCharArray();
		for (int i=0;i<4;i++) {
			qwertyUpper[i]=new char[qwertyStrUpper[i].length()+1];
			for (int i2=0;i2<qwertyStrUpper[i].length();i2++) {
				qwertyUpper[i][i2+1]=qwertyStrUpper[i].charAt(i2);
				qwertyCoor[qwertyUpper[i][i2+1]]=new Coordinate(i,i2+1);
			}
		}
		
		char [] qwerty1HFingers="FGHJfghj".toCharArray();
		
		double [] dist=new double [128];
		for (int i=0;i<qwertyCoor.length;i++) if (qwertyCoor[i]!=null) {
			double minDist=Double.MAX_VALUE;
			for (char c: qwerty1HFingers) minDist=Math.min(minDist,distanceBetween(qwertyCoor[i],qwertyCoor[c]));
			dist[i]=minDist*2;
		}
		dist[' ']=0.0;
		Qwerty1HDist=dist;
	}
	
	private static void initializeDvorak() {
		Coordinate [] dvorakCoor=new Coordinate[128];

		String [] dvorakStrLower={"`123qjlmfp/[]", " 456.orsuyb;=\\", " 789aehtdck-", " 0zx,inwvg'"};
		char [][] dvorakLower=new char [4][];
		for (int i=0;i<4;i++) {
			dvorakLower[i]=new char[dvorakStrLower[i].length()+1];
			for (int i2=0;i2<dvorakStrLower[i].length();i2++) {
				dvorakLower[i][i2+1]=dvorakStrLower[i].charAt(i2);
				dvorakCoor[dvorakLower[i][i2+1]]=new Coordinate(i,i2+1);
			}
		}
		
		String [] dvorakStrUpper={"~!@#QJLMFP?{}", " $%^>ORSUYB:+|", " &*(AEHTDCK_", " )ZX<INWVG\""};
		char [][] dvorakUpper=new char [4][];
		for (int i=0;i<4;i++) {
			dvorakUpper[i]=new char[dvorakStrUpper[i].length()+1];
			for (int i2=0;i2<dvorakStrUpper[i].length();i2++) {
				dvorakUpper[i][i2+1]=dvorakStrUpper[i].charAt(i2);
				dvorakCoor[dvorakUpper[i][i2+1]]=new Coordinate(i,i2+1);
			}
		}
		
		char [] dvorakFingers="EHTDehtd".toCharArray();
		double [] dist=new double [128];
		for (int i=0;i<dvorakCoor.length;i++) if (dvorakCoor[i]!=null) {
			double minDist=Double.MAX_VALUE;
			for (char c: dvorakFingers) minDist=Math.min(minDist,distanceBetween(dvorakCoor[i],dvorakCoor[c]));
			dist[i]=minDist*2;
		}
		dist[' ']=0.0;
		DvorakDist=dist;
	}
	
	public static void main (String [] args) throws Exception {
		initialize2HQwerty();
		initialize1HQwerty();
		initializeDvorak();
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			double q2hd=0.0;
			double q1hd=0.0;
			double dd=0.0;
			for (char c : s.toCharArray()) {
				q2hd+=Qwerty2HDist[c];
				q1hd+=Qwerty1HDist[c];
				dd+=DvorakDist[c];
			}
			System.out.printf("%.2f %.2f %.2f\n", q2hd, q1hd, dd);
		}
	}
}