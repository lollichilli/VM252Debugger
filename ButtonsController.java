import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonsController extends JPanel {
    private JPanel myPanel;
    private ObservableVM252 myModel;

    private JPanel getPanel() {
        return myPanel;
    }

    private ObservableVM252 getModel() {
        return myModel;
    }

    private void setPanel(JPanel other) {
        myPanel = other;
    }

    private void setModel(ObservableVM252 other) {
        myModel = other;
    }

    //
    // Constructors
    //

    public ButtonsController() {
        this(null);
    }

    public ButtonsController(ObservableVM252 initialModel) {
        setModel(initialModel);

        //
        // Create buttons
        //

        JButton quitButton = new JButton("Quit");
        JButton runButton = new JButton("Run");
        JButton helpButton = new JButton("Help");

        setPanel(new JPanel());
        getPanel().setLayout(new GridLayout(3, 3));

        //
        // Add the Buttons to the panel
        //

        getpanel().add(quitButton);
        getpanel().add(runButton);
        getpanel().add(helpButton);

        add(getPanel());

    }
}
