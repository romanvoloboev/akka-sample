package com.romanvoloboev;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.romanvoloboev.actors.DigitsActor;
import com.romanvoloboev.actors.PrintActor;
import com.romanvoloboev.actors.msg.RandomDigitsGenerator;

public class App {

    static final ActorSystem system = ActorSystem.create("akkaSystem");

    public static void main(String[] args) {
        ActorRef printActor = system.actorOf(PrintActor.props());
        ActorRef digitsActor = system.actorOf(DigitsActor.props(printActor));
        digitsActor.tell(new RandomDigitsGenerator(100, 200), ActorRef.noSender());

    }
}
