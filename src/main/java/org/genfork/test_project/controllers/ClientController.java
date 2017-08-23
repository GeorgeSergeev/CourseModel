package org.genfork.test_project.controllers;

import com.sun.javafx.scene.control.skin.LabeledText;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.genfork.test_project.ClientApp;
import org.genfork.test_project.network.ClientHandler;
import org.genfork.test_project.pojo.Course;
import org.genfork.test_project.pojo.GiveObjects;
import org.genfork.test_project.pojo.Professor;
import org.genfork.test_project.pojo.Student;
import org.genfork.test_project.utils.Rnd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/07
 */
public class ClientController {
	private Logger logger = LoggerFactory.getLogger(ClientApp.class);

	@FXML Button btnConnect;
	@FXML Button btnSend;
	@FXML Button btnDisconnect;
	@FXML Button btnTake;
	@FXML Button btnAddStudent;

	@FXML TextField tfHost;
	@FXML TextField tfPort;
	@FXML TextField student_address;
	@FXML TextField student_telephone;
	@FXML TextField student_email;
	@FXML TextField student_record_book;
	@FXML TextField student_name;

	@FXML Text course_number;
	@FXML Text course_name;

	@FXML ListView<Course> cc = new ListView<>();
	@FXML ListView<Professor> pp = new ListView<>();
	@FXML ListView<Student> ss = new ListView<>();
	@FXML ListView<Course> cc_result = new ListView<>();
	@FXML ListView<Professor> pp_result = new ListView<>();
	@FXML ListView<Student> ss_result = new ListView<>();

	private Course course;

	private List<Student> students = new ArrayList<>();
	private List<Course> courses = new ArrayList<>();
	private List<Professor> professors = new ArrayList<>();

	private Pattern integer_pattern = Pattern.compile("[0-9]");
	private Stage stage;

	private BooleanProperty connected = new SimpleBooleanProperty(false);
	private Channel channel;
	private EventLoopGroup group;

