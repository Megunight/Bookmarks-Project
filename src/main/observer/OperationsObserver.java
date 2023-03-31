package observer;

import model.Library;

public interface OperationsObserver {
    void updateView(Library library);

    void updateInfo(Library library);
}
