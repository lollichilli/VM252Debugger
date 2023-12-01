import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import vm252utilities.VM252Utilities;

public class MainController {

    //
    // Private Instance Fields
    //

    private VM252Model myMachineState;
    private VM252Stepper myMachineStepper;

    //
    // Public Accessors
    //

    public VM252Model machineState() {

        return myMachineState;

    }

    public VM252Stepper machineStepper() {

        return myMachineStepper;

    }

    //
    // Private Mutators
    //

    private void setMachineState(VM252Model machineState) {

        myMachineState = machineState;

    }

    private void setMachineStepper(VM252Stepper machineStepper) {

        myMachineStepper = machineStepper;

    }

    //
    // Public Ctors
    //

    public MainController(VM252Model simulatedMachine) {

        setMachineState(simulatedMachine);

    }

}
