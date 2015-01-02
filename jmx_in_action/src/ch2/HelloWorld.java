package ch2;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class HelloWorld extends NotificationBroadcasterSupport implements
		HelloWorldMBean {

	private String greeting;

	public HelloWorld() {
		this.greeting = "hello, world";
	}

	@Override
	public void setGreeting(String greeting) {
		this.greeting = greeting;
		Notification notification = new Notification("ch2.helloWorld.test",
				this, -1, System.currentTimeMillis(), greeting);
		sendNotification(notification);
	}

	@Override
	public String getGreeting() {
		return greeting;
	}

	@Override
	public String printGreeting() {
		System.out.println(greeting);
		return greeting;
	}

}
