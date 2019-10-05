package sg.edu.nus.iss.vmcs.system;

public class BaseController {
    
    protected MainController mainController;

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }  
   
    
    
}
