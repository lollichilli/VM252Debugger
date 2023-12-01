import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.lang.Math;


import vm252architecturespecifications.VM252ArchitectureSpecifications;
import vm252architecturespecifications.VM252ArchitectureSpecifications.Instruction;


public class VM252Stepper
{

    //
    // Private Fields
    //

    private VM252Model myMachineState;

    //
    // Public Accessors
    //

    public VM252Model machineState()
    {

        return myMachineState;

    }

    private byte [ ] fetchMemoryBytes(int memoryAddress, int numberOfBytes)
    {

        byte [ ] memoryBytes = new byte [ Math.max(0, numberOfBytes) ];

        for (int byteNumber = 0;
                numberOfBytes > 0;
                ++ byteNumber, -- numberOfBytes,
                memoryAddress
                = VM252ArchitectureSpecifications.nextMemoryAddress(memoryAddress)
            )

            memoryBytes[ byteNumber ] = machineState().memoryByte(memoryAddress);

        return memoryBytes;

    }

    private int fetchMemoryData(int memoryAddress)
    {

        byte [ ] dataBytes = fetchMemoryBytes(memoryAddress, 2);

        return ((short) (dataBytes[ 0 ] << 8 | dataBytes[ 1 ] & 0xff));

    }

    //
    // Private Mutators
    //

    private void storeMemoryData(int memoryAddress, int data)
    {


        machineState().setMemoryByte(memoryAddress, ((byte) (data >> 8 & 0xff)));
        machineState().setMemoryByte(
                VM252ArchitectureSpecifications.nextMemoryAddress(memoryAddress),
                ((byte) (data & 0xff))
                );

    }

    //
    //  Public Ctor
    //

    public VM252Stepper(VM252Model machineState)
    {

        myMachineState = machineState;
    }

    //
    // Public Operations
    //

    //
    // Public Method void step()
    //
    // Purpose:
    //     Simulates the execution of a single VM252 instruction
    //
    // Formals:
    //     none
    //
    // Pre-conditions:
    //     none
    //
    // Post-conditions:
    //     The simulated accumulator, program counter, and memory contents of
    //     machineState() has been altered to reflect the execution of the instruction
    //     whose encoding resided in simulated memory at the address in the simulated
    //     program counter
    //
    // Returns:
    //     none
    //