	@PostConstruct
	public void initialize() {
		students.add(new Student("Звягинцев Максим Петрович", "some addr", "some telephone", "some mail", 1)
				.setBalance(2000));
		students.add(new Student("Исак Александр Виторович", "some addr", "some telephone", "some mail", 2)
				.setBalance(2000));
		students.add(new Student("Усков Василий Дмитриевич", "some addr", "some telephone", "some mail", 3)
				.setBalance(2000));
		students.add(new Student("Алешенков Владимир Геннадиевич", "some addr", "some telephone", "some mail", 4)
				.setBalance(2000));

		final Professor professor1 = new Professor("Никулин Денис Викторович", "some addr", "some telephone", 500);
		final Professor professor2 = new Professor("Заброда Валерий Денисович", "some addr", "some telephone", 700);
		final Professor professor3 = new Professor("Голубенко Никита Сергеевич", "some addr", "some telephone", 900);

		professors.add(professor1);
		professors.add(professor2);
		professors.add(professor3);

		courses.add(new Course("Физика", 1, 1000).setProfessor(professor1));
		courses.add(new Course("Кибернетика", 2, 1500).setProfessor(professor2));
		courses.add(new Course("Пение", 3, 500));
		courses.add(new Course("Низкоуровневые языки программирования", 4, 2000).setProfessor(professor3));

		cc.setItems(FXCollections.observableList(courses));
		pp.setItems(FXCollections.observableList(professors));

		cc.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			try {
				final LabeledText text = (LabeledText) event.getTarget();
				final Optional<Course> present = courses
						.stream()
						.filter(data -> data.toString().contains(text.getText()))
						.findFirst();
				if (present.isPresent()) {
					(course_number = new Text()).setText(String.valueOf(present.get().getNumber()));
					(course_name = new Text()).setText(present.get().getName());
					startStageAddStudent(present.get());
				}
			} catch (Exception e) {
				logger.error("", e);
			}
		});
	}

	@FXML
	public void onAddStudent() {
		if ((student_name.getText() == null && student_name.getText().isEmpty()) || student_name.getText().length() == 0) {
			logger.debug("Entered student name is null");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Wrong");
			alert.setContentText("Student name is NULL, please correct this!");
			alert.showAndWait();
			return;
		}

		if ((student_address.getText() == null && student_address.getText().isEmpty()) || student_address.getText().length() == 0) {
			logger.debug("Entered student address is null");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Wrong");
			alert.setContentText("Student address is NULL, please correct this!");
			alert.showAndWait();
			return;
		}

		if ((student_telephone.getText() == null && student_telephone.getText().isEmpty()) || student_telephone.getText().length() == 0) {
			logger.debug("Entered student telephone is null");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Wrong");
			alert.setContentText("Student TELEPHONE is NULL, please correct this!");
			alert.showAndWait();
			return;
		}

		if ((student_email.getText() == null && student_email.getText().isEmpty()) || student_email.getText().length() == 0) {
			logger.debug("Entered student email is null");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Wrong");
			alert.setContentText("Student EMAIL is NULL, please correct this!");
			alert.showAndWait();
			return;
		}

		if ((student_record_book.getText() == null && student_record_book.getText().isEmpty()) || student_record_book.getText().length() == 0) {
			logger.debug("Entered student record book is null");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Wrong");
			alert.setContentText("Student RECORD BOOK is NULL, please correct this!");
			alert.showAndWait();
			return;
		}

		final Matcher matcher = integer_pattern.matcher(student_record_book.getText());
		if (!matcher.find() && student_record_book.getText().length() > Integer.MAX_VALUE) {
			logger.debug("Entered student record book is NON pattern of compile");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Wrong");
			alert.setContentText("Student RECORD BOOK have wrong characters (only numbers), please correct this!");
			alert.showAndWait();
			return;
		}

		final Student student = new Student(student_name.getText(), student_address.getText(), student_telephone.getText(),
				student_email.getText(), Integer.parseInt(student_record_book.getText()));

		course.addStudent(student);
		course.setRating(Rnd.generateRandomInts(2, 5, 5));

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Success");
		alert.setContentText("Student have been added to course!");
		alert.showAndWait();

		stage.close();
	}

	@FXML
	public void onSend() {
		if (logger.isDebugEnabled()) {
			logger.debug("[SEND]");
		}

		if (!connected.get()) {
			if (logger.isWarnEnabled()) {
				logger.warn("client not connected; skipping write");
			}
			return;
		}

		Platform.runLater(() -> {
			sendInfo(students);
			sendInfo(courses);
			sendInfo(professors);
		});
	}

	@FXML
	public void onDisconnect() {
		if (!connected.get()) {
			if (logger.isWarnEnabled()) {
				logger.warn("client not connected; skipping disconnect");
			}
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("[DISCONNECT]");
		}

		final Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				updateMessage("Disconnecting");
				updateProgress(0.1d, 1.0d);

				channel.close().sync();

				updateMessage("Closing group");
				updateProgress(0.5d, 1.0d);
				group.shutdownGracefully().sync();

				return null;
			}

			@Override
			protected void succeeded() {
				connected.set(false);
			}

			@Override
			protected void failed() {
				connected.set(false);

				Throwable t = getException();
				logger.error("client disconnect error", t);
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Client");
				alert.setHeaderText(t.getClass().getName());
				alert.setContentText(t.getMessage());
				alert.showAndWait();
			}
		};

		new Thread(task).start();
	}

	@FXML
	public void onTake() throws Exception {
		final GiveObjects obj = new GiveObjects();
		Platform.runLater(() -> sendInfo(obj));
	}

	@FXML
	public void connect() {
		final String host = tfHost.getText();
		final int port = Integer.parseInt(tfPort.getText());

		group = new NioEventLoopGroup();

		final Task<Channel> task = new Task<Channel>() {
			@Override
			protected Channel call() throws Exception {
				if (connected.get()) {
					if (logger.isWarnEnabled()) {
						logger.warn("client already connected; skipping connect");
					}
					failed();
				}

				updateMessage("Bootstrapping");
				updateProgress(0.1d, 1.0d);

				Bootstrap b = new Bootstrap();
				b.group(group)
						.channel(NioSocketChannel.class)
						.remoteAddress(new InetSocketAddress(host, port))
						.handler(new ChannelInitializer<SocketChannel>() {
							@Override
							protected void initChannel(SocketChannel ch) throws Exception {
								ch.pipeline().addLast(new ObjectEncoder(),
										new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
										new ClientHandler(ClientController.this));
							}
						});

				ChannelFuture f = b.connect().sync();
				Channel chn = f.channel();

				updateMessage("Connecting");
				updateProgress(0.2d, 1.0d);

				return chn;
			}

			@Override
			protected void succeeded() {
				channel = getValue();
				connected.set(true);
			}

			@Override
			protected void failed() {
				logger.error("client connect error");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Client");
				alert.setHeaderText(getClass().getSimpleName());
				alert.setContentText("Client is already connected! Disconnecting...");
				alert.showAndWait();

				connected.set(false);
			}
		};

		new Thread(task).start();
	}

	public void setCourses(Course[] courses) {
		cc_result.setItems(FXCollections.observableList(Arrays.asList(courses)));
	}

	public void setStudents(Student[] students) {
		ss_result.setItems(FXCollections.observableList(Arrays.asList(students)));
	}

	public void setProfessors(Professor[] professors) {
		pp_result.setItems(FXCollections.observableList(Arrays.asList(professors)));
	}

	private void setCourse(Course course) {
		this.course = course;
	}

	private void setStage(Stage stage) {
		this.stage = stage;
	}

	private void setChannel(Channel channel) {
		this.channel = channel;
	}

	private void setConnected(boolean connected) {
		this.connected.set(connected);
	}

	private void setGroup(EventLoopGroup group) {
		this.group = group;
	}

	public void startStage2(Channel channel) throws Exception {
		final Stage stage = new Stage();
		final FXMLLoader loader = new FXMLLoader(ClientController.class.getResource("/Client_scene2.fxml"));
		final Parent root = loader.load();

		final String title_window = "2017 (c) GenCloud";
		final String icon = ClientController.class.getResource("/images/icon.png").toExternalForm();

		final Scene scene = new Scene(root);
		stage.setTitle(title_window);
		stage.getIcons().add(new Image(icon));
		stage.setScene(scene);

		final ClientController controller = loader.getController();
		controller.setChannel(channel);
		controller.setConnected(true);
		controller.setStage(stage);
		controller.setGroup(group);

		stage.show();
	}

	private void startStageAddStudent(Course course) throws Exception {
		final Stage stage = new Stage();
		final FXMLLoader loader = new FXMLLoader(ClientController.class.getResource("/Client_scene3.fxml"));
		final Parent root = loader.load();

		final String title_window = "2017 (c) GenCloud";
		final String icon = ClientController.class.getResource("/images/icon.png").toExternalForm();

		final Scene scene = new Scene(root);
		stage.setTitle(title_window);
		stage.getIcons().add(new Image(icon));
		stage.setScene(scene);

		final ClientController controller = loader.getController();
		controller.setCourse(course);
		controller.setChannel(channel);
		controller.setConnected(true);
		controller.setStage(stage);
		controller.setGroup(group);

		stage.show();
	}

	public void startStageTakeInfo(Student[] students, Course[] courses, Professor[] professors) throws Exception {
		final Stage stage = new Stage();
		final FXMLLoader loader = new FXMLLoader(ClientController.class.getResource("/Client_scene4.fxml"));
		final Parent root = loader.load();

		final String title_window = "2017 (c) GenCloud";
		final String icon = ClientController.class.getResource("/images/icon.png").toExternalForm();

		final Scene scene = new Scene(root);
		stage.setTitle(title_window);
		stage.getIcons().add(new Image(icon));
		stage.setScene(scene);

		final ClientController controller = loader.getController();
		controller.setChannel(channel);
		controller.setConnected(true);
		controller.setStage(stage);
		controller.setGroup(group);

		controller.setStudents(students);
		controller.setCourses(courses);
		controller.setProfessors(professors);

		stage.show();
	}

	private <T> void sendInfo(T data) {
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				channel.writeAndFlush(data).sync();
				return null;
			}

			@Override
			protected void failed() {
				Throwable exc = getException();
				logger.error("client send error", exc);
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Client");
				alert.setHeaderText(exc.getClass().getName());
				alert.setContentText(exc.getMessage());
				alert.showAndWait();

				connected.set(false);
			}
		};

		new Thread(task).start();
	}
}

