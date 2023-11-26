import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.lang.Integer;

import java.lang.Integer;

public class MemoryBytesViewAndController extends JPanel implements Observer {

    private JPanel myPanel;
    private VM252Model myModel;
    private JLabel myLabel;
    final JTable myTable;

    //
    // Accessors
    //

    private JPanel getPanel() {
        return myPanel;
    }

    private VM252Model getModel() {
        return myModel;
    }

    private JLabel getLabel() {
        return myLabel;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other) {
        myPanel = other;
    }

    private void setModel(VM252Model other) {

        if (getModel() != null)
            getModel().detach(this);

        myModel = other;

        if (getModel() != null)
            getModel().attach(this);

    }

    private void setLabel(JLabel other) {
        myLabel = other;
    }

    //
    // Constructors
    //

    public MemoryBytesViewAndController() {
        this(null);
    }

    public MemoryBytesViewAndController(VM252Model initialModel) {
        setModel(initialModel);
        setLabel(new JLabel(""));

        //
        // Create a panel to display the state of the machine model
        //

        setPanel(new JPanel());
        // getPanel().setBackground(new Color(255, 255, 0));
        getPanel().add(getLabel());

        myTable = new JTable(410, 21);

        int rowCount = 410;
        int columnCount = 20;
        int rowAddr = 0;

        String[] colHeaders = {
                "Address", "0", "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19"
        };

        for (int header=0; header < 21; ++header){
            myTable.getColumnModel().getColumn(header).setHeaderValue(colHeaders[header]);
        };

        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int memoryValueIndex = 0;

        for (int row = 0; row < rowCount; ++row) {
            myTable.setValueAt("Addr " + rowAddr, row, 0);
            for(int col = 1; col < 21; ++col) {
                if(memoryValueIndex != 8192) {
                    int byteToInt  = (int) getModel().memoryByte(memoryValueIndex) & 0xff;
                    String hexValue = Integer.toHexString(byteToInt);

                    if( hexValue.length() % 2 == 1){
                        hexValue = "0" + hexValue;
                    }
                    myTable.setValueAt(hexValue, row, col);
                    ++memoryValueIndex;

                }

            rowAddr = rowAddr + 20;
            }
        }

        // for (int row = 0; row < rowCount; row++) {
        //     for (int column = 1; column <= columnCount; column++) {
        //         myTable.setValueAt("00", row, column);
        //     }
        // }

        JScrollPane scrollPane = new JScrollPane(myTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 150));

        add(scrollPane, BorderLayout.CENTER);
        add(getPanel());

        myTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                int changedRow = myTable.getSelectedRow();
                int changedColumn = myTable.getSelectedColumn();

                if (changedColumn != 0){
                    String hexValue = myTable.getValueAt(changedRow, changedColumn).toString();

                    int hexToInt = Integer.parseInt(hexValue, 16);
                    int byteIndexToChange = changedRow * 20 + changedColumn;
                    byte intToByte = (byte) hexToInt;
                    getModel().setMemoryByte(byteIndexToChange, intToByte);
                }
                else{
                    int rowAddr = changedRow * 20;
                    myTable.setValueAt("Addr " + rowAddr, changedRow, changedColumn);
                }
            }
        });

    }

    @Override
    public void update() {
        int memoryValueIndex = 0;

        for (int row = 0; row < 410; ++row) {
            for(int col = 1; col < 21; ++col) {
                if(memoryValueIndex != 8192) {
                    int byteToInt  = (int) getModel().memoryByte(memoryValueIndex) & 0xff;
                    String hexValue = Integer.toHexString(byteToInt);

                    if( hexValue.length() % 2 == 1){
                        hexValue = "0" + hexValue;
                    }
                    myTable.setValueAt(hexValue, row, col);
                    ++memoryValueIndex;

                }
            }
        }
    }
}
