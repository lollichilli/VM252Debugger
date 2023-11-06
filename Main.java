import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import vm252utilities.VM252Utilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main
{
    public static void main(String [] commandLineArguments)
    {
        EventQueue.invokeLater(
            () ->
                {
                    //
                    // Set file choosing option
                    //

                    ObjectFileChooser File = new ObjectFileChooser();
                    File.ObjectFileChooser();

                    }

            );

    }
}

class ObjectFileChooser extends JFileChooser
{
    public void ObjectFileChooser()
    {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Opcode Files", "vm252obj");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File("./ObjectFilesForTesting"));

        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION)
        {
            String file = fileChooser.getSelectedFile().getPath();
            byte [] program = VM252Utilities.readObjectCodeFromObjectFile(file);

            //
            // Create program frame
            //

            ProgramFrame frame = new ProgramFrame();

            //
            // Set frame's visibility and closing behavior
            //

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }

        else
        {
            System.exit(0);
        }
    }
}

class ProgramFrame extends JFrame
{

    private static final int OUR_DEFAULT_WIDTH = 800;
    private static final int OUR_DEFAULT_HEIGHT = 900;

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

    public ProgramFrame()
    {
        setTitle("VM252 Debugger");
        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        ObservableVM252 model = new ObservableVM252();
        ButtonsContainer buttonsContainer = new ButtonsContainer(model);
        MachineViewContainer machineViewContainer = new MachineViewContainer(model);
        ContentsView contentsView = new ContentsView(model);

        setPanel(new JPanel());

        getPanel().setLayout(null);

        buttonsContainer.setBounds(0, 0, 800, 200);
        getPanel().add(buttonsContainer);

        machineViewContainer.setBounds(0, 400, 800, 300);
        getPanel().add(machineViewContainer);

        contentsView.setBounds(0, 200, 800, 400);
        getPanel().add(contentsView);

        add(getPanel());
    }
}
