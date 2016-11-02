package timetable;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;



public class Newtab extends JFrame
{
	private JPanel contentPane;
	private JTable tableStudents;
	private JTable tableTeachers;
	private JTable tableLabs;

	JPanel panWelcome;
	JPanel panTeacherGen;
	JPanel panTeacherTable;
	JPanel panTeachTable;
	JPanel panStudentTable;
	JPanel panSwitch;
	JPanel panSwitchTable;
	JPanel panSwitchSelect;
	JPanel panLabTable;
	DefaultTableModel dtmStudent;
	DefaultTableModel dtmTeacher;
	DefaultTableModel dtmLab;
	
	ArrayList<String> filledForWeek;
	ArrayList<String> filledForDay;
	ArrayList<String> facultyList;
	String teachName;
	String currYear;
	String[] year;
	String[] currSubjects;
	String[] sections;
	String[] teachList;
	String[][] readTable;
	Integer currYearNo;
	Integer noOfSec;
	Integer currSection;
	Integer fileNo;
	

	CardLayout cardlay;
	CardLayout switchCard;
	JToggleButton tglbtnSwitch;
	JCheckBox chckbxA;
	JCheckBox chckbxB;
	JCheckBox chckbxC;
	JCheckBox chckbxD;
	JCheckBox chckbxE;
	JCheckBox chckbxPriority;
	JCheckBox chckbxSubj1;
	JCheckBox chckbxSubj2;
	
	
	boolean[] checkedSections;
	JButton btnGoToTeachGen;
	JButton btnGoToStudentTables;
	JButton btnGoToTeachTable;
	JButton btnGoToLabTable;
	JButton btnToChosen;
	JButton btnToAvail;
	JButton btnNextTeach;
	JButton btnGoToClass;
	JButton btnClrChosen;
	JButton btnLockTeach;
	JButton btnClrTeachTable;
	JButton btnHome;
	JButton btnGoToTeach;
	private JTextField txtTeachNameReg;
	
	Subjects sub;
		
	JLabel lblWelcome;
	JLabel lblName;
	JLabel lblYrSemTeacher;
	JLabel lblAvailableSubjects;
	JLabel lblFacultyPanel;
	JLabel lblChooseFaculty;
	JLabel lblNameTeach;
	JLabel lblDisplayName;
	JLabel lblChosenSubjects;
	JLabel lblDisplayChosen;
	JLabel lblsec;
	JLabel lblGetsec;
	
	JComboBox<String> selYear1;
	JComboBox<String> selYear2;
	JComboBox<String> selectSec;
	JComboBox<String> selectTeach;
	JComboBox<String> selectSec1;
	
	
	JList<String> listAvailSubj;
	JList<String> listChosenSubj;
	DefaultListModel<String> availSubj;
	DefaultListModel<String> chosenSubj;
	private JLabel lblSub1;
	private JLabel lblSub2;
	private JLabel lblSub3;
	private JLabel lblSub4;
	private JLabel lblSub5;
	private JLabel lblSub6;
	private JButton btnRefreshSubj;
	private JLabel lblDev;
	private JButton btnEnlarge;

	JFrame loading;
	JLabel load;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Newtab frame = new Newtab();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Newtab()
	{
		setResizable(false);
		
		sub = new Subjects();
		year=new String[] {"","3-2","3-1", "2-2","2-1"};
		sections = new String[] {"0","1","2","3","4"};
		
		loading = new JFrame("Loading");
		load = new JLabel("Generating...");
		loading.getContentPane().add(load);
		loading.setVisible(false);
		loading.setBounds(100, 100, 200, 170);

		
		try
		{
			teachList = TableReader.getAllTeacherNames();
		} catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}


		
		facultyList = new ArrayList<String>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		cardlay = new CardLayout(0, 0);
		contentPane.setLayout(cardlay);

		////////////////////////////////////////////////////WELCOME PANEL//////////////////////////////////////////////////////////////////
		panWelcome = new JPanel();
		contentPane.add(panWelcome, "WelcomePanel");
		panWelcome.setLayout(null);
		
		lblWelcome = new JLabel("Automatic College Time Table Generator");
		lblWelcome.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblWelcome.setBounds(76, 34, 448, 36);
		panWelcome.add(lblWelcome);
		
