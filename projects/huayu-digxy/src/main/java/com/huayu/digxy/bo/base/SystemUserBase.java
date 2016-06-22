package com.huayu.digxy.bo.base;

import java.util.Date;

public class SystemUserBase {
	/**
	*  KEY ID
	*/
	private Long id ; 

	/**
	*  user Name
	*/
	private String name ; 

	/**
	*  petName
	*/
	private String petName ; 

	/**
	*  password
	*/
	private String password ; 

	/**
	*  boy or girl
	*/
	private Byte sex ; 

	/**
	*  signature for yourself
	*/
	private String signature ; 

	/**
	*  email address
	*/
	private String email ; 

	/**
	*  imgSrc
	*/
	private String imgSrc ; 

	/**
	*  cardId
	*/
	private String cardId ; 

	/**
	*  imessager
	*/
	private String imessager ; 

	/**
	*  phone
	*/
	private String phone ; 

	/**
	*  sign in time
	*/
	private Date regtime ; 

	/**
	*  resume
	*/
	private String profile ; 

	/**
	*  sign off time
	*/
	private Date lastSignIn ; 

	/**
	*  prestige
	*/
	private String prestige ; 

	/**
	*  level
	*/
	private Byte level ;

	/**
	*  levelName
	*/
	private String levelName ; 

	/**
	*  1: common ; 100: manager ; 101 : super
	*/
	private Byte role ;

	/**
	*  birthplace
	*/
	private String birthplace ; 

	/**
	*  birthdate
	*/
	private Date birthdate ; 

	/**
	*  current study
	*/
	private String school ; 

	/**
	*  major
	*/
	private String major ; 

	/**
	*  health
	*/
	private String health ; 

	/**
	*  englishLevel
	*/
	private String englishLevel ; 

	/**
	*  edu
	*/
	private String edu ; 

	/**
	*  skillCareer
	*/
	private String skillCareer ; 


	public void setId(Long id){
		this.id = id;
	} 

	public Long getId(){
		return id;
	}  

	public void setName(String name){
		this.name = name;
	} 

	public String getName(){
		return name;
	}  

	public void setPetName(String petName){
		this.petName = petName;
	} 

	public String getPetName(){
		return petName;
	}  

	public void setPassword(String password){
		this.password = password;
	} 

	public String getPassword(){
		return password;
	}  

	public void setSex(Byte sex){
		this.sex = sex;
	} 

	public Byte getSex(){
		return sex;
	}  

	public void setSignature(String signature){
		this.signature = signature;
	} 

	public String getSignature(){
		return signature;
	}  

	public void setEmail(String email){
		this.email = email;
	} 

	public String getEmail(){
		return email;
	}  

	public void setImgSrc(String imgSrc){
		this.imgSrc = imgSrc;
	} 

	public String getImgSrc(){
		return imgSrc;
	}  

	public void setCardId(String cardId){
		this.cardId = cardId;
	} 

	public String getCardId(){
		return cardId;
	}  

	public void setImessager(String imessager){
		this.imessager = imessager;
	} 

	public String getImessager(){
		return imessager;
	}  

	public void setPhone(String phone){
		this.phone = phone;
	} 

	public String getPhone(){
		return phone;
	}  

	public void setRegtime(Date regtime){
		this.regtime = regtime;
	} 

	public Date getRegtime(){
		return regtime;
	}  

	public void setProfile(String profile){
		this.profile = profile;
	} 

	public String getProfile(){
		return profile;
	}  

	public void setLastSignIn(Date lastSignIn){
		this.lastSignIn = lastSignIn;
	} 

	public Date getLastSignIn(){
		return lastSignIn;
	}  

	public void setPrestige(String prestige){
		this.prestige = prestige;
	} 

	public String getPrestige(){
		return prestige;
	}  

	public void setLevel(Byte level){
		this.level = level;
	} 

	public Byte getLevel(){
		return level;
	}  

	public void setLevelName(String levelName){
		this.levelName = levelName;
	} 

	public String getLevelName(){
		return levelName;
	}  

	public void setRole(Byte role){
		this.role = role;
	} 

	public Byte getRole(){
		return role;
	}  

	public void setBirthplace(String birthplace){
		this.birthplace = birthplace;
	} 

	public String getBirthplace(){
		return birthplace;
	}  

	public void setBirthdate(Date birthdate){
		this.birthdate = birthdate;
	} 

	public Date getBirthdate(){
		return birthdate;
	}  

	public void setSchool(String school){
		this.school = school;
	} 

	public String getSchool(){
		return school;
	}  

	public void setMajor(String major){
		this.major = major;
	} 

	public String getMajor(){
		return major;
	}  

	public void setHealth(String health){
		this.health = health;
	} 

	public String getHealth(){
		return health;
	}  

	public void setEnglishLevel(String englishLevel){
		this.englishLevel = englishLevel;
	} 

	public String getEnglishLevel(){
		return englishLevel;
	}  

	public void setEdu(String edu){
		this.edu = edu;
	} 

	public String getEdu(){
		return edu;
	}  

	public void setSkillCareer(String skillCareer){
		this.skillCareer = skillCareer;
	} 

	public String getSkillCareer(){
		return skillCareer;
	}  

}