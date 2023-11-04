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
        JButton pauseButton = new JButton("Pause");
        JButton resumeButton = new JButton("Resume");
        JButton increaseButton = new JButton("Increase");
        JButton decreaseButton = new JButton("Decrease");

        setPanel(new JPanel());
        getPanel().setLayout(new GridLayout(1, 7, 10, 0));

        //
        // Add the Buttons to the panel
        //

        getPanel().add(runButton);
        getPanel().add(pauseButton);
        getPanel().add(resumeButton);
        getPanel().add(increaseButton);
        getPanel().add(decreaseButton);
        getPanel().add(helpButton);
        getPanel().add(quitButton);

        add(getPanel());

    }
}
