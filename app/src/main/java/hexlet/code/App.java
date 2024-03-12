package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private File filepath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private File filepath2;

    @Override
    public Integer call() {
        String string1 = filepath1.toString();
        String string2 = filepath2.toString();
        String diff = Differ.generate(string1, string2, format);
        System.out.println(diff);
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
