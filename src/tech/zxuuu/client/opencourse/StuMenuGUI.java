package tech.zxuuu.client.opencourse;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.OpenCourseInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 公开课主页面
 * 
 * @author LongChen
 */
public class StuMenuGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane spnCourseList;
	private JPanel pnlCourseList;

	private int screenWidth;
	private int screenHeight;
	private int frameWidth;
	private int frameHeight;
	private JLabel label;
	private JLabel lblVcampus;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	// 显示公开课列表
	private void showCourseList() {
		List<OpenCourseInfo> courseList = ResponseUtils.getResponseByHash(
				new Request(App.connectionToServer, null, "tech.zxuuu.server.opencourse.StuMenu.getCourseList", null).send())
				.getListReturn(OpenCourseInfo.class);
		for (OpenCourseInfo course : courseList) {
			pnlCourseList.add(new CourseInfoPane(course.getId(), course.getCourseName(), course.getSpeaker(),
					course.getPreview(), course.getVideo()));
		}
	}

	/**
	 * Create the frame.
	 */
	public StuMenuGUI() {

		
		contentPane = new JPanel();
		

		spnCourseList = new JScrollPane();
		spnCourseList.setBounds(52, 95, 813, 598);
		contentPane.add(spnCourseList);
		spnCourseList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spnCourseList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		pnlCourseList = new JPanel();
		spnCourseList.setViewportView(pnlCourseList);
		pnlCourseList.setLayout(new GridLayout(0, 1, 0, 0));

		label = new JLabel("");
		label.setIcon(new ImageIcon(StuMenuGUI.class.getResource("/resources/assets/icon/opencourse.png")));
		label.setBounds(14, 13, 64, 64);
		contentPane.add(label);

		lblVcampus = new JLabel("在线课堂 - VCampus");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblVcampus.setBounds(102, 32, 239, 34);
		contentPane.add(lblVcampus);

		lblNewLabel = new JLabel("选择一门课程以开始...");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(684, 63, 168, 19);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("播放在线课程需要安装VLC插件，点击了解如何安装...");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI("http://zxuuu.tech/share/installVLC.html"));
				} catch (IOException | URISyntaxException e1) {
					SwingUtils.showError(null, "浏览器打开失败，请手动访问http://zxuuu.tech/share/installVLC.html", "错误");
				}
			}
		});
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(526, 32, 364, 18);
		contentPane.add(lblNewLabel_1);

		JFrame that = this;
		
		showCourseList();
		this.setExtendedState(NORMAL);
		
		/*
		new Thread(new Runnable() {
			@Override
			public void run() {
				showCourseList();
				pnlCourseList.paintImmediately(pnlCourseList.getBounds());
				try {
					wait(2000);
				} catch (Exception e) {
					// Impossible catch.
				}
				that.setVisible(true);
				spnCourseList.getVerticalScrollBar().setValue(spnCourseList.getVerticalScrollBar().getMaximum());
				that.repaint();
				try {
					Thread.sleep(1000);
				}catch(Exception e) {
					//Impossible catch
				}
				that.setExtendedState(ICONIFIED);
				try {
					Thread.sleep(1000);
				}catch(Exception e) {
					//Impossible catch
				}
				that.setExtendedState(NORMAL);
			}
		}).start();*/
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StuMenuGUI.class.getResource("/resources/assets/icon/fav.png")));
		setTitle("在线课堂 - VCampus");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
		frameWidth = 800;
		frameHeight = 600;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2, 922, 762);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//		this.setExtendedState(ICONIFIED);
		
		
		this.setVisible(true);
	}
}
