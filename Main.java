import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import vm252utilities.VM252Utilities;
import javax.swing.filechooser.FileNameExtensionFilter;

class AccumulatorPrinter extends VM252View
{
    
    private final VM252Model myModel;
    
    public AccumulatorPrinter(VM252Model model)
    {       
        myModel = model;       
        }
    
    @Override
    public void updateAccumulator()
    {       
        System.out.println("accumulator is now " + myModel.accumulator());        
        }
    
    }


class ProgramCounterPrinter extends VM252View
{
    
    private final VM252Model myModel;
    
    public ProgramCounterPrinter(VM252Model model)
    {        
        myModel = model;        
        }
    
    @Override
    public void updateProgramCounter()
    {        
        System.out.println("program counter is now " + myModel.programCounter());        
        }
    
    }


class MemoryBytePrinter extends VM252View
{
    
    private final VM252Model myModel;
    
    public MemoryBytePrinter(VM252Model model)
    {        
        myModel = model;        
        }
    
    @Override
    public void updateMemory(int address)
    {        
        System.out.printf("memory byte at address %d is now %02x\n", address, myModel.memoryByte(address));        
        }
    
    }


class StopAnnouncer extends VM252View
{
    
    private final VM252Model myModel;
    
    public StopAnnouncer(VM252Model model)
    {        
        myModel = model;        
        }
    
    @Override
    public void updateStoppedStatus()
    {        
        System.out.printf(
            "machine stops with accumulator %d and program counter %d\n",
                myModel.accumulator(),
                myModel.programCounter()
            );        
        }
    
    }






public class Main
{
    public static void main(String [] commandLineArguments) throws IOException
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
            String objectFile = fileChooser.getSelectedFile().getPath();
            byte [] objectCode = VM252Utilities.readObjectCodeFromObjectFile(objectFile);

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

    public ProgramFrame(byte [] objectCode)
    {
        setTitle("VM252 Debugger");
        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        VM252Model simulatedMachine = new VM252Model();
        
        simulatedMachine.attach(new AccumulatorPrinter(simulatedMachine));
        simulatedMachine.attach(new ProgramCounterPrinter(simulatedMachine));
        // simulatedMachine.attach(new MemoryBytePrinter(simulatedMachine));
        simulatedMachine.attach(new StopAnnouncer(simulatedMachine));

        MainController simulator = new MainController(simulatedMachine);

        // // String objectFileName;

        // // System.out.println("Enter the name of a VM252 object file to run:");
        Scanner inputStream = new Scanner(System.in);
        // // objectFile = inputStream.next();


        ButtonsContainer buttonsContainer = new ButtonsContainer(simulatedMachine);
        MachineViewContainer machineViewContainer = new MachineViewContainer(simulatedMachine);
        ContentsView contentsView = new ContentsView(simulatedMachine);

        setPanel(new JPanel());

        getPanel().setLayout(null);

        buttonsContainer.setBounds(0, 0, 800, 100);
        getPanel().add(buttonsContainer);

        machineViewContainer.setBounds(0, 100, 500, 300);
        getPanel().add(machineViewContainer);

        contentsView.setBounds(0, 400, 400, 250);
        getPanel().add(contentsView);

        add(getPanel());

        try {
            simulator.loadAndRun(objectCode, inputStream, System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}


// public class Main
// {

//     public static void main(String [ ] commandLineArguments) throws IOException
//     {

//         Scanner inputStream = new Scanner(System.in);
        
//         VM252Model simulatedMachine = new VM252Model();
        
//         simulatedMachine.attach(new AccumulatorPrinter(simulatedMachine));
//         simulatedMachine.attach(new ProgramCounterPrinter(simulatedMachine));
//         simulatedMachine.attach(new MemoryBytePrinter(simulatedMachine));
//         simulatedMachine.attach(new StopAnnouncer(simulatedMachine));

//         MainController simulator = new MainController(simulatedMachine);

//         String objectFileName;

//         System.out.println("Enter the name of a VM252 object file to run:");
//         objectFileName = inputStream.next();

//          simulator.loadAndRun(objectFileName, inputStream, System.out);

//         }

//     }