// Austin Yen
// CMPS 12B
// 4/9/15
// Extrema.java
// Finds the minimum and maximum integers in an array

class Extrema {
   
   // maxArray()
   // returns the largest value in int array A
   static int maxArray(int[] A, int p, int r){
	   if(r-p <= 1){
		   return max(A[r],A[p]);
	   }
	   else{
		   int q = (p+r)/2;
		   int leftmax = maxArray(A,p,q);
		   int rightmax = maxArray(A,q+1,r);
		   return max(leftmax, rightmax);
	   }
   }
   // max()
   // returns the larger value or the value itself if both are equal
   static int max(int value1,int value2){ 
	   if(value1<value2){
		   return value2;
	   }
	   else if(value1>value2){
		   return value1;
	   }
	   else if(value1 == value2){
		   return value1;
	   }
	   return 0;
   }
   // min()
   // returns the smaller value or the value itself if both are equal
   static int min(int value1,int value2){ 
	   if(value1<value2){
		   return value1;
	   }
	   else if(value1>value2){
		   return value2;
	   }
	   else if(value1 == value2){
		   return value1;
	   }
	   return 0;
   }
   
   // minArray()
   // returns the smallest value in int array A
   static int minArray(int[] A, int p, int r){
	   if(r-p <= 1){
		   return min(A[r],A[p]);
	   }
	   else{
		   int q = (p+r)/2;
		   int leftmin = minArray(A,p,q);
		   int rightmin = minArray(A,q+1,r);
		   return min(leftmin, rightmin);
	   }
   }
   
   
   // main()
   public static void main(String[] args){
      int[] B = {-1, 2, 6, 3, 9, 2, -3, -2, 11, 5, 7};
      System.out.println( "max = " + maxArray(B, 0, B.length-1) );  // output: max = 11
      System.out.println( "min = " + minArray(B, 0, B.length-1) );  // output: min = -3
   }
   
}