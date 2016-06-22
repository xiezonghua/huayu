package com.toread.etl.configure.bo;

public class TableColumnInfo {
	private String name;
	private String dataType;
	private String isNullable;
	private String fieldLength;
	private String scale;
	private String comment;
	private String isIdentity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIsIdentity() {
		return isIdentity;
	}

	public void setIsIdentity(String isIdentity) {
		this.isIdentity = isIdentity;
	}

	@Override
	public String toString() {
		return "TableColumnInfo [name=" + name + ", dataType=" + dataType
				+ ", isNullable=" + isNullable + ", fieldLength=" + fieldLength
				+ ", scale=" + scale + ", comment=" + comment + ", isIdentity="
				+ isIdentity + "]";
	}


}