		btnGoToTeachGen = new JButton("Enter Faculty");
		btnGoToTeachGen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoToTeachGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlay.show(contentPane, "Faculty Panel");
			}
		});
		btnGoToTeachGen.setBounds(182, 88, 249, 53);
		panWelcome.add(btnGoToTeachGen);
		
		btnGoToStudentTables = new JButton("Student Class Tables");
		btnGoToStudentTables.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoToStudentTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlay.show(contentPane, "StudentPanel");

			}
		});
		btnGoToStudentTables.setBounds(182, 159, 249, 53);
		panWelcome.add(btnGoToStudentTables);
		
		btnGoToTeachTable = new JButton("Teacher Table");
		btnGoToTeachTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoToTeachTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardlay.show(contentPane, "Faculty Tables");
			}
		});
		btnGoToTeachTable.setBounds(182, 233, 249, 53);
		panWelcome.add(btnGoToTeachTable);
		
		btnGoToLabTable = new JButton("Lab Tables");
		btnGoToLabTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardlay.show(contentPane, "Lab Tables");
			}
		});
		btnGoToLabTable.setBounds(506, 226, 151, 60);
		panWelcome.add(btnGoToLabTable);
		
		lblDev = new JLabel("Developed by Tarun T. Lava, M. Sai Chaitanya and T. Sai Srikanth");
		lblDev.setHorizontalAlignment(SwingConstants.CENTER);
		lblDev.setBounds(10, 301, 647, 14);
		panWelcome.add(lblDev);
		btnGoToLabTable.setVisible(false);
		
		/////////////////////////////////////////////////////FACULTY GENERATOR PANEL/////////////////////////////////////
		panTeacherGen = new JPanel();
		contentPane.add(panTeacherGen, "Faculty Panel");
		panTeacherGen.setLayout(null);
		
		lblName = new JLabel("Name");
		lblName.setBounds(20, 29, 64, 14);
		panTeacherGen.add(lblName);
		
		txtTeachNameReg = new JTextField();
		txtTeachNameReg.setBounds(94, 26, 86, 20);
		panTeacherGen.add(txtTeachNameReg);
		txtTeachNameReg.setColumns(10);
		
		selYear1 = new JComboBox<String>();
		selYear1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currYear= (String) selYear1.getSelectedItem();
				setCurrSubjects(currYear);
				selYear2.setSelectedItem(currYear);

			}
		});
		selYear1.setModel(new DefaultComboBoxModel<String>((year)));	
		selYear1.setBounds(94, 54, 86, 20);
		panTeacherGen.add(selYear1);
		
		lblYrSemTeacher = new JLabel("Year-Sem");
		lblYrSemTeacher.setBounds(20, 57, 64, 14);
		panTeacherGen.add(lblYrSemTeacher);
		
		lblAvailableSubjects = new JLabel("Available Subjects");
		lblAvailableSubjects.setBounds(94, 85, 104, 20);
		panTeacherGen.add(lblAvailableSubjects);
		
		listAvailSubj = new JList<String>();
		listAvailSubj.setBorder(new LineBorder(new Color(0, 0, 0)));
		listAvailSubj.setBounds(94, 113, 79, 151);
		availSubj =  new DefaultListModel<String>();
		listAvailSubj.setModel(availSubj);
		panTeacherGen.add(listAvailSubj);
		
		btnToChosen = new JButton(">");
		btnToChosen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] indices = listAvailSubj.getSelectedIndices();
				for(int i = 0;i<indices.length;i++)
				{	
					chosenSubj.addElement(availSubj.get(indices[i]));
					availSubj.remove((indices[i]));
				}	

			}
		});
		btnToChosen.setBounds(250, 130, 41, 23);
		panTeacherGen.add(btnToChosen);
		
		btnToAvail = new JButton("<");
		btnToAvail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rem =chosenSubj.remove(listChosenSubj.getSelectedIndex());
				availSubj.addElement(rem);
			}
		});
		btnToAvail.setBounds(250, 191, 41, 23);
		panTeacherGen.add(btnToAvail);
		
		listChosenSubj = new JList<String>();
		listChosenSubj.setBorder(new LineBorder(new Color(0, 0, 0)));
		listChosenSubj.setBounds(371, 113, 79, 151);
		chosenSubj = new DefaultListModel<String>();
		listChosenSubj.setModel(chosenSubj);
		panTeacherGen.add(listChosenSubj);
		
		btnNextTeach = new JButton("Next Faculty");
		btnNextTeach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFaculty();
			}
		});
		btnNextTeach.setBounds(488, 230, 124, 23);
		panTeacherGen.add(btnNextTeach);
		
		btnGoToClass = new JButton("Gen Class");
		btnGoToClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardlay.show(contentPane, "StudentPanel");
			}
		});
		btnGoToClass.setBounds(488, 177, 124, 23);
		panTeacherGen.add(btnGoToClass);
		
		btnClrChosen = new JButton("Clear");
		btnClrChosen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								chosenSubj.removeAllElements();
			}
		});
		btnClrChosen.setBounds(371, 278, 89, 23);
		panTeacherGen.add(btnClrChosen);
		
		lblFacultyPanel = new JLabel("Faculty Panel");
		lblFacultyPanel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFacultyPanel.setBounds(250, 20, 142, 29);
		panTeacherGen.add(lblFacultyPanel);
		
		btnLockTeach = new JButton("Accept");
		btnLockTeach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtTeachNameReg.getText().equals(""))
					JOptionPane.showMessageDialog(null, "enter teacher's name");
				else if(selYear1.getSelectedIndex()==0)
					JOptionPane.showMessageDialog(null, "select year/sem");
	//			else if(listChosenSubj.getSelectedIndices().length!=2)
		//			JOptionPane.showMessageDialog(null, "2 subjects must be chosen");
				else
				{
					String[] temp = new String[chosenSubj.getSize()];
					for(int i = 0;i<temp.length;i++)
						temp[i]=chosenSubj.get(i);
					try
					{
						makeFaculty(txtTeachNameReg.getText(), temp, chckbxPriority.isSelected());

						try
						{
							teachList = TableReader.getAllTeacherNames();
						} catch (FileNotFoundException e1)
						{
							e1.printStackTrace();
						}

					} catch (IOException e)
					{
						System.out.println("IO problem");
						e.printStackTrace();
					}
				}
			}
		});
		btnLockTeach.setBounds(488, 130, 124, 23);
		panTeacherGen.add(btnLockTeach);
		
		chckbxPriority = new JCheckBox("Priority");
		chckbxPriority.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPriority.setBounds(371, 57, 97, 23);
		panTeacherGen.add(chckbxPriority);
		
		JButton btnHome_2 = new JButton("Home");
		btnHome_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardlay.show(contentPane, "WelcomePanel");
			}
		});
		btnHome_2.setBounds(228, 278, 89, 23);
		panTeacherGen.add(btnHome_2);
		
		JLabel lblChosenSubjects_1 = new JLabel("Chosen Subjects");
		lblChosenSubjects_1.setBounds(371, 85, 104, 17);
		panTeacherGen.add(lblChosenSubjects_1);
		switchCard = new CardLayout(0, 0);
