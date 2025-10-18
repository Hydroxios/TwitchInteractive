package fr.hydroxios.twitchInteractive.utils;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

public class Manager<T> extends ArrayList<T>{

    public Optional<T> find(Predicate<T> predicate){
        return this.stream().filter(predicate).findFirst();
    }

}