    public void step() throws IOException
    {

        if (machineState().stoppedStatus() != VM252Model.StoppedCategory.stopped) {

            Instruction currentInstruction;
            int data;
            boolean suppressProgramCounterIncrement;

            //
            // Let currentInstruction be the the instruction whose encoding resides in
            //     simulated memory at the address in the simulated program counter
            //

            try {

                currentInstruction
                    = new VM252ArchitectureSpecifications.Instruction(
                            fetchMemoryBytes(machineState().programCounter(), 2)
                            );

            }
            catch (IllegalArgumentException exception) {

                currentInstruction
                    = new VM252ArchitectureSpecifications.Instruction(
                            fetchMemoryBytes(machineState().programCounter(), 1)
                            );

            }

            //
            // Simulate the execution of currentInstruction
            //



            suppressProgramCounterIncrement = false;

            // System.out.println(new String [] {"" + currentInstruction.symbolicOpcode() + currentInstruction.numericOperand()});

            // System.out.println(currentInstruction.numericOpcode());
            // System.out.println(currentInstruction.numericOperand());

            switch (currentInstruction.numericOpcode()) {

                case VM252ArchitectureSpecifications.LOAD_OPCODE :
                    machineState().resetDisplayContents();

                    machineState().setAccumulator(
                            fetchMemoryData(currentInstruction.numericOperand())
                            );
                    machineState().setShowContents(new String[] {"Addr " + machineState().programCounter() + ": " + "LOAD " + currentInstruction.numericOperand()});
                    machineState().setNextInstruction("LOAD " + currentInstruction.numericOperand());

                    break;

                case VM252ArchitectureSpecifications.SET_OPCODE :
                    machineState().resetDisplayContents();
                    machineState().setAccumulator(
                            currentInstruction.numericOperand()
                            );
                    machineState().setShowContents(new String [] {"Addr " + machineState().programCounter() + ": " + "SET " + currentInstruction.numericOperand()});
                    machineState().setNextInstruction("SET " + currentInstruction.numericOperand());
                    break;

                case VM252ArchitectureSpecifications.STORE_OPCODE :
                    machineState().resetDisplayContents();
                    storeMemoryData(
                            currentInstruction.numericOperand(),
                            machineState().accumulator()
                            );
                    machineState().setShowContents(new String [] {"Addr " + machineState().programCounter() + ": " + "STORE " + currentInstruction.numericOperand()});
                    machineState().setNextInstruction("STORE " + currentInstruction.numericOperand());
                    break;

                case VM252ArchitectureSpecifications.ADD_OPCODE :
                    machineState().resetDisplayContents();
                    data = fetchMemoryData(currentInstruction.numericOperand());
                    machineState().setAccumulator(
                            machineState().accumulator() + data
                            );
                    machineState().setShowContents(new String [] {"Addr " + machineState().programCounter() + ": " + "ADD " + currentInstruction.numericOperand()});
                    machineState().setNextInstruction("ADD " + currentInstruction.numericOperand());
                    break;

                case VM252ArchitectureSpecifications.SUBTRACT_OPCODE :
                    machineState().resetDisplayContents();
                    data = fetchMemoryData(currentInstruction.numericOperand());
                    machineState().setAccumulator(
                            machineState().accumulator() - data
                            );
                    machineState().setShowContents(new String [] {"Addr " + machineState().programCounter() + ": " + "SUBTRACT " + currentInstruction.numericOperand()});
                    machineState().setNextInstruction("SUBTRACT " + currentInstruction.numericOperand());
                    break;

                case VM252ArchitectureSpecifications.JUMP_OPCODE :
                    machineState().resetDisplayContents();
                    machineState().setProgramCounter(
                            currentInstruction.numericOperand()
                            );
                    suppressProgramCounterIncrement = true;
                    machineState().setShowContents(new String [] {"Addr " + machineState().programCounter() + ": " + "JUMP " + currentInstruction.numericOperand()});
                    machineState().setNextInstruction("JUMP " + currentInstruction.numericOperand());
                    break;

                case VM252ArchitectureSpecifications.JUMP_ON_ZERO_OPCODE :
                    machineState().resetDisplayContents();
                    if (machineState().accumulator() == 0) {
                        machineState().setProgramCounter(
                                currentInstruction.numericOperand()
                                );
                        suppressProgramCounterIncrement = true;
                    }
                    machineState().setShowContents(new String [] {"Addr " + machineState().programCounter() + ": " + "JUMPZ " + currentInstruction.numericOperand()});
                    machineState().setNextInstruction("JUMPZ " + currentInstruction.numericOperand());
                    break;

                case VM252ArchitectureSpecifications.JUMP_ON_POSITIVE_OPCODE :
                    machineState().resetDisplayContents();
                    if (machineState().accumulator() > 0) {
                        machineState().setProgramCounter(
                                currentInstruction.numericOperand()
                                );
                        suppressProgramCounterIncrement = true;
                    }
                    machineState().setShowContents(new String [] {"Addr " + machineState().programCounter() + ": " + "JUMPP " + currentInstruction.numericOperand()});
                    machineState().setNextInstruction("JUMPP " + currentInstruction.numericOperand());
                    break;

                case VM252ArchitectureSpecifications.INPUT_OPCODE :

                    machineState().resetDisplayContents();

                    machineState().setShowContents(new String [] {"Addr " + machineState().programCounter() + ": " + "Running INPUT"});

                    while (!machineState().getInputReady())
                        machineState().resetDisplayContents();

                    machineState().setAccumulator(machineState().getInputValue());

                    machineState().setShowContents(new String[] {"Addr " + machineState().programCounter() + ": " + "Set Input value to " + machineState().getInputValue()});
                    machineState().setNextInstruction("INPUT ");

                    machineState().setInputReady(false);

                    break;

                case VM252ArchitectureSpecifications.OUTPUT_OPCODE :
                    String [] output = {"Addr " + machineState().programCounter() + ": " + "OUTPUT: " + machineState().accumulator()};
                    machineState().setShowContents(output);
                    machineState().setNextInstruction("OUTPUT");
                    break;

                case VM252ArchitectureSpecifications.NO_OP_OPCODE :
                    break;

                case VM252ArchitectureSpecifications.STOP_OPCODE :
                    machineState().setStoppedStatus(
                            VM252Model.StoppedCategory.stopped
                            );
                    suppressProgramCounterIncrement = true;
                    machineState().setShowContents(new String [] {"Addr " + machineState().programCounter() + ": " + "Program Stops"});
                    machineState().setNextInstruction("STOP");
                    break;

            }

            if (! suppressProgramCounterIncrement)
                machineState().setProgramCounter(
                        VM252ArchitectureSpecifications.nextMemoryAddress(
                            machineState().programCounter(),
                            currentInstruction.instructionBytes().length
                            )
                        );

        }

    }

}
