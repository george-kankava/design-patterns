package org.example.obserer;

import java.util.ArrayList;
import java.util.List;

public class NewspaperObserver {

    public interface Observer {
        void notify(String tweet);
    }

    public static class NYTimes implements Observer {

        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! : " + tweet);
            }
        }
    }

    public static class Guardian implements Observer {

        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet another news in London : " + tweet);
            }
        }
    }

    public interface Subject {
        void registerObserver(Observer observer);
        void notifyObservers(String tweet);
    }

    public static class Feed implements Subject {

        List<Observer> observerList = new ArrayList<>();

        @Override
        public void registerObserver(Observer observer) {
            observerList.add(observer);
        }

        @Override
        public void notifyObservers(String tweet) {
            for (Observer observer : observerList) {
                observer.notify(tweet);
            }

        }
    }

    public static void main(String[] args) {
        Subject feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.notifyObservers("The queen said her favourite book is Java 8 in Action!");
    }
}
