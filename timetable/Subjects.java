package timetable;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//import au.com.bytecode.opencsv.CSVReader;
//import au.com.bytecode.opencsv.CSVWriter;

import java.util.ArrayList;

public class Subjects
{
//separate each into core subj,labs and non-core subj
	public String[] subjName21CSE = {"DSC","EDC","BEE","MFCS","PS","DLD","DSC/EDC/BEE(L)","21CSE"};
	public String[] subjName22CSE = {"CO","DMBS","OOP","ES","FLAT","DAA","OOP/DBMS(L)","22CSE"};
	public String[] subjName31CSE = {"DCCN","PPL","OR","OS","MPI","SE","CNOS/MPI(L)","31CSE"};
	public String[] subjName32CSE = {"OOAD","NS","CD","VLSI","MEFA","WT","AECS/CD/WT(L)","32CSE"};
	public ArrayList<String[]> subject;
	public String[] miscCSE = {"MS","ES","MEFA","VLSI","EDC","BEE","MPI","OR","PS","PPL"};
	public String[] impCSE = {"DSC","OOP","LP"};
	
	
	//need to define constants for each course
	public Subjects()
	{
		subject = new ArrayList<String[]>();
		subject.add(subjName21CSE);
		subject.add(subjName22CSE);
		subject.add(subjName31CSE);
		subject.add(subjName32CSE);
	}
	public boolean isLab(int i, int course)
	{
		if(subject.get(course)[i].endsWith("(L)"))
			return true;
		else
			return false;
	}
	
	public boolean isLab(String subj)
	{
		if(subj.endsWith("(L)"))
				return true;
		else
			return false;
	}
	
	public boolean isImpSub(String subj)
	{
		for (String i : impCSE)
			if(i.equalsIgnoreCase(subj))
				return true;
		return false;
	}
	//may need to make a method for temp so that its size is the no. of labs in a course
	
	public String[] getLabs(int course)
	{
		//alternatively, for only 2 labs, set the length of temp1 to 2 then temp2 and 2nd for loop would not be necessary
		String[] temp1 = new String[subject.get(course).length];
		int j=0;
		for(int i = 0;i<subject.get(course).length;i++)
			if(isLab(i,course))
				temp1[j++]=subject.get(course)[i];
		String[] temp2 = new String[j];
		for(int i = 0;i<j;i++)
			temp2[i] = temp1[i]; 
		return temp2;
	}
	
	public boolean isMiscSubj(String subj)
	{
		for (String i : miscCSE)
			if(i.equalsIgnoreCase(subj))
				return true;
		return false;
	}
	
	public boolean isCoreSubj(String subj)
	{
		if(isMiscSubj(subj)==false)
			if(isImpSub(subj)==false)
				return true;
		return false;
	}
}
