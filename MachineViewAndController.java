import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MachineViewAndController extends JPanel implements SimpleObserver {
    private static final int OUR_DEFAULT_FRAME_WIDTH = 200;
    private static final int OUR_DEFAULT_FRAME_HEIGHT = 200;
    private static final int OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH = 10;

    private JPanel myPanel;
    private ObservableVM252 myModel;
    private JLabel myLabel;

    //
    // Accessors
    //

    private JPanel getPanel() {
        return myPanel;
    }

    private ObservableVM252 getModel() {
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

    private void setModel(ObservableVM252 other) {

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

    public MachineViewAndController() {
        this(null);
    }

    public MachineViewAndController(ObservableVM252 initialModel) {

        setSize(OUR_DEFAULT_FRAME_WIDTH, OUR_DEFAULT_FRAME_HEIGHT);
        setModel(initialModel);

        JLabel accLabel = new JLabel("ACC", JLabel.LEFT);
        JLabel progcountLabel = new JLabel("Program Counter", JLabel.LEFT);
        JLabel nextinstrLabel = new JLabel("Next Instruction", JLabel.LEFT);
        JLabel inputLabel = new JLabel("Input", JLabel.LEFT);

        JTextField acc = new JTextField("ACC", OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH);
        JTextField progcount = new JTextField("Program Counter", OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH);
        JTextField nextinstr = new JTextField("Next Instruction", OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH);
        JTextField input = new JTextField("Input", OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH);

        //
        // Create a panel to display the state of the machine model
        //

        setPanel(new JPanel());
        getPanel().setLayout(new GridLayout(4, 2));

        //
        // Add the panel to the container
        //

        getPanel().add(accLabel);
        getPanel().add(acc);
        getPanel().add(progcountLabel);
        getPanel().add(progcount);
        getPanel().add(nextinstrLabel);
        getPanel().add(nextinstr);
        getPanel().add(inputLabel);
        getPanel().add(input);

        add(getPanel());

        setLayout(new GridLayout(1, 1));

    }

    @Override
    public void update() {
        //
        // Update to display the current status of the machine (ACC, PC, Next
        // Instructions)
        //
    }
}
