import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main
{
    public static void main(String [] commandLineArguments)
    {
        EventQueue.invokeLater(
            () ->
                {


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

            );

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

        getPanel().setBackground(new Color(173, 173, 215));

        add(getPanel());
    }
}
