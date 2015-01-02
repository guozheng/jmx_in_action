package ch2;

import javax.management.*;

import com.sun.jdmk.comm.HtmlAdaptorServer;


public class HelloWorldAgent implements NotificationListener{
	
	private MBeanServer mbs;
	
	public HelloWorldAgent() {
		mbs = MBeanServerFactory.createMBeanServer("HelloWorldAgent");
		HtmlAdaptorServer adaptor = new HtmlAdaptorServer();
		HelloWorld hw = new HelloWorld();
		ObjectName adaptorName = null;
		ObjectName helloWorldName = null;
		
		try {
			helloWorldName = new ObjectName("HelloWorldAgent:name=helloWorld1");
			mbs.registerMBean(hw, helloWorldName);
			
			hw.addNotificationListener(this, null, null);
			
			adaptorName = new ObjectName("HelloWorldAgent:name=htmladaptor,port=6868");
			adaptor.setPort(6868);
			mbs.registerMBean(adaptor, adaptorName);
			adaptor.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleNotification(Notification notification, Object handback) {
		System.out.println("got notification: type=" + notification.getType() + 
				", message=" + notification.getMessage() +
				", timestamp=" + notification.getTimeStamp());
	}

	public static void main(String[] args) {
		HelloWorldAgent agent = new HelloWorldAgent();
		System.out.println("helloWorld agent is running...");
	}
}
