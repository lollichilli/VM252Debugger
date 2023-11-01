import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MemoryBytesViewAndController extends JPanel implements SimpleObserver
{

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

    public MemoryBytesViewAndController()
    {
        this(null);
    }

    public MemoryBytesViewAndController(ObservableVM252 initialModel)
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
        // Update to display all the memory bytes of the machine's memory
        //
    }
}
