import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RebelWindow {
	
	static String treeItemSelected;		// a static, temporary value, changed by clicking items from the tree
	static String listItemSelected;		// a static, temporary value, changed by clicking items from the list
//-------------------------------------------------------------------------------------------------------		
	public static void Display(String title, String message){
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);	// blocks other window interaction until you deal with this one
		window.setTitle(title);
		window.setMinWidth(300);
		window.setMinHeight(600);
		Label label = new Label(message);
//-------------------------------------------------------------------------------------------------------					
		TreeItem<String> root, xWing, yWing;					// creating the root item and other items/branches
		root = new TreeItem<>();
		root.setExpanded(true);										// expanded from the start
		
		xWing = makeBranch("X-Wing", root);
		makeBranch("Luke Skywalker", xWing);
		makeBranch("Wedge Antilles", xWing);
		makeBranch("Red Squadron Pilot", xWing);
		
		yWing = makeBranch("Y-Wing", root);
		makeBranch("Horton Salm", yWing);
		makeBranch("\"Dutch\" Vander", yWing);
		
		TreeView<String> tree = new TreeView<String>(root);			// creating the tree with a specified root item
		tree.setShowRoot(false);										// doesn't show the root
		tree.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue)-> {	// when an action (button click) is made, the chosen item gets set to the static value
			treeItemSelected = newValue.getValue().toString();
		});
//-------------------------------------------------------------------------------------------------------		
		ListView<String> listView = new ListView<>();				// creating a list
		//listView.getItems().addAll("Rebels", "Imperials", "Scum");
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listView.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue)-> {
			listItemSelected = newValue;
		});
//-------------------------------------------------------------------------------------------------------				
		Button addButton = new Button("Add");		// creating a button with 2 functions
		addButton.setOnAction(e-> 
		{
			listView.getItems().addAll(treeItemSelected);	// gets the current static value, depending on what was last clicked, and adds it to the list
		});	
//-------------------------------------------------------------------------------------------------------		
		Button removeButton = new Button("Remove");		// creating a button with 2 functions
		removeButton.setOnAction(e-> 
		{
			listView.getItems().remove(listItemSelected);	// gets the current static value, depending on what was last clicked, and removes it from the list
		});
//-------------------------------------------------------------------------------------------------------				
		VBox mainLayout = new VBox(10);				// creating the layout
		mainLayout.getChildren().addAll(label, tree, addButton, listView, removeButton);
		mainLayout.setAlignment(Pos.CENTER);				
		Scene factionScene = new Scene(mainLayout, 200, 200);
	
		window.setScene(factionScene);
		window.showAndWait();
	}
//-------------------------------------------------------------------------------------------------------			
	public static TreeItem<String> makeBranch(String name, TreeItem<String> parent){
		TreeItem<String> item = new TreeItem<String>(name);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;		
	}
}
