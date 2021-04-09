package observer;

import java.util.*;

// Базовый издатель
public class EventManager {
	// Хеш-таблица "название события" = "список подписчиков" 
    Map<String, List<IEventListener>> listeners = new HashMap<>();

    // Конструктор класса, принимает массив событий
    public EventManager(String... operations) {
        for (String operation : operations) {
        	// Добавляем в таблицу название события и список его подписчиков
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    // Метод подписки на событие
    public void subscribe(String eventType, IEventListener listener) {
        List<IEventListener> users = listeners.get(eventType);				// Получаем список подписчиков по данному событию
        users.add(listener);												// Добавляем нового подписчика на событие
    }

    // Метод отписки на событие
    public void unsubscribe(String eventType, IEventListener listener) {
        List<IEventListener> users = listeners.get(eventType);				// Получаем список подписчиков по данному событию
        users.remove(listener);												// Отписываем подписчика от события
    }	
    
    // Метод оповещения подписчиков о каком-либо событии
    public void notify(String eventType) {
        List<IEventListener> users = listeners.get(eventType);				// Получаем список подписчиков по данному событию
        for (IEventListener listener : users) {								// Вызываем их методы update для обработки данного события
            listener.update(eventType);
        }
    }
}
