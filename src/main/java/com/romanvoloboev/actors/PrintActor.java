package com.romanvoloboev.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.Optional;

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

    static class DigitMsg {
        private int digit;

        DigitMsg(int digit) {
            this.digit = digit;
        }

        int getDigit() {
            return digit;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(DigitMsg.class, digitMsg -> {
                    log.info("res: {}", digitMsg.getDigit());
//                    throw new Exception("aaaaaa");
                }).build();
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        log.info("--- preStart");
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        log.info("--- postRestart");
    }

    @Override
    public void preRestart(Throwable reason, Optional<Object> message) throws Exception {
        super.preRestart(reason, message);
        log.error("--- preRestart");
//        log.error("exception: {}, msg: {}", reason.getMessage(), message);
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        log.info("--- postStop");
    }
}
