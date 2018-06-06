package al.aldi.qa.selenium_tests.utils;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CmdParameterHelper {
    private static final Logger logger = LoggerFactory.getLogger(CmdParameterHelper.class);
    public static final String DEV_URL = "http://localhost:1337/login";

    static CmdParameterHelper instance = null;

    private String[] args = null;
    private Options options = new Options();
    private CommandLine commandLine;
    private HelpFormatter formatter = new HelpFormatter();

    private Option url = Option.builder("u").longOpt("url").hasArg().valueSeparator('=').desc("URL for the test object. Overwrites value from config file.").build();
    private Option username = Option.builder().longOpt("username").hasArg().valueSeparator('=').desc("Username to log in. Overwrites value from config file.").build();
    private Option password = Option.builder().longOpt("password").hasArg().valueSeparator('=').desc("Password to log in. Overwrites value from config file.").build();
    private Option config = Option.builder("c").longOpt("config").hasArg().valueSeparator('=').desc("Path to config file").build();
    private Option generateConfig = Option.builder().longOpt("generate-config").desc("Generate sample config file").build();

    TestConfig testConfig = null;

    private CmdParameterHelper() {
        configure();
    }

    private void configure() {
        options.addOption(url);
        options.addOption(username);
        options.addOption(password);
        options.addOption(config);
        options.addOption(generateConfig);
    }

    /**
     * Parse the command line arguments and check for validity
     *
     * @return if the test execution should continue
     */
    public boolean parse(String[] args) {
        this.args = args;

        boolean result = true;
        CommandLineParser parser = new DefaultParser();
        try {
            commandLine = parser.parse(options, args);

            if (commandLine.hasOption("config")) {
                String content = FilesUtils.readFile(commandLine.getOptionValue("config"));
                testConfig = TestConfig.toObject(content);
            } else if (commandLine.hasOption("generate-config")) {
                testConfig = new TestConfig(DEV_URL, "", "");
                FilesUtils.writeToFile("./config.json", testConfig.toJson());
                logger.info("Generated config.json sample file");
                result = false;
            } else if (commandLine.hasOption("h") || commandLine.getArgList().size() == 0) {
                printHelp();
                result = false;
            }

            // overwrites values loaded from config file
            if (result) {
                if (commandLine.hasOption("url")) {
                    getConfig().setUrl(commandLine.getOptionValue("url"));
                }
                if (commandLine.hasOption("username")) {
                    getConfig().setUsername(commandLine.getOptionValue("username"));
                }
                if (commandLine.hasOption("password")) {
                    getConfig().setPassword(commandLine.getOptionValue("password"));
                }
            }

        } catch (ParseException e) {
            logger.error(e.getMessage());
            result = false;
        }
        return result;
    }

    private void printHelp() {
        formatter.printHelp("selenium-boilerplate", options);
    }

    public String getUrl() {
        return commandLine.getOptionValue("url");
    }

    public TestConfig getConfig() {
        if (commandLine == null || !commandLine.hasOption("config")) {
            // create dummy config
            testConfig = new TestConfig(DEV_URL);
        }

        return testConfig;
    }

    public static CmdParameterHelper getInstance() {
        if (instance == null) {
            instance = new CmdParameterHelper();
        }

        return instance;
    }
}
