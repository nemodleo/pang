package �Ĥ�;

import java.awt.*;

import javax.swing.*;
/*
 * Author: �̹���
 * Created: 2008�� 1�� 26�� ����� ���� 1:09:47
 * Modified: 2008�� 1�� 26�� ����� ���� 1:09:47
 * E-Mail : vamalboro@nate.com
 * URL    : http://abum.tistory.com/
 */
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//ũ�� �������� ������ ���Դϴ�.
public class MainFrame extends JFrame {

	private MineGUI			mineGUI			= null;	
	/* menu */
	private JMenuBar		menuBar			= null;
	private JMenu			fileMenu		= null;
	private JMenuItem		newItem			= null;

	private JMenuItem		exitItem		= null;
	
	private JMenu			helpMenu		= null;
	private JMenuItem		helpItem		= null;
	private JMenuItem		inforItem		= null;
	private JMenuItem		storyItem		= null;

	private String			helpString		= "���ʿ� �迭 �ڽ��� �ֽ��ϴ�.\n������ �ڽ��� ��ġ�� ��ǥ�� Ǫ�������\n�������� �� ������ ���ɴϴ�."
			+ "\n���� �����մϴ�.\n���ڸ� ���� �ʰ� ��ǥ�� ���� �˴ϴ�.\n���ڿ� �������� ������ ������ ���� �����ϼ���.\n \n ����� ���ڼ��� map�� ũ�� ������ Protocol.java���� �����մϴ�. ";
	private String			inforString		= "������ : PARK HYUN\n���۳�¥ : 2017. 05~ 06\n\nGL LOVER     in IASA";
	private String			endString		= "���α׷��� ���� �Ͻðڽ��ϱ�?";
	private String			storyString		= "����� ���� ���� �� 3�������� ������ �Ͽ����ϴ�.\n�ѱ����� ���￡�� ��Ȱ���Ͽ����� ������ �����Ǿ� ������ ���� �����Ǿ����ϴ�."
			+ "\n����ġ���ϰ� ������ �� �������� �����ϴ� ������ �����Ǿ� �ٽ� ���� ����� ���������ϴ�.\n���� map�󿡴� ������ ������ ������ ��ġ�� ���ڰ� �ִ� ������ ������ �˴ϴ�."
			+ "\n��ſ��� �־��� ������ �ݼ�Ž����� ���ڰ� ������ ������ ��ġ�� �������ϴ�.\n��ο����� �������Ź��� �� ���Ե� ������ �̸� ������ ��ġ�� �ľ��϶�� ������ �����Խ��ϴ�."
			+ "\n�ݼ� Ž������ ��ġ�� �ø��鼭�� ��ǥ�������� �����ϰ� �����Ͻñ� �ٶ��ϴ�.\n ";
	
	public MainFrame( String title ) {
		super( title );
		initMenu();
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.mineGUI = new MineGUI();
		this.setContentPane(this.mineGUI);
		this.pack();
		this.setCenterPosition();
		// frame icon 
		MediaTracker tracker = new MediaTracker( this );
		Image img = Toolkit.getDefaultToolkit().getImage( "IMG/reset.jpg" );
		tracker.addImage( img, 0 );
		setIconImage( img );
		this.setResizable(false);
		this.setVisible(true);
	}//����
	
	/* Inner class [ActionListener]*/
	class newActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeGame();
		}
	}
	class exitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int result;
			result = JOptionPane.showConfirmDialog( MainFrame.this, endString, "Game End", 
														JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
			if ( result == JOptionPane.YES_OPTION ) System.exit(0);
		}
	}
	class dialogActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == helpItem ) {
				JOptionPane.showMessageDialog( MainFrame.this, helpString, "Help", JOptionPane.INFORMATION_MESSAGE );
			} else if ( e.getSource() == inforItem ) {
				JOptionPane.showMessageDialog( MainFrame.this, inforString, "Information", JOptionPane.INFORMATION_MESSAGE );
			}else if ( e.getSource() == storyItem ) {
				JOptionPane.showMessageDialog( MainFrame.this, storyString, "Story~!", JOptionPane.INFORMATION_MESSAGE );
			}
		}
	} // Inner class end


	/* Member Method */
	public void initMenu() {
		menuBar			= new JMenuBar();
		fileMenu		= new JMenu( "����" );
		newItem			= new JMenuItem( "�� ����" );
		exitItem		= new JMenuItem( "������" );
		
		newItem.addActionListener( new newActionListener() );
		exitItem.addActionListener( new exitActionListener() );
		
		fileMenu.add( newItem );
		fileMenu.addSeparator();
		fileMenu.add( exitItem );
		
		menuBar.add( fileMenu );
		
		helpMenu		= new JMenu( "����" );
		helpItem		= new JMenuItem( "HELP" );
		storyItem		= new JMenuItem( "STory" );
		inforItem		= new JMenuItem( "���� ����" );
		
		helpItem.addActionListener( new dialogActionListener() );
		inforItem.addActionListener( new dialogActionListener() );
		storyItem.addActionListener( new dialogActionListener() );
		
		helpMenu.add( helpItem );
		helpMenu.add( storyItem );
		helpMenu.addSeparator();
		helpMenu.add( inforItem );
		
		menuBar.add( helpMenu );
		
		this.setJMenuBar( menuBar );
	}//ū ����
	public void setCenterPosition() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		int xpos = (int)( screen.getWidth() / 2 - frm.getWidth() / 2 );
		int ypos = (int)( screen.getHeight() / 2 - frm.getHeight() / 2 );
		this.setLocation( xpos, ypos );
	}//â�� ��ġ

	public void changeGame() {
		this.remove(this.mineGUI);
		this.mineGUI = new MineGUI();
		this.setContentPane(this.mineGUI);
		this.pack();
		this.setCenterPosition();
		this.setResizable(false);
		this.setVisible(true);
	}//������ Ŭ���� ����
	public static void main ( String args[] )
	{
		new MainFrame( "�Ĥ�" );//â�� �̸�.
	}
}

