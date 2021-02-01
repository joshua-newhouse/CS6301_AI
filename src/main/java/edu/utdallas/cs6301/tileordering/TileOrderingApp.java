/*
 * Joshua Newhouse
 * Vamsi Somepalli
 */

package edu.utdallas.cs6301.tileordering;

import edu.utdallas.cs6301.tileordering.io.FileIOService;
import edu.utdallas.cs6301.tileordering.io.IOService;
import edu.utdallas.cs6301.tileordering.io.NoOpIOService;
import edu.utdallas.cs6301.tileordering.io.TestIOService;
import edu.utdallas.cs6301.tileordering.tiles.MovementCostTiles;
import edu.utdallas.cs6301.tileordering.tiles.Tiles;

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

        Tiles initialState = options.get("cost") == null ?
                new Tiles(input) : new MovementCostTiles(input);

        String searchStrategy = options.getOrDefault("searchStrategy", "bfs");

        SearchService<Tiles> searchService = new SearchService<>(
                initialState,
                Tiles.getGoal(initialState),
                searchStrategy,
                options.get("dump") == null ? new NoOpIOService() : ioService
        );

        List<Tiles> path = searchService.getPath();
        ioService.writeItem("\n");

        ioService.writeItem(String.format("Final Result for %s:", searchStrategy.toUpperCase()));
        path.forEach(tile -> ioService.write(tile.toString()));
    }

    private static void captureOptions(Map<String, String> options, String[] args) {
        options.put("dump", "");
        options.put("inputFile", args[args.length - 1]);

        for(int i = 0; i < args.length - 1; i++) {
            String cmdLineArg = args[i].toLowerCase().trim();

            switch(cmdLineArg) {
                case "--cost":
                case "-c":
                    options.put("cost", "");
                    break;
                case "--dump":
                case "-d":
                    options.remove("dump");
                    break;
                case "--help":
                case "-h":
                    System.out.printf(
                            "Usage: search [OPTIONS] <search-strategy> <INPUT_FILE>%n" +
                                    "Options:%n" +
                                    "\t-c, --cost\tApplies movement cost.%n" +
                                    "\t-d, --dump\tSuppresses writing every expanded tile sequence.%n" +
                                    "\t-h, --help\tHelp information%n" +
                                    "\t-t, --test\tSupply an initial tile sequence string on command line instead " +
                                    "of reading from a file.%n" +
                                    "\t\t\tExample: search -t WWWWWWxBBBBBB%n%n" +
                                    "Search Strategies:%n" +
                                    "\tBFS\tBreadth first search%n" +
                                    "\tDFS\tDepth first search%n" +
                                    "\tUCS\tUniform cost search%n" +
                                    "\tGS\tGreedy search%n" +
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
    }

    private static IOService getIOService(Map<String, String> options) throws IOException {
        return options.containsKey("test") ?
                new TestIOService(options.get("test")) :
                new FileIOService(options.get("inputFile"));
    }
}
