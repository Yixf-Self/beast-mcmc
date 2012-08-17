package dr.app.bss;

import jam.framework.SingleDocApplication;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BeagleSequenceSimulatorApp {

	// Share those if neccessary
	public static ImageIcon errorIcon;
	private Image beagleSequenceSimulatorImage;
	private static final String BEAGLE_SEQUENCE_SIMULATOR = "Beagle Sequence Simulator";
	
	public BeagleSequenceSimulatorApp() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		boolean lafLoaded = false;

		// Setup Look & Feel
		if (System.getProperty("os.name").toLowerCase().startsWith("mac os x")) {

			// Mac stuff
			System.setProperty("apple.awt.showGrowBox", "true");
			System.setProperty("apple.awt.brushMetalLook", "true");
			System.setProperty("apple.laf.useScreenMenuBar", "true");

			System.setProperty("apple.awt.graphics.UseQuartz", "true");
			System.setProperty("apple.awt.antialiasing", "true");
			System.setProperty("apple.awt.rendering", "VALUE_RENDER_QUALITY");

			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("apple.awt.draggableWindowBackground", "true");
			System.setProperty("apple.awt.showGrowBox", "true");

			UIManager.put("SystemFont", new Font("Lucida Grande", Font.PLAIN, 13));
			UIManager.put("SmallSystemFont", new Font("Lucida Grande", Font.PLAIN, 11));

			try {

				// UIManager.setLookAndFeel(UIManager
				// .getSystemLookAndFeelClassName());

				UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
				lafLoaded = true;

			} catch (Exception e) {
				//
			}

		} else {

			try {

				// UIManager.setLookAndFeel(UIManager
				// .getSystemLookAndFeelClassName());

				UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				lafLoaded = true;

			} catch (Exception e) {
				//
			}

		}

		if (!lafLoaded) {

			try {

				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			} catch (Exception e1) {

				e1.printStackTrace();
				System.out.println("Specified l&f not found. Loading system default l&f");

			}
		}

		 errorIcon = CreateImageIcon("icons/error.png");
		 beagleSequenceSimulatorImage = CreateImage("icons/bss.png");
		 
		SingleDocApplication app = new SingleDocApplication(
				new BeagleSequenceSimulatorMenuFactory(), //
				BEAGLE_SEQUENCE_SIMULATOR, //
				BeagleSequenceSimulatorData.VERSION.concat(" ").concat(BeagleSequenceSimulatorData.DATE_STRING), //
				null //
				);

	      BeagleSequenceSimulatorFrame frame = new BeagleSequenceSimulatorFrame(BEAGLE_SEQUENCE_SIMULATOR);
	      frame.setIconImage(beagleSequenceSimulatorImage);
          app.setDocumentFrame(frame);
		
	}// END: Constructor

	public static void main(String args[]) {

		if (args.length > 1) {

			System.out.println("Command-line interface not yet implemented");

		} else {

			try {
				
				Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
				new BeagleSequenceSimulatorApp();
				
			} catch (UnsupportedClassVersionError e) {
				
				System.err.println("Your Java Runtime Environment is too old. Please update");
				e.printStackTrace();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}//END: try catch block

		}// END: command line check

	}// END: main

	private Image CreateImage(String path) {
		URL imgURL = this.getClass().getResource(path);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage(imgURL);

		if (img != null) {
			return img;
		} else {
			System.out.println("Couldn't find file: " + path + "\n");
			return null;
		}
		
	}// END: CreateImage
	
	private ImageIcon CreateImageIcon(String path) {
		URL imgURL = this.getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: \n" + path + "\n");
			return null;
		}
	}
	
}// END: TestlabOutbreakApp
