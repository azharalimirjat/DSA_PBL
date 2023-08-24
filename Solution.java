import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    ArrayList<CrashCase>[] month=new ArrayList[12];//ArrayList array that stores ArrayList of each month
    MyQueue monthWiseQueue=new MyQueue();//Queue storing data month wise
    MyQueue clearWeatherCrashCaseQueue=new MyQueue();
    MyQueue snowWeatherCrashCaseQueue=new MyQueue();
    MyQueue rainWeatherCrashCaseQueue=new MyQueue();
    MyQueue monday=new MyQueue();//queue having only monday cases
    MyQueue tuesday=new MyQueue();//queue having only tuesday cases
    MyQueue wednesday=new MyQueue();//queue having wednesday cases
    MyQueue thursday=new MyQueue();//queue having thursday cases
    MyQueue friday=new MyQueue();//queue having friday cases
    MyQueue saturday=new MyQueue();//queue having saturday cases
    MyQueue sunday=new MyQueue();//queue having sunday cases

//    I have stored the keys as January being root, even no months on left and odd no months on right in both the trees

    BinaryTree hit_and_run_tree;//tree having only no of hit_and_run cases with month
    BinaryTree not_hit_and_run_tree;//tree having only no of not_hit_and_run cases with month
    //filling month ArrayList array
    public void setMonth(){
        File file=new File("ped_crashes.csv");
        try {
            Scanner scan=new Scanner(file);
            scan.nextLine();
            while (scan.hasNextLine()){
                String[] parts=scan.nextLine().split(",");
                CrashCase c=new CrashCase(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5], parts[6],parts[7],parts[8],parts[9],parts[10],parts[11],parts[12],parts[13],parts[14]);
                switch (parts[1]){
                    case "January"->{
                        if(month[0]==null)month[0]=new ArrayList();
                        month[0].add(c);
                    }
                    case "February"->{
                        if(month[1]==null)month[1]=new ArrayList();
                        month[1].add(c);
                    }
                    case "March"->{
                        if(month[2]==null)month[2]=new ArrayList();
                        month[2].add(c);
                    }
                    case "April"->{
                        if(month[3]==null)month[3]=new ArrayList();
                        month[3].add(c);
                    }
                    case "May"->{
                        if(month[4]==null)month[4]=new ArrayList();
                        month[4].add(c);
                    }
                    case "June"->{
                        if(month[5]==null)month[5]=new ArrayList();
                        month[5].add(c);
                    }
                    case "July"->{
                        if(month[6]==null)month[6]=new ArrayList();
                        month[6].add(c);
                    }
                    case "August"->{
                        if(month[7]==null)month[7]=new ArrayList();
                        month[7].add(c);
                    }
                    case "September"->{
                        if(month[8]==null)month[8]=new ArrayList();
                        month[8].add(c);
                    }
                    case "October"->{
                        if(month[9]==null)month[9]=new ArrayList();
                        month[9].add(c);
                    }
                    case "November"->{
                        if(month[10]==null)month[10]=new ArrayList();
                        month[10].add(c);
                    }
                    case "December"->{
                        if(month[11]==null)month[11]=new ArrayList();
                        month[11].add(c);
                    }

                }
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    //inserting data into wise queue month wise with January on first in and first out
    public void setMonthWiseQueue(){
        for(ArrayList<CrashCase> list:month)
            for (CrashCase CrashCase : list) monthWiseQueue.add(CrashCase);
    }
    public void setSubQueues(){
        MyQueue queue=monthWiseQueue.copy();
        int size= queue.size();
        for(int i=0;i<size;i++){
            CrashCase c=queue.remove();
            switch (c.getWeather_condition()) {
                case "Clear" -> clearWeatherCrashCaseQueue.add(c);
                case "Rain" -> rainWeatherCrashCaseQueue.add(c);
                case "Snow" -> snowWeatherCrashCaseQueue.add(c);
            }
        }
    }
    public void setWeekQueues(){
        MyQueue queue=monthWiseQueue.copy();
        int size= queue.size();
        for(int i=0;i<size;i++){
            CrashCase c=queue.remove();
            switch (c.getDay().toLowerCase()){
                case "monday"->monday.add(c);
                case "tuesday"->tuesday.add(c);
                case "wednesday"->wednesday.add(c);
                case "thursday"->thursday.add(c);
                case "friday"->friday.add(c);
                case "saturday"->saturday.add(c);
                case "sunday"->sunday.add(c);
            }
        }
    }
    public void fillTree(){
        MyQueue queue=monthWiseQueue.copy();
        int size= queue.size();
        String month="January";
        int hit_run=0,not_hit_run=0;
        for(int i=0;i<size;i++){
            CrashCase c=queue.remove();
            if(i==size-1){
                if(c.getHit_run().equals("Hit-and-run"))hit_run++;
                else if (c.getHit_run().equals("Not hit-and-run"))not_hit_run++;
                hit_and_run_tree.add("left",hit_run+","+month);
                not_hit_and_run_tree.add("left",not_hit_run+", "+month);
                return;
            }
            if (!month.equals(c.getMonth())) {
                boolean b = month.equals("February") || month.equals("April") || month.equals("June") || month.equals("August") || month.equals("October") ;
                if (hit_and_run_tree == null) hit_and_run_tree = new BinaryTree(hit_run+", "+month);
                else {
                    if (b) hit_and_run_tree.add("left", hit_run+", "+month);
                    else hit_and_run_tree.add("right", hit_run+", "+month);
                }
                if (not_hit_and_run_tree == null) not_hit_and_run_tree = new BinaryTree(not_hit_run+", "+month);
                else {
                    if (b) not_hit_and_run_tree.add("left", not_hit_run+", "+month);
                    else not_hit_and_run_tree.add("right", not_hit_run+", "+month);
                }
                month = c.getMonth();
                hit_run = 0;
                not_hit_run = 0;
            }
            if(c.getHit_run().equals("Hit-and-run"))hit_run++;
            else if (c.getHit_run().equals("Not hit-and-run"))not_hit_run++;

        }
    }
    public String deadliestDay(MyQueue day){
        //max1: maximum cases for current date and max2 for maximum no of  cases on any date
        int max1=1,max2=1;
        //duplicate queue so that remove method can be called
        MyQueue queue=day.copy();
        //size of queue
        int size= queue.size();
        //CrashCase object for getting starting date
        CrashCase c=queue.remove();
        //current date
        String date=c.getDate()+"/"+c.getMonth()+"/"+c.getYear();
        //initializing tempDate
        String tempDate=date;
        String date1="";
        for(int i=1;i<size;i++){
            //getting one by one object
            CrashCase c1=queue.remove();
            //getting date of every object
            date1=c1.getDate()+"/"+c1.getMonth()+"/"+c1.getYear();
            //checking weather date is same
            if(date1.equals(date))max1++;
            //if date has been changed
            else{
                //if cases on previous date are greater than the previous maximum cases
                if(max1>max2)tempDate=date;
                //getting the max no of cases
                max2=Math.max(max1,max2);
                max1=1;
                //updating the current date
                date=date1;
            }
        }
        if(max1>max2)tempDate=date;
        return Math.max(max1,max2)+", were the maximum number of cases happened on "+tempDate+" on "+c.getDay();
    }
    public void question1( ){
        int max1,max2,maxFriday;
        String fridayDate;
        String[] s=deadliestDay(monday).split(",");
        String str=s[1];
        max1=Integer.parseInt(s[0]);
        String[] s1=deadliestDay(tuesday).split(",");
        max2=Integer.parseInt(s1[0]);
        if(max2>max1)str=s1[1];
        max1=Math.max(max1,max2);
        s1=deadliestDay(wednesday).split(",");
        max2=Integer.parseInt(s1[0]);
        if(max2>max1)str=s1[1];
        max1=Math.max(max1,max2);
        s1=deadliestDay(thursday).split(",");
        max2=Integer.parseInt(s1[0]);
        if(max2>max1)str=s1[1];
        max1=Math.max(max2,max1);
        s1=deadliestDay(friday).split(",");
        max2=Integer.parseInt(s1[0]);
        maxFriday=max2;
        fridayDate=s1[1];
        if(max2>max1)str=s1[1];
        max1=Math.max(max1,max2);
        s1=deadliestDay(saturday).split(",");
        max2=Integer.parseInt(s1[0]);
        if(max2>max1)str=s1[1];
        max1=Math.max(max1,max2);
        s1=deadliestDay(sunday).split(",");
        max2=Integer.parseInt(s1[0]);
        if(max2>max1)str=s1[1];
        max1=Math.max(max1,max2);
        System.out.println(max1+": "+str);
        System.out.println(maxFriday+","+fridayDate+". It was the deadliest Friday among all the Fridays");

    }
    public void question2(){
        System.out.println("Rainy day Crashes of every month");
        MyQueue queue=rainWeatherCrashCaseQueue.copy();
        int size=queue.size();
        int count=0;
        String month=queue.remove().getMonth();
        for(int i=1;i<size;i++){
            String month1=queue.remove().getMonth();
            if(month1.equals(month))count++;
            else {
                System.out.println(month+" CrashCase "+count);
                count=0;
                month=month1;
            }
        }
        System.out.println(month+" CrashCase "+count);
    }
    public void question3(){
        System.out.println("1. Which month had the lowest \"hit and run\" CrashCase?\t\t"+hit_and_run_tree.getSmallest());
        System.out.println("2. Which month had the highest \"hit and run\" CrashCase?\t\t"+hit_and_run_tree.getLargest());
        System.out.println("3. Which month had the lowest \"not hit and run\" CrashCase?\t\t"+not_hit_and_run_tree.getSmallest());
        System.out.println("4. Which month had the highest \"not hit and run\" CrashCase?\t\t"+not_hit_and_run_tree.getLargest());
    }
    public void postulate1(){
        int mondaysCrashCase=0,tuesdaysCrashCase=0,saturdaysCrashCase=0,sundayCrashCase=0;
        MyQueue[] queues={monday,tuesday,saturday,sunday};
        for(MyQueue q:queues){
            int size=q.size();
            for(int i=0;i<size;i++){
                String lightCondition=q.remove().getLight_condition();
                if(lightCondition.equals("Dark lighted")){
                    if (monday.equals(q)) mondaysCrashCase++;
                    else if (tuesday.equals(q)) tuesdaysCrashCase++;
                    else if (saturday.equals(q)) saturdaysCrashCase++;
                    else if (sunday.equals(q)) sundayCrashCase++;
                }
            }
        }
        System.out.println("CrashCase when light condition was dark lighted");
        System.out.println("Monday CrashCase "+mondaysCrashCase);
        System.out.println("Tuesday CrashCase "+tuesdaysCrashCase);
        System.out.println("Saturday CrashCase "+saturdaysCrashCase);
        System.out.println("Sunday CrashCase "+sundayCrashCase);
    }

    public static void main(String[] args) {
        Solution s=new Solution();
        s.setMonth();
        s.setMonthWiseQueue();
        s.setSubQueues();
        s.setWeekQueues();
        s.fillTree();
        while (true){
            System.out.println("\n\n\n");
            System.out.println("1.See which day was the deadliest among all the days from Monday to Sunday and Which month's Friday was deadliest?");
            System.out.println("2.See no of Rainy Crashes in every month");
            System.out.println("3.Lowest and highest hit_and_run and not_hit_and_run");
            System.out.println("4.Comparing Monday and Tuesday crashes with Saturday and Sunday (Postulate1)");
            System.out.println("5.Exit");
            Scanner scan=new Scanner(System.in);
            switch (scan.next()){
                case "1"->s.question1();
                case "2"->s.question2();
                case "3"->s.question3();
                case "4"->s.postulate1();
                case "5"->System.exit(0);
            }
        }
    }
}
