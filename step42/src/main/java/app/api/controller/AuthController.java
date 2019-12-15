package app.api.controller;

import app.api.dto.rq.LoginRq;
import app.api.dto.rq.LogoutRq;
import app.api.dto.rq.RegisterRq;
import app.api.dto.rs.LoginRs;
import app.api.dto.rs.LogoutRs;
import app.api.dto.rs.RegisterRs;
import app.api.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public LoginRs handle_login(@RequestBody LoginRq rq) {
    log.info(rq);
    return new LoginRs("POST:/login:not implemented", "XXXYYYZZZ");
  }

  @PostMapping("/logout")
  public LogoutRs handle_logout(@RequestBody LogoutRq rq) {
    log.info(rq);
    return new LogoutRs("POST:/logout:not implemented");
  }

  @PostMapping("/register")
  public RegisterRs handle_register(@RequestBody RegisterRq rq) {
    log.info(rq);
    boolean result = authService.register_new(rq.getUsername(), rq.getPassword());
    return result ? RegisterRs.Ok() : RegisterRs.AlreadyExists();
  }
}
