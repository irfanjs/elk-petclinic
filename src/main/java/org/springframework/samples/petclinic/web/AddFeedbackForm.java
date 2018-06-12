package org.springframework.samples.petclinic.web;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.samples.petclinic.Clinic;
import org.springframework.samples.petclinic.Feedback;
import org.springframework.samples.petclinic.validation.FeedbackValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;


@Controller
@RequestMapping("/owners/new1")
@SessionAttributes(types = Feedback.class)
public class AddFeedbackForm {
	private final Clinic clinic;
	
	//  private final Logger logger = LoggerFactory.getLogger(getClass());
	  
	  private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AddFeedbackForm.class.getName());

	/*  @Autowired
	    RestTemplate restTemplete;
	 
	    @Bean
	    RestTemplate restTemplate() {
	        return new RestTemplate();
	    }*/

	@Autowired
	public AddFeedbackForm(Clinic clinic) {
		this.clinic = clinic;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		
		/*  String response = (String) restTemplete.exchange("http://localhost:8080/elkdemo", HttpMethod.GET, null, new ParameterizedTypeReference() {}).getBody();
		  LOG.log(Level.ERROR, "/elk - &gt; " + response);
	 
	        try {
	            String exceptionrsp = (String) restTemplete.exchange("http://localhost:8080/exception", HttpMethod.GET, null, new ParameterizedTypeReference() {
	            }).getBody();
	            LOG.log(Level.ERROR, "/elk trying to print exception - &gt; " + exceptionrsp);
	            response = response + " === " + exceptionrsp;
	        } catch (Exception e) {
	            // exception should not reach here. Really bad practice :)
	        }*/
		Feedback feed = new Feedback();
		model.addAttribute(feed);
		return "owners/feedback";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute Feedback feed, BindingResult result, SessionStatus status) {
		  String rsp = "";
	        try {
	            int i = 1 / 0;
	            // should get exception
	        } catch (Exception e) {
	            e.printStackTrace();
	            LOG.error(e);
	             
	            StringWriter sw = new StringWriter();
	            PrintWriter pw = new PrintWriter(sw);
	            e.printStackTrace(pw);
	            String sStackTrace = sw.toString(); // stack trace as a string
	            
	            LOG.error("Exception As String :: - &gt; "+sStackTrace);
	             
	            rsp = sStackTrace;
	        }
		new FeedbackValidator().validate(feed, result);
		if (result.hasErrors()) {
			return "owners/feedback";
		}
		else {
			this.clinic.storeFeedback(feed);
			status.setComplete();
			 LOG.error("First Name: " + feed.getFirstName());
			 LOG.error("Last Name: " + feed.getLastName());
			 LOG.error("Address: " + feed.getAddress());
			 LOG.error("City: " + feed.getCity());
			 LOG.error("Telephone: " + feed.getTelephone());
			 LOG.error("Details: " + feed.getDetails());
			return "redirect:/owners/feedback/" + feed.getId();
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}

}
