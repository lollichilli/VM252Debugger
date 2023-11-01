import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JList;

public class ContentsView extends JPanel implements SimpleObserver
{
    private static final int OUR_DEFAULT_WIDTH = 600;
    private static final int OUR_DEFAULT_HEIGHT = 300;

    private JPanel myPanel;
    private ObservableVM252 myModel;
    private String [] myContents;

    //
    // Accessors
    //

    private JPanel getPanel()
    {
        return myPanel;
    }

    private ObservableVM252 getModel()
    {
        return myModel;
    }

    private String [] getContents()
    {
        return myContents;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {
        myPanel = other;
    }

    private void setContents(String [] other)
    {
        myContents = other;
    }

    private void setModel(ObservableVM252 other)
    {
        if (getModel() != null)
            getModel().detach(this);

        myModel = other;

        if (getModel() != null)
            getModel().attach(this);

    }

    //
    // Constructors
    //

    public ContentsView()
    {
        this(null);
    }

    public ContentsView(ObservableVM252 initialModel)
    {
        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        setPanel(new JPanel());
        getPanel().setBackground(new Color(255, 255, 0));


        //
        // Initially display the model's display contents
        //

        setModel(initialModel);
        setContents(getModel().getShowContents());

    }

    @Override
    public void update()
    {
        setContents(getModel().getShowContents());

        repaint();
    }
}
