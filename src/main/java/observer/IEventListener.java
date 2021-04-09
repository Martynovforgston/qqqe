package observer;

// Подписчик издателя
public interface IEventListener {
    void update(String eventType);
}
