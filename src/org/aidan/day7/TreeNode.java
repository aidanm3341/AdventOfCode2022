package org.aidan.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class TreeNode<T> {
    private T data;
    private List<TreeNode<T>> children;
    private TreeNode<T> parent;

    public TreeNode(T data, TreeNode<T> parent) {
        this.data = data;
        this.children = new ArrayList<>();
        this.parent = parent;
    }

    public TreeNode(T data, List<TreeNode<T>> children, TreeNode<T> parent) {
        this.data = data;
        this.children = children;
        this.parent = parent;
    }

    public void addChild(TreeNode<T> child){
        children.add(child);
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public abstract int getSize();

    public TreeNode<T> getParent() {
        return parent;
    }

    public int calculateDepthOf(T target){
        for(TreeNode<T> child : children){
            if(child.getData().equals(target))
                return 1;
            else if (child.contains(target))
                return 1 + child.calculateDepthOf(target);
        }
        return 0;
    }

    public boolean contains(T target){
        boolean found = false;
        for(TreeNode<T> child : children){
            if(child.getData().equals(target)) {
                found = true;
                break;
            }
            else {
                found = child.contains(target);
                if(found)
                    break;
            }
        }
        return found;
    }

    public TreeNode<T> findLowestCommonNode(T t1, T t2){
        if(this.contains(t1) && this.contains(t2)){
            TreeNode<T> found;
            for (TreeNode<T> child : children){
                found = child.findLowestCommonNode(t1, t2);
                if (found != null)
                    return found;
            }
            return this;
        }
        else{
            return null;
        }
    }

    public Optional<TreeNode<T>> findFirst(Predicate<TreeNode<T>> filter) {
        return children.stream()
                .filter(filter)
                .findFirst();
    }

    public Stream<TreeNode<T>> getAll() {
        return Stream.concat(
                getChildren().stream(),
                getChildren().stream().flatMap(TreeNode::getAll)
        );
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", children=" + children.toString() +
                "}";
    }

    static class File extends TreeNode<String> {
        private int size;

        public File(String name, int size, TreeNode<String> parent) {
            super(name, parent);
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    static class Directory extends TreeNode<String> {
        public Directory(String name, TreeNode<String> parent) {
            super(name, parent);
        }

        public int getSize() {
            int total = 0;
            for (TreeNode<String> n : getChildren()) {
                total += n.getSize();
            }
            return total;
        }
    }
}
