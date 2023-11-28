import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonsContainer extends JPanel {
    private static final int OUR_DEFAULT_WIDTH = 1920;
    private static final int OUR_DEFAULT_HEIGHT = 1080;

    private JPanel myPanel;

    //
    // Accessors
    //

    private JPanel getPanel() {
        return myPanel;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other) {
        myPanel = other;
    }

    //
    // Constructors
    //

    public ButtonsContainer() {
        this(null);
    }

    public ButtonsContainer(VM252Model initialModel) {

        //
        // Create button controller
        //

        ButtonsController buttons = new ButtonsController(initialModel);

        setPanel(new JPanel());
        getPanel().setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);
        getPanel().add(buttons);

        add(getPanel());

    }
}
