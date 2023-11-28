import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MachineViewAndController extends JPanel implements Observer {
    private static final int OUR_DEFAULT_FRAME_WIDTH = 200;
    private static final int OUR_DEFAULT_FRAME_HEIGHT = 200;
    private static final int OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH = 10;

    private JPanel myPanel;
    private VM252Model myModel;
    private JLabel myLabel;
    private JTextField myAccTextField;
    private JTextField myPcTextField;
    private JTextField myNextInstructionTextField;
    private JTextField myInputTextField;


    class AccumulatorView extends VM252View {

        private final MachineViewAndController machineView;

        public AccumulatorView(MachineViewAndController machineView) {
            this.machineView = machineView;
        }

        @Override
        public void updateAccumulator() {
            machineView.getAccTextField().setText("" + machineView.getModel().accumulator());
        }
    }

    class ProgramCounterView extends VM252View {

        private final MachineViewAndController machineView;

        public ProgramCounterView(MachineViewAndController machineView) {
            this.machineView = machineView;
        }

        @Override
        public void updateProgramCounter() {
            machineView.getPcTextField().setText("" + machineView.getModel().programCounter());
        }
    }

    //
    // Accessors
    //

    private JPanel getPanel() {
        return myPanel;
    }

    private VM252Model getModel() {
        return myModel;
    }

    private JLabel getLabel() {
        return myLabel;
    }

    private JTextField getAccTextField() {
        return myAccTextField;
    }

    private JTextField getPcTextField() {
        return myPcTextField;
    }

    private JTextField getInputTextField() {
        return myInputTextField;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other) {
        myPanel = other;
    }

    private void setModel(VM252Model other) {

        if (getModel() != null)
            getModel().detach(this);

        myModel = other;

        if (getModel() != null)
            getModel().attach(this);

    }

    private void setLabel(JLabel other) {
        myLabel = other;
    }

    private void setAccTextField(JTextField other) {
        myAccTextField = other;
    }

    private void setPcTextField(JTextField other) {
        myPcTextField = other;
    }

    private void setInputTextField(JTextField other) {
        myInputTextField = other;
    }

    //
    // Constructors
    //

    public MachineViewAndController(VM252Model initialModel) {
        setSize(OUR_DEFAULT_FRAME_WIDTH, OUR_DEFAULT_FRAME_HEIGHT);
        setModel(initialModel);

        AccumulatorView accumulatorView = new AccumulatorView(this);
        getModel().attach(accumulatorView);

        ProgramCounterView programCounterView = new ProgramCounterView(this);
        getModel().attach(programCounterView);

        JLabel nextinstrLabel = new JLabel("Next Instruction", JLabel.LEFT);
        JTextField nextinstr = new JTextField("Next Instruction", OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH);

        JLabel accLabel = new JLabel("ACC");
        setAccTextField(new JTextField("" + getModel().accumulator()));
        ActionListener setAccumulator = new ActionListener(){
	        public void actionPerformed(ActionEvent accChange){
                try
                {
                    getModel().resetDisplayContents();
                    getModel().setAccumulator(Short.valueOf(getAccTextField().getText()));
                    getModel().setShowContents(new String[] {"Set ACC value to " + getAccTextField().getText()});
                }catch(NumberFormatException err){
                    getModel().setShowContents(new String [] {"Not a valid input. Input for ACC Value must be a number"});
                    getModel().resetDisplayContents();
                }

          }};

        getAccTextField().addActionListener(setAccumulator);

        JLabel counterLabel = new JLabel("PC");
        setPcTextField(new JTextField("" + getModel().programCounter()));
        ActionListener setProgramCounter = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getModel().resetDisplayContents();
                try{
                    if (Short.valueOf(getPcTextField().getText()) >= ((short)8192) || Short.valueOf(getPcTextField().getText()) < ((short) 0))
                    {
                        getModel().setShowContents(new String[] {"No address " + getPcTextField().getText()});
                        getModel().resetDisplayContents();
                        getModel().setProgramCounter(getModel().programCounter());
                    } else
                    {
                        getModel().setProgramCounter(Short.valueOf(getPcTextField().getText()));
                        getModel().setStoppedStatus(VM252Model.StoppedCategory.notStopped);
                        getModel().setShowContents(new String[] {"Set PC value to " + getPcTextField().getText()});
                        getModel().resetDisplayContents();
                        // show next instruction here
                    }
                } catch(NumberFormatException err){
                        getModel().setShowContents(new String [] {"Not a valid input. Input for PC Value must be a number"});
                        getModel().resetDisplayContents();
                    }
            }
        };
        getPcTextField().addActionListener(setProgramCounter);

        JLabel inputLabel = new JLabel("Input");
        setInputTextField(new JTextField("" + getModel().getInputValue()));
        ActionListener setInputValue = new ActionListener(){
	        public void actionPerformed(ActionEvent inputChange){
                getModel().resetDisplayContents();
                try{
                    getModel().setInputValue(Short.valueOf(getInputTextField().getText()));
                    getModel().setInputReady(true);
                }catch(NumberFormatException err){
                    getModel().setShowContents(new String [] {"Bad integer value; try again"});
                    getModel().resetDisplayContents();
                }

          }};
        getInputTextField().addActionListener(setInputValue);

        //
        // Create a panel to display the state of the machine model
        //

        setPanel(new JPanel());
        getPanel().setLayout(new GridLayout(4, 2));

        //
        // Add the panel to the container
        //

        getPanel().add(accLabel);
        getPanel().add(getAccTextField());
        getPanel().add(counterLabel);
        getPanel().add(getPcTextField());
        getPanel().add(nextinstrLabel);
        getPanel().add(nextinstr);
        getPanel().add(inputLabel);
        getPanel().add(getInputTextField());

        add(getPanel());

        setLayout(new GridLayout(1, 1));

    }

    @Override
    public void update() {
        getAccTextField().setText("" + getModel().accumulator());
        getPcTextField().setText("" + getModel().programCounter());
    }
}