////////////////////////////////////////////////////////////////////////TEACHER TABLES/////////////////////////////////////////
		
		panTeacherTable = new JPanel();
		contentPane.add(panTeacherTable, "Faculty Tables");
		panTeacherTable.setLayout(null);
		
		lblChooseFaculty = new JLabel("Choose faculty");
		lblChooseFaculty.setBounds(43, 30, 90, 14);
		panTeacherTable.add(lblChooseFaculty);
				
		lblNameTeach = new JLabel("Name :");
		lblNameTeach.setBounds(44, 84, 46, 14);
		panTeacherTable.add(lblNameTeach);
		
		lblDisplayName = new JLabel("");
		lblDisplayName.setBounds(89, 84, 106, 14);
		panTeacherTable.add(lblDisplayName);
		
		lblChosenSubjects = new JLabel("Chosen Subjects  :");
		lblChosenSubjects.setBounds(225, 84, 106, 14);
		panTeacherTable.add(lblChosenSubjects);
		
		lblDisplayChosen = new JLabel("");
		lblDisplayChosen.setBounds(341, 84, 124, 14);
		panTeacherTable.add(lblDisplayChosen);
		
		tableTeachers = new JTable();
		resetTable(1);
		tableTeachers.setBounds(43, 123, 572, 112);
		panTeacherTable.add(tableTeachers);
		
		btnClrTeachTable = new JButton("Clear");
		btnClrTeachTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTable(1);
			}
		});
		btnClrTeachTable.setBounds(43, 264, 132, 23);
		panTeacherTable.add(btnClrTeachTable);
		
		btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardlay.show(contentPane, "WelcomePanel");

			}
		});
		btnHome.setBounds(235, 281, 114, 34);
		panTeacherTable.add(btnHome);
		
		btnGoToTeach = new JButton("Faculty Generation");
		btnGoToTeach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardlay.show(contentPane, "Faculty Panel");
			}
		});
		btnGoToTeach.setBounds(43, 303, 132, 23);
		panTeacherTable.add(btnGoToTeach);
		
		chckbxA = new JCheckBox("A");
		chckbxA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxA.isSelected())
					checkedSections[0]=true;
				else
					checkedSections[0]=false;

			}
		});
		chckbxA.setBounds(621, 134, 40, 23);
		chckbxA.setVisible(false);
		panTeacherTable.add(chckbxA);
		
		chckbxB = new JCheckBox("B");
		chckbxB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxB.isSelected())
					checkedSections[1]=true;
				else
					checkedSections[1]=false;

			}
		});
		chckbxB.setBounds(621, 160, 40, 23);
		chckbxB.setVisible(false);
		panTeacherTable.add(chckbxB);
		
		 chckbxC = new JCheckBox("C");
		chckbxC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxC.isSelected())
					checkedSections[2]=true;
				else
					checkedSections[2]=false;

			}
		});
		chckbxC.setBounds(621, 186, 40, 23);
		chckbxC.setVisible(false);
		panTeacherTable.add(chckbxC);
		
		chckbxD = new JCheckBox("D");
		chckbxD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxD.isSelected())
					checkedSections[3]=true;
				else
					checkedSections[3]=false;

			}
		});
		chckbxD.setBounds(621, 212, 40, 23);
		chckbxD.setVisible(false);
		panTeacherTable.add(chckbxD);
		
		JButton btnMakeTable = new JButton("Make Table");
		btnMakeTable.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				displayTeacherTable();		
			}
		});
		btnMakeTable.setBounds(433, 264, 132, 23);
		panTeacherTable.add(btnMakeTable);
		
		JButton btnLockTeachTable = new JButton("Lock ");
		btnLockTeachTable.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{			
				try
				{
					teachName = (String)selectTeach.getSelectedItem();
					teachName = teachName.substring(2);
					writeTable(1);
				} catch (IOException e)
				{
						e.printStackTrace();
				}
			}
		});
		btnLockTeachTable.setBounds(433, 303, 132, 23);
		panTeacherTable.add(btnLockTeachTable);

		selectTeach = new JComboBox<String>();
		selectTeach.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String name = (String)selectTeach.getSelectedItem();
				lblDisplayName.setText(name);
				try
				{
					lblDisplayChosen.setText(TableReader.getTeachSubj(name)[0] + ", "+ TableReader.getTeachSubj(name)[1]);
					chckbxSubj1.setText(TableReader.getTeachSubj(name)[0]);
					chckbxSubj2.setText(TableReader.getTeachSubj(name)[1]);
				} catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
			}
		});
		selectTeach.setBounds(143, 27, 79, 20);
		selectTeach.setModel(new DefaultComboBoxModel<String>((teachList)));	
		panTeacherTable.add(selectTeach);
		
		JLabel lblNoOfSections = new JLabel("No of Sections");
		lblNoOfSections.setBounds(340, 30, 79, 14);
		panTeacherTable.add(lblNoOfSections);
		
		selectSec1 = new JComboBox<String>();
		selectSec1.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent arg0) 
			{
				noOfSec =  selectSec1.getSelectedIndex();
				currSection=1;
				checkedSections = new boolean[noOfSec];
				switch(noOfSec)
				{
				case 4: 
					chckbxD.setVisible(true);
					checkedSections[3]=false;
				case 3:
					chckbxC.setVisible(true);
					checkedSections[2]=false;
				case 2:
					chckbxB.setVisible(true);
					checkedSections[1]=false;
				case 1:
					chckbxA.setVisible(true);
					checkedSections[0]=false;
					break;					
				}
			}
		});
		selectSec1.setModel(new DefaultComboBoxModel<String>((sections)));	
		selectSec1.setBounds(433, 27, 52, 20);
		panTeacherTable.add(selectSec1);
		
		chckbxSubj1 = new JCheckBox("subj1");
		chckbxSubj1.setBounds(564, 52, 97, 23);
		panTeacherTable.add(chckbxSubj1);
		
		chckbxSubj2 = new JCheckBox("subj2");
		chckbxSubj2.setBounds(564, 80, 97, 23);
		panTeacherTable.add(chckbxSubj2);
		
		btnEnlarge = new JButton("Enlarge");
		btnEnlarge.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame view = new JFrame();
				JTable tempTable = new JTable();
				tempTable.setModel(tableTeachers.getModel());
				view.getContentPane().add(tempTable);
				view.setVisible(true);
				view.setBounds(100, 100, 600, 170);
			}
		});
		btnEnlarge.setBounds(452, 80, 97, 23);
		panTeacherTable.add(btnEnlarge);
		
		//////////////////////////////////////////////////////////////STUDENT TABLES///////////////////////////
		 panStudentTable = new JPanel();
		contentPane.add(panStudentTable, "StudentPanel");
		panStudentTable.setLayout(null);
		
		JLabel lblyear = new JLabel("Year");
		lblyear.setBounds(29, 11, 34, 14);
		panStudentTable.add(lblyear);
		
		selYear2 = new JComboBox<String>();
		selYear2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currYear= (String) selYear2.getSelectedItem();
				setCurrSubjects(currYear);
				selYear1.setSelectedItem(currYear);
			}
		});
		selYear2.setModel(new DefaultComboBoxModel<String>((year)));	
		selYear2.setBounds(94, 8, 67, 20);
		panStudentTable.add(selYear2);
		
		selectSec = new JComboBox<String>();
		selectSec.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				noOfSec = Integer.parseInt((String) selectSec.getSelectedItem());
				currSection=1;
				lblGetsec.setText(""+(char)(currSection+64));
				checkedSections = new boolean[noOfSec];

			}
		});
		selectSec.setModel(new DefaultComboBoxModel<String>((sections)));	
		selectSec.setBounds(516, 8, 52, 20);
		panStudentTable.add(selectSec);
		
		lblsec = new JLabel("No.of Sections");
		lblsec.setBounds(419, 11, 76, 14);
		panStudentTable.add(lblsec);
		
		JLabel lblSemester = new JLabel("Section :");
		lblSemester.setBounds(10, 58, 57, 14);
		panStudentTable.add(lblSemester);
		
		lblGetsec = new JLabel(">");
		lblGetsec.setBounds(77, 58, 46, 14);
		panStudentTable.add(lblGetsec);
		
		JButton btnFillLabs = new JButton("Fill Labs");
		btnFillLabs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkSecSelected())
					fillLabs();
			}
		});
		btnFillLabs.setBounds(173, 54, 89, 23);
		panStudentTable.add(btnFillLabs);
		
		JButton btnFillTuts = new JButton("Fill Tuts");
		btnFillTuts.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(checkSecSelected())
				{
					fillTut();
					fillTut2Subj();
				}
			}
		});
		btnFillTuts.setBounds(286, 54, 89, 23);
		panStudentTable.add(btnFillTuts);
		
		JButton btnFillSubs = new JButton("Fill Subs");
		btnFillSubs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkSecSelected())
				{
					loading.setVisible(true);
					fillTheory();
					displaySubCount();
					loading.setVisible(false);
				}
			}
		});
		btnFillSubs.setBounds(527, 54, 89, 23);
		panStudentTable.add(btnFillSubs);
		
		JButton btnFillExtra = new JButton("Fill Impt");
		btnFillExtra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkSecSelected())
				{
					fillPrioritySub();
					fillImptSubj();
					displaySubCount();
				}
			}
		});
		btnFillExtra.setBounds(406, 54, 89, 23);
		panStudentTable.add(btnFillExtra);
		
		tableStudents = new JTable();
		resetTable(0);
		tableStudents.setBounds(10, 119, 546, 112);
		panStudentTable.add(tableStudents);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkTableIfFull()==false)
				{
					int dialogResult = JOptionPane.showConfirmDialog (null, "Table is not yet full. Continue?","Warning",currSection);
					if(dialogResult == JOptionPane.YES_OPTION)	
					{
						if(currSection<noOfSec)
						{	
							currSection++;
							lblGetsec.setText(""+(char)(currSection+64));
							resetTable(0);
						}
						else
							JOptionPane.showMessageDialog(null, "All sections over");
					}
				}
				else
				{
					if(currSection<noOfSec)
					{	
						currSection++;
						lblGetsec.setText(""+(char)(currSection+64));
						resetTable(0);
					}
					else
						JOptionPane.showMessageDialog(null, "All sections over");
				}
			}
		});
		btnNext.setBounds(43, 258, 115, 23);
		panStudentTable.add(btnNext);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetTable(0);
				displaySubCount();
			}
		});
		btnReset.setBounds(505, 292, 111, 23);
		panStudentTable.add(btnReset);
		
		JButton btnHome_1 = new JButton("Home");
		btnHome_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				cardlay.show(contentPane, "WelcomePanel");
				resetTable(0);
				lblGetsec.setText("");
				selectSec.setSelectedIndex(0);
				selYear1.setSelectedIndex(0);
			}
		});
		btnHome_1.setBounds(266, 292, 115, 23);
		panStudentTable.add(btnHome_1);
		
		JButton btnLock = new JButton("Lock");
		btnLock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					writeTable(0);
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});
		btnLock.setBounds(266, 258, 115, 23);
		panStudentTable.add(btnLock);
		
		lblSub1 = new JLabel("Sub1");
		lblSub1.setBounds(570, 105, 46, 14);
		panStudentTable.add(lblSub1);
		
		lblSub2 = new JLabel("Sub2");
		lblSub2.setBounds(570, 130, 46, 14);
		panStudentTable.add(lblSub2);
		
		lblSub3 = new JLabel("Sub3");
		lblSub3.setBounds(570, 155, 46, 14);
		panStudentTable.add(lblSub3);
		
		lblSub4 = new JLabel("Sub4");
		lblSub4.setBounds(570, 180, 46, 14);
		panStudentTable.add(lblSub4);
		
		lblSub5 = new JLabel("Sub5");
		lblSub5.setBounds(570, 205, 46, 14);
		panStudentTable.add(lblSub5);
		
		lblSub6 = new JLabel("Sub6");
		lblSub6.setBounds(570, 230, 46, 14);
		panStudentTable.add(lblSub6);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 1; i<currSection;i++ )
					checkPrevTable(currYear,i);//year and section
				displaySubCount();

			}
		});
		btnCheck.setBounds(45, 292, 116, 23);
		panStudentTable.add(btnCheck);
		
		btnRefreshSubj = new JButton("Refresh");
		btnRefreshSubj.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				displaySubCount();
			}
		});
		btnRefreshSubj.setBounds(505, 258, 111, 23);
		panStudentTable.add(btnRefreshSubj);
