import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import vm252utilities.VM252Utilities;
import javax.swing.filechooser.FileNameExtensionFilter;

// class StopAnnouncer extends VM252View {

//     private final VM252Model myModel;

//     public StopAnnouncer(VM252Model model) {
//         myModel = model;
//     }

//     @Override
//     public void updateStoppedStatus() {
//         System.out.printf(
//                 "machine stops with accumulator %d and program counter %d\n",
//                 myModel.accumulator(),
//                 myModel.programCounter());
//     }

// }

public class Main {
    public static void main(String[] commandLineArguments) throws IOException {
        EventQueue.invokeLater(
                () -> {
                    //
                    // Set file choosing option
                    //

                    ObjectFileChooser File = new ObjectFileChooser();
                    File.ObjectFileChooser();

                }

        );

    }
}

class ObjectFileChooser extends JFileChooser {
    public void ObjectFileChooser() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Opcode Files", "vm252obj");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File("./ObjectFilesForTesting"));

        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            String objectFile = fileChooser.getSelectedFile().getPath();
            byte[] objectCode = VM252Utilities.readObjectCodeFromObjectFile(objectFile);

            //
            // Create program frame
            //

            ProgramFrame frame = new ProgramFrame(objectCode);

            //
            // Set frame's visibility and closing behavior
            //

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }

        else {
            System.exit(0);
        }
    }
}

class ProgramFrame extends JFrame {

    private static final int OUR_DEFAULT_WIDTH = 1920;
    private static final int OUR_DEFAULT_HEIGHT = 1080;
    private static final int MINIMUM_WIDTH = 1500;
    private static final int MINIMUM_HEIGHT = 700;

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

    public ProgramFrame(byte[] objectCode) {
        setTitle("VM252 Debugger");
        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        VM252Model simulatedMachine = new VM252Model(objectCode);

        ButtonsContainer buttonsContainer = new ButtonsContainer(simulatedMachine);
        MachineViewContainer machineViewContainer = new MachineViewContainer(simulatedMachine);
        ContentsView contentsView = new ContentsView(simulatedMachine);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        setLayout(new GridBagLayout());
        GridBagConstraints layoutConstraints = new GridBagConstraints();

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.weightx = 1.0;
        layoutConstraints.weighty = 0.1;
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(buttonsContainer, layoutConstraints);

        layoutConstraints.gridy = 1;
        layoutConstraints.weighty = 0.6;
        layoutConstraints.fill = GridBagConstraints.BOTH;
        layoutConstraints.ipady = 50;
        layoutConstraints.insets = new Insets(25, 50, 2, 50);
        mainPanel.add(machineViewContainer, layoutConstraints);

        layoutConstraints.gridy = 2;
        layoutConstraints.weighty = 0.3;
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.ipady = 50;
        layoutConstraints.insets = new Insets(5, 300, 70, 300);
        mainPanel.add(contentsView, layoutConstraints);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true); // Allow resizing of MainPanel

        // Set the minimum size for the frame
        setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));

        // Add a component listener to handle resizing
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // Handle resizing here
                // Example: Adjusting sizes of components dynamically
                Component component = (Component) e.getSource();
                int width = component.getWidth();
                int height = component.getHeight();

                // Example: Adjusting the size of the mainPanel when the frame resizes
                mainPanel.setPreferredSize(new Dimension(width, height));
                revalidate(); // Revalidate the layout
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
    }
}