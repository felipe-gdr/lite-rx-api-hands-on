package io.pivotal.literx;

import com.sun.tools.javac.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a
 * href="http://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux
 * Javadoc</a>
 */
public class Part01Flux {

    // ========================================================================================

    // TODO Return an empty Flux
    Flux<String> emptyFlux() {
        return Flux.create(FluxSink::complete);
    }

    // ========================================================================================

    // TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a
    // collection
    Flux<String> fooBarFluxFromValues() {
        return Flux.create(emitter -> {
            emitter.next("foo");
            emitter.next("bar");
            emitter.complete();
        });
    }

    // ========================================================================================

    // TODO Create a Flux from a List that contains 2 values "foo" and "bar"
    Flux<String> fooBarFluxFromList() {
        return Flux.fromIterable(Arrays.asList("foo", "bar"));
    }

    // ========================================================================================

    // TODO Create a Flux that emits an IllegalStateException
    Flux<String> errorFlux() {
        return Flux.create(emitter -> {
            emitter.error(new IllegalStateException());
        });
    }

    // ========================================================================================

    // TODO Create a Flux that emits increasing values from 0 to 9
    Flux<Integer> counter() {
        return Flux.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                emitter.next(i);
            }
            emitter.complete();
        });
    }

    // ========================================================================================

    // TODO Create a Flux that emits increasing values from 0 to 9 from a stream
    Flux<Integer> counterFromStream() {
        return Flux.fromStream(IntStream.range(0, 10).boxed());
    }

}
