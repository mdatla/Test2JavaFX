package backend;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class AttendanceHandler {

	private static Vector<RemoteDevice> devices = new Vector<RemoteDevice>();
	private static ArrayList<Student> studentList = new ArrayList<Student>();
	private static List<String[]> allLines;

	public static void locateDevices(int timerLength){

		try {
			devices = BluetoothHandler.discoverDevices(timerLength);
			setDevices(devices);
			for(int i = 0; i < devices.size(); i++){
				RemoteDevice bt = devices.get(i);
				System.out.println(bt.getBluetoothAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void setAttend(){

		studentList.clear();
		CSVParser parser = new CSVParser();
		try {
			CSVReader reader = new CSVReader(new FileReader(Globals.currentClass.getClassRosterPath()),1,parser);
			List<String[]> allLines = reader.readAll();
			for(String[] line : allLines){
				Student student = new Student(line[0], line[1]);
				for(int i = 2; i < line.length; i++){
					student.addDevice(line[i]);
				}
				studentList.add(student);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		devices = getDevices();

		for(RemoteDevice device: devices){
			boolean marked = false;
			for(int i = 0; i < studentList.size() && marked == false; i++){
				if(!studentList.get(i).isPresent()){
					System.out.println(studentList.get(i).getDevices().get(0) + device.getBluetoothAddress());
					if(studentList.get(i).checkID(device.getBluetoothAddress())){
						studentList.get(i).setPresent(true);
						Globals.presentStudents++;
						System.out.println(studentList.get(i).getStudentName());
						marked = true;
					}
				}
			}
		}
		setStudents(studentList);
		writeSemesterReport();
	}

	public static void writeSemesterReport(){

		LocalDate date = LocalDate.now();
		String today = date.toString();
		ArrayList<Student> studentList = getStudents();

		try {
			CSVReader reader = new CSVReader(new FileReader(Globals.currentClass.getSemesterReportPath()));
			allLines = reader.readAll();
			Globals.TotalStudents = allLines.size()-1;
			int length = allLines.get(0).length;
			if(allLines.get(0)[length-1].equalsIgnoreCase(today)){
				//assuming that both studentList and semesterReport are sorted the same
				for(int i = 1; i < allLines.size(); i++){
					if(studentList.get(i-1).isPresent()){
						allLines.get(i)[length-1] = "P";
					}else{
						allLines.get(i)[length-1] = "A";
					}
				}
			}else{
				//assuming that both studentList and semesterReport are sorted the same
				String[] s1 = new String[length+1];
				s1[s1.length-1]=today;
				System.arraycopy(allLines.get(0), 0, s1, 0, s1.length-1);
				allLines.remove(0);
				allLines.add(0, s1);
				System.out.println(allLines.size());
				for(int i = 1; i < allLines.size(); i++){
					System.out.println(i);
					if(studentList.get(i-1).isPresent()){
						s1 = new String[length+1];
						s1[s1.length-1] = "P";
						System.arraycopy(allLines.get(i), 0, s1, 0, s1.length-1);
						allLines.remove(i);
						allLines.add(i, s1);
					}else{
						s1 = new String[length+1];
						s1[s1.length-1] = "A";
						System.arraycopy(allLines.get(i), 0, s1, 0, s1.length-1);
						allLines.remove(i);
						allLines.add(i, s1);
					}
				}
			}
			reader.close();
			CSVWriter writer = new CSVWriter(new FileWriter(Globals.currentClass.getSemesterReportPath()));
			writer.writeAll(allLines);
			writer.close();
			setAllLines(allLines);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String[]> getAllLines() {
		return allLines;
	}

	public static void setAllLines(List<String[]> allLines) {
		AttendanceHandler.allLines = allLines;
	}

	public static ArrayList<Student> getStudents(){
		return studentList;
	}

	public static void setStudents(ArrayList<Student> newStudents){
		newStudents = studentList;
	}

	public static Vector<RemoteDevice> getDevices(){
		return devices;
	}

	public static void setDevices(Vector<RemoteDevice> newDevices){
		newDevices = devices;
	}

}
