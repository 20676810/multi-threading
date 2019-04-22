import java.util.concurrent.CountDownLatch;

public class Person implements Runnable
{
	private String name;
	private CountDownLatch countDownLatch;
	
	public Person(String pName,CountDownLatch personLatch)
	{
		name=pName;
		countDownLatch=personLatch;
	}

	public void run() {
		System.out.println(name+"Ѱ����");
		try {
			Thread.sleep(1000);
			System.out.println(name+"����");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		countDownLatch.countDown();
	}

}
