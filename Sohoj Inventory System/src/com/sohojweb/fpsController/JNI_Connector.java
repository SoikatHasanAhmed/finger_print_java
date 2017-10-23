package com.sohojweb.fpsController;

import Login.Login_Page;
import main_body.main_frame;

public class JNI_Connector {
	 /*
	  * This static block of code loads dll library with respect to the os architecture.
	  * 	The jni library is compiled for both x86 and amd64 platform.
	  */
	
	static {
		if(System.getProperty("os.arch").equals("x86"))
		{
			System.loadLibrary("lib_jni_fps_gt-511c3");
		}
		else if(System.getProperty("os.arch").equals("amd64"))
		{
			System.loadLibrary("lib_jni_fps_gt-511c3_64");
		}
	}
		
	private int commSerial;
	
	/* 
	 * fps_openDevice() method returns boolean value:true if the device is connected. If there is any 
	 * 		communication error, then it will returns false. The parameter is the serial communication
	 * 		port number.
	 * 
	 * 	NOTE: THIS FUNCTION IS NOT NEEDED TO USE OTHER FUNCTIONS BECAUSE OTHER FUNCTIONS
	 * 		INDEPENDENTLY OPEN AND CLOSE THE DEVICE AFTER USE.
	 */
	private native boolean fps_openDevice(int comm);
	
	/*
	 * fps_getUserCount() method returns the number of users enrolled in the device. The parameter is the serial communication
	 * 		port number.
	 * 
	 * NOTE: WILL RETURN -1 IF THERE IS ANY COMMUNICATION ERROR
	 */
	private native int fps_getUserCount(int comm);
	
	/*
	 * fps_Enroll() method returns 0 if the enroll was successful, else it will return the ERROR_CODE. ErrorIntrepreter()
	 * 		method is used to interpret the error into understandable format.  The parameter is the serial communication
	 * 		port number and ID. Fingerprint is saved with index of the given ID.
	 */
	private native int fps_Enroll(int comm, int userID);
	
	/* fps_varifyUser() method returns the user id if there was any match, or it returns error code. The parameter is
	 * 		the comm port to communicate with. The returned error can be converted to understandable form by using the 
	 * 		ErrorInterpretor() method.
	 * 
	 */
	
	private native int fps_varifyUser(int comm);
	
	public JNI_Connector(int serialPortNum) {
		this.commSerial = serialPortNum;
	}
	
	public boolean openDevice() {
		return fps_openDevice(this.commSerial);
	}
	public int getUserCount() {
		return fps_getUserCount(this.commSerial);
	}
	
	public String Enroll(int id)
	{
		int errorVal = fps_Enroll(this.commSerial, id);
		return ErrorIntrepreter(errorVal);
	}
	
	public String varifyUser()
	{
		int errorVal = fps_varifyUser(this.commSerial);
		return ErrorIntrepreter(errorVal);
	}
	
	private final String ErrorIntrepreter(int error)
	{
		String errorString = "";
		if(error == 5050)
		{
			errorString = "Enroll successfully";
		}
		else if( error > 4096)
		{
			switch(error)
			{
			case 4097:
				errorString = "Time Out!";
				break;
			case 4098:
				errorString = "Invalid baudrate";
				break;
			case 4099:
				errorString = "Invalid ID";
				break;
			case 4100:
				errorString = "ID is not used!";
				break;
			case 4101:
				errorString = "ID is already used!";
				break;
			case 4102:
				errorString = "Communication error!";
				break;
			case 4103:
				errorString = "ID = NG!";
				break;
			case 4104:
				errorString = "NG!";
				break;
			case 4105:
				errorString = "Database is full";
				break;
			case 4106:
				errorString = "Database is empty";
				break;
			case 4107:
				errorString = "The order of enrollment is incorrect!";
				break;
			case 4108:
				errorString = "Bad finger!";
				break;
			case 4109:
				errorString = "The enrollment is failed!";
				break;
			case 4110:
				errorString = "The command is not supported";
				break;
			case 4111:
				errorString = "The device error!";
				break;
			case 4112:
				errorString = "Canceled!";
				break;
			case 4113:
				errorString = "Invalid Firmware Image!";
				break;
			case 4114:
				errorString = "Finger is not pressed!";
				break;
			case 4115:
				errorString = "NO User !";
				break;
			default:
				errorString = "Unknown Error";
				break;
			}
		}
		else if (error < 200)
		{
			errorString = " This Finger Already in Use";
			if (error <200){
			main_frame.getVarify_Id(error);
			Login_Page.getVarify_Id(error);
		}
		}
		return errorString;
	}
}