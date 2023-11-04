import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JList;

public class ContentsView extends JPanel implements SimpleObserver
{
    private static final int OUR_DEFAULT_WIDTH = 500;
    private static final int OUR_DEFAULT_HEIGHT = 200;

    private JPanel myPanel;
    private ObservableVM252 myModel;
    private String [] myContents;
    private JTextArea contentsContainer;

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

    private JTextArea getContentsContainer()
    {
        return contentsContainer;
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

    private void setContentsContainer(JTextArea other)
    {
        contentsContainer = other;
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
        setModel(initialModel);
        setContents(getModel().getShowContents());

        setPanel(new JPanel());

        setContentsContainer(new JTextArea("Welcome" + "\n", 10, 1));
        getContentsContainer().setBounds(130, 35, OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);
        getContentsContainer().setBackground(new Color(160, 160, 160));
        getContentsContainer().setForeground(Color.WHITE);
        getContentsContainer().setLineWrap(true);
        getContentsContainer().setEditable(false);

        JScrollPane scroll = new JScrollPane(getContentsContainer());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(100, 100, OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        setLayout(null);
        add(scroll);
        //
        // Initially display the model's display contents
        //

    }

    @Override
    public void update()
    {
        setContents(getModel().getShowContents());

        repaint();
    }
}
