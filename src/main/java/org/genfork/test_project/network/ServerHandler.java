package org.genfork.test_project.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.genfork.test_project.pojo.Course;
import org.genfork.test_project.pojo.GiveObjects;
import org.genfork.test_project.pojo.Professor;
import org.genfork.test_project.pojo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/07
 */
@Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
	private Logger logger = LoggerFactory.getLogger(ServerHandler.class);

	private final File stud_file, course_file, proff_file;
	private final FileWriter stud_writer, course_writer, proff_writer;

	private static ServerHandler instance;

	public static ServerHandler getInstance() throws IOException {
		if (instance == null) {
			return instance = new ServerHandler();
		}

		return instance;
	}

	public ServerHandler() throws IOException {
		stud_file = File.createTempFile("students", ".tmp");
		course_file = File.createTempFile("courses", ".tmp");
		proff_file = File.createTempFile("professors", ".tmp");

		stud_writer = new FileWriter(stud_file);
		course_writer = new FileWriter(course_file);
		proff_writer = new FileWriter(proff_file);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		final Gson generator = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().setLenient().create();
		final String json_str = generator.toJson(msg);

		if (msg instanceof List) {
			List list = (List) msg;
			if (list.get(0) instanceof Student) {
				writeJsonStr(json_str, stud_writer);
				stud_file.deleteOnExit();
			} else if (list.get(0) instanceof Course) {
				writeJsonStr(json_str, course_writer);
				course_file.deleteOnExit();
			} else if (list.get(0) instanceof Professor) {
				writeJsonStr(json_str, proff_writer);
				proff_file.deleteOnExit();
			}
		} else if (msg instanceof GiveObjects) {
			final BufferedReader stud_reader = new BufferedReader(new FileReader(stud_file));
			final BufferedReader course_reader = new BufferedReader(new FileReader(course_file));
			final BufferedReader proff_reader = new BufferedReader(new FileReader(proff_file));

			final Student[] students = generator.fromJson(stud_reader, Student[].class);
			final Course[] courses = generator.fromJson(course_reader, Course[].class);
			final Professor[] professors = generator.fromJson(proff_reader, Professor[].class);

			ctx.write(students);
			ctx.write(courses);
			ctx.write(professors);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		if (logger.isDebugEnabled()) {
			logger.debug("[CHANNEL ACTIVE]");
		}

		ctx.channel().closeFuture().addListener(f -> {
			logger.debug("[CLOSE]");
			course_writer.close();
			stud_writer.close();
			proff_writer.close();
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("error in echo server", cause);
		ctx.close();
	}

	private void writeJsonStr(String json_str, FileWriter writer) throws IOException {
		writer.write(json_str);
		writer.flush();
	}

	public FileWriter getStud_writer() {
		return stud_writer;
	}

	public FileWriter getCourse_writer() {
		return course_writer;
	}

	public FileWriter getProff_writer() {
		return proff_writer;
	}
}
	