package com.tree.compilationproject;

import com.tree.compilationproject.nodes.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTreeCell;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

// TODO : LET CHOICE SELECT TO BE FREE

public class MainPageController implements Initializable {

    @FXML
    private TreeView<Exp> treeView;
    @FXML
    private ListView<Exp> choiceList;
    @FXML
    private TextArea logsText;
    @FXML
    private Button generateTree, choiceApplyBtn, nodeDeleteBtn, applyValueBtn;
    @FXML
    private Label text;
    @FXML
    private VBox valueContainer;
    @FXML
    private TextField expressionValue;

    @SuppressWarnings("unchecked")
	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valueContainer.setVisible(false);
        nodeDeleteBtn.setVisible(false);
        initializeRoot();

        TreeItem<Exp> altRootNode = null;
        treeView.setStyle(
                "  -fx-base: #242424 ;\n" +
                        "  -fx-control-inner-background: derive(-fx-base,20%);\n" +
                        "  -fx-control-inner-background-alt: derive(-fx-control-inner-background,-10%);\n" +
                        "  -fx-accent: #006689;\n" +
                        "  -fx-focus-color: #036E83;\n" +
                        "  -fx-faint-focus-color: #036E8322;");
        treeView.setRoot(altRootNode);

        choiceApplyBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // TODO: process select choice from list View
                addItem();
            }
        });

        
        // TODO: When selectign an item in treeview
        treeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue,
                                Object newValue) {

                generateTree.setDisable(false);
                expressionValue.setText("");
                // if the item selected is the root display the button deleteNode
                if (treeView.getSelectionModel().getSelectedItem().getParent() == null) {
                    nodeDeleteBtn.setVisible(true);
                } else {
                    nodeDeleteBtn.setVisible(false);
                }
                TreeItem<Exp> selectedItem = (TreeItem<Exp>) newValue;
                writeMessage("Selected Text : " + selectedItem.getValue().getClass().getSimpleName());
                writeMessage(selectedItem.toString());
                // display child nodes only if it's expanded
                if (selectedItem.getValue().isExpanded()) {
                    displayChoiceListToNode(selectedItem.getValue());
                } else {
                    displayChoiceListToNode(null);
                }
                applyValueBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (!expressionValue.getText().equals("")) {
                             selectedItem.setValue(insertExpressionValue(selectedItem.getValue(), expressionValue.getText()));
                        }
                    }
                });

                generateTree.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        writeMessage(selectedItem.getValue().generateCodeCible());
                    }
                });


            }
        });

        nodeDeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    treeView.setRoot(null);
                    initializeRoot();
            }
        });

    }

    private void initializeRoot() {
        AffExp affExp = new AffExp();
        AltExp altExp = new AltExp();
        this.choiceList.getItems().clear();
        this.choiceList.getItems().addAll(affExp ,altExp);
    }



    private void addItem()
    {
        Exp valueSelected = this.choiceList.getSelectionModel().getSelectedItem();
        if (valueSelected == null)
        {
            this.writeMessage("L'item ne Peut pas etre nulle.");
            return;
        }

        TreeItem<Exp> parentView = treeView.getSelectionModel().getSelectedItem();

        if (parentView == null)
        {
            TreeItem<Exp> rootAff = new TreeItem<>(new AffExp());
            TreeItem<Exp> rootAlt = new TreeItem<>(new AltExp());
            if (valueSelected.getClass().getSimpleName().equals("AffExp")) {
                treeView.setRoot(rootAff);
            } else if (valueSelected.getClass().getSimpleName().equals("AltExp")) {
                treeView.setRoot(rootAlt);
            } else {
                this.writeMessage("Choisissez d'abord un noeud a affecté");
            }
            return;
        }

        TreeItem<Exp> newItemView = new TreeItem<>(processInsertionInExpression(parentView.getValue(), valueSelected));
        parentView.getChildren().add(newItemView);

        if (!parentView.isExpanded())
        {
            parentView.setExpanded(true);
        }
        /*
        Update choice List when adding a node
         */
        displayChoiceListToNode(parentView.getValue());
    }

    // Update Choice list for selected Node
    private void displayChoiceListToNode(Exp node)
    {
        this.choiceList.getItems().clear();
        List<String> nodes = Arrays.asList("IfSign", "ThenSign", "ElseSign");
        if (node == null) {
                choiceList.setVisible(false);
                    valueContainer.setVisible(true);
            return;
        }

        choiceList.setVisible(true);
        valueContainer.setVisible(false);
        if (node.getClass().getSimpleName().equals("OperationExp")) {
            OperationExp operationExp = new OperationExp();
            NombreExp nombreExp = new NombreExp();
            IdentExp identExp = new IdentExp();
            SignExp signExp = new SignExp();
            this.choiceList.getItems().addAll(operationExp, nombreExp, identExp, signExp);
        }
        // Get all possible right expression values
        if (node.getClass().getSimpleName().equals("AffExp"))
        {
            OperationExp operationExp = new OperationExp();
            NombreExp nombreExp = new NombreExp();
            IdentExp identExp = new IdentExp();
            AffSign affSign = new AffSign();
            this.choiceList.getItems().addAll(operationExp, nombreExp, identExp, affSign);
        }
        
        if (node.getClass().getSimpleName().equals("AltExp"))
        {
        	IfSign ifSign = new IfSign();
        	ConditionExp condition = new ConditionExp();
        	AffExp ifStatement = new AffExp();
        	ThenSign thenSign = new ThenSign();
        	ElseSign elseSign = new ElseSign();
        	AffExp elseStatement = new AffExp();
            this.choiceList.getItems().addAll(ifSign, condition, ifStatement, thenSign, elseSign, elseStatement);
        }
        if (node.getClass().getSimpleName().equals("ConditionExp"))
        {
        	OperationExp operationExp = new OperationExp();
        	NombreExp nombreExp = new NombreExp();
            IdentExp identExp = new IdentExp();
        	CmpSign cmp = new CmpSign();
            this.choiceList.getItems().addAll(operationExp, nombreExp, identExp, cmp);
        }

        
    }

    
    
    // Method for Logging
    private void writeMessage(String msg)
    {
        this.logsText.clear();
        this.logsText.appendText(msg + "\n");
    }


    private Exp processInsertionInExpression(Exp parent, Exp child) {
        if (parent.getClass().getSimpleName().equals("AffExp")) {
            AffExp affExp = (AffExp) parent;
            if (((AffExp) parent).getIdentExp() == null && child.getClass().getSimpleName().equals("IdentExp")) {
            	IdentExp child0 = (IdentExp) child;
            	child0.setParent(affExp);
                affExp.setIdentExp((IdentExp) child0);
            }
            else if (child.getClass().getSimpleName().equals("AffSign")) {
                affExp.setAffectationOp((AffSign) child);
            }
            else if (((AffExp) parent).getRightExpression() == null){
                if (child.getClass().getSimpleName().equals("IdentExp"))
                {
                    affExp.setRightExpression((IdentExp) child);
                }
               else if (child.getClass().getSimpleName().equals("OperationExp")) {
                    affExp.setRightExpression((OperationExp) child);
                } else if (child.getClass().getSimpleName().equals("NombreExp")) {
                    affExp.setRightExpression((NombreExp) child);
                }

            }
            child.setExpUsed(true);
            return child;
        }
        else if (parent.getClass().getSimpleName().equals("OperationExp")) {
            OperationExp operationExp = (OperationExp) parent;
            child.setExpUsed(true);

            if (child.getClass().getSimpleName().equals("SignExp")) {
                operationExp.setSignOp((SignExp) child);
                return child;
            }
            if (((OperationExp) parent).getLeftExp() == null){
                if (child.getClass().getSimpleName().equals("IdentExp"))
                {
                    operationExp.setLeftExp((IdentExp) child);
                }
                else if (child.getClass().getSimpleName().equals("OperationExp")) {
                    operationExp.setLeftExp((OperationExp) child);
                } else if (child.getClass().getSimpleName().equals("NombreExp")) {
                    operationExp.setLeftExp((NombreExp) child);
                }
            }  else if (((OperationExp) parent).getRightExp() == null){
                if (child.getClass().getSimpleName().equals("IdentExp"))
                {
                    operationExp.setRightExp((IdentExp) child);
                }
                else if (child.getClass().getSimpleName().equals("OperationExp")) {
                    operationExp.setRightExp((OperationExp) child);
                } else if (child.getClass().getSimpleName().equals("NombreExp")) {
                    operationExp.setRightExp((NombreExp) child);
                }
            }

            return child;
        }
        else if (parent.getClass().getSimpleName().equals("AltExp")) {
            AltExp altExp = (AltExp) parent;
            if (((AltExp) parent).getIfSign() == null && child.getClass().getSimpleName().equals("IfSign")) {
            	
            	altExp.setIfSign((IfSign) child);
            }
            else if (((AltExp) parent).getCondition() == null && child.getClass().getSimpleName().equals("ConditionExp")) {
            	
            	altExp.setCondition((ConditionExp) child);
            }
            else if (((AltExp) parent).getIfStatement() == null && child.getClass().getSimpleName().equals("AffExp")) {
            	
            	altExp.setIfStatement((AffExp) child);
            }
            else if (((AltExp) parent).getElseSign() == null && child.getClass().getSimpleName().equals("ElseSign")) {
            	
            	altExp.setElseSign((ElseSign) child);
            }
            else if (((AltExp) parent).getElseStatement() == null && child.getClass().getSimpleName().equals("AffExp")) {
            	
            	altExp.setElseStatement((AffExp) child);
            }
            
            child.setExpUsed(true);
            return child;
        }
        else if (parent.getClass().getSimpleName().equals("ConditionExp")) {
        	ConditionExp condition = (ConditionExp) parent;

            if (((ConditionExp) parent).getLeftExpression() == null && !child.getClass().getSimpleName().equals("CmpSign")) {
                if (child.getClass().getSimpleName().equals("IdentExp")) {

                    condition.setLeftExpression((IdentExp) child);
                }
                else if (child.getClass().getSimpleName().equals("NombreExp")) {

                    condition.setLeftExpression((NombreExp) child);
                } else if (child.getClass().getSimpleName().equals("OperationExp")) {
                    condition.setLeftExpression((OperationExp) child);
                }
            } else if (((ConditionExp) parent).getRightExpression() == null &&
                    !child.getClass().getSimpleName().equals("CmpSign")) {
                if(child.getClass().getSimpleName().equals("IdentExp")) {

                    condition.setRightExpression((IdentExp) child);
                }
                else if (child.getClass().getSimpleName().equals("NombreExp")) {

                    condition.setRightExpression((NombreExp) child);
                } else if (child.getClass().getSimpleName().equals("OperationExp")) {
                    condition.setRightExpression((OperationExp) child);
                }
            } else if (((ConditionExp) parent).getCmpSign() == null) {
                    condition.setCmpSign((CmpSign) child);
            }
            child.setExpUsed(true);
            return child;
        }
        
        return parent;
    }

    Exp insertExpressionValue(Exp node, String value) {
        if (node.getClass().getSimpleName().equals("IdentExp"))
        {
            ((IdentExp) node).setName(value);
        }
        else if (node.getClass().getSimpleName().equals("SignExp")) {
            ((SignExp) node).setSign(value);
        } else if (node.getClass().getSimpleName().equals("NombreExp")) {
            ((NombreExp) node).setNombre(value);
        }
        else if (node.getClass().getSimpleName().equals("CmpSign")) {
            ((CmpSign) node).setSign(value);
        }

        return node;
    }
}

