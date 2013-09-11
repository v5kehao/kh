package kehao.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class LockingService {

  private Set<String> locks = new HashSet<String>();

  synchronized public void lock(String username) {
    while(!locks.add(username)) {
      try {
        wait();
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  synchronized public void unlock(String username) {
    if(locks.remove(username)) {
      notifyAll();
    }
  }
}
