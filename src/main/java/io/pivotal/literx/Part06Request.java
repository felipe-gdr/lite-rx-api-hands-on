package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 * @author Julien Hoarau
 */
public class Part06Request {

  ReactiveRepository<User> repository = new ReactiveUserRepository();

  // ========================================================================================

  // TODO Create a StepVerifier that initially requests all values and expect 4 values to be
  // received
  StepVerifier requestAllExpectFour(Flux<User> flux) {
    return StepVerifier.create(flux)
        .expectNextCount(4)
        .expectComplete();
  }

  // ========================================================================================

  // TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then
  // requests another value and expects User.JESSE and then cancel.
  StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
    return StepVerifier.create(flux, 1)
        .expectNext(User.SKYLER)
        .thenRequest(1)
        .expectNext(User.JESSE)
        .thenCancel();
  }

  // TODO Modify the subscriber to only request 4 items.
  int request4Integers() {
    AtomicInteger count = new AtomicInteger();
    Flux.range(0, 10)
        .subscribe(new Subscriber<Integer>() {
          @Override
          public void onSubscribe(Subscription s) {
          }

          @Override
          public void onNext(Integer integer) {
            count.incrementAndGet();
          }

          @Override
          public void onError(Throwable t) {

          }

          @Override
          public void onComplete() {

          }
        });

    return count.get();
  }

  // TODO Modify the flux to handle backpressure by buffering
  Flux<Long> overflowExceptionBuffer() {
    return Flux.interval(Duration.ofMillis(1))
        .flatMap(aLong -> Flux.just(aLong).delayElements(Duration.ofSeconds(1)));
  }

  // TODO Modify the flux to handle backpressure by dropping items
  Flux<Long> overflowExceptionDrop() {
    return Flux.interval(Duration.ofMillis(1))
        .flatMap(aLong -> Flux.just(aLong).delayElements(Duration.ofSeconds(1)));
  }

  // TODO Modify the flux to handle backpressure by only
  Flux<Long> overflowExceptionLatest() {
    return Flux.interval(Duration.ofMillis(1))
        .flatMap(aLong -> Flux.just(aLong).delayElements(Duration.ofSeconds(1))).log();

  }

  // TODO Modify the custom publisher to support backpressure
  Publisher<Integer> customPublisher() {
    return new Publisher<Integer>() {

      @Override
      public void subscribe(Subscriber<? super Integer> s) {
        s.onSubscribe(new Subscription() {
          @Override
          public void request(long n) {

          }

          @Override
          public void cancel() {

          }
        });

        for (int i = 0; i < 10; i++) {
          s.onNext(i);
        }

        s.onComplete();

      }
    };
  }
}
