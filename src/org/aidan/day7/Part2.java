package org.aidan.day7;

import org.aidan.utils.Output;

import java.util.List;

public class Part2 implements Output<Integer> {

    private TreeNode<String> root;
    private TreeNode<String> currentNode;

    @Override
    public Integer execute(List<String> input) {
        root = new TreeNode.Directory("/", null);
        currentNode = root;

        input.stream()
                .skip(1)
                .forEach(this::handleInput);

        int unusedSpace = 70000000 - root.getSize();

        return root.getAll()
                .filter(node -> node instanceof TreeNode.Directory)
                .map(TreeNode::getSize)
                .filter(size -> size + unusedSpace > 30000000)
                .reduce(Integer::min)
                .orElse(0);
    }

    private void handleInput(String input) {
        if (input.startsWith("$")) {
            Command command = parseCommand(input);
            if (command.command.equals("cd")) {
                if (command.param.equals("/"))
                    currentNode = root;
                else if (command.param.equals(".."))
                    currentNode = currentNode.getParent();
                else
                    currentNode = currentNode.findFirst(node -> node.getData().equals(command.param)).get();
            }
        } else {
            parseFileOrDir(input);
        }
    }

    private Command parseCommand(String input) {
        String command = input.substring(2, 4);
        if (command.equals("cd"))
            return new Command(command, input.substring(5));
        else
            return new Command(command, "");
    }

    private void parseFileOrDir(String input) {
        String[] strings = input.split(" ");
        if (strings[0].equals("dir"))
            currentNode.addChild(new TreeNode.Directory(strings[1], currentNode));
        else
            currentNode.addChild(new TreeNode.File(strings[1], Integer.parseInt(strings[0]), currentNode));
    }

    static class Command {
        private final String command;
        private final String param;

        public Command(String command, String param) {
            this.command = command;
            this.param = param;
        }

        public String getCommand() {
            return command;
        }

        public String getParam() {
            return param;
        }
    }
}
