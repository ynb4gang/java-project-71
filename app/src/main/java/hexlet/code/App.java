package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionRequested;
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    protected File filepath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    protected File filepath2;


    @Override
    public Integer call() throws Exception {
        Map<String, Object> parsedFirstFile = UniversalParser.parseFile(filepath1);
        Map<String, Object> parsedSecondFile = UniversalParser.parseFile(filepath2);
        if (format.equals("stylish")) {
            Map<String, Object> readyParseOutput = StylishStyleOutput.comparator(parsedFirstFile, parsedSecondFile);
            for (Map.Entry<String, Object> entry : readyParseOutput.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else if (format.equals("plain")) {
            String result = PlainStyleOutput.format(parsedFirstFile, parsedSecondFile);
            System.out.println(result);
        } else if (format.equals("json")) {
            JsonNode jsonNode = JsonStyleOutput.format(parsedFirstFile, parsedSecondFile);
            System.out.println(jsonNode.toPrettyString());
        }
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
