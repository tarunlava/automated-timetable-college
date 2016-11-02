package timetable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class TableReader
{
	String currYear;
	Integer currSection;
	static String[][] table;
	static Scanner eyes;
	static File tableFile;
	
	/**
	 * <pre>
	 * reads the student table depending on the 
	 * given year and section
	 * </pre>
	 * @param currYear
	 * @param currSection
	 * @return 2D String Array which contains the student table for the given year and section
	 * @throws FileNotFoundException
	 */
	public static String[][] readTable(String currYear, Integer currSection) throws FileNotFoundException
	{
		tableFile = new File("table"+currYear+(char)(currSection+64)+".txt");
		eyes = new Scanner(tableFile);
		table = new String[7][8];
		for(int i = 0;eyes.hasNext();i++)
		{
			StringTokenizer tokenizer = new StringTokenizer(eyes.nextLine(), "\t");
			for(int j = 0;j<8;j++)
			{
					table[i][j]=tokenizer.nextToken();
			}
		}
		eyes.close();
		return table;
	}
	
	/**
	 * <pre>
	 * reads the teacher table for the given teacher
	 * </pre>
	 * @param teachName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String[][] readTable(String teachName) throws FileNotFoundException
	{
		tableFile = new File("TT"+teachName+".txt");
		eyes = new Scanner(tableFile);
		table = new String[7][8];
		for(int i = 0;eyes.hasNext();i++)
		{
			StringTokenizer tokenizer = new StringTokenizer(eyes.nextLine(), "\t");
			for(int j = 0;j<8;j++)
			{
					table[i][j]=tokenizer.nextToken();
			}
		}
		eyes.close();
		return table;
	}
	
	/**
	 * <pre>
	 * checks if the given teacher is a priority teacher
	 * </pre>
	 * 
	 * @param teachName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static boolean isPriorityTeach(String teachName) throws FileNotFoundException
	{
		String[][] list = getTeacherList();
		for(int i =0; i<noOfTeachers(); i++)	
			if(list[i][0].equals(teachName))
				if(list[i][1].equals("#"))
					return true;
		return false;
		
	}
	
	/**
	 * <pre>
	 * checks if the given subject is a priority subject
	 * by finding out if it's in a priority teacher's list
	 * <pre>
	 * @param subject
	 * @return
	 */
	public static boolean isPrioritySubj(String subject)
	{
		try
		{
			String[][] list = getTeacherList();
			int no = noOfTeachers();
			String[] subjs;
			for(int i =0;i<no;i++)	
			{
				if(isPriorityTeach(list[i][0]))
				{
					subjs=getTeachSubj(list[i][0]);
					if(subjs[0].equals(subject) || subjs[1].equals(subject))
					{
						return true;
					}
				}
			}
			
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * <pre>
	 * gets all the information about the teachers 
	 * from the teacher list
	 * </pre>
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String[][] getTeacherList() throws FileNotFoundException
	{
		int no = noOfTeachers();
		String[][] list = new String[no][4];
		tableFile = new File("TeacherList.txt");
		Scanner scanner = new Scanner(tableFile);
		eyes = scanner.useDelimiter("\t");
		for(int i = 0; i<no;i++)
		{
			for(int j = 0;j<4;j++)
			{
					String next = eyes.next();
					list[i][j]=next;
			}
		}
		scanner.close();
		eyes.close();
		return list;
	}
	
	/**
	 * reads the teacher lists and returns all the names
	 * 
	 * @return String array of all the teacher names
	 * @throws FileNotFoundException
	 */
	public static String[] getAllTeacherNames() throws FileNotFoundException
	{
		String[][] allTeach = getTeacherList();
		String[] names = new String[ noOfTeachers()];
		for(int i =0;i<names.length;i++)	
			names[i]=allTeach[i][0];
		return names;
	}
	
	/**
	 * counts the no of teachers in teacher list
	 * 
	 * @return number of teachers registered
	 * @throws FileNotFoundException
	 */
	public static int noOfTeachers() throws FileNotFoundException
	{
		int i;
		tableFile = new File("TeacherList.txt");
		Scanner scanner = new Scanner(tableFile);
		eyes = scanner.useDelimiter("\t");
		for(i=0;eyes.hasNext();i++)
			eyes.nextLine();
		scanner.close();
		eyes.close();
		return (i-1);
	}
	
	/**
	 * <pre>
	 * reads teacher list and gets the subjects 
	 * for the given teacher
	 * </pre>
	 * 
	 * @param teachName
	 * @return String array of the teacher's subjects
	 * @throws FileNotFoundException
	 */
	public static String[] getTeachSubj(String teachName) throws FileNotFoundException
	{
		
		String[][] list = getTeacherList();
		String[] subj = new String[2];
		for(int i = 0; i<noOfTeachers();i++)
		{
			if(list[i][0].equals(teachName))
			{
				subj[0]=list[i][2];
				subj[1]=list[i][3];
				return subj;
			}
		}
		return null;
	}
	
	/**<pre>
	 * read the file for the teacher name given
	 * and return that as an array
	 * </pre>
	 * @param teachName
	 * @return 2D string array containing the teacher table
	 * @throws FileNotFoundException
	 */
	public static String[][] readTeachTable(String teachName) throws FileNotFoundException
	{
		tableFile = new File("TT"+teachName+".txt");
		eyes = new Scanner(tableFile);
		table = new String[7][8];
		eyes.nextLine();
		for(int i = 0;eyes.hasNext();i++)
		{
			StringTokenizer tokenizer = new StringTokenizer(eyes.nextLine(), "\t");
			for(int j = 0;j<8;j++)
			{
					table[i][j]=tokenizer.nextToken();
			}
		}
		eyes.close();
		return table;
	}


}
