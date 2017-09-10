package br.gov.go.agr.siav.observers;

import javax.enterprise.event.Observes;

import org.pmw.tinylog.Logger;

import br.com.caelum.vraptor.events.VRaptorInitialized;

public class StartupObserver {
   
   public void observe(@Observes VRaptorInitialized event) {
      // System.setProperty("user.dir",
      // "/root/programas/apache-tomcat-7.0.62/webapps/siav");
      System.setProperty("user.dir", "/home/thiago/workspace/siav");
      Logger.info("VRAPTOR IS RUNNING NOW!");
   }
   
}
