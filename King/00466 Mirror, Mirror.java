import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	private static boolean matches(char [][] mat1, char [][] mat2) {
		boolean flag=true;
		for (int i=0;i<mat1.length && flag;i++) for (int i2=0;i2<mat1.length && flag;i2++) flag&=(mat1[i][i2]==mat2[i][i2]);
		return flag;
	}
	
	private static char [][] rotate90 (char [][] mat) {
		char [][] newMat=new char [mat.length][mat.length];
		for (int i=0;i<mat.length;i++) for (int i2=0;i2<mat.length;i2++) newMat[i2][i]=mat[mat.length-i-1][i2];
		return newMat;
	}
	
	private static char [][] mirror(char [][] mat) {
		char [][] newMat=new char [mat.length][mat.length];
		for (int i=0;i<mat.length;i++) for (int i2=0;i2<mat.length;i2++) newMat[i][i2]=mat[mat.length-i-1][i2];
		return newMat;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int t=1;
		while ((s=br.readLine())!=null) {
			int n=Integer.parseInt(s);
			char [][] left=new char [n][n];
			char [][] right=new char [n][n];
			for (int row=0;row<n;row++) {
				s=br.readLine();
				for (int col=0;col<n;col++) left[row][col]=s.charAt(col);
				
				for (int col=n+1;col<s.length();col++) right[row][col-n-1]=s.charAt(col);
			}
			
			if (matches(left, right)) {
				System.out.printf("Pattern %d was preserved.\n", t++);
				continue;
			}
			
			char [][] rotate90=rotate90(left);
			if (matches(right, rotate90)) {
				System.out.printf("Pattern %d was rotated 90 degrees.\n", t++);
				continue;
			}
			
			char [][] rotate180=rotate90(rotate90);
			if (matches(right, rotate180)) {
				System.out.printf("Pattern %d was rotated 180 degrees.\n", t++);
				continue;
			}
			
			char [][] rotate270=rotate90(rotate180);
			if (matches(right, rotate270)) {
				System.out.printf("Pattern %d was rotated 270 degrees.\n", t++);
				continue;
			}
			
			char [][] vr=mirror(left);
			if (matches(right, vr)) {
				System.out.printf("Pattern %d was reflected vertically.\n", t++);
				continue;
			}
			
			char [][] vrRotate90=rotate90(vr);
			if (matches(right, vrRotate90)) {
				System.out.printf("Pattern %d was reflected vertically and rotated 90 degrees.\n", t++);
				continue;
			}
			
			char [][] vrRotate180=rotate90(vrRotate90);
			if (matches(right, vrRotate180)) {
				System.out.printf("Pattern %d was reflected vertically and rotated 180 degrees.\n", t++);
				continue;
			}
			
			char [][] vrRotate270=rotate90(vrRotate180);
			if (matches(right, vrRotate270)) {
				System.out.printf("Pattern %d was reflected vertically and rotated 270 degrees.\n", t++);
				continue;
			}
			
			System.out.printf("Pattern %d was improperly transformed.\n", t++);
		}
	}

}