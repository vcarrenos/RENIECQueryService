package pe.gob.midis.sisfoh.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemConfigHelper {
	
	private static String osUser;
	private static String programName;
	private static String osName;
	private static String machineName;
	private static String hostAddress;
	
    public static void create() {
		
		SystemConfigHelper.osUser = System.getProperty("user.name");
		System.out.println("OsUser: [" + SystemConfigHelper.osUser + "]");
		
		SystemConfigHelper.programName = System.getProperty("program.name");
		System.out.println("programName: [" + SystemConfigHelper.programName + "]");
		
		SystemConfigHelper.osName = System.getProperty("os.name");
		System.out.println("OsName: [" + SystemConfigHelper.osName + "]");
		
		java.net.InetAddress localMachine;
		try {
			localMachine = java.net.InetAddress.getLocalHost();
			SystemConfigHelper.machineName = localMachine.getHostName();
			System.out.println("machineName: [" + SystemConfigHelper.machineName + "]");
			
			SystemConfigHelper.hostAddress = InetAddress.getLocalHost().getHostAddress();
			System.out.println("hostAddress: [" + SystemConfigHelper.hostAddress + "]");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		if (StringUtil.isNullOrEmpty(SystemConfigHelper.hostAddress))
			SystemConfigHelper.hostAddress = "127.0.0.1";

	}

	public static String getOsUser() {
		return SystemConfigHelper.osUser;
	}

	public static String getProgramName() {
		return SystemConfigHelper.programName;
	}

	public static String getOsName() {
		return SystemConfigHelper.osName;
	}

	public static String getMachineName() {
		return SystemConfigHelper.machineName;
	}

	public static String getHostAddress() {
		return SystemConfigHelper.hostAddress;
	}

}

