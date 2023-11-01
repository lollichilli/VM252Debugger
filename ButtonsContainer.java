import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonsContainer extends JPanel
{
    private static final int OUR_DEFAULT_WIDTH = 500;
    private static final int OUR_DEFAULT_HEIGHT = 500;

    private JPanel myPanel;

    //
    // Accessors
    //

    private JPanel getPanel()
    {
        return myPanel;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {
        myPanel = other;
    }

    //
    // Constructors
    //

    public ButtonsContainer()
    {
        this(null);
    }

    public ButtonsContainer(ObservableVM252 initialModel)
    {

        //
        // Create button controller
        //

        ButtonsController buttons = new ButtonsController(initialModel);

        setPanel(new JPanel());
        getPanel().setBackground(new Color(255, 255, 0));
        getPanel().setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);
        getPanel().add(buttons);

        add(getPanel());

    }
}
