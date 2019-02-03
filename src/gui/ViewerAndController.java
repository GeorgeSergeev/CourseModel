package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.Model;

import model.uml.Process;
import model.uml.Science;
import model.uml.Student;

public class ViewerAndController {

	private final static JFrame frame = new JFrame("Server side");
	private final static JPanel table = new JPanel();
	private final static GridLayout tableLayout = new GridLayout();
	private final static JPanel bottom = new JPanel();
	private final static JLabel labelForMarks = new JLabel("Оценки");
	private final static JLabel courseLabel = new JLabel("Курс");
	private final static JLabel studentLabel = new JLabel("Студент");
	private final static Hashtable<Science, ActionListener> buttons = new Hashtable<Science, ActionListener>();
	static {
		try {
			Class.forName(Model.class.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bottom.setLayout(new GridLayout(Science.values().length, 3, 5, 20));
		frame.setSize(800, 600);
		tableLayout.setColumns(3);
		tableLayout.setHgap(50);
		tableLayout.setVgap(30);
		table.setLayout(tableLayout);
		table.setBackground(Color.GRAY);
		for (final Science science : Science.values()) {
			Student[] allStudents = new Student[Student.getCountOfStudents()];
			for (int j = 0; j < allStudents.length; j++) {
				allStudents[j] = Student.getById(j);
			}
			DefaultComboBoxModel<Student> model = new DefaultComboBoxModel<Student>(
					allStudents);
			bottom.add(new JLabel(science.toString()));
			final JButton button = new JButton();
			final JComboBox<Student> selector = new JComboBox<Student>(model);
			class SelectorListener implements ActionListener {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					boolean can = ((Student) selector.getSelectedItem())
							.isNotEnvolved(science);
					button.setText(can ? "Добавить студента"
							: "Удалить студента");
				}

			}
			final SelectorListener selectorListener = new SelectorListener();
			buttons.put(science, selectorListener);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Student student = (Student) selector.getSelectedItem();
					boolean can = student.isNotEnvolved(science);
					if (can) {
						science.addStudent(student);
					} else {
						science.deleteStudent(student);
					}
				}

			});
			bottom.add(button);
			selector.addActionListener(selectorListener);
			bottom.add(selector);
			selectorListener.actionPerformed(null);
		}
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		top.add(table);
		body.add(top, BorderLayout.NORTH);
		body.add(bottom, BorderLayout.CENTER);
		frame.setContentPane(body);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		refreshTableOfProcesses();
	}

	static void refreshTableOfProcesses() {
		table.removeAll();
		table.add(labelForMarks);
		table.add(courseLabel);
		table.add(studentLabel);
		ArrayList<Process> processes = Student.getAllProcesses();
		tableLayout.setRows(processes.size() + 1);
		for (Process process : processes) {
			table.add(new JLabel(process.marks.toString()));
			table.add(new JLabel(process.getCourse().name()));
			table.add(new JLabel(process.getStudent().getId() + ""));
		}
		// пробежимся переберём заголовки кнопок (удалить/добавить)
		for (Science science : Science.values()) {
			buttons.get(science).actionPerformed(null);
		}
	}
}
