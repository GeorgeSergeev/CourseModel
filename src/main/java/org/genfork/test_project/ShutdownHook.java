package org.genfork.test_project;

import org.genfork.test_project.network.ServerHandler;

import java.io.FileWriter;
import java.io.IOException;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/08
 */
class ShutdownHook extends Thread {
	ShutdownHook() {
		setName(getClass().getSimpleName());
		setDaemon(true);
	}

	@Override
	public void run() {
		try {
			final FileWriter course_writer = ServerHandler.getInstance().getCourse_writer();
			final FileWriter stud_writer = ServerHandler.getInstance().getStud_writer();
			final FileWriter proff_writer = ServerHandler.getInstance().getProff_writer();

			course_writer.close();
			stud_writer.close();
			proff_writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
