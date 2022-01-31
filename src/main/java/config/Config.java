package config;

import controller.Action;
import s.Menu;

import java.util.List;

public interface Config {

    public List<Action> inicializactions();
    public Menu inicializemenu();

}
