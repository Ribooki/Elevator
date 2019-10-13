package Elevator;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private Scene mainScene;
    private Stage testStage;
    private Scene testScene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = new AnchorPane();
        primaryStage.setTitle("Elevator");
        primaryStage.setWidth(700);
        primaryStage.setHeight(500);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth()/4);
        primaryStage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight()/4);
        MainInterface mainInterface = new MainInterface(primaryStage);
        List<VBox> vBoxs = new ArrayList<VBox>();
        for(int i=0; i<mainInterface.getBoxes().size(); i++){
            vBoxs.add(mainInterface.getBoxes().get(i).vBox);
        }

        root.getChildren().addAll(vBoxs);
        mainScene = new Scene(root,700, 500);
        primaryStage.setScene(mainScene);
        primaryStage.show();


        AnchorPane root2 = new AnchorPane();
        testStage = new Stage();
        testStage.setTitle("Test panel");
        testStage.setWidth(500);
        testStage.setHeight(300);
        testStage.setX(primaryScreenBounds.getMinX());
        testStage.setY(primaryScreenBounds.getMinY());

        TestInterface testInterface = new TestInterface(testStage);
        root2.getChildren().add(testInterface.getButtonsBox().vBox);

        testStage.setScene(new Scene(root2, 500,300));
        testStage.show();
    }


    public static void main(String[] args) {
        BasicProtocol protocol = new BasicProtocol();
//        FIFOprotocol protocol = new FIFOprotocol();
        protocol.start();

        launch(args);
    }
}
