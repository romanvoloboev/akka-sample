package com.romanvoloboev.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.romanvoloboev.actors.msg.DigitMsg;
import com.romanvoloboev.actors.msg.RandomDigitsGenerator;

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

    private DigitsActor(ActorRef printActor) {
        this.printActor = printActor;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(RandomDigitsGenerator.class, randomDigitsGenerator -> {

                    printActor.tell(new DigitMsg(randomDigitsGenerator.nextInt()), getSelf());
                }).build();
    }


}
