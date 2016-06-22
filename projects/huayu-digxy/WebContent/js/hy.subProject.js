var hy = hy ? hy : {};

if("undefined" == typeof(hy.subProject)){
	hy.subProject = {
		add : function(){
			var param = ["name" ,"description" ,"flagImage" , "goal" , "projectId"]; 
			if(!hyCom.validate(param)) return;
			var dataParam = {} ; 
			
			dataParam["subProjectModel.projectId"] =  jq("#projectId").val();
			dataParam["subProjectModel.name"] =  jq("#name").val();
			dataParam["subProjectModel.description"] =  jq("#description").val();
			dataParam["subProjectModel.flagImage"] =  jq("#flagImage").val();
			dataParam["subProjectModel.goal"] =  jq("#goal").val();
			
			if(!hy.subProject.loadCourseResources(dataParam)) return;
			
			hyCom.request(hy.subProject.url.add , dataParam , function(data){
				hyCom.msg("添加成功");
				hyCom.redirect("/sp/m");
			});
			
		},
		
		loadCourseResources: function(dataParam){
			var count = parseInt(jq("#courseResourceCount").val());
			for(var i=0 ; i < count; i++){
				var resParam = ["courseResources["+i+"].name" , "courseResources["+i+"].docName","courseResources["+i+"].description" ]
				if(!hyCom.validate(resParam)) return false;
				hyCom.loadModel(resParam , dataParam);
			}
			return true;
		},
		
		addCourseResourceHtml: function(wrapperID , countID){
			var i = parseInt(jq("#"+countID).val()) ;
			jq("#"+countID).val(i+1);
			var htmlMap = new Array();
			htmlMap.push('<p>基础资料'+(i+1)+'<span class="ic-arrow ic-arrow-down" id="courseResourceCollapseId'+i+'" /></p>')
			htmlMap.push('<section style="border:#ccc 1px solid;" id="courseResourceWrapperId'+i+'">')
			htmlMap.push('<section >')
			htmlMap.push('	<label for="courseResources['+i+'].name">资料名: </label>')
			htmlMap.push('	<input id="courseResources['+i+'].name" name="courseResources['+i+'].name"/> ')
			htmlMap.push('	<span id="courseResources['+i+'].name_msg"></span>')
			htmlMap.push('</section>')
			htmlMap.push('<section >')
			htmlMap.push('	<label for="courseResources['+i+'].docName">资料文件：</label>')
			
			htmlMap.push('	<input class="un-editable" disabled="disabled" id="courseResources['+i+'].docName" name="member.docName"/> ')
			htmlMap.push('	<span id="courseResources['+i+'].docName_msg"></span>')
			htmlMap.push('')
			htmlMap.push('	<section style="text-align:center;">')
			htmlMap.push('		<span class="btn btn-success fileinput-button">')
			htmlMap.push('			<i class="glyphicon glyphicon-plus"></i>')
			htmlMap.push('			<span>上传文件</span>')
			htmlMap.push('			<!-- The file input field used as target for the file upload widget -->')
			htmlMap.push('			<input id="uploadDoc'+i+'" type="file" name="myFile" multiple >')
			htmlMap.push('		</span>')
			htmlMap.push('	</section>')
			htmlMap.push('</section>')
			htmlMap.push('<section >')
			htmlMap.push('	<label for="courseResources['+i+'].description">描述:</label>')
			htmlMap.push('	<textarea  rows="10" cols="40" id="courseResources['+i+'].description" name="courseResources['+i+'].description"></textarea> ')
			htmlMap.push('	<span id="courseResources['+i+'].description_msg"></span>')
			htmlMap.push('</section></section>');
			
			jq("#"+wrapperID).append(htmlMap.join(""));
			
			hyCom.upload("uploadDoc"+i , "courseResources["+i+"].docName");
			hyFacade.folding("courseResourceCollapseId"+i , "courseResourceWrapperId"+i );

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
			
			hyCom.request(hy.subProject.url.audit , dataParam , function(data){
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
					html.push('<span class="gd_u_type"><a href="#" onclick="subProject.applyerInfo(');
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
							html.push('<a href="#" onclick="subProject.opt(\'user_');
							html.push(data[i].attenderId);
							html.push('\');" >处理</a>');
							html.push('<section id="opuser_');
							html.push(data[i].attenderId);
							html.push('" class="op" style="right:20px;">');
							html.push('<ul class="dropDown">');
							html.push('<li><a href="#" onclick="subProject.attenderAudit(');
							html.push(data[i].id);
							html.push(', 2)">通过</a></li>');
							html.push('<li><a href="#" onclick="subProject.attenderAudit(');
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
			
			hyCom.request(hy.subProject.url.pa_verify , dataParam , function(data){
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
			add : "/sp/add" ,
		}
	};
}