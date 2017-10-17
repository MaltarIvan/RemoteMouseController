package client;

/**
 * Created by Maltar on 10.10.2017..
 */
public class Action {
    private String execute;
    private String description;

    private Action(String execute, String description) {
        this.execute = execute;
        this.description = description;
    }

    public static Action makeMoveAction(int x, int y) {
        return new Action("move", "x=" + x + ":y=" + y);
    }

    public static Action makeClickAction(String button) {
        return new Action("click", button);
    }

    @Override
    public String toString() {
        return "Action{" +
                "execute='" + execute + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
