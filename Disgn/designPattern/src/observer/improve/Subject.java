package observer.improve;



/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public interface Subject {
     public  void registerObserver(Observer o );
     public  void removeObserver(Observer o );
     public  void notifyObservers();
}
