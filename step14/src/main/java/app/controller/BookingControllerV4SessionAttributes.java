package app.controller;

import app.session.CustomerDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Log4j2
@Controller
@RequestMapping("/v4")
/**
 * that the mark,
 * wee need to `retain` this attribute between requests
 */
@SessionAttributes(
    names = { CustomerDetails.ATTR },
    types = { CustomerDetails.class})
public class BookingControllerV4SessionAttributes {

  private static String fmt(String f, Object... as) {
    return String.format(f, as);
  }

  /**
   * that the rule how to create the initial value for our attribute
   */
  @ModelAttribute(CustomerDetails.ATTR)
  public CustomerDetails create(HttpSession session) {
    log.info(fmt("Creating new object CustomerDetails for session %s", session.getId()));
    return new CustomerDetails(session.getId());
  }

  /**
   * http://localhost:8080/booking
   */
  @GetMapping("booking")
  public String handle_booking_get(@ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd) {
    log.info(fmt("GET  -> /booking : %s", cd));
    return "v4/1booking";
  }

  @PostMapping("booking")
  public RedirectView handle_booking_post(@ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd) {
    log.info(fmt("POST -> /booking : %s", cd));
    return new RedirectView("customer");
  }

  /**
   * http://localhost:8080/customer
   */
  @GetMapping("customer")
  public String handle_customer_get(@ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd) {
    log.info(fmt("GET  -> /customer: %s", cd));
    return "v4/2customer";
  }

  @PostMapping("customer")
  public RedirectView handle_customer_post(@ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd) {
    log.info(fmt("POST -> /customer: %s", cd));
    return new RedirectView("payment");
  }

  /**
   * http://localhost:8080/payment
   */
  @GetMapping("payment")
  public String handle_payment_get(@ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd) {
    log.info(fmt("GET  -> /payment : %s", cd));
    return "v4/3payment";
  }

  @PostMapping("payment")
  public RedirectView handle_payment_post(@ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd) {
    log.info(fmt("POST -> /payment : %s", cd));
    return new RedirectView("review");
  }

  /**
   * http://localhost:8080/review
   */
  @GetMapping("review")
  public String handle_review_get(@ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd) {
    log.info(fmt("GET  -> /review  : %s", cd));
    return "v4/4review";
  }

  @PostMapping("review")
  public RedirectView handle_review_post(@ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd) {
    log.info(fmt("POST -> /review  : %s", cd));
    return new RedirectView("confirm");
  }

  /**
   * http://localhost:8080/confirm
   */
  @GetMapping("confirm")
  public String handle_confirm_get(@ModelAttribute(CustomerDetails.ATTR) CustomerDetails cd) {
    log.info(fmt("GET  -> /confirm  : %s", cd));
    return "v4/5confirm";
  }

}
