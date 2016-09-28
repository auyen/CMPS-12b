// Austin Yen
// 5/18/15
// Simulation.java
// Executes simulations on processing time based
// on an input file, and returns results on one 
// output file and the trace on another output file

import java.io.*;
import java.util.*;

public class Simulation{
   public static void main(String[] args) throws IOException{
	   int jobs;
	   
	   
//    1.  check command line arguments
	   if(args.length!=1){
		   System.out.println("Usage: Simulation infile");
		   System.exit(1);
	   }

	   
//    2.  open files for reading and writing
	   Scanner in = new Scanner(new File(args[0]));
	   String reportname = args[0]+".rpt";
	   String tracename = args[0]+".trc";
	   PrintWriter outRpt = new PrintWriter(new FileWriter(reportname));
	   PrintWriter outTrc = new PrintWriter(new FileWriter(tracename));

	   
//    3.  read in jobs from input file
	   jobs = in.nextInt();
	   Job[] allJobs = new Job[jobs];
	   Job[] backup = new Job[jobs];
	   int i = 0;
	   while(in.hasNextLine()){
		   backup[i] = new Job(in.nextInt(),in.nextInt());
		   i++;
	   }
	   
	   
	   // initial text for output files
	   outRpt.write("Report File: "+reportname+"\n"+jobs+" Jobs:\n");
	   outTrc.write("Trace File: "+tracename+"\n"+jobs+" Jobs:\n");
	   for(int a = 0; a < backup.length; a++){
		   outTrc.write(backup[a].toString()+" ");
		   outRpt.write(backup[a].toString()+" ");
	   }
	   outTrc.write("\n");
	   outRpt.write("\n");
	   outRpt.write("\n***********************************************************\n");
	   
	   
//    4.  run simulation with n processors for n=1 to n=m-1  {
	   for(int n = 1;n<jobs; n++ ){
		   int time = 0;

		   // text for output files 
		   outTrc.write("\n*****************************\n");
		   if(n == 1){
			   outTrc.write("1 processor: \n");
		   }
		   else{
			   outTrc.write(n+" processors: \n");
		   }
		   outTrc.write("*****************************\n");
		   outTrc.write("time = 0\n0: ");
		   for(int a = 0; a < backup.length; a++){
			   outTrc.write(backup[a].toString()+" ");
		   }
		   for(int b = 1; b <= n; b++){
			   outTrc.write("\n"+b+": ");
		   }
		   
		   outTrc.write("\n");
		   
		   // make a queue containing all the jobs using the backup
		   System.arraycopy(backup, 0, allJobs, 0, backup.length);
		   Queue storage = new Queue();
		   for(int j = 0; j < allJobs.length; j++){
			   storage.enqueue(allJobs[j]);
		   }
		   
//    5.      declare and initialize an array of n processor Queues and any 
//            necessary storage Queues
		   Queue[] processorQueue = new Queue[n];
		   for(int k = 0; k < n; k++){
			   processorQueue[k] = new Queue();
		   }
		   
		   
//
//    6.      while unprocessed jobs remain  {
		   boolean finished = false;
		   
		   // start from time = arrival time of first job
		   time = ((Job) storage.peek()).getArrival();
		   
		   while(!finished){
			// find potential time changes
			   ArrayList<Integer> times = new ArrayList<Integer>(); // this stores the actual arrival or finish times
			   ArrayList<Integer> processorindex = new ArrayList<Integer>(); // this stores the index of the times
			   ArrayList<Job> jobtimes = new ArrayList<Job>(); // this stores the corresponding jobs
			   if(!storage.isEmpty() && ((Job) storage.peek()).getFinish() == -1){
				   times.add(((Job) storage.peek()).getArrival());
				   jobtimes.add((Job) storage.peek());
				   processorindex.add(0);
			   }
			   for(int a = 0; a < processorQueue.length; a++){
				   if(processorQueue[a].length()>0){
					   times.add(((Job) processorQueue[a].peek()).getFinish());
					   jobtimes.add((Job) processorQueue[a].peek());
					   processorindex.add(a);
				   }   
			   }  
			   
			   
			   
			   // finding the soonest time out of possible ones
			   int min = times.get(0);
			   int a;
			   int index = 0;
			   for(a = 1; a < times.size(); a++){
				   if(min > times.get(a)){
					   min = times.get(a);
					   index = a;
				   }
			   }
			   time = min;
			   
			   
//    7.          determine the time of the next arrival or finish event and 
//                update time
			   
			   
			   
			   outTrc.write("\ntime="+time+"\n");

//    8.          complete all processes finishing now
			   
			   // if the job is from a processor
			   if(jobtimes.get(index).getFinish() == min){
				  storage.enqueue(processorQueue[processorindex.get(index)].dequeue());
				  if(!processorQueue[processorindex.get(index)].isEmpty()){
					  ((Job) processorQueue[processorindex.get(index)].peek()).computeFinishTime(time);
				  }
				   	   
			   }
			   

//    9.          if there are any jobs arriving now, assign them to a processor 
//                Queue of minimum length and with lowest index in the queue array.
			   
			   
			   // else if the job is from storage
			   // find the processor with the shortest queue
			   else if(jobtimes.get(index).getArrival() == min){
				   
				   // to account for items with the same arrival time
				   int arrival = 0;
				   if(storage.length() > 0){
					   arrival = ((Job) storage.peek()).getArrival();
				   }
				   while((storage.length() > 0) && ((Job) storage.peek()).getArrival() == arrival){
					   int processorsize = processorQueue[0].length();
					   int minindex = 0;
					   for(int b = 0; b < processorQueue.length; b++){
					   
						   if(processorQueue[b].length() < processorsize){
							   processorsize = processorQueue[b].length();
							   minindex = b;
						   }
					   }
				   
				   
					   if(processorQueue[minindex].isEmpty()){
						   ((Job) storage.peek()).computeFinishTime(time);
					   }
					   processorQueue[minindex].enqueue(storage.dequeue());
				   }	   
				   
			   }
			  
			   // print everything in the trace
			   outTrc.write("0: "+storage.toString()+"\n");
			   for(int c = 0; c < processorQueue.length; c++){
				   outTrc.write((c+1)+": "+processorQueue[c].toString()+"\n");
			   }
//
//    10.     } end loop
			   if(!storage.isEmpty() && ((Job) storage.peek()).getFinish() != -1 && storage.length() == jobs){
				   finished = true;
			   }
		   }  
		   
//
//    11.     compute the total wait, maximum wait, and average wait for 
//            all Jobs, then reset finish times
		   int totalWaitTime = 0;
		   int maxWaitTime = 0;
		   while(!storage.isEmpty()){
			   if(((Job) storage.peek()).getWaitTime() > maxWaitTime){
				   maxWaitTime = ((Job) storage.peek()).getWaitTime();
			   }
			   totalWaitTime += ((Job) storage.dequeue()).getWaitTime();
			   
		   }
		   double averageWaitTime = (double)((totalWaitTime*100)/jobs)/100;
		   if(n == 1){
			   outRpt.write("1 processor: ");
		   }	   
		   else{
			   outRpt.write(n+" processors: ");
		   }
		   outRpt.write("totalWait = "+totalWaitTime+", maxWait = "+maxWaitTime+", averageWait = "+averageWaitTime+"\n");
		   
		   for(int j = 0; j < allJobs.length; j++){
			   allJobs[j].resetFinishTime();
			   storage.enqueue(allJobs[j]);
		   }
//
//    12. } end loop
	   }
//
//    13. close input and output files
	   in.close();
	   outRpt.close();
	   outTrc.close();

   }
}

