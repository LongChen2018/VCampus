package tech.zxuuu.client.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.auth.AuthGUI;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.ManagerType;
import tech.zxuuu.entity.UserType;

import tech.zxuuu.net.ConnectionToServer;
import tech.zxuuu.net.ResponseListener;
import tech.zxuuu.net.Session;
import tech.zxuuu.util.SwingUtils;

/**
 * 客户端全局根对象（App）
 * 
 * @author z0gSh1u
 */
public class App extends JFrame {

  /******* 新增部分 *******/
	public static boolean hasLogon = false; // 登录状态
	public static Session session; // 登陆Session
	public static ConnectionToServer connectionToServer; // 到服务器的连接
	public static ResponseQueue responseQueue;
	private ResponseListener responseListener;
  /***********************/
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					App frame = new App();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	/**
	 * 要求进行界面路由
	 */
	public static void requireRouting() {
		JFrame target = null;
		UserType userType = App.session.getUserType();
		ManagerType managerType = 
				(userType == UserType.MANAGER ? App.session.getManager().getManagerType() : null);
		
		target = userType == UserType.STUDENT ? new AppStudent()
				: userType == userType.TEACHER ? new AppTeacher()
						: userType == userType.MANAGER
								? (managerType == ManagerType.LIBRARY ? new AppLibraryManager()
										: managerType == ManagerType.OPENCOURSE ? new AppOpencourseManager()
												: managerType == ManagerType.SHOP ? new AppShopManager()
														: managerType == ManagerType.TEACHING ? new AppTeachingManager() : null)
								: null;											
		if (target == null) {
			System.err.println("Failed routing.");
			System.exit(1);
		}
		target.setVisible(true);
	}
	
	/**
	 * Create the frame.
	 */
	public App() {

	  /******* 新增部分 *******/
		// 连接到服务器
		App.connectionToServer = Utils.formConnection();
		// 报错，结束运行
		if (App.connectionToServer == null) {
			SwingUtils.showError(null, "服务器连接失败！连接到：" + Utils.getServerHost() + ":" + Utils.getMainPort(), "错误");
			System.exit(0);
		}
		// 初始化响应队列
		App.responseQueue = ResponseQueue.getInstance();
		// 开始侦听响应
		this.responseListener = new ResponseListener(App.connectionToServer);
		this.responseListener.start();
	  /***********************/
		
		if (!App.hasLogon) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						AuthGUI frame = new AuthGUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 893, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblClientMainPage = new JLabel("Client Main Page");
		panel.add(lblClientMainPage);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
	}

}
