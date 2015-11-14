package com.guidepointsecurity;

import com.guidepointsecurity.commandmodule.CommandModule;
import com.guidepointsecurity.utils.Utils;
import org.apache.commons.cli.*;

import java.util.Random;
import java.util.Scanner;


public class Main {

    /**
     * CommandLine strings
     */
    public static final String display = " Display help for GuidanceSoftware";
    public static final String commandModuleInfo = "CommandModule";
    public static final String loading = " Loading GuidanceSoftware ....";
    public static final String ready = " GuidanceSoftware ready ....";
    public static final String welcome = " GuidanceSoftware - Navigational System";
    public static final String command = " Loading CommandModule ....";
    public static final String commandError = " Unrecognized option!";
    public static final String directionalCoordinates = " Enter directional coordinates";
    public static final String coordinatesReceived = " Coordinates received ....";
    public static final String navigationCalculation = " Calculating new Navigation operation";
    public static final String updatedNavigation = " Navigation successfully updated!";

    public static void main(String[] args) {

        /** Local objects and variables
         * **/
        Utils utils = new Utils();
        String commandTag = "["+utils.currentDateTime()+"]";

        /** Build CommandLine Handler
         * **/
        Options options = new Options();
        options.addOption("h", false, display);
        options.addOption("c", false, commandModuleInfo);
        CommandLineParser commandLineParser = new DefaultParser();
        /**
         * Intro message
         */
        System.out.println("\n" + "###################################################################");
        System.out.println("#  We've stolen classified SpaceX navigational software ...       #");
        System.out.println("#  and plan to hijack the rockets used for the Mars missions ...  #");
        System.out.println("#  ... can you help us find any vulns?                            #");
        System.out.println("###################################################################" + "\n");
        System.out.println(commandTag + loading);

        for (int i = 0 ; i < 100; i++) {
            Random random = new Random();
            int number = random.nextInt(9 + 1) + 4;
            System.out.println(commandTag + " " + utils.intToString(number, 4) + " " +
                    utils.intToString(number, 4) + " " +
                    utils.intToString(number, 4) + " " +
                    utils.intToString(number, 4) + " " +
                    utils.intToString(number, 4) + " " +
                    utils.intToString(number, 4) + " " +
                    utils.intToString(number, 4));
        }

        System.out.println(commandTag + ready);

        try {
            CommandLine commandLine = commandLineParser.parse(options, args);
            if (commandLine.hasOption("h")) {
                System.out.println(commandTag + welcome);
            } else if (commandLine.hasOption("c")) {
                System.out.println(commandTag + command);
                /**
                 * Start threaded CommandModule
                 */
                Thread thread = new CommandModule();
                thread.setDaemon(true);
                thread.start();
            }
        } catch (ParseException e) {
            System.out.println(commandTag + commandError);
        }

        while(true) {
            /**
             * Get directional coordinates
             */
            System.out.println(commandTag + directionalCoordinates);

            Scanner scanner = new Scanner(System.in);
            String coordinates = scanner.nextLine();

            System.out.println(commandTag + coordinatesReceived + coordinates);
            System.out.println(commandTag + navigationCalculation);

            for (int i = 0 ; i < 100; i++) {
                Random random = new Random();
                int number = random.nextInt(9 + 1) + 4;
                System.out.println(commandTag + " " + utils.intToString(number, 4) + " " +
                        utils.intToString(number, 4) + " " +
                        utils.intToString(number, 4) + " " +
                        utils.intToString(number, 4) + " " +
                        utils.intToString(number, 4) + " " +
                        utils.intToString(number, 4) + " " +
                        utils.intToString(number, 4));
            }

            System.out.println(commandTag + updatedNavigation);
        }
    }
}
