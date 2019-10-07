package Elevator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private Scene mainScene;
    private Stage testStage;
    private Scene testScene;
    public static Protocol controller;
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = new AnchorPane();
        primaryStage.setTitle("Elevator");
        primaryStage.setWidth(700);
        primaryStage.setHeight(500);

        MainInterface mainInterface = new MainInterface(primaryStage);
        List<VBox> vBoxs = new ArrayList<VBox>();
        for(int i=0; i<mainInterface.getBoxs().size(); i++){
            vBoxs.add(mainInterface.getBoxs().get(i).vBox);
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

        TestInterface testInterface = new TestInterface(testStage);
        root2.getChildren().add(testInterface.getButtonsBox().vBox);

        testStage.setScene(new Scene(root2, 500,300));
        testStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        controller = new BasicProtocol(new Elevator(100));

    }
}
