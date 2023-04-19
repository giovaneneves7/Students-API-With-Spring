package br.com.ifba.giovaneneves.registrationprojectwithspringframework.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GiovaneNeves
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentsTableModel extends AbstractTableModel {

    //----------------------------------------------{ ATTRIBUTES }----------------------------------------------//

    private List<Student> studentList = new ArrayList<>();
    private String[] collumns = {"ID", "Name", "Registration", "Average grades", "Academic Year", "Age"};

    //----------------------------------------------{ METHODS}----------------------------------------------//

    @Override
    public String getColumnName(int column){

        return this.getCollumns()[column];
    }

    @Override
    public int getRowCount() {
        return this.getStudentList().size();
    }

    @Override
    public int getColumnCount() {
        return this.getCollumns().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch(columnIndex){
            case 0:
                return this.getStudentList().get(rowIndex).getId();
            case 1:
                return this.getStudentList().get(rowIndex).getName();
            case 2:
                return this.getStudentList().get(rowIndex).getRegistrationNumber();
            case 3:
                return this.getStudentList().get(rowIndex).getAverageGrades();
            case 4:
                return this.getStudentList().get(rowIndex).getAcademicYear();
            /*case 5:
                return this.getStudentList().get(rowIndex).getAge();*/

        }

        return null;
    }

    /**
     * Adds a new row to the table
     * @param student to be added to the student list
     */
    public void addRow(Student student){

        this.getStudentList().add(student);

        //--+ Update the table +--//
        this.fireTableDataChanged();

    }

    /**
     *
     * @param row to be removed from the JTable.
     */
    public void removeRow(int row){

        this.getStudentList().remove(row);
        this.fireTableRowsDeleted(row, row);

    }

}
