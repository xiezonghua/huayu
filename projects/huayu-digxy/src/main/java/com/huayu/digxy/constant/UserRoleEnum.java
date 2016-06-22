package com.huayu.digxy.constant;

public enum UserRoleEnum {
	ADMIN(0), CUSTOMER(1);

	private byte value;

	private UserRoleEnum(Integer value) {
		this.value = value.byteValue();
	}

	public byte getValue() {
		return value;
	}
}
