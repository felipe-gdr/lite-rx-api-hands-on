package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
public class Part06Request {

  ReactiveRepository<User> repository = new ReactiveUserRepository();

  // ========================================================================================

  // TODO Create a StepVerifier that initially requests all values and expect 4 values to be
  // received
  StepVerifier requestAllExpectFour(Flux<User> flux) {
    return null;
  }

  // ========================================================================================

  // TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then
  // requests another value and expects User.JESSE and then cancel.
  StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
    return null;
  }


}
