package ch.l0r5.aoc22;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
public class Day2 {

  private static final String INPUT_FILE_PATH = "src/main/resources/day2/input.txt";

  private Day2() {
  }

  static Day2 run() {
    log.info("** Running day 2... **");
    return new Day2();
  }

  public void solution() {
    log.info("Calc solution...");
    List<String> content = readFile();

    int sum = 0;
    for (String line : content) {
      String[] game = line.split(" ");
      sum += calcPointsFirst(game);
    }
    log.info("** SOLUTION PART 1 **");
    log.info("Total points: {}", sum);

    sum = 0;
    for (String line : content) {
      String[] game = line.split(" ");
      sum += calcPointsSecond(game);
    }
    log.info("** SOLUTION PART 2 **");
    log.info("Total points: {}", sum);

    log.info("** Finished Day 2. **");
  }

  private int calcPointsFirst(String[] game) {
//    rock      A X
//    paper     B Y
//    scissors  C Z
    int win = 6;
    int draw = 3;
    int lose = 0;
    Map<String, Integer> shapePointsTable = Map.of("X", 1, "Y", 2, "Z", 3);
    Map<String, Integer> matchPointsTable = Map.of(
      "AX", draw,
      "BX", lose,
      "CX", win,
      "AY", win,
      "BY", draw,
      "CY", lose,
      "AZ", lose,
      "BZ", win,
      "CZ", draw);
    String matchResult = Arrays.toString(game);
    matchResult = matchResult.substring(1, matchResult.length() - 1).replace(",", "").replace(" ", "");

    int matchPoints = matchPointsTable.get(matchResult);
    int shapePoints = shapePointsTable.get(game[1]);

    return matchPoints + shapePoints;
  }

  private int calcPointsSecond(String[] game) {

//    win      X
//    draw     Y
//    lose     Z
    int win = 6;
    int draw = 3;
    int lose = 0;

//    rock      A
//    paper     B
//    scissors  C
    Map<String, Integer> shapePointsTable = Map.of("rock", 1, "paper", 2, "scissors", 3);
    Map<String, Integer> matchPointsTable = Map.of(
      "AX", lose + shapePointsTable.get("scissors"),
      "BX", lose + shapePointsTable.get("rock"),
      "CX", lose + shapePointsTable.get("paper"),
      "AY", draw + shapePointsTable.get("rock"),
      "BY", draw + shapePointsTable.get("paper"),
      "CY", draw + shapePointsTable.get("scissors"),
      "AZ", win + shapePointsTable.get("paper"),
      "BZ", win + shapePointsTable.get("scissors"),
      "CZ", win + shapePointsTable.get("rock")
    );

    String matchResult = Arrays.toString(game);
    matchResult = matchResult.substring(1, matchResult.length() - 1).replace(",", "").replace(" ", "");

    int matchPoints = matchPointsTable.get(matchResult);

    return matchPoints;
  }

  private List<String> readFile() {
    log.info("Read File");
    List<String> content = Collections.emptyList();
    try {
      content = Files.readAllLines(Paths.get(INPUT_FILE_PATH));
      log.info("Read {} lines from file.", content.size());

    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return content;
  }
}
