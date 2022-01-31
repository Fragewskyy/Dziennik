package s;

import controller.Action;


import java.util.List;

public class Menu {
    private View.View view;

    public Menu(View.View view) {
        this.view = view;
    }

    public void show(List<Action> actions) {

        for (Action action : actions) {
            view.info(actions.indexOf(action)+": "+action.getlabel());

        }
    }

    public Action getChoice(List<Action> actions) {
        while (true) {
            try {
                int choice = view.readInt("Choose action");
                return actions.get(choice);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong choice. Try again!");
            }

        }
    }
}

