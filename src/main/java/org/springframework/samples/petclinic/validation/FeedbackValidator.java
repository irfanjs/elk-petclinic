package org.springframework.samples.petclinic.validation;

import org.springframework.samples.petclinic.Feedback;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>Owner</code> forms.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public class FeedbackValidator {

    public FeedbackValidator() {
        super();
    }

    public void validate(Feedback feed, Errors errors) {
        if (!StringUtils.hasLength(feed.getFirstName())) {
            errors.rejectValue("firstName", "required", "required");
        }
        if (!StringUtils.hasLength(feed.getLastName())) {
            errors.rejectValue("lastName", "required", "required");
        }
        if (!StringUtils.hasLength(feed.getAddress())) {
            errors.rejectValue("address", "required", "required");
        }
        if (!StringUtils.hasLength(feed.getCity())) {
            errors.rejectValue("city", "required", "required");
        }
        
        if (!StringUtils.hasLength(feed.getDetails())) {
            errors.rejectValue("details", "required", "required");
        }

        // validate that telephone number only contains digits
        String telephone = feed.getTelephone();
        if (StringUtils.hasLength(telephone)) {
            for (int i = 0; i < telephone.length(); ++i) {
                if (!Character.isDigit(telephone.charAt(i))) {
                    errors.rejectValue("telephone", "nonNumeric", "non-numeric");
                    break;
                }
            }
        }
        else {
            errors.rejectValue("telephone", "required", "required");
        }
    }

}
