import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class UI
{
    Validate valid = new Validate();

    Stage window = new Stage();
    Scene mainMenu, newClientForm, cityListUI;
    boolean answer, finished;

    public void buildMainMenu()
    {

        Button newClient, freeTicket, close;
        Label welcomeMessage;

        welcomeMessage = new Label("Hello and welcome to our tourist agency app");
        welcomeMessage.setFont(new Font(24));

        newClient = new Button("Register New \n       Client");
        freeTicket = new Button("Giveaway");
        close = new Button ("Close");

        newClient.setMinWidth(100);
        freeTicket.setMinWidth(100);
        close.setMinWidth(100);

        window.setTitle("Tourist Agency app");

        GridPane mainLayout = new GridPane();
        mainLayout.setPadding(new Insets(30,30,30,30));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setVgap(20);
        mainLayout.setHgap(10);

        GridPane.setHalignment(welcomeMessage, HPos.CENTER);
        GridPane.setHalignment(newClient, HPos.CENTER);
        GridPane.setHalignment(freeTicket, HPos.CENTER);
        GridPane.setHalignment(close, HPos.CENTER);
        GridPane.setConstraints(newClient, 0 , 4);
        GridPane.setConstraints(freeTicket, 0 , 5);
        GridPane.setConstraints(close, 0 , 6);

        mainLayout.getChildren().addAll(welcomeMessage,newClient,freeTicket,close);

        mainMenu = new Scene(mainLayout,600,400);
        window.setScene(mainMenu);
        window.show();

        newClient.setOnAction(e1 ->
        {
            window.setOnCloseRequest(e2 ->
            {
                if(!alertWindowChoice("Alert","Are you sure you want to quit?\n   Your work will not be saved!", "Yes","No")) e2.consume();
            });
            buildFormUI();
        });

        close.setOnAction(e ->
        {
            if(alertWindowChoice("Alert","Are you sure you want to quit?", "Yes","No")) window.close();
        });

        window.setOnCloseRequest(e ->
        {
            if(!alertWindowChoice("Alert","Are you sure you want to quit?", "Yes","No")) e.consume();
        });

        freeTicket.setOnAction(e ->
        {
            if (Traveler.traveler_counter > 0)
            {
                try
                {
                    alertWindowBasic("Winner!", "The winner of our ticket giveaway for " + Giveaway.GIVEAWAY_CITY_NAME + " in " + Giveaway.GIVEAWAY_COUNTRY_NAME + " is: " + Giveaway.goldenTicket(Traveler.AllTravelers), "Close");
                } catch (InvocationTargetException invocationTargetException)
                {
                    invocationTargetException.printStackTrace();
                } catch (IllegalAccessException illegalAccessException)
                {
                    illegalAccessException.printStackTrace();
                } catch (NoSuchMethodException noSuchMethodException)
                {
                    noSuchMethodException.printStackTrace();
                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
            else
            {
                alertWindowBasic("Alert", "There's no registered travelers", "Cancel");
            }
        });
    }

    public void buildFormUI()
    {
        VBox baseForm = new VBox(20);
        HBox locationLayout = new HBox(10);
        GridPane secForm = new GridPane();
        Label formLabel = new Label("Tell us some things about yourself");
        formLabel.setFont(new Font(20));
        Button submitForm = new Button ("Submit");

        baseForm.setPadding(new Insets(20,20,20,20));
        baseForm.getChildren().addAll(formLabel, secForm, submitForm);
        baseForm.setSpacing(13);
        baseForm.setAlignment(Pos.TOP_CENTER);

        Label formName, formAge, formLocation, preferences, travelerType;
        TextField textName, textAge, textCity, textCountry;

        textCity = new TextField();
        textCountry = new TextField();

        CheckBox museums = new CheckBox("Museums");
        CheckBox cafes = new CheckBox("Cafes");
        CheckBox restaurants = new CheckBox("Restaurants");
        CheckBox bars = new CheckBox("Bars");
        CheckBox beaches = new CheckBox("Beaches");
        CheckBox monuments = new CheckBox("Monuments");

        museums.setDisable(true);
        cafes.setDisable(true);
        restaurants.setDisable(true);
        bars.setDisable(true);
        beaches.setDisable(true);
        monuments.setDisable(true);

        ComboBox<String> travelerTypeCombobox = new ComboBox<>();
        travelerTypeCombobox.getItems().addAll("Traveler", "Tourist", "Business");
        travelerTypeCombobox.setMinWidth(140);
        travelerTypeCombobox.setPromptText("Choose..");

        travelerTypeCombobox.setOnAction(e ->
        {
            if (travelerTypeCombobox.getValue().equals("Traveler") || travelerTypeCombobox.getValue().equals("Tourist"))
            {
                museums.setDisable(false);
                cafes.setDisable(false);
                restaurants.setDisable(false);
                bars.setDisable(false);
                beaches.setDisable(false);
                monuments.setDisable(false);
                locationLayout.setDisable(true);
                textCity.clear();
                textCountry.clear();
            }
            else
            {
                museums.setDisable(true);
                museums.setSelected(false);
                cafes.setDisable(true);
                cafes.setSelected(false);
                restaurants.setDisable(true);
                restaurants.setSelected(false);
                bars.setDisable(true);
                bars.setSelected(false);
                beaches.setDisable(true);
                beaches.setSelected(false);
                monuments.setDisable(true);
                monuments.setSelected(false);
                locationLayout.setDisable(false);
            }
        });

        secForm.setHgap(10);
        secForm.setVgap(10);

        textCity.setMaxWidth(75);
        textCity.setPromptText("City Name");
        textCountry.setMaxWidth(80);
        textCountry.setPromptText("Country init");
        locationLayout.getChildren().addAll(textCity, textCountry);
        locationLayout.setDisable(true);

        GridPane.setConstraints(formName = new Label("Name:"), 0 , 1);
        GridPane.setConstraints(formAge = new Label("Age:"), 0 , 2);
        GridPane.setConstraints(formLocation = new Label("Current\nLocation:"), 0 , 3);
        GridPane.setConstraints(textName = new TextField(), 1 , 1);
        GridPane.setConstraints(textAge = new TextField(), 1 , 2);
        GridPane.setConstraints(locationLayout, 1 , 3);
        GridPane.setConstraints(preferences = new Label("What are your preferences?"), 10, 0);
        GridPane.setConstraints(museums,10,1);
        GridPane.setConstraints(cafes,10,2);
        GridPane.setConstraints(restaurants,10,3);
        GridPane.setConstraints(bars,10,4);
        GridPane.setConstraints(beaches,10,5);
        GridPane.setConstraints(monuments,10,6);
        GridPane.setConstraints(travelerType = new Label("What type of traveler\n          are you?"),1,4);
        GridPane.setConstraints(travelerTypeCombobox,1,5);

        preferences.setFont(new Font(15));
        travelerType.setFont(new Font(15));
        secForm.getChildren().addAll(formName, formAge, formLocation , locationLayout, textName,textAge, preferences, museums,cafes,restaurants,bars,beaches,monuments, travelerType, travelerTypeCombobox);

        newClientForm = new Scene(baseForm,600,400);
        window.setScene(newClientForm);

        submitForm.setOnAction(e -> submitForm(textName, textAge, textCity, textCountry, museums, cafes, restaurants,bars,beaches,monuments,travelerTypeCombobox));
    }

    public void alertWindowBasic(String title, String message, String buttonMessage)
    {
        Stage alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setMinWidth(250);

        Label label = new Label(message);
        Button button = new Button(buttonMessage);

        VBox layout = new VBox();
        button.setOnAction(e -> alertWindow.close());

        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(label, button);
        layout.setSpacing(15);
        layout.setAlignment(Pos.CENTER);

        Scene alert = new Scene(layout);

        alertWindow.setScene(alert);
        alertWindow.showAndWait();
    }

    public boolean alertWindowChoice(String title, String message, String buttonYes, String buttonNo)
    {

        Stage alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setMinWidth(250);

        Label label = new Label(message);
        Button yesButton = new Button(buttonYes);
        Button noButton = new Button(buttonNo);

        VBox baseLayout = new VBox();
        HBox secLayout = new HBox();

        yesButton.setOnAction(e ->
        {
            answer = true;
            alertWindow.close();
        });

        noButton.setOnAction(e ->
        {
            answer = false;
            alertWindow.close();
        });

        secLayout.getChildren().addAll(yesButton,noButton);
        secLayout.setSpacing(25);
        secLayout.setAlignment(Pos.CENTER);

        baseLayout.setPadding(new Insets(10,10,10,10));
        baseLayout.getChildren().addAll(label, secLayout);
        baseLayout.setSpacing(15);
        baseLayout.setAlignment(Pos.CENTER);

        Scene alert = new Scene(baseLayout);

        alertWindow.setScene(alert);
        alertWindow.showAndWait();

        return answer;
    }

    public void submitForm(TextField name, TextField age,TextField city, TextField country, CheckBox museums, CheckBox cafes, CheckBox restaurants, CheckBox bars, CheckBox beaches, CheckBox monuments, ComboBox<String> travelerType)
    {
        if (valid.validName(name.getText()) && valid.isPositiveInt(age.getText()) && travelerType.getValue() != null && (travelerType.getValue().equals("Business") || museums.isSelected() || cafes.isSelected() || restaurants.isSelected() || bars.isSelected() || beaches.isSelected() || monuments.isSelected()) && (!travelerType.getValue().equals("Business") || valid.validCity(city.getText(), country.getText())))
        {
            String tempTravelerType = travelerType.getValue();

            if (tempTravelerType.equals("Traveler"))
            {
                Traveler.AllTravelers.add(new Traveler());
            }
            else if (tempTravelerType.equals("Tourist"))
            {
                Traveler.AllTravelers.add(new Tourist());
            }
            else Traveler.AllTravelers.add(new Business());

            Boolean [] selections = new Boolean[] {museums.isSelected(), cafes.isSelected(), restaurants.isSelected(), bars.isSelected(), beaches.isSelected(), monuments.isSelected()};

            Traveler.AllTravelers.get(Traveler.traveler_counter-1).buildTraveler(name.getText(), Integer.parseInt(age.getText()), city.getText(), country.getText(),selections);
            buildCitiesUI();
        }
        else
        {
            alertWindowBasic("Alert", "One or more entries are empty or invalid", "Back");
        }
    }

    public void buildCitiesUI ()
    {
        ArrayList<String> cityList = new ArrayList<>();

        VBox baseLayout = new VBox(30);
        baseLayout.setAlignment(Pos.CENTER);
        baseLayout.setPadding(new Insets(20,20,20,20));
        HBox textLayout = new HBox(20);
        textLayout.setAlignment(Pos.CENTER);
        HBox buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        cityListUI = new Scene(baseLayout, 600, 400);
        window.setScene(cityListUI);

        Label addCitiesInstruction = new Label("Add the cities you would like to investigate");
        Label selectedCitiesLabel = new Label ("Selected cities");
        addCitiesInstruction.setFont(new Font(15));
        selectedCitiesLabel.setFont(new Font(15));
        TextField cityName = new TextField();
        TextField countryName = new TextField();
        Button addButton = new Button("Add");
        addButton.setMinWidth(100);
        Button submitButton = new Button("Submit");
        submitButton.setMinWidth(100);
        submitButton.setDisable(true);

        ListView<String> selectedCitiesList = new ListView<>();
        selectedCitiesList.setMaxWidth(200);

        cityName.setMaxWidth(130);
        cityName.setPromptText("City Name");
        countryName.setMaxWidth(130);
        countryName.setPromptText("Country initials");

        textLayout.getChildren().addAll(cityName,countryName);
        buttonLayout.getChildren().addAll(addButton, submitButton);
        baseLayout.getChildren().addAll(addCitiesInstruction, textLayout, selectedCitiesLabel, selectedCitiesList, buttonLayout);

        addButton.setOnAction(e ->
        {
            if (valid.validCity(cityName.getText(), countryName.getText()))
            {
                if (cityList.contains((cityName.getText()+","+countryName.getText()).toLowerCase())) alertWindowBasic("Alert", "This city has already\n    been selected", "Back");
                else
                {
                    submitButton.setDisable(false);
                    cityList.add((cityName.getText() + "," + countryName.getText()).toLowerCase());
                    selectedCitiesList.getItems().add((cityName.getText() + " " + countryName.getText()).toLowerCase());
                }
            }
            else
            {
                alertWindowBasic("Alert", "This city doesn't exist", "Back");
            }
        });

        submitButton.setOnAction(e ->
        {
            window.setScene(mainMenu);
            try
            {
                Traveler.AllTravelers.get(Traveler.traveler_counter-1).addCities(cityList);
                getResult();
            }
            catch (InvocationTargetException invocationTargetException)
            {
                invocationTargetException.printStackTrace();
            }
            catch (NoSuchMethodException noSuchMethodException)
            {
                noSuchMethodException.printStackTrace();
            }
            catch (IllegalAccessException illegalAccessException)
            {
                illegalAccessException.printStackTrace();
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        });
    }

    public void getResult() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException
    {
        City bestCity;
        Traveler currentTraveler = Traveler.AllTravelers.get(Traveler.traveler_counter-1);

        bestCity = currentTraveler.compareCities(currentTraveler.getTravelerCitiesArray());
        alertWindowBasic("Result", "Your suggested destination is: "+bestCity.getName()+" in "+bestCity.getCountry(),"Continue");

        currentTraveler.getTravelerCitiesArray().clear();
        currentTraveler.setVisit(bestCity.getCityName());
        Traveler.saveTravelers();

        CollaborativeFiltering.getCriteria();

        if (Traveler.travelerExists(Traveler.AllTravelers.get(Traveler.traveler_counter-1)))
        {
            Traveler.AllTravelers.remove(Traveler.traveler_counter-1);
            Traveler.traveler_counter--;
        }
        window.setOnCloseRequest(e ->
        {
            if(!alertWindowChoice("Alert","Are you sure you want to quit?", "Yes","No")) e.consume();
        });
    }
}
