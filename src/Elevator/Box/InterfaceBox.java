package Elevator.Box;


import Elevator.Interface.*;

public class InterfaceBox extends AbstractBox {
    private Interfacer interfacePanel;

    public InterfaceBox(String title, int idOfInterface) {
        super(title);
        switch(idOfInterface){
            case 0:
                interfacePanel = new InteriorInterface();
                break;
            case 1:
                interfacePanel = new ExteriorInterface();
                break;
            case 2:
                interfacePanel = new TestInterface();
                break;
        }
        vBox.getChildren().add(interfacePanel.setInterface());
    }





}
