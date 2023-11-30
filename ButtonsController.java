import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonsController extends JPanel {

    private JPanel myPanel;
    private VM252Model myModel;
    private JButton runButton, helpButton, pauseButton, resumeButton, increaseButton, decreaseButton ;

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

        JTextField textFieldba = new JTextField("", 20);
        Dimension textFieldSize = new Dimension(300, 30);
        textFieldba.setPreferredSize(textFieldSize);
        textFieldba.setMaximumSize(textFieldSize);
        textFieldba.setMinimumSize(textFieldSize);

        //
        // Create buttons
        //

        JButton quitButton = new JButton(quitIcon);
        runButton = new JButton(runIcon);
        helpButton = new JButton(helpIcon);
        pauseButton = new JButton("Pause");
        resumeButton = new JButton("Resume");
        increaseButton = new JButton("Increase");
        decreaseButton = new JButton("Decrease");

        runButton.setPreferredSize(new Dimension(32, 32));
        helpButton.setPreferredSize(new Dimension(32, 32));
        quitButton.setPreferredSize(new Dimension(32, 32));

        // Set layout manager to FlowLayout with CENTER alignment
        setLayout(new FlowLayout(FlowLayout.CENTER));

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

        toolbar.add(runButton);
        toolbar.addSeparator();
        toolbar.add(baJLabel);
        toolbar.add(textFieldba);
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

        increaseButton.addActionListener(new ChangeSpeedListener());
        decreaseButton.addActionListener(new ChangeSpeedListener());
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

        RunButtonActionListener runListener = new RunButtonActionListener();
        runButton.addActionListener(runListener);


        // Make the JToolBar so that it can't be dragged
        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        // Set the preferred size of the JToolBar
        toolbar.setPreferredSize(new Dimension(700, 40));

        // Add the JToolBar to the panel
        getPanel().add(toolbar, BorderLayout.NORTH);

        add(getPanel());

    }

    private class ChangeRunningStatus implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == pauseButton) {
                getModel().setPauseStatus(true);
            } else if (event.getSource() == resumeButton) {
                getModel().setPauseStatus(false);
            }
        }
    }

    private class RunButtonActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            ExecutionThread runThread = new ExecutionThread();
            runThread.start();
        }
    }

    private class ExecutionThread extends Thread{
        @Override
        public void run()
        {
            boolean hitBreakPoint = false;

            while(getModel().stoppedStatus() != VM252Model.StoppedCategory.stopped && !hitBreakPoint)
            {
                if (getModel().getPauseStatus())
                    ; // do nothing
                else if (getModel().getBreakPoint() == getModel().programCounter())
                {
                    getModel().runProgram();
                    getModel().setShowContents(new String [] {"Hit breakpoint at address " + getModel().getBreakPoint() });
                    hitBreakPoint = true;
                } else
                    getModel().runProgram();

                try
                {
                    Thread.sleep(getModel().getExecutionSpeed());
                } catch(Exception e){}
            }
        }
    }

    private class quitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Close current JFrame
            ((JFrame)myPanel.getTopLevelAncestor()).dispose();

            // Create a new FileChooser
            ObjectFileChooser newFile = new ObjectFileChooser();

            newFile.ObjectFileChooser();
        }
    }

    private class ChangeSpeedListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == increaseButton)
            {
                int currentExecutionSpeed = getModel().getExecutionSpeed();
                getModel().setExecutionSpeed(currentExecutionSpeed < 0 ? 0 : (currentExecutionSpeed - 500));
            }else if (event.getSource() == decreaseButton)
            {
                int currentExecutionSpeed = getModel().getExecutionSpeed();
                getModel().setExecutionSpeed(currentExecutionSpeed + 500);
            }
        }
    }

}
