package kehao;

import java.awt.*;
import java.util.Collection;
import javax.swing.*;

import com.beust.jcommander.JCommander;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import kehao.cmd.CmdArgProcessor;
import kehao.config.AppConfig;
import kehao.config.BackgroundModeConfig;
import kehao.config.UIConfig;
import kehao.ui.AppFrame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartApp {
  public static void main(String args[]) {
    CmdArgs argument = new CmdArgs();
    JCommander jc = new JCommander(argument, args);
    boolean enableUI = true;
    if(argument.isHelp() || (args.length == 0 && GraphicsEnvironment.isHeadless())) {
      jc.usage();
      return;
    } else {
      if(argument.isBackgroundMode()) {
        enableUI = false;
        if(argument.isLazyMode()) {
          jc.usage();
          return;
        }
      } else {
        try {
          UIManager.setLookAndFeel(new LunaLookAndFeel());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    AnnotationConfigApplicationContext ctx;
    if(enableUI) {
      ctx = new AnnotationConfigApplicationContext(AppConfig.class, UIConfig.class);
      AppFrame ui = ctx.getBean(AppFrame.class);
      ui.setVisible(true);
    } else {
      ctx = new AnnotationConfigApplicationContext(AppConfig.class, BackgroundModeConfig.class);
    }
    ctx.registerShutdownHook();
    Collection<CmdArgProcessor> argProcessors = ctx.getBeansOfType(CmdArgProcessor.class).values();
    for(CmdArgProcessor argProcessor : argProcessors) {
      argProcessor.process(argument);
    }
  }
}
