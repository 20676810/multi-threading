
public class NormalTest {

	private static final int THREAD_NUM = 7;;

	public static void main(String[] args) throws InterruptedException {

		for (int i = 1; i <= THREAD_NUM; i++) {
			final int index=i;
			Thread t = new Thread(new Runnable() {
				public void run() {
					System.out.println("��ʼ�ռ���"+index+"������");
					try {
						Thread.sleep(1000);
						System.out.println("��"+index+"���������ռ�");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			t.start();
		}
		
		System.out.println("�������飬�ٻ�����");

	}

}
