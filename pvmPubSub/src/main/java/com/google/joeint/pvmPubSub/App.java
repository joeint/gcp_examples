package com.google.joeint.pvmPubSub;

import java.util.Iterator;

import com.google.cloud.pubsub.PubSub;
import com.google.cloud.pubsub.PubSubOptions;
import com.google.cloud.pubsub.ReceivedMessage;

/**
 * Sample app pulling for messages from the pub/sub subscription
 *
 */
public class App {
	public static void main(String[] args) {
		PubSub pubsub = PubSubOptions.getDefaultInstance().getService();

		Iterator<ReceivedMessage> messages = pubsub.pull("preempted_topic_sub", 100);
		// Ack deadline is renewed until the message is consumed
		while (messages.hasNext()) {
			ReceivedMessage message = messages.next();
			System.out.println(message.getPayloadAsString());
			// do something with message and ack/nack it
			message.ack(); // or message.nack()
		}
	}
}
