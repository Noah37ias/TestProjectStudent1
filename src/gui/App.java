package gui;

import controller.Controller;
import javafx.application.Application;
import storage.Storage;

public class App {
	void main() {
		Storage storage = new Storage();
		Controller.setStorage(storage);
		Controller.initStorage();

		Application.launch(StartVindue.class);
	}
}
