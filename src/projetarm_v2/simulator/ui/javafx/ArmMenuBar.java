/*
 * Copyright (c) 2018-2019 Valentin D'Emmanuele, Gilles Mertens, Dylan Fraisse, Hugo Chemarin, Nicolas Gervasi
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package projetarm_v2.simulator.ui.javafx;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;

/**
 * The menu bar of the software
 */
public class ArmMenuBar {

    private MenuBar menuBar;

    private MenuItem newMemoryWindow;
    private MenuItem newRegistersWindow;
    private MenuItem newLedGameWindow;
    private MenuItem newEightSegmentDisplayWindow;

    private MenuItem switchMode;
    private MenuItem reloadMenuItem;

    //FILE
    private MenuItem neW ;
    private MenuItem openFile;
    private MenuItem save ;
    private  MenuItem saveAs ;
    private  MenuItem exitMenu ;

    private MenuItem runMenuItem ;
    private MenuItem runStepMenuItem ;
    private MenuItem stopMenuItem ;

    private MenuItem preferences;

    private HashSet<MenuItem> disableInExecution;
    private HashSet<MenuItem> disableInEdition;

    private HostServices services;

	private MenuItem newInterpreterWindow;

    /**
     * creates all the graphical elements in the menu bar
     * @param services the host services to use http clickable links
     */
    public ArmMenuBar(HostServices services){
    	this.services = services;
    	
         Menu fileMenu = new Menu("File");
         Menu windowMenu = new Menu("Window");
         Menu runMenu = new Menu("Run");
         Menu helpMenu = new Menu("Help");

        //FILE
        this.neW = new MenuItem("New");
        this.openFile = new MenuItem("Open File...");
        this.save = new MenuItem("Save");
        this.saveAs = new MenuItem("Save As...");
        this.preferences = new MenuItem("Preferences");
        this.exitMenu = new MenuItem("Exit");
        fileMenu.getItems().addAll(neW, openFile, new SeparatorMenuItem(), save, saveAs,new SeparatorMenuItem(), preferences, new SeparatorMenuItem(), exitMenu);

        //WINDOW
        this.newMemoryWindow = new MenuItem("new Memory");
        this.newRegistersWindow = new MenuItem("new Registers");
        this.newLedGameWindow = new MenuItem("new Led Game");
        this.newEightSegmentDisplayWindow = new MenuItem("EightSegment");
        windowMenu.getItems().addAll(this.newMemoryWindow, this.newRegistersWindow, this.newLedGameWindow, this.newEightSegmentDisplayWindow);

        //RUN
        this.switchMode = new MenuItem("Switch Mode");
        this.runMenuItem = new MenuItem("Run");
        this.runStepMenuItem = new MenuItem("Run Step by Step");
        this.stopMenuItem = new MenuItem("Stop");
        this.reloadMenuItem = new MenuItem("Reload");
        runMenu.getItems().addAll(this.switchMode, runMenuItem, runStepMenuItem, stopMenuItem, reloadMenuItem);

        final MenuItem aboutMenu = new MenuItem("About");
        helpMenu.getItems().add(aboutMenu);

        Menu interpreter = new Menu("Interpreter");
        this.newInterpreterWindow = new MenuItem("Launch");
        interpreter.getItems().add(newInterpreterWindow);
        
        this.menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, windowMenu, runMenu, interpreter, helpMenu);

        disableInEdition = new HashSet<>();
        disableInExecution = new HashSet<>();

        disableInExecution.add(neW);
        disableInExecution.add(openFile);
        disableInExecution.add(save);
        disableInExecution.add(saveAs);

        disableInEdition.add(runMenuItem);
        disableInEdition.add(runStepMenuItem);
        disableInEdition.add(stopMenuItem);
        disableInEdition.add(reloadMenuItem);

        exitMenu.setOnAction(actionEvent -> Platform.exit());
        
        aboutMenu.setOnAction(actionEvent -> {
            	final Stage aboutPopUp = new Stage();
                aboutPopUp.setTitle("#@RMStrong");
                Image applicationIcon = new Image("file:logo.png");
                aboutPopUp.getIcons().add(applicationIcon);
                aboutPopUp.getIcons().add(applicationIcon);
                aboutPopUp.setTitle("About - #@RMStrong");
                
				try {
					VBox main = FXMLLoader.load(getClass().getResource("/resources/aboutView.fxml"));
					main.getStylesheets().add("/resources/style.css");
					aboutPopUp.setScene(new Scene(main, 500, 280));
					Hyperlink gitLink = (Hyperlink)main.lookup("#linkGit");
					gitLink.setOnAction(actionEvent1 -> this.services.showDocument(gitLink.getText()));
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
                aboutPopUp.show();
            });
    }

    public MenuItem getReloadMenuItem() {
        return reloadMenuItem;
    }

    public MenuItem getSwitchMode() {
        return switchMode;
    }

    public MenuItem getNewMemoryWindow() {
        return newMemoryWindow;
    }

    public MenuItem getNewRegistersWindow() {
        return newRegistersWindow;
    }
    
    public MenuItem getNewLedGame() {
        return newLedGameWindow;
    }

    public MenuItem getNewEightSegmentDisplayWindow() {
        return newEightSegmentDisplayWindow;
    }

    public MenuItem getOpenFile() {
        return openFile;
    }

    public MenuItem getNeW() {
        return neW;
    }

    public MenuItem getSave() {
        return save;
    }

    public MenuItem getSaveAs() {
        return saveAs;
    }

    public MenuItem getExitMenu() {
        return exitMenu;
    }

    public MenuItem getRunMenuItem() {
        return runMenuItem;
    }

    public MenuItem getRunStepMenuItem() {
        return runStepMenuItem;
    }

    public MenuItem getStopMenuItem() {
        return stopMenuItem;
    }

    public MenuItem getPreferences() {
        return preferences;
    }

	public MenuItem getNewInterpreterWindow() {
		return newInterpreterWindow;
	}
    
    public void setExecutionMode(boolean executionMode){
        for (MenuItem item : this.disableInEdition){
            item.setDisable(!executionMode);
        }

        for (MenuItem item : this.disableInExecution){
            item.setDisable(executionMode);
        }
    }

    public Node getNode(){
        return this.menuBar;
    }
}
