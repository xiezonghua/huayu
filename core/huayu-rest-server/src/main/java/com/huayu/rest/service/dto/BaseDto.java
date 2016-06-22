package com.huayu.rest.service.dto;

import java.beans.PropertyDescriptor;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author wangmingzhe
 * 
 */
@XmlRootElement
public abstract class BaseDto {

	protected boolean success = true;
	protected String errorMessage = "";
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String toString() {
		StringBuffer strToReturn = new StringBuffer();
		PropertyDescriptor[] propDescs = PropertyUtils.getPropertyDescriptors(this);

		for (int i = 0; i < propDescs.length; i++) {
		String propName = propDescs[i].getName();
		
		    if ( PropertyUtils.isReadable(this, propName)
		             && PropertyUtils.isWriteable(this, propName) ) {

		      strToReturn.append( System.getProperty("line.separator") + " ==> " + propName + ": " );
		      try {
		          Object value = PropertyUtils.getProperty(this, propName);
		          strToReturn.append( value );
		      } catch (Exception e) {
		          strToReturn.append("");
		      }
		    }
		  }
		return strToReturn.toString();
	}
}
