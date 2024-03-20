import controllers.DiaryController;
import controllers.EntryController;
import dtos.requests.*;

import javax.swing.*;

public class Main {
    private static final RegisterRequest registerRequest = new RegisterRequest();
    private static final LoginRequest loginRequest = new LoginRequest();
    private static final CreateEntryRequest createEntryRequest = new CreateEntryRequest();
    private static final UpdateEntryRequest updateEntryRequest = new UpdateEntryRequest();
    private static final RemoveUserRequest removeUserRequest = new RemoveUserRequest();

    public static void main(String[] args) {
        startApp();
    }

    private static void startApp() {
        print("Welcome to C19 GossipVille");

        gotoMainMenu();
    }



    private static void gotoMainMenu() {
        String mainMenu = """
                What do you want to do today?
                
                1 -> Register to C19 GossipVille
                2 -> Login
                3 -> Create a gossip
                4 -> Find one gist
                5 -> Update gist
                6 -> View all gossips
                7 -> Delete gossip
                8 -> Logout
                9 -> Close C19 GossipVille account
                10 -> End application
                
                Select option:
                """;

        String userChoice = input(mainMenu);
        handle(userChoice);
    }

    private static void handle(String userChoice) {
        switch (userChoice) {
            case "1" -> registerUser();
            case "2" -> login();
            case "3" -> createGossip();
            case "4" -> findGist();
            case "5" -> updateGist();
            case "6" -> viewAllGossips();
            case "7" -> deleteGossip();
            case "8" -> logout();
            case "9" -> removeUser();
            case "10" -> exitApp();
            default -> gotoMainMenu();
        }
    }

    private static void exitApp() {
        print("Thank you for using C19 GossipVille");
        System.exit(0);
    }

    private static void removeUser() {
        String username = input("Enter your username:");
        String password = input("Enter your password:");
        removeUserRequest.setUsername(username);
        removeUserRequest.setPassword(password);

        print(DiaryController.removerUserWith(removeUserRequest));
        gotoMainMenu();
    }

    private static void logout() {
        String username = input("Enter your username:");
        print(DiaryController.logout(username));
        gotoMainMenu();
    }

    private static void deleteGossip() {
        String username = input("Enter your username:");
        String id = input("Enter the id of the gist:");

        try {
            print(EntryController.deleteEntryBy(Integer.parseInt(id), username));
        }
        catch (NumberFormatException e) {
            print("Please enter a valid gist id.");
        }
        finally {
            gotoMainMenu();
        }
    }

    private static void viewAllGossips() {
        String username = input("Enter your username:");
        for (var output : EntryController.getEntriesFor(username)) print(output.toString());
        gotoMainMenu();
    }

    private static void updateGist() {
        String username = input("Enter your username:");
        String id = input("Enter the id of the gist:");
        try {
            updateEntryRequest.setId(Integer.parseInt(id));
        }
        catch (NumberFormatException e) {
            print("Please enter a valid gist id.");
            gotoMainMenu();
        }

        String title = input("Enter the new title of the gossip:");
        String body = input("What is the latest gist?");

        updateEntryRequest.setTitle(title);
        updateEntryRequest.setBody(body);
        updateEntryRequest.setAuthor(username);

        print(DiaryController.updateEntry(updateEntryRequest));
        gotoMainMenu();
    }

    private static void findGist() {
        String username = input("Enter your username:");
        String id = input("Enter the id of the gist:");

        try {
            print(EntryController.getEntryBy(Integer.parseInt(id), username));
        }
        catch (NumberFormatException e) {
            print("Please enter a valid gist id.");
        }
        finally {
            gotoMainMenu();
        }
    }

    private static void createGossip() {
        String username = input("Enter your username:");
        String title = input("Enter title of the gossip:");
        String body = input("What is the latest gist?");
        createEntryRequest.setAuthor(username);
        createEntryRequest.setTitle(title);
        createEntryRequest.setBody(body);

        print(DiaryController.createEntry(createEntryRequest));
        gotoMainMenu();
    }

    private static void login() {
        String username = input("Enter your username:");
        String password = input("Enter your password:");
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        print(DiaryController.login(loginRequest));
        gotoMainMenu();
    }

    private static void registerUser() {
        String username = input("Enter a username:");
        String password = input("Enter a password:");
        registerRequest.setUsername(username);
        registerRequest.setPassword(password);

        print(DiaryController.registerUser(registerRequest));
        gotoMainMenu();
    }

    private static String input(String prompt) {
        return JOptionPane.showInputDialog(null, prompt, "Input", JOptionPane.QUESTION_MESSAGE);
    }

    private static void print(String message) {
        JOptionPane.showMessageDialog(null, message, "C19 GossipVille", JOptionPane.INFORMATION_MESSAGE);
    }
}