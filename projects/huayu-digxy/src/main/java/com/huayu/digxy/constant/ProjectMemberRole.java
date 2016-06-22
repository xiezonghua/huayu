package com.huayu.digxy.constant;

public enum ProjectMemberRole {
	CREATOR((byte) 0), BONE_MEMBER((byte) 1), ATTENDER((byte) 2);

	private byte value;

	private ProjectMemberRole(byte value) {
		this.value = value;
	}

	public byte getValue() {
		return value;
	}

}
