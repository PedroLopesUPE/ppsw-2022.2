package br.upe.ppsw.jabberpoint;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.upe.ppsw.jabberpoint.apresentacao.model.DemoPresentation;
import br.upe.ppsw.jabberpoint.apresentacao.model.Presentation;
import br.upe.ppsw.jabberpoint.apresentacao.model.Style;
import br.upe.ppsw.jabberpoint.apresentacao.model.XMLAccessor;
import br.upe.ppsw.jabberpoint.apresentacao.view.SlideViewerFrame;

@SpringBootApplication
public class JabberPointApplication implements CommandLineRunner {

  protected static final String IOERR = "IO Error: ";
  protected static final String JABERR = "Jabberpoint Error ";
  protected static final String JABVERSION = "Jabberpoint 1.6 -";

  public static void main(String[] argv) {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(JabberPointApplication.class);
    builder.headless(false);
    builder.web(WebApplicationType.NONE);
    builder.run(argv);
  }

  @Override
  public void run(String... args) throws Exception {
    Style.createStyles();

    Presentation presentation = new Presentation();

    new SlideViewerFrame(JABVERSION, presentation);

    try {
      if (args.length <= 1) {
    	DemoPresentation demopresentation = new DemoPresentation();
        demopresentation.loadFile(presentation, "");
      } else {
        new XMLAccessor().loadFile(presentation, args[1]);
      }

      presentation.setSlideNumber(0);

    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, IOERR + ex, JABERR, JOptionPane.ERROR_MESSAGE);
    }
  }

}
