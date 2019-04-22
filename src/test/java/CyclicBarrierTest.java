import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	private static final int THREAD_NUM = 7;

	public static void main(String[] args) {
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUM, new Runnable() {

			public void run() {
				System.out.println("法师集结完毕，同时出发，开始寻找龙珠");
				summonDragon();
			}
		});

		for (int i = 1; i <= THREAD_NUM; i++) {
			final int index = i;
			Thread t = new Thread(new Runnable() {

				public void run() {
					System.out.println("召唤第" + index + "个法师");
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
				System.out.println("集齐龙珠，召唤神龙");
			}
		});

		for (int i = 1; i <= THREAD_NUM; i++) {
			final int index = i;
			Thread t = new Thread(new Runnable() {

				public void run() {
					System.out.println("开始收集第" + index + "颗龙珠");
					try {
						Thread.sleep(1000);
						System.out.println("第" + index + "颗龙珠已收集");
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
