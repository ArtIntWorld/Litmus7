package com.litmus7.employeemanager.constant;

import java.util.regex.Pattern;

public class PatternConstants {
	public static final Pattern CSVTYPE_REGEX = Pattern.compile(".*\\.csv$");
	public static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    public static final Pattern PHONE_REGEX = Pattern.compile("^\\d{10}$");
    public static final Pattern NAME_REGEX = Pattern.compile("^[A-Za-z]{1,50}$");
    public static final Pattern DEPARTMENT_REGEX = Pattern.compile("^[A-Za-z ]{1,50}$");
    public static final Pattern SALARY_REGEX = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
    public static final Pattern DATE_REGEX = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$");
}
