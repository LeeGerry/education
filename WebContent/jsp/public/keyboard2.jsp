<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
			else if (keyClass == "clear"){
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
		value="p" style="width: 40px; height: 40px;">p</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="t" style="width: 40px; height: 40px;">t</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="k" style="width: 40px; height: 40px;">k</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="b" style="width: 40px; height: 40px;">b</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="d" style="width: 40px; height: 40px;">d</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ɡ" style="width: 40px; height: 40px;">ɡ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="t͡ʃ" style="width: 40px; height: 40px;">t͡ʃ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="d͡ʒ" style="width: 40px; height: 40px;">d͡ʒ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="s" style="width: 40px; height: 40px;">s</button>
	<br>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ʃ" style="width: 40px; height: 40px;">ʃ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="z" style="width: 40px; height: 40px;">z</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ʒ" style="width: 40px; height: 40px;">ʒ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="f" style="width: 40px; height: 40px;">f</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="θ" style="width: 40px; height: 40px;">θ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="v" style="width: 40px; height: 40px;">v</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ð" style="width: 40px; height: 40px;">ð</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="h" style="width: 40px; height: 40px;">h</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="n" style="width: 40px; height: 40px;">n</button>
	<br>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="m" style="width: 40px; height: 40px;">m</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ŋ" style="width: 40px; height: 40px;">ŋ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ʔ" style="width: 40px; height: 40px;">ʔ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="l" style="width: 40px; height: 40px;">l</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="r" style="width: 40px; height: 40px;">r</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="w" style="width: 40px; height: 40px;">w</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="j" style="width: 40px; height: 40px;">j</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ɾ" style="width: 40px; height: 40px;">ɾ</button>
	<button class="btn btn btn-default consonant"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();"
		value="ɫ" style="width: 40px; height: 40px;">ɫ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);setFlags('consonant');disableKeys();" value="ɹ"
		style="width: 40px; height: 40px;">ɹ</button>
	<br>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="i" style="width: 40px; height: 40px;">i</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɪ" style="width: 40px; height: 40px;">ɪ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɛ" style="width: 40px; height: 40px;">ɛ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="e͡ɪ" style="width: 40px; height: 40px;">e͡ɪ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="æ" style="width: 40px; height: 40px;">æ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɑ" style="width: 40px; height: 40px;">ɑ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɑ͡u" style="width: 40px; height: 40px;">ɑ͡u</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɑ͡ɪ" style="width: 40px; height: 40px;">ɑ͡ɪ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ʌ" style="width: 40px; height: 40px;">ʌ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɔ" style="width: 40px; height: 40px;">ɔ</button>
	<br>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɔ͡ɪ" style="width: 40px; height: 40px;">ɔ͡ɪ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="o͡ʊ" style="width: 40px; height: 40px;">o͡ʊ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ʊ" style="width: 40px; height: 40px;">ʊ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="u" style="width: 40px; height: 40px;">u</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɝ" style="width: 40px; height: 40px;">ɝ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ə" style="width: 40px; height: 40px;">ə</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="ɚ" style="width: 40px; height: 40px;">ɚ</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="n̩" style="width: 40px; height: 40px;">n̩</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="m̩" style="width: 40px; height: 40px;">m̩</button>
	<button class="btn btn btn-default vowel"
		onclick="copyText(value);setkeyLen(value);setFlags('vowel');disableKeys();"
		value="l̩" style="width: 40px; height: 40px;">l̩</button>
	<br>
	<button class="btn btn btn-default stress"
		onclick="copyText(value);setkeyLen(value);setFlags('stress');disableKeys();"
		value="ˈ" style="width: 40px; height: 40px;">ˈ</button>
	<button class="btn btn btn-default stress"
		onclick="copyText(value);setkeyLen(value);setFlags('stress');disableKeys();"
		value="ˌ" style="width: 40px; height: 40px;">ˌ</button>
	<button class="btn btn btn-default stress"
		onclick="copyText(value);setkeyLen(value);setFlags('stress');disableKeys();"
		value="." style="width: 40px; height: 40px;">.</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̃" style="width: 40px; height: 40px;">̃</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="͊" style="width: 40px; height: 40px;">͊</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="͋" style="width: 40px; height: 40px;">͋</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="ː" style="width: 40px; height: 40px;">ː</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̥" style="width: 40px; height: 40px;">̥</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̬" style="width: 40px; height: 40px;">̬</button>
	<br>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="ͪ" style="width: 40px; height: 40px;">ͪ</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̝" style="width: 40px; height: 40px;">̝</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̞" style="width: 40px; height: 40px;">̞</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̟" style="width: 40px; height: 40px;">̟</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̱" style="width: 40px; height: 40px;">̱</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̚" style="width: 40px; height: 40px;">̚</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̤" style="width: 40px; height: 40px;">̤</button>
	<button class="btn btn btn-default diacrit"
		onclick="copyText(value);setkeyLen(value);setFlags('diacrit');disableKeys();"
		value="̰" style="width: 40px; height: 40px;">̰</button>
	<br>
	<button class="btn btn btn-default" onclick="backspace()"
		style="width: 100px; height: 40px;">Backspace</button>
	<button class="btn btn btn-default"
		onclick="clean();setFlags('clear');disableKeys();"
		style="width: 100px; height: 40px;">Clear</button>
</div>