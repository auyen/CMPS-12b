// Austin Yen
// 4/17/15
// FileReverse.java
// Given an input file, returns an output file 
// with string contents of input file reversed
import java.io.*;
import java.util.Scanner;
public class FileReverse {
	public static void main(String[] args)throws IOException{
		Scanner in = null;
		PrintWriter out = null;
		String line = null;
		String[] token = null;
		int i, n, lineNumber = 0;
		// check command line arguments
		if(args.length < 2){
			System.out.println("Usage: FileTokens infile outfile");
			System.exit(1);
		}
		// open files
		in = new Scanner(new File(args[0]));
		out = new PrintWriter(new FileWriter(args[1]));
		// read lines from in, extract and print tokens from each line
		while( in.hasNextLine() ){
			lineNumber++;
			// trim leading and trailing spaces, then add one trailing space so 
			// split works on blank lines
			line = in.nextLine().trim() + " "; 
			// split line around white space 
			token = line.split("\\s+");  
			// prints each reversed token on a new line in output file
			for (int j = 0; j < token.length; j++){
				out.println(stringReverse(token[j],token[j].length()));
			}	
		}
		
		// close both files
		in.close();
		out.close();
	}
	
	// recursively reverses the first n characters of s
	public static String stringReverse(String s, int n){
		if (n > 0){
			return (stringReverse(s.substring(1), n-1) + s.charAt(0));
		}
		return "";
	}
}
