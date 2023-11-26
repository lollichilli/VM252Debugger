import vm252architecturespecifications.VM252ArchitectureSpecifications;
import vm252architecturespecifications.VM252ArchitectureSpecifications.Instruction;
import java.io.IOException;

public class VM252Model extends SimpleObservable implements ObservableVM252
{

    public enum StoppedCategory {
        notStopped,
        stopped
    };

    private int myAccumulator;
    private int myProgramCounter;
    private final byte [ ] myMemory
        = new byte [ VM252ArchitectureSpecifications.MEMORY_SIZE_IN_BYTES ];
    private StoppedCategory myStoppedStatus;
    private short myInput;
    private short myBreakPoint;
    private boolean inputReady = false;
    private int myExecutionSpeed;
    private boolean myPauseStatus;
    private String [] myContents;
    private VM252Stepper stepper;

    //
    // Public Accessors
    //

    public int accumulator()
    {

        return myAccumulator;

    }

    public int programCounter()
    {

        return myProgramCounter;

    }

    public byte memoryByte(int address) throws IllegalArgumentException
    {

        if (address < 0
                || VM252ArchitectureSpecifications.MEMORY_SIZE_IN_BYTES <= address)

            throw
                new IllegalArgumentException(
                        "Attempt to getch memory byte from illegal memory address " + address
                        );

        else

            return myMemory[ address ];

    }

    public StoppedCategory stoppedStatus()
    {

        return myStoppedStatus;

    }

    public short getInputValue()
    {
        return myInput;
    }

    public boolean getInputReady()
    {
        return inputReady;
    }

    public short getBreakPoint()
    {
        return myBreakPoint;
    }

    public int getExecutionSpeed()
    {
        return myExecutionSpeed;
    }

    public boolean getPauseStatus()
    {
        return myPauseStatus;
    }

    public String [] getShowContents()
    {
        return myContents;
    }

    //
    // Public Mutators
    //

    public void setAccumulator(int other)
    {

        myAccumulator = ((short) other);

        announceAccumulatorChange();

    }

    public void setProgramCounter(int other) throws IllegalArgumentException
    {

        if (other < 0 || VM252ArchitectureSpecifications.MEMORY_SIZE_IN_BYTES <= other)

            throw
                new IllegalArgumentException(
                        "Attempt to set program counter to illegal memory address " + other
                        );

        else {

            myProgramCounter = other;

            announceProgramCounterChange();

        };

    }

    public void setMemoryByte(int address, byte other) throws IllegalArgumentException
    {

        if (address < 0
                || VM252ArchitectureSpecifications.MEMORY_SIZE_IN_BYTES <= address)

            throw
                new IllegalArgumentException(
                        "Attempt to set memory byte at illegal memory address " + address
                        );

        else {

            myMemory[ address ] = other;

            announceMemoryChange(address);

        }

    }

    public void setStoppedStatus(StoppedCategory other)
    {

        myStoppedStatus = other;

        announceStoppedStatusChange();

    }

    public void setInputValue(short other)
    {
        myInput = other;
        announceChange();
    }

    public void setInputReady(boolean other)
    {
        inputReady = other;
    }

    public void setBreakPoint(short other)
    {
        myBreakPoint = other;
    }

    public void setExecutionSpeed(int other)
    {
        myExecutionSpeed = other;
    }

    public void setPauseStatus(boolean other)
    {
        myPauseStatus = other;
    }

    public void setShowContents(String [] other)
    {
        myContents = other;
        announceChange();
    }

    public void resetDisplayContents()
    {
        String [] contents = {""};
        myContents = contents;
    }

    //
    // Public Ctors

    public VM252Model(byte[] objectCode)
    {
        String [] welcomeContents = {"Welcome to VM252Debuger"};

        for (int address = 0; address < objectCode.length; ++ address)
        {
            setMemoryByte(address, objectCode[ address ]);
        }
        setAccumulator(0);
        setProgramCounter(0);
        setShowContents(welcomeContents);
        setExecutionSpeed(500);
        setBreakPoint((short)8192);
        setPauseStatus(false);
        setStoppedStatus(StoppedCategory.notStopped);
        stepper = new VM252Stepper(this);
    }

    public void runProgram()
    {
        try {
            stepper.step();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    // Public Implementations of Observable interface methods
    //

    @Override
    public void announceAccumulatorChange()
    {

        for (Observer currentObserver : observers())

            if (currentObserver instanceof VM252Observer)
                ((VM252Observer) currentObserver).updateAccumulator();

    }

    @Override
    public void announceProgramCounterChange()
    {

        for (Observer currentObserver : observers())

            if (currentObserver instanceof VM252Observer)
                ((VM252Observer) currentObserver).updateProgramCounter();

    }

    @Override
    public void announceMemoryChange(int changeAddress)
    {

        for (Observer currentObserver : observers())

            if (currentObserver instanceof VM252Observer)
                ((VM252Observer) currentObserver).updateMemory(changeAddress);

    }

    @Override
    public void announceStoppedStatusChange()
    {

        for (Observer currentObserver : observers())

            if (currentObserver instanceof VM252Observer)
                ((VM252Observer) currentObserver).updateStoppedStatus();

    }

}

