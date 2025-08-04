package com.litmus7.employeemanager.util;

import com.litmus7.employeemanager.constant.PatternConstants;

public class ValidationsUtil {	
	
	public static boolean isValidEmail(String email) {	
	    return PatternConstants.EMAIL_REGEX.matcher(email).matches() && email != null;
    }

    public static boolean isValidPhone(String phone) {
        return PatternConstants.PHONE_REGEX.matcher(phone).matches() && phone != null;
    }

    public static boolean isValidName(String name) {
        return PatternConstants.NAME_REGEX.matcher(name).matches() && name != null;
    }

    public static boolean isValidDepartment(String department) {
        return PatternConstants.DEPARTMENT_REGEX.matcher(department).matches() && department != null;
    }

    public static boolean isValidSalary(String salary) {
        return PatternConstants.SALARY_REGEX.matcher(salary).matches() && salary != null;
    }
	    
    public static boolean isValidDate(String date) {
        return PatternConstants.DATE_REGEX.matcher(date).matches() && date != null;
    }
    
    public static boolean validateEmployee(String[] employee) {
    	return isValidName(employee[1]) && isValidName(employee[2]) && isValidEmail(employee[3]) && isValidPhone(employee[4]) && isValidSalary(employee[6]) && isValidDepartment(employee[5]) && isValidDate(employee[7]);
    }
}
