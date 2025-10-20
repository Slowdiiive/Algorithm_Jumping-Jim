import java.io.*;
import java.util.*;

public class JumpingJim {

    static class MazeGraph {
        int rows, cols;
        int[][] maze;
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        MazeGraph(int[][] maze) {
            this.rows = maze.length;
            this.cols = maze[0].length;
            this.maze = maze;
            buildGraph();
        }

        private int index(int r, int c) {
            return r * cols + c + 1;
        }

        private void buildGraph() {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    int value = maze[r][c];
                    int from = index(r, c);
                    adjList.putIfAbsent(from, new ArrayList<>());

                    // East
                    if (c + value < cols) {
                        adjList.get(from).add(index(r, c + value));
                    }
                    // West
                    if (c - value >= 0) {
                        adjList.get(from).add(index(r, c - value));
                    }
                    // South
                    if (r + value < rows) {
                        adjList.get(from).add(index(r + value, c));
                    }
                    // North
                    if (r - value >= 0) {
                        adjList.get(from).add(index(r - value, c));
                    }
                }
            }
        }

        List<Integer> bfs(int start, int goal) {
            Queue<List<Integer>> queue = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();

            queue.add(Arrays.asList(start));
            visited.add(start);

            while (!queue.isEmpty()) {
                List<Integer> path = queue.poll();
                int last = path.get(path.size() - 1);

                if (last == goal) return path;

                for (int neighbor : adjList.getOrDefault(last, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        List<Integer> newPath = new ArrayList<>(path);
                        newPath.add(neighbor);
                        queue.add(newPath);
                    }
                }
            }
            return null;
        }

        List<String> getDirections(List<Integer> path) {
            List<String> directions = new ArrayList<>();
            for (int i = 1; i < path.size(); i++) {
                int prev = path.get(i - 1);
                int curr = path.get(i);
                int r1 = (prev - 1) / cols, c1 = (prev - 1) % cols;
                int r2 = (curr - 1) / cols, c2 = (curr - 1) % cols;

                if (r2 == r1 && c2 > c1) directions.add("E");
                else if (r2 == r1 && c2 < c1) directions.add("W");
                else if (c2 == c1 && r2 > r1) directions.add("S");
                else if (c2 == c1 && r2 < r1) directions.add("N");
            }
            return directions;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("jim-input.txt"));
        String[] dims = reader.readLine().trim().split("\\s+");
        int rows = Integer.parseInt(dims[0]);
        int cols = Integer.parseInt(dims[1]);

        int[][] maze = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] line = reader.readLine().trim().split("\\s+");
            for (int j = 0; j < cols; j++) {
                maze[i][j] = Integer.parseInt(line[j]);
            }
        }
        reader.close();

        MazeGraph graph = new MazeGraph(maze);
        List<Integer> path = graph.bfs(1, rows * cols);

        BufferedWriter writer = new BufferedWriter(new FileWriter("jim-output.txt"));
        if (path != null) {
            List<String> directions = graph.getDirections(path);
            for (String dir : directions) {
                writer.write(dir + " ");
            }
        } else {
            writer.write("No path found.");
        }
        writer.close();
    }
}