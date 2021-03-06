if("undefined" == typeof(hyValidate)){
	var hyValidate = {
			rule : {
				numericRegex : /^[0-9]+$/,
		        integerRegex : /^\-?[0-9]+$/,
		        decimalRegex : /^\-?[0-9]*\.?[0-9]+$/,
		        emailRegex : /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
		        alphaRegex : /^[a-z]+$/i,
		        alphaNumericRegex : /^[a-z0-9]+$/i,
		        alphaDashRegex : /^[a-z0-9_\-]+$/i,
		        naturalRegex : /^[0-9]+$/i,
		        naturalNoZeroRegex : /^[1-9][0-9]*$/i,
		        ipRegex : /^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$/i,
		        base64Regex : /[^a-zA-Z0-9\/\+=]/i,
		        numericDashRegex : /^[\d\-\s]+$/,
		        urlRegex : /^((http|https):\/\/(\w+:{0,1}\w*@)?(\S+)|)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/,
		        realnameRegex: "^[A-Za-z\\u4e00-\\u9fa5]+$",  // 真实姓名
		        mobileRegex: "^0?(13|15|18)[0-9]{9}$", //手机
			},
			
			date : function(value){
				return !/Invalid|NaN/.test( new Date( value ).toString() );
			},
			
			email : function(value){
				return emailRegex.test(value);
			},
			
			realname : function(value){
				return realnameRegex.test(value);
			},
			
			mobile : function(value){
				return mobileRegex.test(value);
			},
			
			numeric : function(value){
				return numericRegex.test(value);
			},
			
			
			//--身份证号码验证-支持新的带x身份证 
			isCardID : function (num , msgId)  
			{ 
			    var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1); 
//			    var error; 
			    var varArray = new Array(); 
//			    var intValue; 
			    var lngProduct = 0; 
			    var intCheckDigit; 
			    var intStrLen = num.length; 
			    var idNumber = num;     
			    // initialize 
			    if ((intStrLen != 15) && (intStrLen != 18)) { 
			        error = "输入身份证号码长度不对！"; 
			        //alert(error); 
			        //frmAddUser.txtIDCard.focus(); 
			        return false; 
			    }     
			    // check and set value 
			    for(var i=0;i<intStrLen;i++) { 
			        varArray[i] = idNumber.charAt(i); 
			        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) { 
			        	error = "错误的身份证号码！."; 
			            //alert(error); 
			            //frmAddUser.txtIDCard.focus(); 
			            return false; 
			        } else if (i < 17) { 
			            varArray[i] = varArray[i]*factorArr[i]; 
			        } 
			    } 
			    if (intStrLen == 18) { 
			        //check date 
			        var date8 = idNumber.substring(6,14); 
			        if (this.date(date8) == false) { 
			            error = "身份证中日期信息不正确！."; 
			            //alert(error); 
			            return false; 
			        }         
			        // calculate the sum of the products 
			        for(var i=0;i<17;i++) { 
			            lngProduct = lngProduct + varArray[i]; 
			        }         
			        // calculate the check digit 
			        intCheckDigit = 12 - lngProduct % 11; 
			        switch (intCheckDigit) { 
			            case 10: 
			                intCheckDigit = 'X'; 
			                break; 
			            case 11: 
			                intCheckDigit = 0; 
			                break; 
			            case 12: 
			                intCheckDigit = 1; 
			                break; 
			        }         
			        // check last digit 
			        if (varArray[17].toUpperCase() != intCheckDigit) { 
			            error = "身份证效验位错误!...正确为： " + intCheckDigit + "."; 
			            //alert(error); 
			            return false; 
			        } 
			    }  
			    else{        //length is 15 
			        //check date 
			        var date6 = idNumber.substring(6,12); 
			        if (this.date(date6) == false) { 
			            //alert("身份证日期信息有误！."); 
			            return false; 
			        } 
			    } 
			    //alert ("Correct."); 
			    return true; 
			}
	};
};