import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MachineViewContainer extends JPanel
{
    private static final int OUR_DEFAULT_WIDTH = 600;
    private static final int OUR_DEFAULT_HEIGHT = 300;


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
    // Ctors
    //

    public MachineViewContainer()
    {
        this(null);
    }

    public MachineViewContainer(ObservableVM252 initialModel)
    {

        //
        // Create a container for displaying machine status
        //

        MachineViewAndController statusView = new MachineViewAndController(initialModel);

        //
        // Create a container for displaying machine's memory bytes
        //

        MemoryBytesViewAndController memoryView = new MemoryBytesViewAndController(initialModel);

        setPanel(new JPanel());
        getPanel().setBackground(new Color(255, 255, 0));

        //
        // add panels to container
        //

        getPanel().add(statusView);
        getPanel().add(memoryView);

        add(getPanel());

    }
}
