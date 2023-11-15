import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonsController extends JPanel {

    private JPanel myPanel;
    private VM252Model myModel;
    private JButton quitButton, runButton, helpButton, pauseButton, resumeButton, increaseButton, decreaseButton ;

    private JPanel getPanel() {
        return myPanel;
    }

    private VM252Model getModel() {
        return myModel;
    }

    private void setPanel(JPanel other) {
        myPanel = other;
    }

    private void setModel(VM252Model other) {
        myModel = other;
    }

    //
    // Constructors
    //

    public ButtonsController() {
        this(null);
    }

    public ButtonsController(VM252Model initialModel) {
        setModel(initialModel);

        // Create icons with an ImageObserver
        ImageIcon runIcon = new ImageIcon(
                new ImageIcon("Icons/Run.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        ImageIcon helpIcon = new ImageIcon(
                new ImageIcon("Icons/Help.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        ImageIcon quitIcon = new ImageIcon(
                new ImageIcon("Icons/Quit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));



        //
        // Create labels
        //

        JTextField textFieldba = new JTextField("",10);

        //
        // Create buttons
        //

        JButton quitButton = new JButton(quitIcon);
        JButton runButton = new JButton(runIcon);
        JButton helpButton = new JButton(helpIcon);
        JButton pauseButton = new JButton("Pause");
        JButton resumeButton = new JButton("Resume");
        JButton increaseButton = new JButton("Increase");
        JButton decreaseButton = new JButton("Decrease");

        runButton.setPreferredSize(new Dimension(32, 32));
        helpButton.setPreferredSize(new Dimension(32, 32));
        quitButton.setPreferredSize(new Dimension(32, 32));

        //
        // Create labels
        //

        JLabel baJLabel = new JLabel("ba:");
        

        //
        // Set the preferred size of the icon buttons to match the button size
        //
        setPanel(new JPanel());
        getPanel().setLayout(new GridLayout(1, 7, 10, 0));

        //
        // Create a JToolBar with horizontal orientation
        //
        JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);

        //
        // Add buttons to the JToolBar with separators for spacing
        //

        toolbar.add(baJLabel);
        toolbar.add(textFieldba);
        toolbar.add(runButton);
        toolbar.addSeparator();
        toolbar.add(pauseButton);
        toolbar.addSeparator();
        toolbar.add(resumeButton);
        toolbar.addSeparator();
        toolbar.add(increaseButton);
        toolbar.addSeparator();
        toolbar.add(decreaseButton);
        toolbar.addSeparator();
        toolbar.add(quitButton);
        toolbar.addSeparator();
        toolbar.add(helpButton);


        //
        // listeners
        //

        pauseButton.addActionListener(new ChangeRunningStatus());
        resumeButton.addActionListener(new ChangeRunningStatus());
        quitButton.addActionListener(new quitListener());

        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] helpContents = { "ba MA = Set a breakpoint at address MA",
                        "help = Print this help message",
                        "n = Execute next machine instruction",
                        "q = Quit",
                        "r = Run machine until error occurs or stop instruction is executed"
                };
                getModel().setShowContents(helpContents);
            }
        });

        // Make the JToolBar so that it can't be dragged
        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        // Set the preferred size of the JToolBar
        toolbar.setPreferredSize(new Dimension(450, 40));

        // Add the JToolBar to the panel
        getPanel().add(toolbar, BorderLayout.NORTH);

        add(getPanel());


    }

	private class ChangeRunningStatus implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == pauseButton)
                ButtonsController.this.getModel().setStoppedStatus(VM252Model.StoppedCategory.stopped);
            else if (event.getSource() == resumeButton)
                ButtonsController.this.getModel().setStoppedStatus(VM252Model.StoppedCategory.notStopped);
            else
                ;
        }
    }

    private class quitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            System.exit(0); // Exit the program
        }
    }

    
}
