package ch.l0r5.aoc22;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Day1 {

  private static final String INPUT_FILE_PATH = "src/main/resources/day1/input.txt";

  private Day1() {
  }

  static Day1 run() {
    log.info("** Running day 1... **");
    return new Day1();
  }

  public void solution() {
    log.info("Calc solution...");
    List<String> content = readFile();
    List<Integer> sums = getSums(content);
    int highestNumber = getHighest(sums);
    log.info("** SOLUTION PART 1 **");
    log.info("The highest number is: {}", highestNumber);
    List<Integer> highestThree = findTopK(sums, 3);
    log.info("The highest three sums are: {}", highestThree.toString());
    int getSumOfThree = highestThree
      .stream()
      .reduce(Integer::sum)
      .get();
    log.info("** SOLUTION PART 2 **");
    log.info("The sum of the highest three is: {}", getSumOfThree);
    log.info("** Finished Day 1. **");
  }

  private int getHighest(List<Integer> sums) {
    return sums.stream().max(Integer::compare).orElse(0);
  }

  private List<Integer> getSums(List<String> content) {
    List<Integer> sums = new ArrayList<>();
    int currentSum = 0;
    for (String line : content) {
      if (line.equals("")) {
        sums.add(currentSum);
        log.info("Added new Batch with sum of {} calories.", currentSum);
        currentSum = 0;
      } else {
        int newCalories = Integer.parseInt(line);
        currentSum += newCalories;
        log.info("+{} calories. Current sum: {}.", newCalories, currentSum);
      }
    }
    log.info("Collected {} number of sums.", sums.size());
    return sums;
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

  private List<Integer> findTopK(List<Integer> input, int k) {
    Set<Integer> sortedSet = new TreeSet<>(Comparator.reverseOrder());
    sortedSet.addAll(input);
    return sortedSet.stream().limit(k).collect(Collectors.toList());
  }
}
