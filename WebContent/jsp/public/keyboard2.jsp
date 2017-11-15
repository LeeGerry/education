<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
.btn-default{
	width: 50px;
	height: 50px;
	font-size: 18px; 
}
.btn-control{
	width: 120px;
	height: 50px;
	font-size: 18px; 
}
	
.diacrit{
	font-size: 24px;
}
	
.stress{
	font-size: 24px;
}
</style>

<script type="text/javascript">
		var undo = "";
		var keyLenArr = [];
		var diaCount = 3;
		var stressFlag = false;
		
		function copyText(value) {
			var str1 = unescape(value.replace(/\\(u[0-9a-fA-F]{4})/gm, '%$1'));
			var next = document.getElementById("ta").value;
			next += str1;
			document.getElementById("ta").value = next;
		}
		function backspace() {
			//document.getElementById("getSymble").value="";
			var text = document.getElementById("ta").value;
			
			var keyLen = keyLenArr.pop()
			text = text.slice(0, keyLen*-1);
				
			document.getElementById("ta").value = text;
			
			stressFlag = false;
			
			if (diaCount < 3 && diaCount>0){
				diaCount = diaCount-1;
			}
			else{
				diaCount = 3;
			}
			
			disableKeys();
		}
		function clean() {
			document.getElementById("ta").value = "";
		}
		
		
		function save() {
			var select = document.getElementById("select");
			var index = select.selectedIndex ;  
			var showIndex = "answer" + index;
			var showLabel = document.getElementById(showIndex);
			var ans = document.getElementById("ta");
			showLabel.innerHTML = ans.value;
		}
		
		
		function submitResult(number) {
	       var arr = "";
	       for (var i = 0; i < number; i++) {  
	        	var showIndex = "answer" + i;
				var showLabel = document.getElementById(showIndex);
				arr += showLabel.innerHTML + "/";
	        } 
	       var url = document.getElementById("sub").value;
	       //url += "&result="+arr;
	       /* alert(result); */
	      	/* alert(arr); */
			var myForm = document.createElement("form");  
	        myForm.method = "post";  
	        myForm.action = url;  
	        
	        var myInput = document.createElement("input");  
	        myInput.name = "result";
	        myInput.value = arr;
	        myForm.appendChild(myInput);  
	        document.body.appendChild(myForm);  
	        myForm.submit();  
	        return myForm;
		}
		
		function setkeyLen(pressedKey){
			keyLenArr.push(pressedKey.length);
		}
		
		function setFlags(keyClass){
			if (keyClass == "stress"){
				stressFlag = true;
				diaCount = 3;
			}
			else if (keyClass == "diacrit"){
				diaCount++;
				stressFlag = false;
			}
			else if ((keyClass == "clear")||(keyClass == "space")){
				diaCount = 3;
				stressFlag = false;
			}
			else{
				diaCount = 0;
				stressFlag = false;
			}
		}
		
		function disableKeys(){
			var ans = document.getElementById("ta");
			
			
			if (stressFlag == true){
				var stressBtns = document.getElementsByClassName("stress");
				for (i = 0; i < stressBtns.length; i++){
					stressBtns[i].disabled = true;
				}
				var saveBtn = document.getElementById("save");
				saveBtn.disabled = true;
				saveBtn.title = "A letter must be entered before you can submit.";
			}
			else{
				var stressBtns = document.getElementsByClassName("stress");
				for (i = 0; i < stressBtns.length; i++){
					stressBtns[i].disabled = false;
				}
				var saveBtn = document.getElementById("save");
				saveBtn.disabled = false;
			}
			
			if (diaCount > 1){
				var diaBtns = document.getElementsByClassName("diacrit");
				for (i = 0; i < diaBtns.length; i++){
					diaBtns[i].disabled = true;
				}
			}
			else{
				var diaBtns = document.getElementsByClassName("diacrit");
				for (i = 0; i < diaBtns.length; i++){
					diaBtns[i].disabled = false;
				}
			}
			
			if (ans.value.length<1){
				var saveBtn = document.getElementById("save");
				saveBtn.disabled = true;
				saveBtn.title = "A letter must be entered before you can submit.";
			}
		}
		
		window.onload = disableKeys;
	</script>
<div class="row w3-center">

	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="p"  >p</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="t"  >t</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="k"  >k</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="b"  >b</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="d"  >d</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ɡ"  >ɡ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="t͡ʃ"  >t͡ʃ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="d͡ʒ"  >d͡ʒ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="s"  >s</button>
	<br>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ʃ"  >ʃ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="z"  >z</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ʒ"  >ʒ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="f"  >f</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="θ"  >θ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="v"  >v</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ð"  >ð</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="h"  >h</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="n"  >n</button>
	<br>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="m"  >m</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ŋ"  >ŋ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ʔ"  >ʔ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="l"  >l</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="r"  >r</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="w"  >w</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="j"  >j</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ɾ"  >ɾ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ɫ"  >ɫ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();" value="ɹ"
		 >ɹ</button>
	<br>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="i"  >i</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɪ"  >ɪ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɛ"  >ɛ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="e͡ɪ"  >e͡ɪ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="æ"  >æ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɑ"  >ɑ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɑ͡u"  >ɑ͡u</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɑ͡ɪ"  >ɑ͡ɪ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ʌ"  >ʌ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɔ"  >ɔ</button>
	<br>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɔ͡ɪ"  >ɔ͡ɪ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="o͡ʊ"  >o͡ʊ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ʊ"  >ʊ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="u"  >u</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɝ"  >ɝ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ə"  >ə</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɚ"  >ɚ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="n̩"  >n̩</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="m̩"  >m̩</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="l̩"  >l̩</button>
	<br>
	<button class="btn btn btn-default stress"
		onclick="copyText(value);setkeyLen(value);setFlags('stress');disableKeys();"
		value="ˈ"  >ˈ</button>
	<button class="btn btn btn-default stress"
		onclick="copyText(value);setkeyLen(value);setFlags('stress');disableKeys();"
		value="ˌ"  >ˌ</button>
	<button class="btn btn btn-default stress"
		onclick="copyText(value);setkeyLen(value);setFlags('stress');disableKeys();"
		value="."  >.</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̃"  >̃</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="͊"  >͊</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="͋"  >͋</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="ː"  >ː</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̥"  >̥</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̬"  >̬</button>
	<br>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="ͪ"  >ͪ</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̝"  >̝</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̞"  >̞</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̟"  >̟</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̱"  >̱</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̚"  >̚</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̤"  >̤</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̰"  >̰</button>
	<br>
	<button class="btn btn btn-default btn-control" 
		onclick="copyText(value);setkeyLen(value);setFlags('space');disableKeys();"
		value=" " >Space</button>
	<button class="btn btn btn-default btn-control" onclick="backspace()"
		>Backspace</button>
	<button class="btn btn btn-default btn-control"
		onclick="clean();setFlags('clear');disableKeys();"
		>Clear</button>
</div>