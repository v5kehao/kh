package kehao.ui.widget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;

public abstract class AsyncActionListener implements ActionListener, Runnable {

  private ExecutorService executorService;

  protected AsyncActionListener(ExecutorService executorService) {
    this.executorService = executorService;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    executorService.submit(this);
  }

}
