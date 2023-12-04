import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ContentsView extends JPanel implements Observer {
    private static final int OUR_DEFAULT_WIDTH = 1920;
    private static final int OUR_DEFAULT_HEIGHT = 1080;

    private JPanel myPanel;
    private VM252Model myModel;
    private String[] myContents;
    private JTextArea contentsContainer;

    //
    // Accessors
    //

    private JPanel getPanel() {
        return myPanel;
    }

    private VM252Model getModel() {
        return myModel;
    }

    private String[] getContents() {
        return myContents;
    }

    private JTextArea getContentsContainer() {
        return contentsContainer;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other) {
        myPanel = other;
    }

    private void setContents(String[] other) {
        myContents = other;
    }

    private void setModel(VM252Model other) {
        if (getModel() != null)
            getModel().detach(this);

        myModel = other;

        if (getModel() != null)
            getModel().attach(this);

    }

    private void setContentsContainer(JTextArea other) {
        contentsContainer = other;
    }

    //
    // Constructors
    //

    public ContentsView() {
        this(null);
    }

    public ContentsView(VM252Model initialModel) {
        setModel(initialModel);
        setContents(getModel().getShowContents());

        setPanel(new JPanel());

        setContentsContainer(new JTextArea("Welcome" + "\n", 20, 20));
        getContentsContainer().setBounds(100, 100, OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);
        getContentsContainer().setBackground(new Color(0, 0, 0));
        getContentsContainer().setForeground(Color.GREEN);
        getContentsContainer().setLineWrap(true);
        getContentsContainer().setEditable(false);
        contentsContainer.setPreferredSize(new Dimension(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT));

        // setting the font size
        int fontSize = 18;
        contentsContainer.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));

        // Set the margin to increase space between text and the edge
        contentsContainer.setMargin(new Insets(50, 50, 50, 50)); // Cha

        JScrollPane scroll = new JScrollPane(getContentsContainer());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(50, 50, OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);

        //
        // Initially display the model's display contents
        //

    }

    @Override
    public void update() {

        if (!(getModel().getShowContents().length == 1 && getModel().getShowContents()[0] == "")) {
            String displayString = "";

            for (String content : getModel().getShowContents()) {
                displayString = displayString + content + "\n";
            }

            getContentsContainer().append(displayString);
            getContentsContainer().setCaretPosition(getContentsContainer().getDocument().getLength());

        }
    }

    public void setMargin(Insets insets) {
        getContentsContainer().setMargin(insets);
    }
}
