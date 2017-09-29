<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	var undo = "";
	var keyLenArr = [];
	function copyText(value) {
		var str1 = unescape(value.replace(/\\(u[0-9a-fA-F]{4})/gm, '%$1'));
		var next = document.getElementById("ta").value;
		next += str1;
		document.getElementById("ta").value = next;
	}

	function clean() {
		document.getElementById("ta").value = "";
	}

	function save() {
		var select = document.getElementById("select");
		var index = select.selectedIndex;
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

	function backspace() {
		//document.getElementById("getSymble").value="";
		var text = document.getElementById("ta").value;

		var keyLen = keyLenArr.pop()
		text = text.slice(0, keyLen * -1);

		document.getElementById("ta").value = text;
	}

	function setkeyLen(pressedKey) {
		keyLenArr.push(pressedKey.length);
	}
</script>
<div class="row w3-center">
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="p"
		style="width: 40px; height: 40px;">p</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="t"
		style="width: 40px; height: 40px;">t</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="k"
		style="width: 40px; height: 40px;">k</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="b"
		style="width: 40px; height: 40px;">b</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="d"
		style="width: 40px; height: 40px;">d</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɡ"
		style="width: 40px; height: 40px;">ɡ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="t͡ʃ"
		style="width: 40px; height: 40px;">t͡ʃ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="d͡ʒ"
		style="width: 40px; height: 40px;">d͡ʒ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="s"
		style="width: 40px; height: 40px;">s</button>
	<br>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ʃ"
		style="width: 40px; height: 40px;">ʃ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="z"
		style="width: 40px; height: 40px;">z</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ʒ"
		style="width: 40px; height: 40px;">ʒ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="f"
		style="width: 40px; height: 40px;">f</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="θ"
		style="width: 40px; height: 40px;">θ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="v"
		style="width: 40px; height: 40px;">v</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ð"
		style="width: 40px; height: 40px;">ð</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="h"
		style="width: 40px; height: 40px;">h</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="n"
		style="width: 40px; height: 40px;">n</button>
	<br>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="m"
		style="width: 40px; height: 40px;">m</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ŋ"
		style="width: 40px; height: 40px;">ŋ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ʔ"
		style="width: 40px; height: 40px;">ʔ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="l"
		style="width: 40px; height: 40px;">l</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="r"
		style="width: 40px; height: 40px;">r</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="w"
		style="width: 40px; height: 40px;">w</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="j"
		style="width: 40px; height: 40px;">j</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɾ"
		style="width: 40px; height: 40px;">ɾ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɫ"
		style="width: 40px; height: 40px;">ɫ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɹ"
		style="width: 40px; height: 40px;">ɹ</button>
	<br>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="i"
		style="width: 40px; height: 40px;">i</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɪ"
		style="width: 40px; height: 40px;">ɪ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɛ"
		style="width: 40px; height: 40px;">ɛ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="e͡ɪ"
		style="width: 40px; height: 40px;">e͡ɪ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="æ"
		style="width: 40px; height: 40px;">æ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɑ"
		style="width: 40px; height: 40px;">ɑ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɑ͡u"
		style="width: 40px; height: 40px;">ɑ͡u</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɑ͡ɪ"
		style="width: 40px; height: 40px;">ɑ͡ɪ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ʌ"
		style="width: 40px; height: 40px;">ʌ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɔ"
		style="width: 40px; height: 40px;">ɔ</button>
	<br>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɔ͡ɪ"
		style="width: 40px; height: 40px;">ɔ͡ɪ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="o͡ʊ"
		style="width: 40px; height: 40px;">o͡ʊ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ʊ"
		style="width: 40px; height: 40px;">ʊ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="u"
		style="width: 40px; height: 40px;">u</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɝ"
		style="width: 40px; height: 40px;">ɝ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ə"
		style="width: 40px; height: 40px;">ə</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="ɚ"
		style="width: 40px; height: 40px;">ɚ</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="n̩"
		style="width: 40px; height: 40px;">n̩</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="m̩"
		style="width: 40px; height: 40px;">m̩</button>
	<button class="btn btn btn-default"
		onclick="copyText(value);setkeyLen(value);" value="l̩"
		style="width: 40px; height: 40px;">l̩</button>
	<br>
	<button class="btn btn btn-default" onclick="backspace()"
		style="width: 100px; height: 40px;">Backspace</button>
	<button class="btn btn btn-default" onclick="clean()"
		style="width: 100px; height: 40px;">Clear</button>
</div>
