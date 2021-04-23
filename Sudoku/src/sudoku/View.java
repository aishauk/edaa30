package sudoku;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class View extends Application {
	
	private static final int SIZE = 9;

	@Override
	public void start(Stage stage) throws Exception {
		
		int[][] board = new int [SIZE][SIZE];
		final int LENGTH = 40;	//varje rutas storlek
		TilePane tile = new TilePane();
		tile.setPrefColumns(SIZE);
		tile.setPrefRows(SIZE);
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 500, 500);
		stage.setTitle("Sudoku");
		stage.setScene(scene);
		stage.show();
		
		//skapa HBox
		HBox hbox = new HBox(20);
		
		//knappar
		Button solve = new Button("Solve");
		Button clear = new Button("Clear");
		
		//sökknapp
		Button find = new Button("Find");
		find.setDefaultButton(true);    //sökknappen aktiveras vid return, V1
		
		
		
	}
	
	
}
