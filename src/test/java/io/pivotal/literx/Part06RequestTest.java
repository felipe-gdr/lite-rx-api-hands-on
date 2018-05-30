package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
public class Part06RequestTest {

  Part06Request workshop = new Part06Request();
  ReactiveRepository<User> repository = new ReactiveUserRepository();

  // ========================================================================================

  @Test
  public void requestAll() {
    Flux<User> flux = repository.findAll();
    StepVerifier verifier = workshop.requestAllExpectFour(flux);
    verifier.verify();
  }

  // ========================================================================================

  @Test
  public void requestOneByOne() {
    Flux<User> flux = repository.findAll();
    StepVerifier verifier = workshop.requestOneExpectSkylerThenRequestOneExpectJesse(flux);
    verifier.verify();
  }
}
