// Austin Yen
// 4/19/15
// Search.java
// Given a a file with a list of words, this program
// merge sorts it and then binary searches it to return
// the line number(s) that the word(s) requested is/are found
// if they exist.
import java.util.*;
import java.io.*;
public class Search {
	public static void main(String[] args) throws IOException{
		
		// if less than 2 arguments are given, print instructions and exit
		if(args.length<2){
			System.out.println("Usage: Search file target1, [target2 ..]");
			System.exit(1);
		}
		
		// adds all items in file to an arraylist--this is to find the number of lines
		ArrayList<String> sort = new ArrayList<String>();
		Scanner in = new Scanner(new File(args[0]));
		while(in.hasNextLine()){
			sort.add(in.nextLine());
		}
		
		// convert to array
		String[] sorted = new String[sort.size()];
		sorted = sort.toArray(sorted);
		
		// create array for line numbers
		int[] lineNumber = new int[sort.size()];
		for(int i = 0; i < sort.size(); i++){
			lineNumber[i] = i+1;
		}
		
		// sort the contents of the array
		mergeSort(sorted,lineNumber,0,sorted.length-1);
		
		// search for the terms specified as arguments
		// also provides the original line number if a term is found
		for (int i = 1; i <args.length; i++){
			int found = binarySearch(sorted,0,sorted.length-1,args[i]);
			if(found<0){
				System.out.println(args[i]+" not found.");
			}
			else if(found>=0){ //if the item is found, returns the index
				System.out.println(args[i]+" found on line "+lineNumber[found]);
			}
		}
		
	}
	
	
	// mergeSort()
	// sorts String array A[p,...,q]
	public static void mergeSort(String[] A, int[] B, int p, int r){
	      int q;
	      if(p < r) {
	         q = (p+r)/2;
	         // System.out.println(p+" "+q+" "+r);
	         mergeSort(A, B, p, q);
	         mergeSort(A, B, q+1, r);
	         merge(A, B, p, q, r);
	      }
	}

	// merge()
	// merges sorted subarrays A[p..q] and A[q+1..r]
	// performs the same operations on B to keep track of original line numbers
	public static void merge(String[] A, int[] B, int p, int q, int r){
		int n1 = q-p+1;
		int n2 = r-q;
		String[] L = new String[n1];
		String[] R = new String[n2];
		
		int[] lineNumbL = new int[n1];
		int[] lineNumbR = new int[n2];
		
		int i, j, k;

		for(i=0; i<n1; i++){
			L[i] = A[p+i];
			lineNumbL[i] = B[p+i];
		}
		

		
		for(j=0; j<n2; j++){
			R[j] = A[q+j+1];
			lineNumbR[j] = B[q+j+1];
		}
	
		i = 0; j = 0;
		for(k=p; k<=r; k++){
			if( i<n1 && j<n2 ){
				if( L[i].compareTo(R[j])<0 ){
					A[k] = L[i];
					B[k] = lineNumbL[i];
					i++;
				}else{
					A[k] = R[j];
					B[k] = lineNumbR[j];
					j++;
				}
			}else if( i<n1 ){
				A[k] = L[i];
				B[k] = lineNumbL[i];
	            i++;
			}else{ // j<n2
	            A[k] = R[j];
	            B[k] = lineNumbR[j];
	            j++;
			}
		}
	
	}
	// binarySearch()
	// pre: Array A[p..r] is sorted
	public static int binarySearch(String[] A, int p, int r,  String target){
		int q;
		if(p > r) {
			return -1;
		}else{
			q = (p+r)/2;
			if(target.compareTo(A[q]) == 0){
				return q;
			}else if(target.compareTo(A[q])<0){
				return binarySearch(A, p, q-1, target);
			}else{ // target > A[q]
				return binarySearch(A, q+1, r, target);
			}
		}
	}
}
