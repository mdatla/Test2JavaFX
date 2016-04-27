package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import backend.LectureClass;

public class Globals {
	public static LectureClass currentClass = new LectureClass();
	public static ArrayList<LectureClass> classList = new ArrayList<LectureClass>();
	public static int rollCallLength;
	public static double progress;
	public static String filePath="";

	public static int TotalStudents=0;
	public static int presentStudents=0;

	public static int defaultMin = 0;
	public static int defaultSec = 0;

	public static void initializeGlobals() throws FileNotFoundException, IOException{
		currentClass = null;
		ArrayList<LectureClass> example = new ArrayList<LectureClass>();
		String[] tempArray = SetupHelper.getAllClasses();
		for (String s: tempArray){
			LectureClass temp = new LectureClass();
			temp.setName(s);
			temp.setClassRosterPath(filePath+"Roster~"+s+".csv");
			temp.setSemesterReportPath(filePath+"SemReport~"+s+".csv");

			example.add(temp);

			File f = new File("config.ini");
			if(f.isDirectory() || !f.exists()) { 
				f.createNewFile();
				FileOutputStream fos = new FileOutputStream(f);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
				bw.write("00#30\n");
				bw.close();
			}

			BufferedReader br = new BufferedReader(new FileReader(f)); 
			String line;
			String[] defaultTimes = null;
			while ((line = br.readLine()) != null) {
				defaultTimes = line.trim().split("#");
			}
			
			defaultMin = Integer.parseInt(defaultTimes[0]);
			defaultSec =  Integer.parseInt(defaultTimes[1]);

		}
		Globals.classList = example;

		for(LectureClass temp : classList){
			System.out.println(temp.getName());
		}
	}


}