//////////////////////////////////////////////////////////LAB TABLES ///////////////////////////////////////		
		panLabTable = new JPanel();
		contentPane.add(panLabTable, "Lab Tables");
		panLabTable.setLayout(null);
		
		JLabel lblLabs = new JLabel("Labs:");
		lblLabs.setBounds(108, 37, 46, 14);
		panLabTable.add(lblLabs);
		
		JComboBox selectLabs = new JComboBox();
		selectLabs.setBounds(195, 34, 81, 20);
		panLabTable.add(selectLabs);
		
		tableLabs = new JTable();
		resetTable(2);
		tableLabs.setBounds(71, 87, 522, 169);
		panLabTable.add(tableLabs);
		
		JButton btnHome_3 = new JButton("Home");
		btnHome_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardlay.show(contentPane, "WelcomePanel");
			}
		});
		btnHome_3.setBounds(270, 280, 89, 23);
		panLabTable.add(btnHome_3);
	}
	
	/**
	 * gets the  serial no related to the year, semester
	 * to be used in the setCurrSubject()
	 * 
	 * @param current year 
	 * @return branch code+year+sem
	 */
	private int getCurrYearNo(String currYear)
	{
		if(currYear.equals( "2-1"))
			return 0521;
		else if(currYear.equals( "2-2"))
			return 0522;
		else if(currYear.equals( "3-1"))
			return 0531;
		else if (currYear.equals( "3-2"))
			return 0532;
		return 0;
					
	}
	
	/**
	 * sets the lists according to the year-sem selected
	 * 
	 * @param currYear
	 */
	private void setCurrSubjects(String currYear)
	{
		int yearNo = getCurrYearNo(currYear);
	//	String[] temp;
		switch(yearNo)
		{
		case 0521:
			currSubjects = sub.subject.get(0);
			availSubj.removeAllElements();
			for(int i = 0; i<currSubjects.length-1;i++)
				availSubj.addElement(currSubjects[i]);
			break;
		case 0522:
			currSubjects = sub.subject.get(1);
			availSubj.removeAllElements();
			for(int i = 0; i<currSubjects.length-1;i++)
				availSubj.addElement(currSubjects[i]);
			break;
		case 0531:
			currSubjects = sub.subject.get(2);
			availSubj.removeAllElements();
			for(int i = 0; i<currSubjects.length-1;i++)
				availSubj.addElement(currSubjects[i]);
				break;
		case 0532:
			currSubjects = sub.subject.get(3);
			availSubj.removeAllElements();
			for(int i = 0; i<currSubjects.length-1;i++)
				availSubj.addElement(currSubjects[i]);
				break;
		}
	}
	
	/**
	 * clears faculty generator 
	 */
	private void clearFaculty()
	{
		txtTeachNameReg.setText("");
		availSubj.removeAllElements();
		chosenSubj.removeAllElements();
		chckbxPriority.setSelected(false);
		selYear1.getModel().setSelectedItem("");
	}
	
	/**
	 * writes the faculty information to a file
	 * 
	 * '#' indicates that it's a priority teacher
	 * '$' indicates that it's a normal teacher
	 * 
	 * @param teachname
	 * @param chosenSubj
	 * @param priority
	 * @throws IOException
	 */
	private void makeFaculty(String teachname, String[] chosenSubj, boolean priority) throws IOException
	{
		File f = new File("TT"+teachname+".txt");
		BufferedWriter pen = new BufferedWriter(new FileWriter(f));
		pen.flush();
		pen.write("");
		pen.close();
		f = new File("TeacherList.txt");
		pen = new BufferedWriter(new FileWriter(f,true));
		pen.flush();
		pen.newLine();
		pen.append(teachname+"\t");
		if(priority)
			pen.append("#\t");
		else
			pen.append("$\t");
		pen.append(chosenSubj[0]+"\t");
		pen.append(chosenSubj[1]+"\t");
		pen.close();
	}
	
	/**
	 * Resets the table to the default empty table
	 */
	private void resetTable(int selection)
	{
		if(selection==0)
		{
			dtmStudent = new DefaultTableModel(
					new Object[][] 
							{
							{"Days", "1", "2", "3", "4", "5", "6", "7"},
							{"Mon", null, null, null, null, null, null, null},
							{"Tue", null, null, null, null, null, null, null},
							{"Wed", null, null, null, null, null, null, null},
							{"Thu", null, null, null, null, null, null, null},
							{"Fri", null, null, null, null, null, null, null},
							{"Sat", null, null, null, null, null, null, null},
							},

							new String[] 
									{
							"Days", "1", "2", "3", "4", "5", "6", "7"
									}
					);
			tableStudents.setModel(dtmStudent);
		}
		if(selection==1)
		{
			dtmTeacher = new DefaultTableModel(
					new Object[][] 
							{
							{"Days", "1", "2", "3", "4", "5", "6", "7"},
							{"Mon", null, null, null, null, null, null, null},
							{"Tue", null, null, null, null, null, null, null},
							{"Wed", null, null, null, null, null, null, null},
							{"Thu", null, null, null, null, null, null, null},
							{"Fri", null, null, null, null, null, null, null},
							{"Sat", null, null, null, null, null, null, null},
							},

							new String[] 
									{
							"Days", "1", "2", "3", "4", "5", "6", "7"
									}
					);
			tableTeachers.setModel(dtmTeacher);
		}
		if(selection==2)
		{
			dtmLab = new DefaultTableModel(
					new Object[][] 
							{
							{"Days", "1", "2", "3", "4", "5", "6", "7"},
							{"Mon", null, null, null, null, null, null, null},
							{"Tue", null, null, null, null, null, null, null},
							{"Wed", null, null, null, null, null, null, null},
							{"Thu", null, null, null, null, null, null, null},
							{"Fri", null, null, null, null, null, null, null},
							{"Sat", null, null, null, null, null, null, null},
							},

							new String[] 
									{
							"Days", "1", "2", "3", "4", "5", "6", "7"
									}
					);
			tableLabs.setModel(dtmLab);
		}
	}
	
	/**<pre>
	 * fills in the labs into the time table
	 * 
	 * ranLab - picks a lab => int n depending on the no of labs
	 * ranDay - picks a day => int n depending on the no of days that are acceptable for lab
	 * ranTime - picks a day => int n depending on the possible times a lab can occur
	 * prevTime - it stores the previous time that a lab was in so the next lab would be at a different time	 * 
	 * 
	 * param. for ranLab and ranTime may change according to the class chosen 
	 * and may have to be put in as param.
	 * </pre>
	 */
	private void fillLabs()
	{
		Random r = new Random();
		int labCount =1;
		int labcountextra=0;
		int ranLab = r.nextInt(labCount);
		int ranDay = r.nextInt(5);
		int ranTime  = r.nextInt(2);
		int prevTime = 0;
		String[] labs = sub.getLabs(0);
		ArrayList<Integer> doneDays = new ArrayList<Integer>();
		ArrayList<String> doneLabs = new ArrayList<String>();
		//to make the lab come twice 
		while(labcountextra <2) 
		{
			do{
				//finds a day which does not have lab
				while(doneDays.contains(ranDay))
				{
					ranDay = r.nextInt(5);
				}
				ranTime  = r.nextInt(2);
				//finds a period which is not the same as the previous allocated lab
				while(prevTime==ranTime)
				{
					ranTime  = r.nextInt(2);
				}
				labcountextra++;

				//setting the period
				prevTime = ranTime;
				if(ranTime==0)
					ranTime=2;
				if(ranTime==1)
					ranTime=5;

				//allocates the lab to table, adds it to list of finished labs and adds the day to list of finished days
				dtmStudent.setValueAt(getSubject(ranLab+6), ranDay+1, ranTime);
				dtmStudent.setValueAt(getSubject(ranLab+6), ranDay+1, ranTime+1);
				dtmStudent.setValueAt(getSubject(ranLab+6), ranDay+1, ranTime+2);
				doneDays.add(ranDay);
				doneLabs.add(labs[ranLab]);
			}while(doneLabs.size()<labs.length);
		}
	}
	
	/**
	 *Gets the subject from subjects class
	 * @param temp - index of the subject
	 * @return Subject as a string
	 */
	private String getSubject(int temp)
	{
		return currSubjects[temp];
	}
	
	/**<pre>
	 * gets the index of subject in array
	 * 
	 * (Internal/support function)
	 * </pre>
	 * @param subject name
	 * @return
	 */
	private int getSubjInt(String subject)
	{
		for(int i = 0; i<currSubjects.length; i++)
			if(currSubjects[i].equals(subject))
				return i;
		return -1;
	}
	
	/**
	 * <pre>
	 * fills in all the theory subjects 
	 * which are not priority or important
	 * </pre>
	 */
	private void fillTheory()
	{
		int subjInt;
		String subject;
		Random r = new Random();
		//traverses through the 2d arraylist for inserting subjects
		filledForWeek = new ArrayList<>();
		int[] subjCount={0,0,0,0,0,0,0};
		for(int i = 1;i<7;i++)
		{
			filledForDay = new ArrayList<>();// makes a new arraylist for each day
			for (int j = 1; j < 8; j++)
			{
				if(dtmStudent.getValueAt(i,j)==null)// adds a subject to the table if the cell is empty
				{	
					subjCount = getCount();
					// counter to prevent infinite loop due to random collisions
					int counter=0;
					while(true)
					{
						counter++;
						subjInt = r.nextInt(7);
						subject=getSubject(subjInt);
						if(sub.isImpSub(subject))
							continue;
						if(TableReader.isPrioritySubj(subject))
							continue;
						if(counter>100)
							break;
						if(sub.isLab(subject))
							continue;
						if(subjCount[subjInt]>5 ) 
							continue;
						if(filledForDay.contains(subject))
								continue;
						break;
					}
					dtmStudent.setValueAt(getSubject(subjInt), i, j);
					filledForDay.add(getSubject(subjInt));
					System.out.println(subject+": "+i+","+j);
				}
			}
		}
	}

	//as of now, only one important subject per year so string array will only contain one value
	/**
	 * <pre>
	 * fills all the important subjects
	 * defined in the array in Subjects class
	 * 2 hours continous and 6 hours a week
	 * 
	 * first it finds the empty days and fills it in that
	 * then it finds any remaining days and fills the rest
	 * </pre>
	 */
	private void fillImptSubj()
	{
		String[] subjs = getImpSubj();
		ArrayList<Integer> doneDays = new ArrayList<Integer>();
		if(subjs!=null)
		{
			int[] emptyDays = getEmptyDays();
			Random r = new Random();
			int noOfHours = 0;
			int ranTime;
			int ranDay = r.nextInt(6);
			int counter= 0;

			boolean allocated = false;
			for(int i = 0;i<emptyDays.length;i++)
			{
				if(emptyDays[i]==1 && noOfHours<3)
				{	
					allocated =false;
					while(allocated==false)
					{
						ranTime = r.nextInt(4);
						switch(ranTime)
						{
						case 0:
							if(dtmStudent.getValueAt(i +1, 1)==null && dtmStudent.getValueAt(i +1, 2)==null )
							{
								dtmStudent.setValueAt(subjs[0],i +1, 1);
								dtmStudent.setValueAt(subjs[0],i +1, 2);
								doneDays.add(i );
								noOfHours++;
								allocated=true;
							}
							break;
						case 1:
							if(dtmStudent.getValueAt(i +1, 3)==null && dtmStudent.getValueAt(i +1, 4)==null )
							{
								dtmStudent.setValueAt(subjs[0], i +1, 3);
								dtmStudent.setValueAt(subjs[0], i +1, 4);
								doneDays.add(i );
								noOfHours++;
								allocated=true;
							}
							break;
						case 2:
							if(dtmStudent.getValueAt(i +1, 5)==null && dtmStudent.getValueAt(i+1, 6)==null )
							{
								dtmStudent.setValueAt(subjs[0],i +1, 5);
								dtmStudent.setValueAt(subjs[0], i +1, 6);
								doneDays.add(i );
								noOfHours++;
								allocated=true;
							}
							break;
						case 3:
							if(dtmStudent.getValueAt(i +1, 6)==null && dtmStudent.getValueAt(i +1, 7)==null )
							{
								dtmStudent.setValueAt(subjs[0], i +1, 6);
								dtmStudent.setValueAt(subjs[0],i +1, 7);
								doneDays.add(i );
								noOfHours++;
								allocated=true;
							}
							break;
						}
					}
				}
			}
//			System.out.println("empty allocated");
			counter=0;
			while(noOfHours<3)
			{
				counter++;
				if(counter>100)
					break;
				ranTime = r.nextInt(4);
				while(doneDays.contains(ranDay))
					ranDay = r.nextInt(6);
				switch(ranTime)
				{
				case 0:
					if(dtmStudent.getValueAt(ranDay+1, 1)==null && dtmStudent.getValueAt(ranDay+1, 2)==null )
					{
						dtmStudent.setValueAt(subjs[0], ranDay+1, 1);
						dtmStudent.setValueAt(subjs[0], ranDay+1, 2);
						doneDays.add(ranDay);
						noOfHours++;
					}
					break;
				case 1:
					if(dtmStudent.getValueAt(ranDay+1, 3)==null && dtmStudent.getValueAt(ranDay+1, 4)==null )
					{
						dtmStudent.setValueAt(subjs[0], ranDay+1, 3);
						dtmStudent.setValueAt(subjs[0], ranDay+1, 4);
						doneDays.add(ranDay);
						noOfHours++;
					}
					break;
				case 2:
					if(dtmStudent.getValueAt(ranDay+1, 5)==null && dtmStudent.getValueAt(ranDay+1, 6)==null )
					{
						dtmStudent.setValueAt(subjs[0], ranDay+1, 5);
						dtmStudent.setValueAt(subjs[0], ranDay+1, 6);
						doneDays.add(ranDay);
						noOfHours++;
					}
					break;
				case 3:
					if(dtmStudent.getValueAt(ranDay+1, 6)==null && dtmStudent.getValueAt(ranDay+1, 7)==null )
					{
						dtmStudent.setValueAt(subjs[0], ranDay+1, 6);
						dtmStudent.setValueAt(subjs[0], ranDay+1, 7);
						doneDays.add(ranDay);
						noOfHours++;
					}
					break;
				}
			}
			
		}
		
	}
	
	/**
	 *fills the subjects that are priority teachers teach
	 *5 classes, not in 1st and 7th  
	 */
	private void fillPrioritySub()
	{
		String[] subjs = getPrioritySubj();
		ArrayList<Integer> doneDays = new ArrayList<Integer>();
		Random r = new Random();
		int noOfHours = 0;
		int ranTime;
		int ranDay = r.nextInt(6);

		if(subjs != null )
		{
			for(int i =0; i<2;i++)
			{
				noOfHours = 0;
				doneDays = new ArrayList<Integer>();
				while(noOfHours<5)
				{
					ranTime = r.nextInt(5);
					while(doneDays.contains(ranDay))
						ranDay = r.nextInt(5);
					switch(ranTime)
					{
					case 0:
						if(dtmStudent.getValueAt(ranDay+1, 2)==null)
						{
							dtmStudent.setValueAt(subjs[i], ranDay+1, 2);
							doneDays.add(ranDay);
							noOfHours++;
						}
						break;
					case 1:
						if(dtmStudent.getValueAt(ranDay+1, 3)==null)
						{
							dtmStudent.setValueAt(subjs[i], ranDay+1, 3);
							doneDays.add(ranDay);
							noOfHours++;
						}
						break;
					case 2:
						if(dtmStudent.getValueAt(ranDay+1, 4)==null)
						{
							dtmStudent.setValueAt(subjs[i], ranDay+1, 4);
							doneDays.add(ranDay);
							noOfHours++;
						}
						break;
					case 3:
						if(dtmStudent.getValueAt(ranDay+1, 5)==null)
						{
							dtmStudent.setValueAt(subjs[i], ranDay+1, 5);
							doneDays.add(ranDay);
							noOfHours++;
						}
						break;
					case 4: 
						if(dtmStudent.getValueAt(ranDay+1, 6)==null)
						{
							dtmStudent.setValueAt(subjs[i], ranDay+1, 6);
							doneDays.add(ranDay);
							noOfHours++;
						}
						break;
					}

				}

			}
		}
	}
	
	/**
	 * finds and returns all the priority subjects 
	 * 
	 * @return String array of all the priority subjects
	 */
	private String[] getPrioritySubj()
	{
		String[] listOfPrior = new String[10];
		int j = 0;
		for(String i: currSubjects)
		{
			if(TableReader.isPrioritySubj(i))
			{	
				listOfPrior[j]=i;
				j++;
				System.out.println(i);
			}
		}
		if(j==0)
			return null;
		return listOfPrior;

	}
	
	
	/**
	 * find and returns all the important subjects
	 * 
	 * @return String array of all the important subjects
	 */
	private String[] getImpSubj()
	{
		String[] listOfImpt = new String[10];
		int j = 0;
		for(String i: currSubjects)
		{
			if(sub.isImpSub(i))
			{	
				listOfImpt[j]=i;
				j++;
			}
		}
		if(j==0)
			return null;
		return listOfImpt;
	}

	/**
	 * write the table to a file named table.txt
	 * @throws IOException
	 */	
	private void writeTable(int choice) throws IOException
	{
		File f;
		BufferedWriter pen;
		if(choice ==0)
		{
			f = new File("table"+currYear+(char)(currSection+64)+".txt");
			pen = new BufferedWriter(new FileWriter(f));
			pen.flush();
			for (int i = 0;i<7;i++)
			{	for(int j = 0;j<8;j++)
				pen.write((String) dtmStudent.getValueAt(i, j)+"\t");
			pen.newLine();
			}
		}
		else
		{
			///// can change the naming convention to the subjects that the teacher is teaching for more efficient reading
			f = new File("TT"+teachName+".txt");
			pen = new BufferedWriter(new FileWriter(f));
//			pen.flush();
			for (int i = 0;i<7;i++)
			{	for(int j = 0;j<8;j++)
				pen.write((String) dtmTeacher.getValueAt(i, j)+"\t");
			pen.newLine();
			}
		}
		pen.close();
	}
