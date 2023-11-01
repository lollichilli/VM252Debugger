class ObservableVM252 extends SimpleObservable
{
    private short myACCValue;
    private short myPCValue;
    private byte [] myMemoryValue;
    private String myNextInstruction;
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

    public String getNextInstruction()
    {
        return myNextInstruction;
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

    public void setNextInstruction(String other)
    {
        myNextInstruction = other;
        announceChange();
    }

    public void setShowContents(String [] other)
    {
        myContents = other;
        announceChange();
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
