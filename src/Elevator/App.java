package Elevator;

import Elevator.Controller.BasicProtocol;
import Elevator.Controller.FIFOprotocol;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private Scene mainScene;
    private Stage testStage;
    @Override
    public void start(Stage primaryStage) throws Exception{

        //primary windows
        final HBox root = new HBox();
        mainScene = new Scene(root,700, 500);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Elevator");
        primaryStage.setWidth(700);
        primaryStage.setHeight(500);

        //pos of windows
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth()-primaryStage.getWidth())/2);
        primaryStage.setY((primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight()-primaryStage.getHeight())/4);

        MainWindow mainWindow = new MainWindow(root);
        List<VBox> vBoxs = new ArrayList<VBox>();
        for(int i = 0; i< mainWindow.getBoxes().size(); i++){
            vBoxs.add(mainWindow.getBoxes().get(i).getvBox());
        }
        root.getChildren().addAll(vBoxs);
        primaryStage.show();

        //test windows
        final HBox root2 = new HBox();
        testStage = new Stage();
        testStage.setTitle("Test panel");
        testStage.setWidth(500);
        testStage.setHeight(300);
        testStage.setX(primaryScreenBounds.getMinX());
        testStage.setY(primaryScreenBounds.getMinY());

        TestWindow testWindow = new TestWindow(root2);
        root2.getChildren().add(testWindow.getInterfaceBox().getvBox());

        testStage.setScene(new Scene(root2, 500,300));
        testStage.show();
    }


    public static void main(String[] args) {
        BasicProtocol basicProtocol = new BasicProtocol();
        FIFOprotocol fifoProtocol = new FIFOprotocol();
        basicProtocol.start();
        fifoProtocol.start();

        launch(args);
    }
}