/**
 * goes through the student table to find out if it has been filled
 * 	
 * @return true if table is completely filled, false otherwise
 */
	private boolean checkTableIfFull()
	{
		for (int i = 0;i<7;i++)
			for(int j = 0;j<8;j++)
				if(dtmStudent.getValueAt(i, j)==null)
					return false;
		return true;
	}
	
	/**
	 * goes through the table and counts the number of each subject allocated
	 * 
	 * @return int array which contains the number of subjects allocated in the dtmstudent currently
	 */
	private int[] getCount()
	{
		int[] count = new int[currSubjects.length];
		for (int i = 1;i<7;i++)
			for(int j = 1;j<8;j++)
				if(dtmStudent.getValueAt(i, j)!=null)
					for(String k: currSubjects)
						if(((String)dtmStudent.getValueAt(i, j)).equals(k))
							count[getSubjInt(k)]++;
		return count;
	}

	/**
	 * displays the count of the subjects 
	 */
	private void displaySubCount()
	{
			int[] count = getCount();			
			lblSub1.setText(getSubject(0)+":"+count[0]);
			lblSub2.setText(getSubject(1)+":"+count[1]);
			lblSub3.setText(getSubject(2)+":"+count[2]);
			lblSub4.setText(getSubject(3)+":"+count[3]);
			lblSub5.setText(getSubject(4)+":"+count[4]);
			lblSub6.setText(getSubject(5)+":"+count[5]);
	}
	
	/**
	 * fills in the 3-class tutorial 
	 */
	private void fillTut()
	{
		Random r = new Random();
		int ranDay = r.nextInt(5);
		int ranTime  = r.nextInt(2);
		while(isLabThere(ranDay+1))
		{
			ranDay= r.nextInt(5);
		}
		if(ranTime==0)
			ranTime=2;
		if(ranTime==1)
			ranTime=5;
		dtmStudent.setValueAt("TUT", ranDay+1, ranTime);
		dtmStudent.setValueAt("TUT", ranDay+1, ranTime+1);
		dtmStudent.setValueAt("TUT", ranDay+1, ranTime+2);
		
	}
	
	/**
	 * fills in the 2-class tutorial 
	 */
	private void fillTut2Subj()
	{
		Random r = new Random();
		int ranDay = r.nextInt(5);
		int ranTime  = r.nextInt(2);
		while(isLabThere(ranDay+1))
		{
			ranDay= r.nextInt(5);
		}
		if(ranTime==0)
			ranTime=2;
		if(ranTime==1)
			ranTime=5;
		dtmStudent.setValueAt("TUT", ranDay+1, ranTime);
		dtmStudent.setValueAt("TUT", ranDay+1, ranTime+1);

		
	}

	/**
	 * checks if there is lab on the given day
	 * @param day
	 * @return true if there's a lab that day
	 */
	private boolean isLabThere(int day)
	{
		int check=0;
		try
		{
			if(dtmStudent.getValueAt(day, 2).equals(null))
				return true;
		}
		catch(NullPointerException ne)
		{
			check++;
		}
		try
		{
			if(dtmStudent.getValueAt(day, 5).equals(null))
				return true;
		}
		catch(NullPointerException ne)
		{
			check++;
		}
		if(check ==2)
			return false;
		return true;
	}

	/**
	 * checks if the day is completely empty
	 * @param day
	 * @return true if nothing has been allocated yet
	 */
	private boolean isDayEmpty(int day)
	{
		for(int j =1;j<8;j++)
			if(dtmStudent.getValueAt(day,j)!=null )
				if(((String)dtmStudent.getValueAt(day, j)).equals("")==false)
					return false;
		return true;		
	}
	
	/**
	 * O means full,
	 * 1 means empty
	 * @return integer array which contains the empty days
	 */
	private int[] getEmptyDays()
	{
		int[] days={0,0,0,0,0,0};
		for(int i = 1;i<7;i++)
			if(isDayEmpty(i))
				days[i-1]=1;
		return days;
	}
	
	/**
	 * <pre>
	 * -creates and displayed the teacher table 
	 * -depends on the chosen subject and section
	 * </pre>
	 */
	private void displayTeacherTable()
	{
		try
		{
			String[] subj  = TableReader.getTeachSubj((String)selectTeach.getSelectedItem());
			int[] subjChecked = new int[]{0,0};
			if(chckbxSubj1.isSelected())
				subjChecked[0]=1;
			if(chckbxSubj2.isSelected())
				subjChecked[1]=1;
			for(int i=0;i<2;i++)
			{
				if(subjChecked[i]==0)
					continue;
				String sub = subj[i];
				String[] subjInfo =getSubjInfo(sub);
				for(int j = 1 ;j<=noOfSec;j++)
				{
					if(checkedSections[j-1]==true)
					{		
						getPrevTable(subjInfo[0]+"-"+Integer.parseInt(subjInfo[1]),j);
						for (int i1 = 0;i1<7;i1++)
							for(int j1 = 0;j1<8;j1++)
								if(readTable[i1][j1].equals(sub))
								{
									if(dtmTeacher.getValueAt(i1, j1)!=null)
										JOptionPane.showMessageDialog(null, "Conflict at "+i1+","+j1, "Alert", currSection);
									dtmTeacher.setValueAt(sub+"-"+subjInfo[2]+(char)(j+64), i1,j1);
								}					
					}
				}
			}
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * get the information about the subject
	 * @param subject
	 * @return String array of year, sem and branch of the given subject
	 */
	private String[] getSubjInfo(String subject)
	{
		String[] toReturn = new String[3];
		for (int i = 0; i < sub.subject.size(); i++)
		{
			for(int j = 0;j<sub.subject.get(i).length-1;j++)
			{
				if(sub.subject.get(i)[j].equals(subject))
				{
					toReturn[0]=sub.subject.get(i)[sub.subject.get(i).length-1].charAt(0)+"";
					toReturn[1]=sub.subject.get(i)[sub.subject.get(i).length-1].charAt(1)+"";
					toReturn[2]=sub.subject.get(i)[sub.subject.get(i).length-1].substring(2);
				}
			}
		}
		return toReturn;
	}
	
	/**
	 * reads the student table based on year/sem and section
	 * @param year
	 * @param section
	 */
	private void getPrevTable(String year, Integer section)
	{
		try
		{
			readTable = TableReader.readTable(year, section);
		} catch (FileNotFoundException e)
		{
			System.out.println(year+" "+section+" "+"File doesn't exist");
		}
	}
	
	/**
	 * checks if a section has been selected
	 * @return true if a section was selected
	 */
	private boolean checkSecSelected()
	{
		if(selectSec.getSelectedIndex()==0)
		{
			JOptionPane.showMessageDialog(null, "Select no of Sections");
			return false;
		}
		return true;
	}

	/**
	 * removes any allocation of a conflicting subject
	 * @param year
	 * @param section
	 */
	private void checkPrevTable(String year, Integer section) 
	{
			getPrevTable(year, section);
			for(int i = 1;i<7;i++)
			{
				for (int j = 1; j < 8; j++)
				{
					if(readTable[i][j].equals(tableStudents.getValueAt(i, j)))
						tableStudents.setValueAt(null, i, j);
				}
			}
			
		}
}
		
	
	
	