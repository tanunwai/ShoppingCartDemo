$(function(){
	//alert('Hello jQuery');
	$('.errorClass').each(function(){
		showError($(this));
	});
	$('.form-control').focus(function(){
		var labelId=$(this).attr('id')+'Error';
		$('#'+labelId).text("");
		showError($('#'+labelId+Error));
	});
	$('.form-control').blur(function(){
		var id=$(this).attr('id');
		var fName='valid'+id.substring(0,1).toUpperCase()+'()';
		var result=id[fName];
		return result;
	});
	$('#lgoinForm').submit(function(){
		//alert('button is working');
		var isCheck=new Boolean(true); 
		if(!validUserName()){isCheck=false;}
		if(!validPasswd()){isCheck=false;}
		if(!validGcdCode()){isCheck=false;}		
		return isCheck;
	})
	/* eye hide and show */
	const togglePassword = document.querySelector('#togglePassword');
	const password = document.querySelector('#loginPass');

	togglePassword.addEventListener('click', function () {
  		// toggle the type attribute
  		const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
  		password.setAttribute('type', type);
  		// toggle the eye slash icon
  		this.classList.toggle('fa-eye-slash');
	});	
})
	/* 驗証登入帳號 */
	function validUserName(){
		var id='userName';
		var value=$('#'+id).val();
		if(!value){
			$('#'+id+'Error').text('The account cannot be empty');
			showError($('#'+id+'Error'));
			return false;
		}
		if(value.length<3 || value.length>10){
			$('#'+id+'Error').text('The account length must be 3~10');
			showError($('#'+id+'Error'));
			return false;
		}
		return true;
	}
	/* 驗証登入密碼 */
	function validPasswd(){
		var id='loginPass';
		var value=$('#'+id).val();		
		if(!value){
			$('#'+id+'Error').text('The passwordd cannot be empty');
			showError($('#'+id+'Error'));
			return false;
		}
		if(value.length<4 || value.length>8){
			$('#'+id+'Error').text('The passwordd length must be 4~8');
			showError($('#'+id+'Error'));
			return false;
		}
		return true;
	}
	/* 圖形碼驗証 */
	function validGcdCode(){
		var id='gcdCode';
		var value=$('#'+id).val();
		if(!value){
			$('#'+id+'Error').text('the Code cannot be empty');
			showError($('#'+id+'Error'));
			return false;
		}
		if(value.length !=4){
			$('#'+id+'Error').text('Code length must be 4');
			showError($('#'+id+'Error'));
			return false
		}
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
		return true;
	}
	/* showError函式 */
	function showError(ele){
		var text=ele.text();
		if(!text){
			ele.css('display','none');
		}else{
			ele.css('display','');
		}
	}
	/* 換一張 */
	function _gcdCodeChange(){
		document.getElementById('imgGcdCode').src=document.getElementById('imgGcdCode').src+'?nocache='+new Date().getTime();		
	}