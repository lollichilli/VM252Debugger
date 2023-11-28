import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import vm252utilities.VM252Utilities;
import javax.swing.filechooser.FileNameExtensionFilter;


// class MemoryBytePrinter extends VM252View {

//     private final VM252Model myModel;

//     public MemoryBytePrinter(VM252Model model) {
//         myModel = model;
//     }

//     @Override
//     public void updateMemory(int address) {
//         System.out.printf("memory byte at address %d is now %02x\n", address, myModel.memoryByte(address));
//     }

// }

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

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonsContainer, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.6;
        gbc.ipadx = 200;
        add(machineViewContainer, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.3;
        add(contentsView, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

}

