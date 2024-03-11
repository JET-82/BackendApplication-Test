package team2.test.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.test.utils.ResponseType;

@RestController
public class HealthController {
  @GetMapping("/health")
  public ResponseType.Result<Object> health() {
    return ResponseType.success();
  }
}
