
package org.springframework.samples.petclinic.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.Clinic;
import org.springframework.samples.petclinic.Vets;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



/**
 * Annotation-driven <em>MultiActionController</em> that handles all non-form
 * URL's.
 *
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class ClinicController {
	
	 private static final Logger LOG = Logger.getLogger(ClinicController.class.getName());

	private final Clinic clinic;
	
	/* @Autowired
	    RestTemplate restTemplete;
	 
	    @Bean
	    RestTemplate restTemplate() {
	        return new RestTemplate();
	    }*/


	@Autowired
	public ClinicController(Clinic clinic) {
		this.clinic = clinic;
	}

	/**
	 * Custom handler for the welcome view.
	 * <p>
	 * Note that this handler relies on the RequestToViewNameTranslator to
	 * determine the logical view name based on the request URL: "/welcome.do"
	 * -&gt; "welcome".
	 */
	@RequestMapping("/")
	public String welcomeHandler() {
		   String response = "Hello user ! " + new Date();
	        LOG.log(Level.ERROR, "/elkdemo - &gt; " + response);
		return "welcome";
	}

	/**
	 * Custom handler for displaying vets.
	 *
	 * <p>Note that this handler returns a plain {@link ModelMap} object instead of
	 * a ModelAndView, thus leveraging convention-based model attribute names.
	 * It relies on the RequestToViewNameTranslator to determine the logical
	 * view name based on the request URL: "/vets.do" -&gt; "vets".
	 *
	 * @return a ModelMap with the model attributes for the view
	 */
	@RequestMapping("/vets")
	public ModelMap vetsHandler() {
		/*  String response = (String) restTemplete.exchange("http://localhost:8080/elkdemo", HttpMethod.GET, null, new ParameterizedTypeReference() {}).getBody();
		  LOG.log(Level.INFO, "/elk - &gt; " + response);
	 
	        try {
	            String exceptionrsp = (String) restTemplete.exchange("http://localhost:8080/exception", HttpMethod.GET, null, new ParameterizedTypeReference() {
	            }).getBody();
	            LOG.log(Level.INFO, "/elk trying to print exception - &gt; " + exceptionrsp);
	            response = response + " === " + exceptionrsp;
	        } catch (Exception e) {
	            // exception should not reach here. Really bad practice :)
	        }*/
		Vets vets = new Vets();
		vets.getVetList().addAll(this.clinic.getVets());
		return new ModelMap(vets);
	}

	/**
	 * Custom handler for displaying an owner.
	 *
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@RequestMapping("/owners/{ownerId}")
	public ModelAndView ownerHandler(@PathVariable("ownerId") int ownerId) {
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
		ModelAndView mav = new ModelAndView("owners/show");
		mav.addObject(this.clinic.loadOwner(ownerId));
		return mav;
	}
	
	@RequestMapping("/owners/feedback/{feedbackId}")
	public ModelAndView feedbackHandler(@PathVariable("feedbackId") int feedbackId) {
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
		ModelAndView mav = new ModelAndView("owners/show1");
		mav.addObject(this.clinic.loadFeedback(feedbackId));
		return mav;
	}

	/**
	 * Custom handler for displaying an list of visits.
	 *
	 * @param petId the ID of the pet whose visits to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@RequestMapping(value="/owners/*/pets/{petId}/visits", method=RequestMethod.GET)
	public ModelAndView visitsHandler(@PathVariable int petId) {
		ModelAndView mav = new ModelAndView("visits");
		mav.addObject("visits", this.clinic.loadPet(petId).getVisits());
		return mav;
	}

}
