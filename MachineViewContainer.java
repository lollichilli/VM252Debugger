import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MachineViewContainer extends JPanel {
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
    // Ctors
    //

    public MachineViewContainer() {
        this(null);
    }

    public MachineViewContainer(VM252Model initialModel) {

        //
        // Create a container for displaying machine status
        //

        MachineViewAndController statusView = new MachineViewAndController(initialModel);

        //
        // Create a container for displaying machine's memory bytes
        //

        MemoryBytesViewAndController memoryView = new MemoryBytesViewAndController(initialModel);

        setPanel(new JPanel());

        //
        // Add panels to container
        //

        statusView.setBounds(0, 0, 700, 300);
        getPanel().add(statusView);

        memoryView.setBounds(400, 20, 700, 300);
        getPanel().add(memoryView);

        // Set the layout manager of the frame to BorderLayout
        setLayout(new BorderLayout());

        // Add the panel to the frame with a BorderLayout constraint (e.g., WEST for
        // left)
        add(getPanel(), BorderLayout.CENTER);

    }
}
