package com.litmus7.employeemanager.util;

import com.litmus7.employeemanager.constant.PatternConstants;
import com.litmus7.employeemanager.dao.EmployeeDAO;


public class ValidationUtil {
	
	public static int validateCSVFile(String filepath) {
		
		if (filepath == null || filepath == "") {  return 0; }
		else if ( ! PatternConstants.CSVTYPE_REGEX.matcher(filepath).matches()) { return 1; }
		else {  return 2; }
		
	}
	
	public static boolean isValidEmail(String email) {
		
		if(email == null) {
			System.out.println("Its eempty");
		}
		
	    return PatternConstants.EMAIL_REGEX.matcher(email).matches() && email != null;
    }
	
	public static boolean isValidID(String id) {
		
		try {
			EmployeeDAO checkId = new EmployeeDAO();
			
			return ! checkId.checkEmployeeByID(Integer.parseInt(id));
		
		} catch(NumberFormatException e) {
			return false;
		}
	}

    public static boolean isValidPhone(String phone) {
    	//System.out.println(phone);
        return PatternConstants.PHONE_REGEX.matcher(phone).matches() && phone != null;
    }

    public static boolean isValidName(String name) {
    	//System.out.println(name);
        return PatternConstants.NAME_REGEX.matcher(name).matches() && name != null;
    }

    public static boolean isValidDepartment(String department) {
    	//System.out.println(department);
        return PatternConstants.DEPARTMENT_REGEX.matcher(department).matches() && department != null;
    }

    public static boolean isValidSalary(String salary) {
    	//System.out.println(salary);
        return PatternConstants.SALARY_REGEX.matcher(salary).matches() && salary != null;
    }
	    
    public static boolean isValidJoinDate(String date) {
    	//System.out.println(date);
        return PatternConstants.DATE_REGEX.matcher(date).matches() && date != null;
    }
}
