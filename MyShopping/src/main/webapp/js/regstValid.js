$(function(){
	//alert('Hello jQuery');
	$('.errorClass').each(function(){		
		showError($(this));
	});
		/* 取得焦點隱藏 */ 
		$('.form-control').focus(function(){
			var labelId=$(this).attr('id')+'Error';
			$('#'+labelId).text('');	
			showError($('#'+labelId+'Error'));
		});
		/* 失去焦點 */
		$('.form-control').blur(function(){
			var id=$(this).attr("id");
			var fName='validDate'+id.substring(0,1).toUpperCase()+'()';
			var result=id[fName];
			return result;
		});
	/*if click submit then show method */
	$('#registForm').submit(function(){
		//alert('button is working');
		var isCheck=new Boolean(true);
			if(!validateUserName()){isCheck=false;}
			if(!validatePassword()){isCheck=false;}
			//if(!validateRePassword()){isCheck=false;}
			if(!validateRealName()){isCheck=false;}
			if(!validateEmail()){isCheck=false;}
			if(!validatePhone()){isCheck=false;}
			//if(!validateSex()){isCheck=false;}
			//if(!validateBirthday()){isCheck=false;}
			//if(!validateAddress()){isCheck=false;}
			if(!validateChkgcdCode()){isCheck=false;}
				return isCheck;
	})
	/* eye hide and show 
	const togglePassword = document.querySelector('#togglePassword');
	const password = document.querySelector('#loginPass');

	togglePassword.addEventListener('click', function () {
  		// toggle the type attribute
  		const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
  		password.setAttribute('type', type);
  		// toggle the eye slash icon
  		this.classList.toggle('fa-eye-slash');
	});*/
});
	/* 登入名稱校驗 */
	function validateUserName(){
		var id='userName';
		var value=$('#'+id).val();
		if(!value){
			$('#'+id+'Error').text("The User account cannot be empty");
			showError($('#'+id+'Error'));
			return false;
		}
		if(value.length<3||value.length>10){
			$('#'+id+'Error').text('The User account must be 3~10');
			showError($('#'+id+'Error'));
			return false;
		}
		let xhr=new XMLHttpRequest();
		let urlString='api/checkacc/chkacc/'+value+'/rawdata';
		xhr.open('GET',urlString,true);
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4){
				if(xhr.status==200){
					let jsonString=xhr.responseText;
					let successObj=JSON.parse(jsonString);
					$('#'+id+'Error').text(successObj.userName+' '+'has already been used');
					showError($('#'+id+'Error'));
					return false;
				}if(xhr.status==400){
					let JsonString=xhr.responseText;
					let errorObj=JSON.parse(JsonString);
					$('#'+id+'Error').text(errorObj.msg);
					showError($('#'+id+'Error'));
					return true;
				}
			}
		}
		xhr.send();
		return true;
	}
	/* 登入密碼校驗 */
	function validatePassword(){
		var id='loginPass';
		var value=$('#'+id).val();
		if(!value){
			$('#'+id+'Error').text('Password field cannot be empty');
			showError($('#'+id+'Error'));
			return false;
		}
		if(value.length<4||value.length>8){
			$('#'+id+'Error').text('Password field must be 4~8');
			showError($('#'+id+'Error'));
			return false;
		}
		return true; 
	}
	/*密碼再次驗証
	function validateRePassword(){
		var id='reloginPass';
		var value=$('#'+id).val();
		if(!value){
			$('#'+id+'Error').text('RePassword field cannot be empty');
			showError($('#'+id+'Error'));
			return false;
		}
		if(value.length<4||value.length>8){
			$('#'+id+'Error').text('RePassword field must be 4~8');
			showError($('#'+id+'Error'));
			return false;
		}
		if(value!=$('#loginPass').val()){
			$('#'+id+'Error').text('The passwords on both sides are different');
			showError($('#'+id+'Error'));
			return false;
		}
		return true; 
	}*/
	/* 校驗真實名稱 */
	function validateRealName(){
		var id='realName';
		var value=$('#'+id).val();
		if(!value){
			$('#'+id+'Error').text('RealName cannot be empty');
			showError($('#'+id+'Error'));
			return false;
		}	
		return true;
	}
	/* 校驗Email */
	function validateEmail(){
		var id='email';
		var value=$('#'+id).val();
		if(!value){
			$('#'+id+'Error').text('Emailt field cannot be empty');
			showError($('#'+id+'Error'));
			return false;
		}
		/*if(!/^([a-zA-Z0-9_-]) @([a-zA-Z0-9_-]) ((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)){
			$('#'+id+'Error').text('Email format is incorrect');
			showError($('#'+id+'Error'));
			return false;
		} */
		return true;
	}
	/* 聯絡電話校驗 */
	function validatePhone(){
		var id='phone';
		var value=$('#'+id).val();
		if(!value){
			$('#'+id+'Error').text('Phone field cannot be empty');
			showError($('#'+id+'Error'));
			return false;
		}	
		return true;
	}
	/* 姓別欄校驗 
	function validateSex(){
		var value=$("input[type='radio'][name='sex']:checked").val();
		if(!value){
			$('#'+id+'Error').text('Choice the field');
			showError($('#'+id+'Error'));
			return false;
		}	
		return true;
	}*/
	/* 校驗生日欄 
	function validateBirthday(){
		var id='birthday';
		var value=$('#'+id).val();
		if(!value){
			$('#'+id+'Error').text('Birthday field cannot be empty');
			showError($('#'+id+'Error'));
			return false;
		}	
		return true;
	}*/
	/* 校驗地址欄 
	function validateAddress(){
		var id='address';
		var value=$('#'+id).val();
		if(!value){
			$('#'+id+'Error').text('Address field cannot be empty');
			showError($('#'+id+'Error'));
			return false;
		}	
		return true;
	}*/
	/* 圖形碼驗証 */
	function validateChkgcdCode(){
		var id='gcdCode';
		var value=$('#'+id).val();
		if(!value){
			$('#'+id+'Error').text('Graphic code field cannot be empty');
			showError($('#'+id+'Error'));
			return false;
		}
		if(value.length!=4){
			$('#'+id+'Error').text('Graphic code field must be 4');
			showError($('#'+id+'Error'));
			return false;
		}
		/*var objSetting={
			url:'GcdCodeServlet',
			type:'POST',
			data:{method:'ajaxverifyCode',gcdCode:value},
			dataType:'json',
			async:true,
			cache:false,
			errror:function(){
				$('#'+id+'Error').text('fill in the wrong code');
				showError($('#'+id+'Error'));
				return false;
			},
			success:function(result){
				if(!result){
					$('#'+id+'Error').text('fill in the wrong code');
					showError($('#'+id+'Error'));
					return false;
				}
			}
		}*/		
		let xhr=new XMLHttpRequest();
		let urlString='api/gcdcode/chkgcd/'+value+'/rawdata';
		xhr.open('GET',urlString,true);
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4){
				if(xhr.status==200){
					let jsonStringA=xhr.responseText;
					let successObjA=JSON.parse(jsonStringA);
					$('#'+id+'Error').text(successObjA.msg);
					showError($('#'+id+'Error'));
					return true;
				}if(xhr.status==400){
					let JsonStringB=xhr.responseText;
					let errorObjB=JSON.parse(JsonStringB);
					$('#'+id+'Error').text(errorObjB.msg);
					showError($('#'+id+'Error'));
					return false;
				}
			}
		}
		xhr.send();
		//$.ajax(objSetting)
		return true;
	}
	/* 定義showError() */
		function showError(ele){
			var text=ele.text();
			if(!text){
				ele.css('dispplay','none');
			}else{
				ele.css('display',"");
			}
		}															
	/* 換一張 */
	function _gcdCodeChange(){
		document.getElementById('imgGcdCode').src=document.getElementById('imgGcdCode').src+'?nocache='+new Date().getTime();		
	}	