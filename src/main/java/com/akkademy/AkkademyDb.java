package com.akkademy;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.akkademy.messages.SetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mmiles on 4/18/17.
 */
public class AkkademyDb extends AbstractActor {
    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);
    protected final Map<String, Object> map = new HashMap<String, Object>();

    private AkkademyDb() {
        receive(ReceiveBuilder.
                match(SetRequest.class, message -> {
                            log.info("Received Set Request: {}", message);
                            map.put(message.getKey(), message.getValue());
                        }).
                    matchAny(o -> log.info("received unknown message: {}", o)).build()
                    );
    }

}
