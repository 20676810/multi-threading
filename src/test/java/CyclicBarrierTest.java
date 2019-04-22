import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	private static final int THREAD_NUM = 7;

	public static void main(String[] args) {
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUM, new Runnable() {

			public void run() {
				System.out.println("��ʦ������ϣ�ͬʱ��������ʼѰ������");
				summonDragon();
			}
		});

		for (int i = 1; i <= THREAD_NUM; i++) {
			final int index = i;
			Thread t = new Thread(new Runnable() {

				public void run() {
					System.out.println("�ٻ���" + index + "����ʦ");
					try {
						cyclicBarrier.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			t.start();
		}
	}

	private static void summonDragon() {
		final CyclicBarrier summonCyclicBarrier = new CyclicBarrier(THREAD_NUM, new Runnable() {

			public void run() {
				System.out.println("�������飬�ٻ�����");
			}
		});

		for (int i = 1; i <= THREAD_NUM; i++) {
			final int index = i;
			Thread t = new Thread(new Runnable() {

				public void run() {
					System.out.println("��ʼ�ռ���" + index + "������");
					try {
						Thread.sleep(1000);
						System.out.println("��" + index + "���������ռ�");
						summonCyclicBarrier.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			t.start();
		}
	}
}
