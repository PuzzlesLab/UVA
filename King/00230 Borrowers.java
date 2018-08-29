import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

class Main {
	
	private static class Book implements Comparable<Book>{
		String author;
		String name;
		
		public Book(String author, String name) {
			this.author=author;
			this.name=name;
		}
		
		public int compareTo(Book b) {
			if (this.author.equals(b.author)) return this.name.compareTo(b.name);
			return this.author.compareTo(b.author);
		}
		
		public String toString() { return this.name; }
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Book> booksOnShelves=new LinkedList<>();
		HashMap<String, Book> bookMap=new HashMap<>();
		String s;
		while (!(s=br.readLine()).equals("END")) {
			String [] split=s.split("\" by ");
			booksOnShelves.add(new Book(split[1], split[0]+"\""));
			bookMap.put(split[0]+"\"", booksOnShelves.getLast());
		}
		Collections.sort(booksOnShelves);
		
		TreeSet<Book> booksNotOnShelves=new TreeSet<>();
		while (!(s=br.readLine()).equals("END")) {
			if (!s.equals("SHELVE")) {
				int index=s.indexOf(" ");
				String op=s.substring(0, index);
				String book=s.substring(index+1, s.length());
				
				if (op.equals("BORROW")) booksOnShelves.remove(bookMap.get(book));
				else if (op.equals("RETURN")) booksNotOnShelves.add(bookMap.get(book));
			} else {
				StringBuilder sb=new StringBuilder();
				for (Book b : booksNotOnShelves) {
					int index=0;
					for (;index<booksOnShelves.size() && b.compareTo(booksOnShelves.get(index))>0;index++) {}
					if (index==0) sb.append("Put "+b.name+" first\n");
					else sb.append("Put "+b.name+" after "+booksOnShelves.get(index-1).name+"\n");
					
					booksOnShelves.add(index, b);
				}
				booksNotOnShelves.clear();
				System.out.print(sb.toString());
				System.out.println("END");
			}
		}
	}

}
