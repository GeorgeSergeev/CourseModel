package web;

import gui.Engine;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

import org.json.JSONObject;

public class Servlet extends HttpServlet {
	/**
	 * Иннициализация модели
	 */
	static {
		try {
			Class.forName(Model.class.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Честно говоря, мне самому такое представляется несколько забавным: сервер
	 * запускает десктопное приложение :-) По уму десктопное приложение хорошо
	 * бы запускать из другой Ява-машины, связывая с сервером по TCP, либо
	 * вообще через посредника, т. е., через модель, вынесенную в базу данных,
	 * но мне времени не хватило. Для того, чтобы сгладить это обстоятельство, я
	 * запускаю приложение Engine "косвенно" (статическая иннициализация класса
	 * ведь обязательно просыпается, едва упомянешь каким-либо образом класс)
	 */
	static {
		try {
			Class.forName(Engine.class.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		int symbol;
		String fromClient = "";
		do {
			symbol = isr.read();
			if (symbol > -1) {
				fromClient += (char) symbol;
			}
		} while (symbol > -1);
		OutputStream os = response.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		try {// Не прислал ли нам клиент джейсончик?
			JSONObject state = new JSONObject(fromClient);
			Deserialization.applyJSON(state.getJSONArray("processes"));
			// Viewer.refreshTableOfProcesses();
		} catch (Exception e) {// Клиент не прислал джейсончик, а наоборот,
			// просит джейсончик
			Object resp;
			if (fromClient.equals("Refresh me processes!")) {
				resp = Serialization.prepareCurrentState();
			} else if (fromClient.equals("Give me the foundment!")) {
				resp = Serialization.prepareFoundment();
			} else {
				resp = "You are a bot, aren't You?";
			}
			osw.write(resp.toString());
		}
		osw.close();

	}

}
