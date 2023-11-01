import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MachineViewAndController extends JPanel implements SimpleObserver
{
    private static final int OUR_DEFAULT_FRAME_WIDTH = 200;
    private static final int OUR_DEFAULT_FRAME_HEIGHT = 200;
    private static final int OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH = 5;

    private JPanel myPanel;
    private ObservableVM252 myModel;
    private JLabel myLabel;

    //
    // Accessors
    //

    private JPanel getPanel()
    {
        return myPanel;
    }

    private ObservableVM252 getModel()
    {
        return myModel;
    }

    private JLabel getLabel()
    {
        return myLabel;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {
        myPanel = other;
    }

    private void setModel(ObservableVM252 other)
    {

        if (getModel() != null)
            getModel().detach(this);

        myModel = other;

        if (getModel() != null)
            getModel().attach(this);

    }

    private void setLabel(JLabel other)
    {
        myLabel = other;
    }


    //
    // Constructors
    //

    public MachineViewAndController()
    {
        this(null);
    }

    public MachineViewAndController(ObservableVM252 initialModel)
    {
        setModel(initialModel);
        setLabel(new JLabel(""));

        //
        // Create a panel to display the state of the machine model
        //

        setPanel(new JPanel());
        getPanel().setBackground(new Color(255, 255, 0));
        getPanel().add(getLabel());

        //
        // Add the panel to the container
        //

        add(getPanel());
    }

    @Override
    public void update()
    {
        //
        // Update to display the current status of the machine (ACC, PC, Next Instructions)
        //
    }
}

