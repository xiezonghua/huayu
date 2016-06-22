var hy = hy ? hy : {};

if("undefined" == typeof(hy.project)){
	hy.project = {
		add : function(){
//			var param = ["name" , "beginDatetime", "endDatetime" , "attenderLimit" ,"description" , "planDoc" , "fosterDoc" , "talentsDoc"];
			var param = ["name" ,"description" ,"flagImage" ]; 
			if(!hyCom.validate(param)) return;
			var memberParam = ["member.name" , "member.imgSrc","member.education" , "member.researchFields" , "member.responsibility","member.job" , "member.jobTitle" ,"member.projectExperience" ];
			if(!hyCom.validate(memberParam)) return;
			
			var dataParam = hyCom.loadModel(memberParam);
			dataParam["projectModel.name"] =  jq("#name").val();
			dataParam["projectModel.description"] =  jq("#description").val();
			dataParam["projectModel.flagImage"] =  jq("#flagImage").val();
			
			hy.project.loadBoneMembers(dataParam);
			
			hyCom.request(hy.project.url.add , dataParam , function(data){
				hyCom.msg("添加成功");
				hyCom.redirect("/p/m");
			});
			
		},
		
		loadBoneMembers: function(dataParam){
			var count = parseInt(jq("#boneMemberCount").val());
			for(var i=0 ; i < count; i++){
				var membersParam = ["boneMembers["+i+"].name" , "boneMembers["+i+"].imgSrc","boneMembers["+i+"].education" , "boneMembers["+i+"].researchFields" , "boneMembers["+i+"].responsibility","boneMembers["+i+"].job" , "boneMembers["+i+"].jobTitle" ,"boneMembers["+i+"].projectExperience"]
				if(!hyCom.validate(membersParam)) return;
				hyCom.loadModel(membersParam , dataParam);
			}
			return dataParam;
		},
		
		addBoneMemberHtml: function(wrapperID , countID){
			var i = parseInt(jq("#"+countID).val()) ;
			jq("#"+countID).val(i+1);
			var htmlMap = new Array();
			htmlMap.push('<p>骨干成员'+(i+1)+'<span class="ic-arrow ic-arrow-down" id="boneMemberCollapseId'+i+'" /></p>')
			htmlMap.push('<section style="border:#ccc 1px solid;" id="boneMemberWrapperId'+i+'">')
			htmlMap.push('<section >')
			htmlMap.push('	<label for="boneMembers['+i+'].name">姓名：</label>')
			htmlMap.push('	<input id="boneMembers['+i+'].name" name="boneMembers['+i+'].name"/> ')
			htmlMap.push('	<span id="boneMembers['+i+'].name_msg"></span>')
			htmlMap.push('</section>')
			htmlMap.push('<section >')
			htmlMap.push('	<label for="boneMembers['+i+'].imgSrc">头像：</label>')
			htmlMap.push('	<input id="boneMembers['+i+'].imgSrc" name="member.imgSrc"/> ')
			htmlMap.push('	<span id="boneMembers['+i+'].imgSrc_msg"></span>')
			htmlMap.push('')
			htmlMap.push('	<section style="text-align:center;">')
			htmlMap.push('		<span class="btn btn-success fileinput-button">')
			htmlMap.push('			<i class="glyphicon glyphicon-plus"></i>')
			htmlMap.push('			<span>上传创建人头像</span>')
			htmlMap.push('			<!-- The file input field used as target for the file upload widget -->')
			htmlMap.push('			<input id="uploadImgSrc'+i+'" type="file" name="myFile" multiple >')
			htmlMap.push('		</span>')
			htmlMap.push('	</section>')
			htmlMap.push('</section>')
			htmlMap.push('<section >')
			htmlMap.push('	<label for="boneMembers['+i+'].education">学历：</label>')
			htmlMap.push('	<input id="boneMembers['+i+'].education" name="boneMembers['+i+'].education"/> ')
			htmlMap.push('	<span id="boneMembers['+i+'].education"></span>')
			htmlMap.push('</section>')
			htmlMap.push('<section >')
			htmlMap.push('	<label for="boneMembers['+i+'].researchFields">研究领域：</label>')
			htmlMap.push('	<input id="boneMembers['+i+'].researchFields" name="boneMembers['+i+'].researchFields"/> ')
			htmlMap.push('	<span id="boneMembers['+i+'].researchFields_msg"></span>')
			htmlMap.push('</section>')
			htmlMap.push('<section >')
			htmlMap.push('	<label for="boneMembers['+i+'].responsibility">职责：</label>')
			htmlMap.push('	<input id="boneMembers['+i+'].responsibility" name="boneMembers['+i+'].responsibility"/> ')
			htmlMap.push('	<span id="boneMembers['+i+'].responsibility_msg"></span>')
			htmlMap.push('</section>')
			htmlMap.push('')
			htmlMap.push('	')
			htmlMap.push('<section >')
			htmlMap.push('	<label for="boneMembers['+i+'].job">单位（公司/学校）：</label>')
			htmlMap.push('	<input id="boneMembers['+i+'].job" name="boneMembers['+i+'].job"/> ')
			htmlMap.push('	<span id="boneMembers['+i+'].job_msg"></span>')
			htmlMap.push('</section>')
			htmlMap.push('<section >')
			htmlMap.push('	<label for="boneMembers['+i+'].jobTitle">职务：</label>')
			htmlMap.push('	<input id="boneMembers['+i+'].jobTitle" name="boneMembers['+i+'].jobTitle"/> ')
			htmlMap.push('	<span id="boneMembers['+i+'].jobTitle_msg"></span>')
			htmlMap.push('</section>')
			htmlMap.push('<section >')
			htmlMap.push('	<label for="boneMembers['+i+'].projectExperience">项目经验</label>')
			htmlMap.push('	<textarea  rows="10" cols="40" id="boneMembers['+i+'].projectExperience" name="boneMembers['+i+'].projectExperience"></textarea> ')
			htmlMap.push('	<span id="boneMembers['+i+'].projectExperience_msg"></span>')
			htmlMap.push('</section></section>')
			
			jq("#"+wrapperID).append(htmlMap.join(""));
			
			hyCom.upload("uploadImgSrc"+i , "boneMembers["+i+"].imgSrc");
			hyFacade.folding("boneMemberCollapseId"+i , "boneMemberWrapperId"+i );

		},
		
		audit : function(id , type , index){
			if(!id || !type){
				hyCom.msg("页面加载有误，请重新加载重试");
				return;
			}
			var msg = jq("#refuseMsg").val();
			
			if( type == 3 && !msg){
				return this.refuseMsg(id,type , this.audit);
			}
			
			var dataParam = {
				"projectModel.id": id,
				"projectModel.status" :type ,
				"projectModel.checkMsg" : msg
			};
			
			hyCom.request(hy.project.url.audit , dataParam , function(data){
				jq("#status_"+id).html(auditStatus[type].typeName);
				hyCom.msg("处理完成");
			});
			

			if(index){
				hyCom.dialogCloseById(index);
			}
		},
		refuseMsg : function(id, type , callback){
			var html =  new Array();
			html.push(' <section style="text-align:center;">');
			html.push('<p style="padding:20px;"><textarea cols="50" rows="8" id="refuseMsg" style="border:1px solid #afafaf;"></textarea></p>');
			html.push('<p style=""><input type="button" value="确定" id="refuseBtn"'); 
			html.push(' /></p>');
			html.push('</section>');
			
			var index = hyCom.openHtml(html.join("") , "填写拒绝原因");
			
			jq("#refuseBtn").click(function(){
				callback && callback(id, type , index);
			});
			
		},
		
		opt : function(id){
			jq("#op"+id).show();

			jq("section[class='op']").mouseleave(function(){
					jq(this).hide();	
			});
		},
		
		//attendre info
		attenderInfo: function(projectId, isAttender){
			hyCom.request(this.url.pa_g, {"projectModel.id": projectId} , function(data){
				var html = new Array();
				html.push('<div class="result_header"> <span class="font-weight:700"> </span>');
				html.push('	 共计  <span class="data_list_summary">');
				html.push(data.length);
				html.push('</span>&nbsp;位参与人');
				html.push('	</div> <div> <section class="basic_article"> <p>');
				html.push('						<span class="gd_u_type">名称</span>');
			    html.push('							<span class="gd_u_type">申请时间</span>');
			    html.push('							<span class="gd_u_type">角色</span>');
			    html.push('									<span class="gd_u_type" >状态</span>');
			    if(!isAttender){
			    	html.push('						<span class="gd_handler">操作</span>');
			    }else{
			    	html.push('						<span class="gd_handler">审核信息</span>');
			    }
				html.push('					</p>	 </section>');
				
				for(var i = 0 ; i < data.length ; i++){
					html.push('<section class="mouse_hover basic_article"> <section class="article_title">');
					html.push('<span class="gd_u_type"><a href="#" onclick="project.applyerInfo(');
					html.push(data[i].attenderId);
					html.push(',\'');
					html.push(data[i].attenderPetName);
					html.push('\')" >');		
					html.push(data[i].attenderPetName);
					html.push('</a></span>');
					html.push('<span class="gd_u_type">');
					html.push(data[i].applyDate);
					html.push('</span> ');
					html.push('<span class="gd_u_type "  style="color:#FF6666;" >');
					html.push(attenderRoles[data[i].role].typeName);
					html.push('</span>');
					html.push('<span class="gd_u_type "  style="color:#FF6666;"  id="attenderStatus_');
					html.push(data[i].id);
					html.push('">');
					html.push(attenderStatus[data[i].state].typeName);
					html.push('</span>');
					
					if(!isAttender){
						html.push('<span class="gd_handler">');
						if(data[i].role == 2){
							html.push('<a href="#" onclick="project.opt(\'user_');
							html.push(data[i].attenderId);
							html.push('\');" >处理</a>');
							html.push('<section id="opuser_');
							html.push(data[i].attenderId);
							html.push('" class="op" style="right:20px;">');
							html.push('<ul class="dropDown">');
							html.push('<li><a href="#" onclick="project.attenderAudit(');
							html.push(data[i].id);
							html.push(', 2)">通过</a></li>');
							html.push('<li><a href="#" onclick="project.attenderAudit(');
							html.push(data[i].id);
							html.push(', 4)">拒绝</a></li>');
							html.push('</ul> </section></span>');
						}
					}else{
						html.push('						<span class="gd_handler">');
						html.push(data[i].msg);
						html.push('</span>');
					}
					
					html.push('</section></section>');
				}
				
				html.push("</div>");
				
				hyCom.open({
					type : 1,
					area : [ '700px', '360px' ],
					shadeClose : true, // 点击遮罩关闭
					content : html.join(""),
					title: "项目申请人管理" , 
	
				});
			});
		},
		
		attenderAudit : function(id , type , index){
			if(!id || !type){
				hyCom.msg("页面加载有误，请重新加载重试");
				return;
			}
			
			var msg = jq("#refuseMsg").val();
			
			if( type == 4 && !msg){
				return this.refuseMsg(id,type , this.attenderAudit);
			}
			var dataParam = {
				"projectModel.id": id,
				"projectModel.status" :type , 
				"projectModel.msg" : msg
			};
			
			hyCom.request(hy.project.url.pa_verify , dataParam , function(data){
				jq("#attenderStatus_"+id).html(attenderStatus[type].typeName);
				hyCom.msg("处理完成");
			});
			
			if(index){
				hyCom.dialogCloseById(index);
			}
		},
		
		applyerInfo : function(id , name){
			layer.open({
	            type: 2,
	            title: "项目参与人信息 ----"+name,
	            shadeClose: true,
	            shade: false,
	            maxmin: true, //开启最大化最小化按钮
	            area: ['720px', '450px'],
	            content: 'applyer?userModel.id='+id
	        });
		},
		
		url :{
			add : "/p/add" ,
			audit: "/p/audit",
			pa_g: "/pa/g" , 
			pa_verify: "/pa/verify",
		}
	};
}