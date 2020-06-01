import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start (Stage primaryStage) throws Exception
    {
        mysql.makeJDBCConnection();
        mysql.getDataFromDB();

        UI gui = new UI();

        Traveler.loadTravelers();
        gui.buildMainMenu();
    }
}