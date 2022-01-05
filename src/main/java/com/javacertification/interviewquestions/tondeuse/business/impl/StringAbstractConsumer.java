package com.javacertification.interviewquestions.tondeuse.business.impl;

import java.util.function.Consumer;

/**
 * Parent abstract class that defines a string consumer to be exploited by other layers.
 */
class StringAbstractConsumer {
    Consumer<String> consumer;

    StringAbstractConsumer(Consumer<String> consumer) {
        this.consumer = consumer;
    }
}
