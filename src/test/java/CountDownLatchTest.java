import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	private static final int THREAD_NUM = 7;
	private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
	private static CountDownLatch personLatch = new CountDownLatch(3);

	public static void main(String[] args) {

		// �ٻ�����
		summonDragon();
		// �ٻ�����
		summonPerson();

	}

	private static void summonDragon() {
		for (int i = 1; i <= THREAD_NUM; i++) {
			final int index = i;
			Thread t = new Thread(new Runnable() {
				public void run() {
					System.out.println("��ʼ�ռ���" + index + "������");
					try {
						Thread.sleep(1000);
						System.out.println("��" + index + "���������ռ�");
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
			System.out.println("�������飬�ٻ�����");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void summonPerson() {
		Person person1 = new Person("�����", personLatch);
		Person person2 = new Person("������", personLatch);
		Person person3 = new Person("������", personLatch);
		new Thread(person1).start();
		new Thread(person2).start();
		new Thread(person3).start();

		try {
			personLatch.await();
			System.out.println("����Ӣ�ۣ�׼������");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
