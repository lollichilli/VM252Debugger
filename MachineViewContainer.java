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

        // Set preferred sizes for the components (adjust these as needed)
        statusView.setPreferredSize(new Dimension(400, 200));
        memoryView.setPreferredSize(new Dimension(400, 200));

        // Add components to the container
        add(statusView, BorderLayout.NORTH); // Place statusView at the top
        add(memoryView, BorderLayout.CENTER); // Place memoryView in the center

    }
}
