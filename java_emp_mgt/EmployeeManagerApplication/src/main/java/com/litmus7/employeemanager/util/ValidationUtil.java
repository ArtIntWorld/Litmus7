package com.litmus7.employeemanager.util;

import com.litmus7.employeemanager.constant.PatternConstants;

public class ValidationUtil {
	
	public static boolean validateCSVType(String filename) {
		//System.out.println(filename);
		return PatternConstants.CSVTYPE_REGEX.matcher(filename).matches();
	}
	
	public static boolean isValidEmail(String email) {
		//System.out.println(email);
	    return PatternConstants.EMAIL_REGEX.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
    	//System.out.println(phone);
        return PatternConstants.PHONE_REGEX.matcher(phone).matches();
    }

    public static boolean isValidName(String name) {
    	//System.out.println(name);
        return PatternConstants.NAME_REGEX.matcher(name).matches();
    }

    public static boolean isValidDepartment(String department) {
    	//System.out.println(department);
        return PatternConstants.DEPARTMENT_REGEX.matcher(department).matches();
    }

    public static boolean isValidSalary(String salary) {
    	//System.out.println(salary);
        return PatternConstants.SALARY_REGEX.matcher(salary).matches();
    }
	    
    public static boolean isValidJoinDate(String date) {
    	//System.out.println(date);
        return PatternConstants.DATE_REGEX.matcher(date).matches();
    }
}
