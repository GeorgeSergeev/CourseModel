package gui;
/**
 * 
 * Я бы мог, конечно refreshTableOfProcesses() вызывать напрямую из
 * WEB-приложения (едва придёт послание от клиента), но
 * я не хотел, чтобы сервлет занимался обслуживанием
 * WEB-приложения. А поскольку теперь
 * обновление живёт само по себе, я и из
 * самого GUI-приложения убрал вызов обновления 
 * @author Arsen Pan
 *
 */

public class Engine extends Thread {
	private final static int FRAMERATE = 25;
	static {
		Engine engine = new Engine();
		engine.start();
	}

	/**
	 * С привычною для кинематографа
	 * частотою 25 кадров в секунду,
	 * на всём протяжении работы программы мониторим произошедшие
	 * изменения в модели.
	 */
	public void run() {
		do {
			ViewerAndController.refreshTableOfProcesses();
			try {
				Thread.sleep(1000 / FRAMERATE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}
	/**
	 * Ниже идёт точка входа в десктопное приложение.
	 * Не удивляйтесь, что она
	 * пустая. В вышерасположенную статическую
	 * иннициализацию класса "косвенно" всё
	 * равно войдём
	 * (точно так же
	 * мы в неё входим и из сервлета)
	 * 
	 * @param arguments
	 */
	public static void main(String[] arguments) {

	}
}
