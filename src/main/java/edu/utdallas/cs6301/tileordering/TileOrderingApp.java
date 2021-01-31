package edu.utdallas.cs6301.tileordering;

import edu.utdallas.cs6301.tileordering.io.FileIOService;
import edu.utdallas.cs6301.tileordering.io.IOService;
import edu.utdallas.cs6301.tileordering.io.TestIOService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileOrderingApp {
    public static void main(String[] args) throws IOException {
        Map<String, String> options = new HashMap<>();
        captureOptions(options, args);

        IOService ioService = getIOService(options);
        String input = ioService.getInputString();

        SearchService<Tiles> searchService = new SearchService<>(
                new Tiles(input),
                Tiles.getGoal(input.length()),
                options.getOrDefault("searchStrategy", "bfs")
        );

        List<Tiles> path = searchService.getPath();
        path.forEach(tile -> ioService.write(tile.toString()));
    }

    private static void captureOptions(Map<String, String> options, String[] args) {
        for(int i = 0; i < args.length; i++) {
            String cmdLineArg = args[i].toLowerCase().trim();

            switch(cmdLineArg) {
                case "--cost":
                case "-c":
                    options.put("cost", "");
                    break;
                case "--help":
                case "-h":
                    System.out.printf(
                            "Usage: search [OPTIONS] <search-strategy> <INPUT_FILE>%n" +
                                    "Options:%n" +
                                    "\t-c, --cost\tApplies movement cost.%n" +
                                    "\t-h, --help\tHelp information%n" +
                                    "\t-t, --test <initial tile sequence> \tRun in testing mode using specified initial tile sequence%n" +
                                    "\t\t\t\tExample: search -t WWWWWWxBBBBBB%n%n" +
                                    "Search Strategies:%n" +
                                    "\tBFS\t\tBreadth first search%n" +
                                    "\tDFS\t\tDepth first search%n" +
                                    "\tUCS\t\tUniform cost search%n" +
                                    "\tGS\t\tGreedy search%n" +
                                    "\tA-star\tA* search%n"
                    );
                    System.exit(0);
                case "--test":
                case "-t":
                    options.put("test", args[i + 1]);
                    i++;
                    break;
                case "bfs":
                case "dfs":
                case "ucs":
                case "gs":
                case "a-star":
                    options.put("searchStrategy", cmdLineArg);
                    break;
                default:
                    System.out.printf("Unknown option: %s%n", args[i]);
                    break;
            }
        }

        options.put("inputFile", args[args.length - 1]);
    }

    private static IOService getIOService(Map<String, String> options) throws IOException {
        return options.containsKey("test") ?
                new TestIOService(options.get("test")) :
                new FileIOService(options.get("inputFile"));
    }
}
