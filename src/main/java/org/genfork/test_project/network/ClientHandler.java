package org.genfork.test_project.network;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import javafx.application.Platform;
import org.genfork.test_project.controllers.ClientController;
import org.genfork.test_project.pojo.Course;
import org.genfork.test_project.pojo.Professor;
import org.genfork.test_project.pojo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/07
 */
@Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter {
	private Logger logger = LoggerFactory.getLogger(ClientHandler.class);

	private ClientController controller;

	private Student[] students;
	private Course[] courses;
	private Professor[] professors;

	private OpFlag flag = OpFlag.NULL;

	public ClientHandler(ClientController controller) {
		this.controller = controller;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.debug("[RECEIVING MESSAGE]");
		if (msg instanceof Student[]) {
			students = (Student[]) msg;
		} else if (msg instanceof Course[]) {
			courses = (Course[]) msg;
		} else if (msg instanceof Professor[]){
			professors = (Professor[]) msg;
		}

		flag = OpFlag.RECEIVE_OBJECTS;
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		if (flag == OpFlag.RECEIVE_OBJECTS) {
			Platform.runLater(() -> {
				try {
					controller.startStageTakeInfo(students, courses, professors);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		Platform.runLater(() -> {
			try {
				controller.startStage2(ctx.channel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("error in echo client", cause);
		cause.printStackTrace();
		ctx.close();
	}

	private enum OpFlag {
		NULL,
		RECEIVE_OBJECTS
	}
}
