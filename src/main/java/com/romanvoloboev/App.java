package com.romanvoloboev;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.romanvoloboev.actors.DigitsActor;
import com.romanvoloboev.actors.PrintActor;

public class App {

    static final ActorSystem system = ActorSystem.create("akkaSystem");

    public static void main(String[] args) {
        ActorRef printActor = system.actorOf(PrintActor.props());
        ActorRef digitsActor = system.actorOf(DigitsActor.props(printActor));
        for (int i = 0; i< 100; i++) {
            digitsActor.tell(new DigitsActor.RandomDigitsGenerator(2+i, 200), ActorRef.noSender());
        }

    }
}
