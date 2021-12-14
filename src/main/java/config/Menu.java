package config;

import controller.Action;
import view.View;

import java.util.List;

public class Menu {
    private View view;

    public Menu(View view) {
        this.view = view;
    }

    public void show(List<Action> actions) {
        int i=1;
        for (Action action : actions) {
            view.info(actions.indexOf(action)+": "+action.getlabel());
            i++;
        }
    }

    public Action getChoice(List<Action> actions) {
        int choice = view.readInt("Choice");
        return actions.get(choice);
    }
}

