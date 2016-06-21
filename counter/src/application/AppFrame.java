package application;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class AppFrame extends JFrame{

	private static Database db;
	private CardLayout cards = new CardLayout();
	private JPanel contentPane;
	private JPanel originalPane;
	private Name curUser;
	private Account curAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("TitledBorder.color", Color.BLACK);
			//UIManager.put("Panel.background", Color.WHITE);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// Connect to database
		db = new Database();
		db.startDB();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame frame = new AppFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1312, 772);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(cards);
		setContentPane(contentPane);
		originalPane = new Main(this);
		contentPane.add(originalPane);
	}
	
	// Getter and setter functions
	
	public Database getDatabase() {
		return db;
	}
	
	public Name getUser() {
		return curUser;
	}
	
	public Account getAccount() {
		return curAccount;
	}
	
	public void setUser(Name user) {
		curUser = user;
	}
	
	public void setAccount(Account account) {
		curAccount = account;
	}
	
	// CardLayout manipulation
	
	public void addPanel(JPanel panel) {
		contentPane.add(panel);
		cards.next(contentPane);
	}
	
	public void next() {
		cards.next(contentPane);
	}
	
	public void prev() {
		cards.previous(contentPane);
		//this.removeAll();
	}
	
	public void backToMain() {
		contentPane.removeAll();
		contentPane.add(originalPane);
	}

}
