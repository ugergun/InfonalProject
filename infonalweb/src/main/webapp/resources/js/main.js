var personel = "";
$(document).ready(function() {
	$body = $("body");
	$(document).on({
		ajaxStart : function() {
			$body.addClass("loading");
		},
		ajaxStop : function() {
			$body.removeClass("loading");
		}
	});
});
function insertuser() {
	var pn = $("#email").val();
	var fn = $("#firstName").val();
	var ln = $("#lastName").val();
	var patt1 = new RegExp("^[a-zA-ZğüşçöıĞÜİŞÇÖ]{3,32}$");
	var patt2 = new RegExp("^[0-9]{10}$");
	if (!(patt1.test(fn))) {
		alert("En az 3 en çok 32 harfden oluşan bir isim giriniz. ");
		document.getElementById("choose").innerHTML = "";
		$('#wrapper').dialog('open');
	} else if (!(patt1.test(ln))) {
		alert("En az 3 en çok 32 harfden oluşan bir soyisim giriniz. ");
		document.getElementById("choose").innerHTML = "";
		$('#wrapper').dialog('open');
	} else if (!(patt2.test(pn))) {
		alert("Telefon numarası 10 haneli olmalı ve sayılardan oluşmalı");
		document.getElementById("choose").innerHTML = "";
		$('#wrapper').dialog('open');
	} else {
		$
				.ajax({
					type : "post",
					url : "http://localhost:8080/infonalweb/rest/insert",
					datatype : "html",
					data : 'firstName=' + fn + '&lastName=' + ln
							+ '&phoneNumber=' + pn,
					success : function(response) {
						alert("Kayıt Başarılı");
						location.href = 'http://localhost:8080/infonalweb';
					},
					error : function() {
					}
				});

	}
}
$(document).ready(function() {

	$('#wrapper').dialog({
		autoOpen : false,
		height : 410,
		width : 560,
		title : 'Kayıt Ekranı'
	});
	$('#opener').click(function() {
		$('#wrapper').dialog('open');
	});
});
function updatepage(id, firstName, lastName, phone) {
	personel = id;
	$("#firstName2").val(firstName + "");
	$("#lastName2").val(lastName + "");
	$("#phoneNumber2").val(phone + "");
	$('#update').dialog('open');
}

function updateuser() {
	var pn = $("#phoneNumber2").val();
	var fn = $("#firstName2").val();
	var ln = $("#lastName2").val();
	var patt1 = new RegExp("^[a-zA-ZğüşçöıĞÜİŞÇÖ]{3,32}$");
	var patt2 = new RegExp("^[0-9]{10}$");
	if (!(patt1.test(fn))) {
		alert("En az 3 en çok 32 harfden oluşan bir isim giriniz. ");
	} else if (!(patt1.test(ln))) {
		alert("En az 3 en çok 32 harfden oluşan bir soyisim giriniz");
	} else if (!(patt2.test(pn))) {
		alert("Telefon numarası 10 haneli olmalı ve sayılardan oluşmalı");
	} else {
		$.ajax({
			type : "post",
			url : "http://localhost:8080/infonalweb/rest//updateuser",
			datatype : "html",
			data : 'id=' + personel + '&firstName=' + fn + '&lastName=' + ln
					+ '&phoneNumber=' + pn,
			success : function(response) {
				$('#update').dialog('close');
				location.href = 'http://localhost:8080/infonalweb'
			},
			error : function() {
				alert('Error while request..');
			}
		});
	}
}
function removepage(id) {
	if (!confirm("Bunu silmek istediğinize emin misiniz? Bu işlemin geri dönüşü yoktur."))
		return false;
	$.ajax({
		type : "post",
		url : "http://localhost:8080/infonalweb/rest/deleteuser",
		datatype : "html",
		data : 'id=' + id,
		success : function(response) {
			location.href = 'http://localhost:8080/infonalweb'
		},
		error : function() {
			alert('Error while request..');
		}
	});
}
$(document).ready(function() {
	$('#update').dialog({
		autoOpen : false,
		height : 270,
		width : 620,
		title : 'Kullanıcı Güncelle'
	});
});
$(window)
		.load(
				function hadi() {
					$
							.ajax({
								type : "post",
								url : "http://localhost:8080/infonalweb/rest/getusers",
								success : function(response) {
									for (var i = 0; i < response.length; i++) {
										var first = response[i].firstName + "";
										var last = response[i].lastName + "";
										var phone = response[i].phoneNumber
												+ "";
										var id = response[i].id + "";
										$("#users")
												.append(
														"<tr>" + "<td>"
																+ first
																+ "</td>"
																+ "<td>"
																+ last
																+ "</td>"
																+ "<td>"
																+ phone
																+ "</td>"
																+ '<td><input type="button" style="height:30px; width:150px" onclick="updatepage(\''
																+ id
																+ '\', \''
																+ first
																+ '\', \''
																+ last
																+ '\', \''
																+ phone
																+ '\' )" id="edit'
																+ id
																+ '" value="Kullanıcı Guncelle" /></td>'
																+ '<td><input type="button" style="height:30px; width:150px" onclick="removepage(\''
																+ id
																+ '\')" id="remove'
																+ id
																+ '" value="Kullanıcıyı Sil" /></td>'
																+ "</tr>");
									}
								},
								error : function() {
								}
							});
					if (document.getElementById("choose").innerHTML == "Hatali Giris") {
						$('#wrapper').dialog('open');
					}
					if (document.getElementById("choose").innerHTML == "success") {
						insertuser();
					}
				})