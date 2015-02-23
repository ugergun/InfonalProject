<%@ tag import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ tag import="net.tanesha.recaptcha.ReCaptchaFactory" %>

<script type="text/javascript">var RecaptchaOptions = {theme : 'clean'};</script> 
<%
	ReCaptcha reCaptcha = ReCaptchaFactory.newReCaptcha("6LdsegITAAAAAPV9PBRnMB8hcHrBGil8GdJjsL4D", "6LdsegITAAAAAMQUSjoXpjjAQhNaZrCaInOGdgE6", false);
	out.print(reCaptcha.createRecaptchaHtml(null, null));
%>