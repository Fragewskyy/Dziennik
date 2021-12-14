package view;

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
        int choice = view.readInt("Choice");
        return actions.get(choice);
    }
}

