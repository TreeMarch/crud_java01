package crud.crud_java01;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import crud.crud_java01.Student;
import crud.crud_java01.Repository;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private ObservableList<Student> studentList;
    private Repository studentRepo = new Repository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ObservableList<Student> students = FXCollections.observableArrayList(studentRepo.findAll());
        table.setItems(students);
    }

    public TextField studentId;
    public TextField studentName;
    public TextField studentEmail;

    public TableColumn<Student,String> colId;
    public TableColumn<Student,String> colName;
    public TableColumn<Student,String> colEmail;

    public TableView<Student> table;

    public void save(ActionEvent actionEvent) {
        Student student = new Student(studentId.getText(), studentName.getText(), studentEmail.getText());
        ObservableList<Student> students = table.getItems();
        student.setId(studentId.getText());
        student.setName(studentName.getText());
        student.setEmail(studentEmail.getText());
        studentRepo.save(student);
        students.add(student);
        table.setItems(students);
    }

    public void update(ActionEvent actionEvent) {
        Student student = table.getSelectionModel().getSelectedItem();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ObservableList<Student> students = table.getItems();

    }

    public void delete(ActionEvent actionEvent) {
        Student student = table.getSelectionModel().getSelectedItem();
        if (student == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING !!!");
            alert.setHeaderText("You didn't select any customer \nPlease select a customer");
            alert.show();
        } else {
            studentRepo.delete(student);
            table.getItems().remove(student);
        }
    }


    public void clear(ActionEvent actionEvent) {
        studentId.clear();
        studentName.clear();
        studentEmail.clear();
    }
}
