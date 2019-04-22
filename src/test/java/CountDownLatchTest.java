import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	private static final int THREAD_NUM = 7;
	private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
	private static CountDownLatch personLatch = new CountDownLatch(3);

	public static void main(String[] args) {

		// 召唤神龙
		summonDragon();
		// 召唤人物
		summonPerson();

	}

	private static void summonDragon() {
		for (int i = 1; i <= THREAD_NUM; i++) {
			final int index = i;
			Thread t = new Thread(new Runnable() {
				public void run() {
					System.out.println("开始收集第" + index + "颗龙珠");
					try {
						Thread.sleep(1000);
						System.out.println("第" + index + "颗龙珠已收集");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					countDownLatch.countDown();
				}
			});
			t.start();
		}

		try {
			countDownLatch.await();
			System.out.println("集齐龙珠，召唤神龙");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void summonPerson() {
		Person person1 = new Person("孙悟空", personLatch);
		Person person2 = new Person("龟仙人", personLatch);
		Person person3 = new Person("贝吉塔", personLatch);
		new Thread(person1).start();
		new Thread(person2).start();
		new Thread(person3).start();

		try {
			personLatch.await();
			System.out.println("集齐英雄，准备出发");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
