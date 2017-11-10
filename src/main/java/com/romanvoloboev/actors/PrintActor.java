package com.romanvoloboev.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.romanvoloboev.actors.msg.DigitMsg;

/**
 * Created at 10.11.17
 *
 * @author romanvoloboev
 */
public class PrintActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(PrintActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(DigitMsg.class, digitMsg -> {
                    log.info("res: {}", digitMsg.getDigit());
                }).build();
    }
}
