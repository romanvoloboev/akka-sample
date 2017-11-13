package com.romanvoloboev.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.PrimitiveIterator;
import java.util.Random;

/**
 * Created at 10.11.17
 *
 * @author romanvoloboev
 */
public class DigitsActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private final ActorRef printActor;

    public static Props props(ActorRef printActor) {
        return Props.create(DigitsActor.class, () -> new DigitsActor(printActor));
    }

    public static class RandomDigitsGenerator {
        private PrimitiveIterator.OfInt randomIterator;

        public RandomDigitsGenerator(int min, int max) {
            randomIterator = new Random().ints(min, max + 1).iterator();
        }

        public int nextInt() {
            return randomIterator.nextInt();
        }
    }

    public DigitsActor(ActorRef printActor) {
        this.printActor = printActor;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(RandomDigitsGenerator.class, randomDigitsGenerator -> {
                    printActor.tell(new PrintActor.DigitMsg(randomDigitsGenerator.nextInt()), getSelf());
                }).build();
    }


}
