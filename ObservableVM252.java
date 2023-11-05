class ObservableVM252 extends SimpleObservable
{
    private short myACCValue;
    private short myPCValue;
    private byte [] myMemoryValue;
    private short myInputValue;
    private short myBreakPoint;
    private String myNextInstruction;
    private boolean myHaltStatus;
    private boolean myPauseStatus;
    private boolean isInputReady = false;
    private int myExecutingSpeed;
    private String [] myContents;

    //
    // Public Accessor
    //

    public short getACCValue()
    {
        return myACCValue;
    }

    public short getPCValue()
    {
        return myPCValue;
    }

    public byte [] getMemoryValue()
    {
        return myMemoryValue;
    }

    public short getInputValue()
    {
        return myInputValue;
    }

    public String getNextInstruction()
    {
        return myNextInstruction;
    }

    public boolean getHaltStatus()
    {
        return myHaltStatus;
    }

    public short getBreakPoint()
    {
        return myBreakPoint;
    }

    public boolean getPauseStatus()
    {
        return myPauseStatus;
    }

    public boolean getInputStatus()
    {
        return isInputReady;
    }

    public int getExecutingSpeed()
    {
        return myExecutingSpeed;
    }

    public String [] getShowContents()
    {
        return myContents;
    }

    //
    // Public Mutators
    //

    public void setACCValue(short other)
    {
        myACCValue = other;
        announceChange();
    }

    public void setPCValue(short other)
    {
        myPCValue = other;
        announceChange();
    }

    public void setMemoryValue(byte [] other)
    {
        myMemoryValue = other;
        announceChange();
    }

    public void setInputValue(short other)
    {
        myInputValue = other;
        announceChange();
    }

    public void setNextInstruction(String other)
    {
        myNextInstruction = other;
        announceChange();
    }

    public void setHaultStatus(boolean other)
    {
        myHaltStatus = other;
    }

    public void setBreakPoint(short other)
    {
        myBreakPoint = other;
    }

    public void setPauseStatus(boolean other)
    {
        myPauseStatus = other;
    }

    public void setInputStatus(boolean other)
    {
        isInputReady = other;
    }

    public void setExecutingSpeed(int other)
    {
        myExecutingSpeed = other;
    }

    public void setShowContents(String [] other)
    {
        myContents = other;
        announceChange();
    }

    public void resetContents()
    {
        String[] contents = {""};
        myContents = contents;
    }

    //
    // Constructor
    //

    ObservableVM252()
    {
        super();

        String [] welcomeContents = {"Welcome to VM252Debuger"};

        setACCValue((short)0);
        setPCValue((short)0);
        setMemoryValue(new byte [8192]);
        setNextInstruction("");
        setShowContents(welcomeContents);
    }

    ObservableVM252(short initialACCValue, short initialPCValue, byte [] initialMemoryValue, String initialNextInstruction, String [] initialContents)
    {

        setACCValue(initialACCValue);
        setPCValue(initialPCValue);
        setMemoryValue(initialMemoryValue);
        setNextInstruction(initialNextInstruction);
        setShowContents(initialContents);
    }
}
