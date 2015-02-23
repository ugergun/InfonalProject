package com.umutgergun.infonal.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.umutgergun.infonal.model.Person;
import com.umutgergun.infonal.mongodb.MongoDBPersonDAO;
import com.mongodb.MongoClient;

@Controller
public class HomeController {

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody String add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDBPersonDAO mongodao = new MongoDBPersonDAO(mongo);
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		System.out.println(firstName + lastName + phoneNumber);
		Person p = new Person();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setPhoneNumber(phoneNumber);
		mongodao.createPerson(p);

		return "Succes";
	}

	public void saveDatabase() {

	}

	@RequestMapping(value = "/getusers", method = RequestMethod.POST)
	public @ResponseBody List<Person> getuser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDBPersonDAO mongodao = new MongoDBPersonDAO(mongo);
		return mongodao.readAllPerson();
	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public @ResponseBody String updateuser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDBPersonDAO mongodao = new MongoDBPersonDAO(mongo);
		Person p = new Person();
		p.setId(id);
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setPhoneNumber(phoneNumber);
		mongodao.updatePerson(p);

		return "Success";
	}

	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
	public @ResponseBody String deleteuser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDBPersonDAO mongodao = new MongoDBPersonDAO(mongo);
		Person p = new Person();
		p.setId(id);
		mongodao.deletePerson(p);
		return "Success";
	}

	@Autowired
	private ReCaptcha reCaptchaService = null;

	@RequestMapping(value = { "/", "/recaptcha" }, method = RequestMethod.GET)
	public String show() {
		return "index";
	}

	@RequestMapping(value = "/recaptcha", method = RequestMethod.POST)
	public String verify(HttpServletRequest request, Model model)
			throws Exception {
		String challenge = request.getParameter("recaptcha_challenge_field");
		String response = request.getParameter("recaptcha_response_field");
		String remoteAddr = request.getRemoteAddr();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("email");
		ReCaptchaResponse reCaptchaResponse = reCaptchaService.checkAnswer(
				remoteAddr, challenge, response);

		if (reCaptchaResponse.isValid()) {
			model.addAttribute("firstname",firstName);
			model.addAttribute("lastname",lastName);
			model.addAttribute("phonenumber",phoneNumber);
			model.addAttribute("message", "success");
			return "index";
		} else {
			model.addAttribute("firstname",firstName);
			model.addAttribute("lastname",lastName);
			model.addAttribute("phonenumber",phoneNumber);
			model.addAttribute("message", "Hatali Giris");
			return "index";
		}
	}

}