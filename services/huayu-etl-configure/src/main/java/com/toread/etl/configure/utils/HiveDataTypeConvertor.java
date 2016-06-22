package com.toread.etl.configure.utils;

public class HiveDataTypeConvertor {
	public final static String STRING_TYPE = "string" ;
	public final static String INT_TYPE = "int";
	public final static String TINYINT_TYPE = "tinyint";	 
	public final static String SMALLINT_TYPE = "smallint";		 
	public final static String BIGINT_TYPE = "bigint";	
	public final static String BOOLEAN_TYPE = "boolean";	 	 
	public final static String FLOAT_TYPE = "float";	 
	public final static String DOUBLE_TYPE = "double";	 
	public final static String BINARY_TYPE = "binary";
	public final static String TIMESTAMP_TYPE = "timestamp";
	public final static String DECIMAL_TYPE = "decimal";
	public final static String CHAR_TYPE = "char";
	public final static String VARCHAR_TYPE = "varchar";
	public final static String DATE_TYPE = "date";
	
	public static String getType(String dataType){
		if(dataType.equals("int")){
			return INT_TYPE;
		}
		
		if(dataType.equals("tinyint") || dataType.contains("bit")){
			return TINYINT_TYPE;
		}
		
		if(dataType.equals("bigint")){
			return BIGINT_TYPE;
		}
		
		if(dataType.contains("int")){
			return INT_TYPE;
		}
		
		if(dataType.equals("boolean")){
			return BOOLEAN_TYPE;
		}
		
		if(dataType.equals("binary")){
			return BINARY_TYPE;
		}
		
		
		if(dataType.equals("timestamp")){
			return TIMESTAMP_TYPE;
		}
		
		if(dataType.contains("date")){
			return TIMESTAMP_TYPE;
		}
		
		if(dataType.equals("decimal")){
			return DECIMAL_TYPE;
		}
		
		
		return STRING_TYPE ;
	}
}
