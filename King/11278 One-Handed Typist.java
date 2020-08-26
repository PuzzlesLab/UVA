import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		char [] map=new char [128];
		map[' ']=' ';
		String [] qwertyRows= {
				"`1234567890-=",
				"qwertyuiop[]\\",
				"asdfghjkl;'",
				"zxcvbnm,./",
				"~!@#$%^&*()_+",
				"QWERTYUIOP{}|",
				"ASDFGHJKL:\"",
				"ZXCVBNM<>?"
				
		};
		String [] dvorakRows= {
				"`123qjlmfp/[]",
				"456.orsuyb;=\\",
				"789aehtdck-",
				"0zx,inwvg'",
				"~!@#QJLMFP?{}",
				"$%^>ORSUYB:+|",
				"&*(AEHTDCK_",
				")ZX<INWVG\""
		};
		for (int i=0;i<qwertyRows.length;i++) for (int i2=0;i2<qwertyRows[i].length();i2++) {
			map[qwertyRows[i].charAt(i2)]=dvorakRows[i].charAt(i2);
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringBuilder sb=new StringBuilder();
			for (char c : s.toCharArray()) sb.append(map[c]);
			System.out.println(sb.toString());
		}
	}
}